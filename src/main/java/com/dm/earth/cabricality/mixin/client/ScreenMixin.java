package com.dm.earth.cabricality.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import com.dm.earth.cabricality.util.func.CabfBlur;
import net.krlite.equator.color.PreciseColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Mixin(Screen.class)
public class ScreenMixin {
	@Shadow
	@Nullable
	protected MinecraftClient client;

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		if (this.client != null)
			CabfBlur.INSTANCE.onScreenChange(this.client.currentScreen);
	}

	@ModifyArgs(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void renderBackground(Args args) {
		float lerp = (float) Math.pow(CabfBlur.INSTANCE.getProgress(), 1 / 3.0);
		PreciseColor first = PreciseColor.of(0xC0101010L), second = PreciseColor.of(0xD0101010L);
		args.set(5, first.withOpacity(first.alpha() * lerp).toColor().getRGB());
		args.set(6, second.withOpacity(second.alpha() * lerp).toColor().getRGB());
	}
}
