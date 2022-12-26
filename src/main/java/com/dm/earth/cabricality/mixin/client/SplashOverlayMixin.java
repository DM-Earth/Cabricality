package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.client.CabricalityClient;

import net.minecraft.client.gui.screen.SplashOverlay;

import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {
	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;init(Lnet/minecraft/client/MinecraftClient;II)V"))
	private void onResourceReloaded(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		CabricalityClient.finishLoading();
	}
}
