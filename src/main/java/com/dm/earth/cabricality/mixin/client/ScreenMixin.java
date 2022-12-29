package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.util.func.CabfBlur;

import com.dm.earth.cabricality.util.func.CabfRenderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.awt.*;

@Mixin(Screen.class)
public class ScreenMixin {
	@Shadow
	@Nullable
	protected MinecraftClient client;

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		if (this.client != null) CabfBlur.INSTANCE.onScreenChange(this.client.currentScreen);
	}

	@ModifyArgs(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void renderBackground(Args args) {
		float lerp = (float) Math.pow(CabfBlur.INSTANCE.getProgress(), 1 / 3.0);
		Color first = new Color((args.get(5)), true), second = new Color((args.get(6)), true);
		args.set(5, CabfRenderer.castOpacity(first, (first.getAlpha() / 255.0F * lerp)).getRGB());
		args.set(6, CabfRenderer.castOpacity(second, (second.getAlpha() / 255.0F * lerp)).getRGB());
	}
}
