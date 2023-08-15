package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.PushUtil;
import com.dm.earth.cabricality.lib.util.ScreenUtil;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.render.frame.FrameInfo;
import net.krlite.equator.visual.animation.animated.AnimatedDouble;
import net.krlite.equator.visual.color.AccurateColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Screen.class)
public class ScreenAnimator {
	@Unique
	private static final AnimatedDouble opacity = new AnimatedDouble(0, 1, 450, Curves.Exponential.Quadratic.OUT);

	@ModifyArgs(
			method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"
			)
	)
	private void fadeIn(Args args) {
		if (Cabricality.CONFIG.fadeScreenBackground()) {
			PushUtil.SCREEN.pull(opacity::replay);

			int upper = args.get(5), lower = args.get(6);

			args.set(5, AccurateColor.fromARGB(upper).opacity(opacity.value() * 0xC0 / 0xFF).toInt());
			args.set(6, AccurateColor.fromARGB(lower).opacity(opacity.value() * 0xC0 / 0xFF).toInt());
		}
	}
}

@Mixin(GameRenderer.class)
class PostScreenAnimator {
	@Unique
	private static final AnimatedDouble opacity = new AnimatedDouble(1, 0, 450, Curves.Exponential.Quadratic.OUT);

	@Inject(
			method = "render",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/hud/InGameHud;render(Lnet/minecraft/client/util/math/MatrixStack;F)V",
					shift = At.Shift.AFTER
			)
	)
	private void fadeOut(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
		if (Cabricality.CONFIG.fadeScreenBackground()) {
			PushUtil.POST_SCREEN.pull(opacity::replay);

			if (opacity.isPlaying()) {
				FrameInfo.scaled().render(new MatrixStack(),
						flat -> flat.new Rectangle()
										.colorTop(AccurateColor.fromARGB(0xC0101010L))
										.colorBottom(AccurateColor.fromARGB(0xD0101010L))
										.opacityMultiplier(opacity.value())
				);
			}
		}
	}
}

@Mixin(MinecraftClient.class)
class PostScreenTriggerer {
	@Shadow
	@Nullable
	public Screen currentScreen;

	@Inject(method = "setScreen", at = @At("HEAD"))
	private void setScreen(@Nullable Screen screen, CallbackInfo ci) {
		if (Cabricality.CONFIG.fadeScreenBackground()) {
			if (currentScreen == null && screen != null && !ScreenUtil.isUnextendedScreen(screen.getClass())) PushUtil.SCREEN.push();
			if (currentScreen != null && screen == null && !ScreenUtil.isUnextendedScreen(currentScreen.getClass())) PushUtil.POST_SCREEN.push();
		}
	}
}
