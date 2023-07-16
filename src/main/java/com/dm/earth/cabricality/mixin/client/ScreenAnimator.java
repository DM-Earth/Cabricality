package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.lib.util.PushUtil;
import com.dm.earth.cabricality.lib.util.func.Pusher;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.render.frame.FrameInfo;
import net.krlite.equator.visual.animation.animated.AnimatedDouble;
import net.krlite.equator.visual.animation.base.Animation;
import net.krlite.equator.visual.color.AccurateColor;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
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

@Mixin(MinecraftClient.class)
class PostScreenTriggerer {
	@Inject(method = "setScreen", at = @At("HEAD"))
	private void setScreen(@Nullable Screen screen, CallbackInfo ci) {
		if (screen == null) PushUtil.POST_SCREEN.push();
	}
}

@Mixin(GameRenderer.class)
class PostScreenAnimator {
	private static final Animation<Double> opacity = new AnimatedDouble(1, 0, 450, Curves.Exponential.Quadratic.OUT);

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;render(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
	private void renderVignette(InGameHud inGameHud, MatrixStack matrixStack, float tickDelta) {
		PushUtil.POST_SCREEN.pull(opacity::replay);

		if (opacity.isPlaying()) {
			FrameInfo.scaled().render(matrixStack,
					flat -> flat.new Rectangle()
									.colorTop(AccurateColor.fromARGB(0xC0101010L))
									.colorBottom(AccurateColor.fromARGB(0xD0101010L))
									.opacityMultiplier(opacity.value())
			);
		}

		inGameHud.render(matrixStack, tickDelta);
	}
}
