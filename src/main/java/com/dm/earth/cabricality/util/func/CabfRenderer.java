package com.dm.earth.cabricality.util.func;

import com.dm.earth.cabricality.math.Node;
import com.dm.earth.cabricality.math.Rect;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class CabfRenderer {
	public static Color castAlpha(Color color) {
		return castAlpha(color, 0);
	}

	public static Color castAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}

	public static Color castOpacity(Color color) {
		return castOpacity(color, 0);
	}

	public static Color castOpacity(Color color, float opacity) {
		return new Color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, opacity);
	}

	public static Color blend(Color first, Color second) {
		return blend(first, second, 0.5);
	}

	public static Color blend(Color first, Color second, double ratio) {
		int red = (int) (first.getRed() * ratio + second.getRed() * (1 - ratio));
		int green = (int) (first.getGreen() * ratio + second.getGreen() * (1 - ratio));
		int blue = (int) (first.getBlue() * ratio + second.getBlue() * (1 - ratio));
		int alpha = (int) (first.getAlpha() * ratio + second.getAlpha() * (1 - ratio));
		return new Color(red, green, blue, alpha);
	}

	public record Drawer(MatrixStack matrixStack) {
		public void rectGradiantFromMiddleWithScissor(Rect scissor, Color colorMiddle, Color colorOuter) {
			rectGradiantFromMiddleWithScissor(new Rect(), scissor, colorMiddle, colorOuter);
		}

		public void rectGradiantFromMiddleWithScissor(Rect rect, Rect scissor, Color colorMiddle, Color colorOuter) {
			// Upper
			verticalRectGradiant(new Rect(rect.lu(), scissor.lu(), scissor.ru(), rect.ru()), colorOuter, colorMiddle);
			// Lower
			verticalRectGradiant(new Rect(scissor.ld(), rect.ld(), rect.rd(), scissor.rd()), colorMiddle, colorOuter);
			// Left
			horizontalRectGradiant(new Rect(rect.lu(), rect.ld(), scissor.ld(), scissor.lu()), colorOuter, colorMiddle);
			// Right
			horizontalRectGradiant(new Rect(scissor.ru(), scissor.rd(), rect.rd(), rect.ru()), colorMiddle, colorOuter);
		}

		public void rectGradiantFromMiddleWithScissor(Rect scissor, Color colorMiddle, Color colorOuter, double expandRatio) {
			rectGradiantFromMiddleWithScissor(new Rect(), scissor, colorMiddle, colorOuter, expandRatio);
		}

		public void rectGradiantFromMiddleWithScissor(Rect rect, Rect scissor, Color colorMiddle, Color colorOuter, double expandRatio) {
			// Upper
			verticalRectGradiant(new Rect(rect.lu(), scissor.lu(), scissor.ru(), rect.ru()), colorOuter, colorMiddle, 1 - expandRatio);
			// Lower
			verticalRectGradiant(new Rect(scissor.ld(), rect.ld(), rect.rd(), scissor.rd()), colorMiddle, colorOuter, expandRatio);
			// Left
			horizontalRectGradiant(new Rect(rect.lu(), rect.ld(), scissor.ld(), scissor.lu()), colorOuter, colorMiddle, 1 - expandRatio);
			// Right
			horizontalRectGradiant(new Rect(scissor.ru(), scissor.rd(), rect.rd(), rect.ru()), colorMiddle, colorOuter, expandRatio);
		}

		public void rect(Color color) {
			rect(new Rect(new Node(0, 0), new Node(MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledHeight())), color);
		}

		public void rect(Rect rect, Color color) {
			rectGradiant(rect, color, color, color, color);
		}

		public void horizontalRectGradiant(Rect rect, Color left, Color right) {
			rectGradiant(rect, left, left, right, right);
		}

		public void verticalRectGradiant(Rect rect, Color upper, Color lower) {
			rectGradiant(rect, upper, lower, lower, upper);
		}

		public void rectGradiant(Rect rect, Color lu, Color ld, Color rd, Color ru) {
			prepare();
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder buffer = tessellator.getBuffer();
			buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

			drawRect(buffer, rect, lu, ld, rd, ru);

			tessellator.draw();
			cleanup();
		}

		public void verticalRectGradiant(Rect rect, Color upper, Color lower, double upperToLowerRatio) {
			upperToLowerRatio = MathHelper.clamp(upperToLowerRatio, 0, 1);

			if (Math.abs((rect.lu().y() - rect.ld().y()) / 2 - (rect.ru().y() + rect.rd().y()) / 2) <= 1) {
				rectGradiant(rect, upper, lower, lower, upper);
				return;
			}

			verticalRectGradiant(
					new Rect(rect.lu(), rect.ld().scale(rect.lu(), upperToLowerRatio), rect.rd().scale(rect.ru(), upperToLowerRatio), rect.ru()),
					upper, blend(upper, lower, upperToLowerRatio)
			);

			verticalRectGradiant(
					new Rect(rect.ld().scale(rect.lu(), upperToLowerRatio), rect.ld(), rect.rd(), rect.rd().scale(rect.ru(), upperToLowerRatio)),
					blend(upper, lower, upperToLowerRatio), lower
			);
		}

		public void horizontalRectGradiant(Rect rect, Color left, Color right, double leftToRightRatio) {
			leftToRightRatio = MathHelper.clamp(leftToRightRatio, 0, 1);

			if (Math.abs((rect.lu().x() + rect.ld().x()) / 2 - (rect.ru().x() + rect.rd().x()) / 2) <= 1) {
				horizontalRectGradiant(rect, left, right);
				return;
			}

			horizontalRectGradiant(
					new Rect(rect.lu(), rect.ld(), rect.rd().scale(rect.ld(), leftToRightRatio), rect.ru().scale(rect.lu(), leftToRightRatio)),
					left, blend(left, right, leftToRightRatio)
			);

			horizontalRectGradiant(
					new Rect(rect.ru().scale(rect.lu(), leftToRightRatio), rect.rd().scale(rect.ld(), leftToRightRatio),
							rect.rd(), rect.ru()), blend(left, right, leftToRightRatio), right
			);
		}

		private void prepare() {
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableTexture();

			RenderSystem.enableBlend();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		}

		private void drawRect(BufferBuilder builder, Rect rect, Color color) {
			drawRect(builder, rect, color, color, color, color);
		}

		private void drawRect(BufferBuilder builder, Rect rect, Color lu, Color ld, Color rd, Color ru) {
			drawVertex(builder, rect.lu(), lu);
			drawVertex(builder, rect.ld(), ld);
			drawVertex(builder, rect.rd(), rd);
			drawVertex(builder, rect.ru(), ru);
		}

		private void drawVertex(BufferBuilder builder, Node node, Color color) {
			builder.vertex(matrixStack.peek().getModel(), (float) node.x(), (float) node.y(), 0)
					.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();
		}

		private void cleanup() {
			RenderSystem.enableTexture();
		}
	}
}
