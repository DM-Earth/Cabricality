package dm.earth.cabricality.mixin.client;

import dm.earth.cabricality.client.screen.MissingModScreen;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.util.mod.CabfModDeps;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import static dm.earth.cabricality.Cabricality.CONFIG;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientModifier {
	@Redirect(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"
			)
	)
	private void checkMods(MinecraftClient client, Screen screen) {
		if (!CabfModDeps.isAllLoaded())
			// If not fully loaded, set screen to MissingModScreen
			client.setScreen(new MissingModScreen(CabfModDeps.getAllMissing(), CabfModDeps.isLoaded(true, false) ? screen : null));
		else
			client.setScreen(screen);
	}

	@Inject(
			method = "getWindowTitle",
			at = @At("HEAD"),
			cancellable = true
	)
	private void modifyWindowTitle(CallbackInfoReturnable<String> cir) {
		ModContainer container = FabricLoader.getInstance().getModContainer(Cabricality.ID).orElseThrow();
		cir.setReturnValue(container.getMetadata().getName() + (CONFIG.includeVersionInWindowTitle() ? (" " + container.getMetadata().getVersion().getFriendlyString()) : ""));
	}
}
