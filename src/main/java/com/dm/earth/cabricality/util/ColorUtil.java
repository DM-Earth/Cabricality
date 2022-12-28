package com.dm.earth.cabricality.util;

import com.dm.earth.cabricality.util.math.Node;
import com.dm.earth.cabricality.util.math.Rect;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ColorUtil {
	public static Color castAlpha(Color color) {
		return castAlpha(color, 0);
	}

	public static Color castAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}

	public record Drawer(MatrixStack matrixStack) {
		public void rectGradiantFromMiddleWithScissor(Rect scissor, Color colorMiddle, Color colorOuter) {
			rectGradiantFromMiddleWithScissor(
					new Rect(
							new Node(0, 0),
							new Node(MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledHeight())
					), scissor, colorMiddle, colorOuter
			);
		}

		public void rectGradiantFromMiddleWithScissor(Rect rect, Rect scissor, Color colorMiddle, Color colorOuter) {
			// Upper
			rectGradiant(new Rect(rect.lu(), scissor.lu(), scissor.ru(), rect.ru()), colorOuter, colorMiddle, colorMiddle, colorOuter);
			// Lower
			rectGradiant(new Rect(scissor.ld(), rect.ld(), rect.rd(), scissor.rd()), colorMiddle, colorOuter, colorOuter, colorMiddle);
			// Left
			rectGradiant(new Rect(rect.lu(), rect.ld(), scissor.ld(), scissor.lu()), colorOuter, colorOuter, colorMiddle, colorMiddle);
			// Right
			rectGradiant(new Rect(scissor.ru(), scissor.rd(), rect.rd(), rect.ru()), colorMiddle, colorMiddle, colorOuter, colorOuter);
		}

		public void horizontalGradiant(Rect rect, Color left, Color right) {
			rectGradiant(rect, left, left, right, right);
		}

		public void verticalGradiant(Rect rect, Color top, Color bottom) {
			rectGradiant(rect, top, bottom, bottom, top);
		}

		public void rectGradiant(Rect rect, Color lu, Color ld, Color rd, Color ru) {
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableTexture();

			RenderSystem.enableBlend();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder buffer = tessellator.getBuffer();

			buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

			buffer.vertex(matrixStack.peek().getModel(), (float) rect.ru().x(), (float) rect.ru().y(), 0)
					.color(ru.getRed(), ru.getGreen(), ru.getBlue(), ru.getAlpha()).next();

			buffer.vertex(matrixStack.peek().getModel(), (float) rect.lu().x(), (float) rect.lu().y(), 0)
					.color(lu.getRed(), lu.getGreen(), lu.getBlue(), lu.getAlpha()).next();

			buffer.vertex(matrixStack.peek().getModel(), (float) rect.ld().x(), (float) rect.ld().y(), 0)
					.color(ld.getRed(), ld.getGreen(), ld.getBlue(), ld.getAlpha()).next();

			buffer.vertex(matrixStack.peek().getModel(), (float) rect.rd().x(), (float) rect.rd().y(), 0)
					.color(rd.getRed(), rd.getGreen(), rd.getBlue(), rd.getAlpha()).next();

			tessellator.draw();
			RenderSystem.enableTexture();
		}
	}
}
