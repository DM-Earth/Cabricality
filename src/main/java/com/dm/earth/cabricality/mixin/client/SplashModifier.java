package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.Cabricality;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.krlite.equator.input.Mouse;
import net.krlite.equator.math.algebra.Theory;
import net.krlite.equator.math.geometry.flat.Vector;
import net.krlite.equator.render.frame.FrameInfo;
import net.krlite.equator.render.renderer.Flat;
import net.krlite.equator.visual.animation.base.Interpolation;
import net.krlite.equator.visual.animation.interpolated.InterpolatedVector;
import net.krlite.equator.visual.color.Palette;
import net.krlite.equator.visual.color.base.ColorStandard;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SplashOverlay.class)
public class SplashModifier {
	@Shadow
	private float progress;

	@Shadow
	private long reloadCompleteTime;

	@Shadow
	@Final
	private boolean reloading;

	@Shadow
	private long reloadStartTime;

	@Unique
	private static final InterpolatedVector.Linear shifting = new InterpolatedVector.Linear(Vector.ZERO, 0.01);

	static {
		Mouse.Callbacks.Move.EVENT.register(position -> {
			if (FrameInfo.scaled().contains(position)) {
				Vector diff = position.subtract(FrameInfo.scaled().center());
				double scalar = diff.magnitude() / (FrameInfo.scaled().d() / 2);

				shifting.target(diff.scale(Theory.approximation(scalar, 0.1, 1)));
			} else shifting.target(Vector.ZERO);
		});
	}

	@ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;_clearColor(FFFF)V", remap = false))
	private void renderStaticBackground(Args args) {
		float
				red = Cabricality.Colors.CABF_DIM_PURPLE.redAsFloat(),
				green = Cabricality.Colors.CABF_DIM_PURPLE.greenAsFloat(),
				blue = Cabricality.Colors.CABF_DIM_PURPLE.blueAsFloat(),
				alpha = Cabricality.Colors.CABF_DIM_PURPLE.opacityAsFloat();

		args.set(0, red);
		args.set(1, green);
		args.set(2, blue);
		args.set(3, alpha);
	}

	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V"))
	private void renderLogo(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		double
				complete = reloadCompleteTime > -1 ? (float) (Util.getMeasuringTimeMs() - reloadCompleteTime) / 1000 : -1,
				start = reloadStartTime > -1 ? (float) (Util.getMeasuringTimeMs() - reloadStartTime) / 500 : -1;

		double opacity, logoOpacity;

		if (complete >= 1) {
			opacity = logoOpacity = 1 - Theory.clamp(complete - 1, 0, 1);
		} else if (reloading) {
			opacity = Theory.clamp(start, 0.15, 1);
			logoOpacity = Theory.clamp(start, 0, 1);
		} else {
			opacity = logoOpacity = 1;
		}

		Vector shift = shifting.value().interpolate(Vector.ZERO, Math.pow(Theory.clamp(complete, 0, 1), 1D / 3D));

		// Background
		background: {
			FrameInfo.scaled()
					.render(matrixStack,
							flat -> flat.new Rectangle()
											.colors(Cabricality.Colors.CABF_DIM_PURPLE)
											.opacityMultiplier(opacity)
					);
		}

		// Progress ring
		progressRing: {
			double opacityMultiplier = mapToPower(progress, 2, 0.5);
			double offset = -Math.PI / 2, radians = progress * 2 * Math.PI;

			FrameInfo.scaled()
					.squareInner()
					.scaleCenter(0.8)
					.shift(shift.scale(-0.05))
					.render(matrixStack,
							flat -> flat.new Oval()
											.offset(offset)
											.radians(radians)

											.mode(Flat.Oval.OvalMode.FILL_GRADIANT_OUT)
											.mixMode(ColorStandard.MixMode.PIGMENT)

											.addColor(0, Palette.TRANSPARENT)
											.addColor(radians, Cabricality.Colors.CABF_MID_PURPLE)
											.opacityMultiplier(opacityMultiplier * (1 - Theory.clamp(complete, 0, 1)))
					);
		}

		// Logo
		logo: {
			FrameInfo.scaled()
					.squareInner()
					.scaleCenter(0.7)
					.scaleCenter(1 + 1.1 * Math.pow(1 - logoOpacity, 1D / 2D))
					.shift(shift.scale(0.05))
					.render(matrixStack,
							flat -> flat.new Oval()
											.colorCenter(Cabricality.Colors.CABF_MID_PURPLE)
											.opacityMultiplier(logoOpacity)
					);

			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
			RenderSystem.setShaderColor(1, 1, 1, (float) logoOpacity);

			FrameInfo.scaled()
					.squareInner()
					.scaleCenter(0.55)
					.scaleCenter(1 + 0.1 * shifting.value().magnitude() / (FrameInfo.scaled().d() / 2))
					.shift(shift)
					.render(matrixStack,
							flat -> flat.new Rectangle()
											.texture(Cabricality.Textures.CABRICALITY_LOGO_NEON)
					);

			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SplashOverlay;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"))
	private void clearBackground(MatrixStack matrixStack, int xBegin, int yBegin, int xEnd, int yEnd, int color) {
		// Do nothing
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SplashOverlay;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIFFIIII)V"))
	private void clearMojangLogo(MatrixStack matrixStack, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight) {
		// Do nothing
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SplashOverlay;renderProgressBar(Lnet/minecraft/client/util/math/MatrixStack;IIIIF)V"))
	private void clearProgressBar(SplashOverlay splashOverlay, MatrixStack matrixStack, int xMin, int yMin, int xMax, int yMax, float opacity) {
		// Do nothing
	}

	@Unique
	private double mapToPower(double x, double power, double threshold) {
		return threshold + (1 - threshold) * Math.pow(x, power);
	}
}
