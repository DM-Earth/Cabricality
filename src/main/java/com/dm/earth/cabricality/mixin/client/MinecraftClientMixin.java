package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.screen.MissingModScreen;
import com.dm.earth.cabricality.util.ModChecker;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
	private void getWindowTitle(CallbackInfoReturnable<String> cir) {
		Optional<String> title = QuiltLoader.getModContainer(Cabricality.ID).map(container -> container.metadata().version().raw());
		title.ifPresent(t -> cir.setReturnValue("Cabricality " + t)); // If present, set title
	}

	@Redirect(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"
			)
	)
	private void setScreen(MinecraftClient client, Screen screen) {
		if (!ModChecker.isFullLoaded()) { // If not full loaded, set screen to MissingModScreen
			client.setScreen(new MissingModScreen(ModChecker.getMissingModList(), ModChecker.getModDedicatedUrlList(), screen));
		} else {
			client.setScreen(screen);
		}
	}
}
