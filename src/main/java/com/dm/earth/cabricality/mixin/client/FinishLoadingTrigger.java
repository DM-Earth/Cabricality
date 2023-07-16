package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.CabricalityClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.gui.screen.world.OptimizeWorldScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FinishLoadingTrigger {
	@Mixin(MinecraftClientGame.class)
	static class MinecraftClientGameMixin {
		@Inject(method = "onStartGameSession", at = @At("HEAD"))
		private void onWorldLoaded(CallbackInfo ci) {
			CabricalityClient.finishLoading();
			Cabricality.finishLoading();
		}
	}

	@Mixin(OptimizeWorldScreen.class)
	static class OptimizeWorldScreenMixin {
		@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/booleans/BooleanConsumer;accept(Z)V"), remap = false)
		private void onWorldOptimized(CallbackInfo ci) {
			CabricalityClient.finishLoading();
		}
	}

	@Mixin(SplashOverlay.class)
	static class SplashOverlayMixin {
		@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;init(Lnet/minecraft/client/MinecraftClient;II)V"))
		private void onResourceReloaded(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo ci) {
			CabricalityClient.finishLoading();
		}
	}
}
