package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.lib.util.PushUtil;
import com.dm.earth.cabricality.lib.util.ScreenUtil;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.render.frame.FrameInfo;
import net.krlite.equator.visual.animation.animated.AnimatedDouble;
import net.krlite.equator.visual.color.AccurateColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class ScreenAnimator {
	@Unique
	private static final AnimatedDouble opacity = new AnimatedDouble(1, 0, 450, Curves.Exponential.Quadratic.OUT);

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

@Mixin(MinecraftClient.class)
class PostScreenTriggerer {
	@Shadow
	@Nullable
	public Screen currentScreen;

	@Inject(method = "setScreen", at = @At("HEAD"))
	private void setScreen(@Nullable Screen screen, CallbackInfo ci) {
		if (
				screen == null && (currentScreen != null && !ScreenUtil.isUnextendedScreen(currentScreen.getClass()))
		) {
			PushUtil.POST_SCREEN.push();
		}
	}
}
