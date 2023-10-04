package dm.earth.cabricality.mixin.client;

import dm.earth.cabricality.Mod;
import dm.earth.cabricality.client.screen.MissingModScreen;
import dm.earth.cabricality.config.CabfConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dm.earth.cabricality.Cabricality;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

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
		if (!Mod.Dependency.isAllLoaded())
			// If not fully loaded, set screen to MissingModScreen
			client.setScreen(new MissingModScreen(
					Mod.Dependency.getAllMissing(),
					Mod.Dependency.isLoaded(true, false) ? screen : null
			));
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
		cir.setReturnValue(
				container.getMetadata().getName() + (
						CabfConfig.includeVersionInWindowTitle()
								? (" " + container.getMetadata().getVersion().getFriendlyString())
								: ""
				)
		);
	}
}
