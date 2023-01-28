package com.dm.earth.cabricality.mixin.client.cloth;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import com.dm.earth.cabricality.util.func.CabfBlur;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;

@Mixin(ClothConfigScreen.class)
public class ClothConfigScreenAnimator {
	@ModifyArgs(method = "render", at = @At(value = "INVOKE",
			target = "Lme/shedaniel/clothconfig2/gui/ClothConfigScreen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void renderBackground(Args args) {
		CabfBlur.blurBackground(args, 5, 6);
	}

	@ModifyArgs(method = "render", at = @At(value = "INVOKE",
			target = "Lme/shedaniel/clothconfig2/gui/ClothConfigScreen;overlayBackground(Lnet/minecraft/client/util/math/MatrixStack;Lme/shedaniel/math/Rectangle;IIIII)V"))
	private void renderOverlayBackground(Args args) {
		float lerp = (int) Math.pow(CabfBlur.INSTANCE.getProgress() * 255, 1 / 3.0);
		args.set(5, lerp);
		args.set(6, lerp);
	}
}
