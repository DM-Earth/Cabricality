package com.dm.earth.cabricality.mixin.client;

import java.util.Optional;
import org.objectweb.asm.Opcodes;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.screen.MissingModScreen;
import com.dm.earth.cabricality.util.func.CabfBlur;
import com.dm.earth.cabricality.util.mod.CabfModDeps;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
	private void checkMods(MinecraftClient client, Screen screen) {
		if (!CabfModDeps.isAllLoaded())
			// If not full loaded, set screen to MissingModScreen
			client.setScreen(new MissingModScreen(CabfModDeps.getAllMissing(),
					CabfModDeps.isLoaded(true, false) ? screen : null));
		else
			client.setScreen(screen);
	}

	@Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
	private void modifyWindowTitle(CallbackInfoReturnable<String> cir) {
		Optional<String> title = QuiltLoader.getModContainer(Cabricality.ID)
				.map(container -> container.metadata().version().raw());
		title.ifPresent(t -> cir.setReturnValue("Cabricality " + t)); // If present, set title
	}

	@Inject(method = "setScreen", at = @At(value = "FIELD",
			target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;",
			opcode = Opcodes.PUTFIELD))
	private void blurScreen(Screen screen, CallbackInfo ci) {
		CabfBlur.INSTANCE.onScreenChange(screen);
	}
}
