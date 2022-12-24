package com.dm.earth.cabricality.client.screen;

import com.dm.earth.cabricality.Cabricality;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;

import net.minecraft.client.gui.widget.PlainTextButtonWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@ClientOnly
public class MissingModScreen extends Screen implements Drawable {
	private final Map<String, String> missingMods, urls;
	@Nullable private final Screen parent;

	public MissingModScreen(@Nullable Map<String, String> missingMods) {
		this(missingMods, null, null);
	}

	public MissingModScreen(@Nullable Map<String, String> missingMods, @Nullable Map<String, String> urls) {
		this(missingMods, urls, null);
	}

	public MissingModScreen(@Nullable Map<String, String> missingMods, @Nullable Screen parent) {
		this(missingMods, null, parent);
	}

	public MissingModScreen(@Nullable Map<String, String> missingMods, @Nullable Map<String, String> urls, @Nullable Screen parent) {
		super(Cabricality.genTranslatableText("screen", "missing_mod", "title"));
		this.missingMods = missingMods == null ? new HashMap<>() : missingMods;
		this.urls = urls == null ? new HashMap<>() : urls;
		this.parent = parent;
	}

	public boolean isPauseScreen() {
		return false;
	}

	public boolean shouldCloseOnEsc() {
		return this.parent != null;
	}

	public void onClose() {
		if (this.client != null && this.shouldCloseOnEsc()) this.client.setScreen(parent);
	}

	protected void init() {
		if (!missingMods.isEmpty()) {
			AtomicInteger index = new AtomicInteger(0);
			int widest = missingMods.values().stream().max(Comparator.comparingInt(String::length)).map(String::length).orElse(0);
			String brackets =
					"[" +
							String.join(
									"", Collections.nCopies(
											this.textRenderer.getWidth(Cabricality.genTranslatableText("screen", "missing_mod", "button", "download", "prefix"))
													+ this.textRenderer.getWidth(Cabricality.genTranslatableText("screen", "missing_mod", "button", "download", "suffix")) + widest + 5,
											" "
									)) +
							"]";
			missingMods.forEach((modid, name) -> {
				Text text =
						Cabricality.genTranslatableText("screen", "missing_mod", "button", "download", "prefix")
								.append(name)
								.append(Cabricality.genTranslatableText("screen", "missing_mod", "button", "download", "suffix"))
								.formatted(urls.containsKey(modid) ? Formatting.RESET : Formatting.STRIKETHROUGH)
								.formatted(Formatting.LIGHT_PURPLE);
				int y = 60 + (height - 90) * index.incrementAndGet() / (missingMods.size() + 1);
				// Brackets
				modDownloadButton(modid, name, new LiteralText(brackets).formatted(urls.containsKey(modid) ? Formatting.RESET : Formatting.STRIKETHROUGH).formatted(Formatting.DARK_PURPLE), y);
				// Layered Text
				modDownloadButton(modid, name, text, y);
			});
			if (this.shouldCloseOnEsc()) {
				Text blank = new LiteralText(String.join(
						"", Collections.nCopies(
								(this.textRenderer.getWidth(Cabricality.genTranslatableText("screen", "missing_mod", "button", "download", "prefix"))
										 + this.textRenderer.getWidth(Cabricality.genTranslatableText("screen", "missing_mod", "button", "download", "suffix")) + widest) / 4,
								" "
						))
				);
				Text quit =
						new LiteralText("[")
								.append(blank)
								.append("×")
								.append(blank)
								.append("]").formatted(Formatting.RED);
				Text skip =
						new LiteralText("[")
								.append(blank)
								.append("→")
								.append(blank)
								.append("]").formatted(Formatting.WHITE);
				skipAndQuitButton(quit, skip, this.height - 30, this.textRenderer.getWidth(brackets));
			} else {
				quitButton(new LiteralText(brackets).formatted(Formatting.RED), this.height - 30);
				quitButton(new LiteralText("×").formatted(Formatting.RED), this.height - 30);
			}
		}
	}

	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrixStack);
		Text title = new LiteralText(this.title.getString()).formatted(Formatting.BOLD);
		Text subtitle = Cabricality.genTranslatableText("screen", "missing_mod", "subtitle");

		matrixStack.push();
		scale(matrixStack, 1.7F);
		DrawableHelper.drawCenteredText(matrixStack, this.textRenderer, title, this.width / 2, 25, 0xFFFFFF);

		scale(matrixStack, 0.65F);
		DrawableHelper.drawCenteredText(matrixStack, this.textRenderer, subtitle, this.width / 2, 53, 0xAEAEAE);
		matrixStack.pop();

		super.render(matrixStack, mouseX, mouseY, delta);
	}

	public void renderBackground(MatrixStack matrixStack) {
		// Render Background Texture
		super.renderBackground(matrixStack);

		// Render Colored Overlay
		Color color = new Color(0x1B1329);
		float
				r = color.getRed() / 255.0F,
				g = color.getGreen() / 255.0F,
				b = color.getBlue() / 255.0F,
				a = 0.57F;

		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();

		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);

		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(matrixStack.peek().getModel(), 0, height, 0.0F).color(r, g, b, a).next();
		bufferBuilder.vertex(matrixStack.peek().getModel(), width, height, 0.0F).color(r, g, b, a).next();
		bufferBuilder.vertex(matrixStack.peek().getModel(), width, 0, 0.0F).color(r, g, b, a).next();
		bufferBuilder.vertex(matrixStack.peek().getModel(), 0, 0, 0.0F).color(r, g, b, a).next();
		bufferBuilder.end();

		BufferRenderer.draw(bufferBuilder);

		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

	private void scale(MatrixStack matrixStack, float scale) {
		matrixStack.translate(this.width * -0.5F * (scale - 1), -5 * (scale - 1), 0);
		matrixStack.scale(scale, scale, scale);
	}

	private void modDownloadButton(String modid, String name, Text text, int y) {
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 - this.textRenderer.getWidth(text) / 2, y,
						this.textRenderer.getWidth(text), 10, text,
						buttonWidget -> {
							if (urls.containsKey(modid)) {
								Cabricality.logInfo("Opening URL for mod " + name + " (" + modid + ")...");
								try {
									URL url = Util.make(new URL(urls.get(modid)), (u) -> {
										try {
											u.toURI();
										} catch (Exception exception) {
											Cabricality.logDebugAndError("Cannot handle URL for mod " + name + "!", exception);
										}
									});
									Util.getOperatingSystem().open(url);
								} catch (MalformedURLException malformedURLException) {
									Cabricality.logDebugAndError("Invalid URL for mod " + name + "!", malformedURLException);
								}
							} else {
								Cabricality.logInfo("No URL found for mod " + name + " (" + modid + ")!");
							}
						}, this.textRenderer
				)
		);
	}

	private void skipAndQuitButton(Text quit, Text skip, int y, int wideness) {
		// Quit
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 - wideness / 2, y,
						this.textRenderer.getWidth(quit), 10, quit,
						buttonWidget -> {
							if (this.client != null) client.stop();
						}, this.textRenderer
				)
		);
		// Skip
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 + wideness / 2 - this.textRenderer.getWidth(skip), y,
						this.textRenderer.getWidth(skip), 10, skip,
						buttonWidget -> this.onClose(), this.textRenderer
				)
		);
	}

	private void quitButton(Text quit, int y) {
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 - this.textRenderer.getWidth(quit) / 2, y,
						this.textRenderer.getWidth(quit), 10, quit,
						buttonWidget -> {
							if (this.client != null) client.stop();
						}, this.textRenderer
				)
		);
	}
}
