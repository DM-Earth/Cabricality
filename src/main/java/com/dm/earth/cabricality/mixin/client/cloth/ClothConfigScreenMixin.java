package com.dm.earth.cabricality.mixin.client.cloth;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import com.dm.earth.cabricality.util.func.CabfBlur;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import net.krlite.equator.color.PreciseColor;

@Mixin(ClothConfigScreen.class)
public class ClothConfigScreenMixin {
	@ModifyArgs(method = "render", at = @At(value = "INVOKE",
			target = "Lme/shedaniel/clothconfig2/gui/ClothConfigScreen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void renderBackground(Args args) {
		float lerp = (float) Math.pow(CabfBlur.INSTANCE.getProgress(), 1 / 3.0);
		PreciseColor first = PreciseColor.of(0xC0101010L), second = PreciseColor.of(0xD0101010L);
		args.set(5, first.withOpacity(first.alpha() * lerp).toColor().getRGB());
		args.set(6, second.withOpacity(second.alpha() * lerp).toColor().getRGB());
	}

	@ModifyArgs(method = "render", at = @At(value = "INVOKE",
			target = "Lme/shedaniel/clothconfig2/gui/ClothConfigScreen;overlayBackground(Lnet/minecraft/client/util/math/MatrixStack;Lme/shedaniel/math/Rectangle;IIIII)V"))
	private void renderOverlayBackground(Args args) {
		float lerp = (int) Math.pow(CabfBlur.INSTANCE.getProgress() * 255, 1 / 3.0);
		args.set(5, lerp);
		args.set(6, lerp);
	}
}
