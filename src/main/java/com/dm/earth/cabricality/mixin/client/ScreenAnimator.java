package com.dm.earth.cabricality.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.dm.earth.cabricality.lib.util.func.CabfBlur;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Mixin(Screen.class)
public class ScreenAnimator {
	@Shadow
	@Nullable
	protected MinecraftClient client;

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		if (this.client != null) CabfBlur.INSTANCE.onScreenChange(this.client.currentScreen);
	}

	@ModifyArgs(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void renderBackground(Args args) {
		CabfBlur.blurBackground(args, 5, 6);
	}
}
