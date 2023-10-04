package dm.earth.cabricality.mixin.client;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.client.CabricalityClient;
import dm.earth.cabricality.lib.util.ConfettiUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.gui.screen.world.OptimizeWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FinishLoadingTrigger {
	@Mixin(MinecraftClient.class)
	static class MinecraftClientGameMixin {
		@Inject(method = "onGameLoaded", at = @At("HEAD"))
		private void onGameLoaded(CallbackInfo ci) {
			ConfettiUtil.finishLoading();
		}
	}

	@Mixin(OptimizeWorldScreen.class)
	static class OptimizeWorldScreenMixin {
		@Inject(
				method = "tick",
				at = @At(
						value = "INVOKE",
						target = "Lit/unimi/dsi/fastutil/booleans/BooleanConsumer;accept(Z)V"
				),
				remap = false
		)
		private void onWorldOptimized(CallbackInfo ci) {
			ConfettiUtil.finishLoading();
		}
	}

	@Mixin(SplashOverlay.class)
	static class SplashOverlayMixin {
		@Inject(
				method = "render",
				at = @At(
						value = "INVOKE",
						target = "Lnet/minecraft/client/gui/screen/Screen;init(Lnet/minecraft/client/MinecraftClient;II)V"
				)
		)
		private void onResourceReloaded(GuiGraphics graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
			ConfettiUtil.finishLoading();
		}
	}
}
