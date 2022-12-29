package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.math.Timer;
import com.dm.earth.cabricality.math.util.BlurUtil;

import com.dm.earth.cabricality.util.ColorUtil;

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

	private Timer timer = new Timer(175);

	@Inject(method = "init()V", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		timer = timer.reset();
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		if (this.client != null) {
			BlurUtil.INSTANCE.onScreenChange(this.client.currentScreen);
		}
	}

	@ModifyArgs(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void renderBackground(Args args) {
		float lerp = (float) Math.pow(timer.queueAsPercentage(), 1 / 3.0);
		Color first = new Color((args.get(5)), true), second = new Color((args.get(6)), true);
		args.set(5, ColorUtil.castOpacity(first, (first.getAlpha() / 255.0F * lerp)).getRGB());
		args.set(6, ColorUtil.castOpacity(second, (second.getAlpha() / 255.0F * lerp)).getRGB());
	}
}
