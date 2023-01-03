package com.dm.earth.cabricality.client.screen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.mod.CabfModDeps;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
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
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

@ClientOnly
@SuppressWarnings("all")
public class MissingModScreen extends Screen {
	@Nullable
	private final ArrayList<CabfModDeps> missingMods;
	@Nullable
	private final Screen parent;
	private final boolean renderBackgroundTexture;

	public MissingModScreen(@Nullable ArrayList<CabfModDeps> missingMods) {
		this(missingMods, null, MinecraftClient.getInstance().world == null);
	}

	public MissingModScreen(@Nullable ArrayList<CabfModDeps> missingMods, @Nullable Screen parent) {
		this(missingMods, parent, MinecraftClient.getInstance().world == null);
	}

	public MissingModScreen(@Nullable ArrayList<CabfModDeps> missingMods,
			boolean renderBackgroundTexture) {
		this(missingMods, null, renderBackgroundTexture);
	}

	public MissingModScreen(@Nullable ArrayList<CabfModDeps> missingMods, @Nullable Screen parent,
			boolean renderBackgroundTexture) {
		super(Cabricality.genTranslatableText("screen", "missing_mod",
				"title" + (missingMods.size() == 1 ? "" : "_plural")));
		this.missingMods = missingMods;
		this.parent = parent;
		this.renderBackgroundTexture = renderBackgroundTexture;
	}

	public boolean isPauseScreen() {
		return renderBackgroundTexture;
	}

	public boolean shouldCloseOnEsc() {
		return this.parent != null;
	}

	public void onClose() {
		if (this.client != null && this.shouldCloseOnEsc())
			this.client.setScreen(parent);
	}

	protected void init() {
		if (missingMods != null && !missingMods.isEmpty()) {
			int widest = missingMods.stream().map(mod -> mod.getRawName().length())
					.max(Comparator.naturalOrder()).orElse(0);
			String brackets = "[" + " ".repeat(widest + 17) + "]";
			AtomicInteger index = new AtomicInteger(0);

			missingMods.forEach(mod -> {
				Text text = new LiteralText(mod.getRawName())
						.formatted(mod.hasUrl() ? Formatting.RESET : Formatting.STRIKETHROUGH)
						.formatted(mod.isRequired() ? Formatting.RED : Formatting.LIGHT_PURPLE);
				int y = 60
						+ (height - 90) * index.incrementAndGet() / ((int) missingMods.size() + 1);
				// Brackets
				modDownloadButton(mod, new LiteralText(brackets).formatted(Formatting.DARK_PURPLE),
						y);
				// Names
				modDownloadButton(mod, text, y);
			});

			if (this.shouldCloseOnEsc()) {
				Text blank = new LiteralText(" ".repeat(widest / 2 - 2));
				Text quit = new LiteralText("[").append(blank).append("×").append(blank).append("]")
						.formatted(Formatting.RED);
				Text skip = new LiteralText("[").append(blank).append("→").append(blank).append("]")
						.formatted(Formatting.WHITE);
				// Bracketed icons
				skipAndQuitButton(quit, skip, this.height - 30,
						this.textRenderer.getWidth(brackets));
			} else {
				// Brackets
				quitButton(new LiteralText(brackets).formatted(Formatting.RED), this.height - 30);
				// Icon
				quitButton(new LiteralText("×").formatted(Formatting.RED), this.height - 30);
			}
		} else {
			this.client.setScreen(parent);
		}
	}

	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrixStack);
		Text title = new LiteralText(this.title.getString()).formatted(Formatting.BOLD);
		Text subtitle = Cabricality.genTranslatableText("screen", "missing_mod", "subtitle");

		// Subtitle
		DrawableHelper.drawCenteredText(matrixStack, this.textRenderer, subtitle, this.width / 2,
				56, 0xAEAEAE);

		// Title
		matrixStack.push();
		scale(matrixStack, 1.65F);
		DrawableHelper.drawCenteredText(matrixStack, this.textRenderer, title, this.width / 2, 25,
				0xFFFFFF);
		matrixStack.pop();

		super.render(matrixStack, mouseX, mouseY, delta);
	}

	public void renderBackground(MatrixStack matrixStack) {
		// Render Background Texture if Allowed
		if (renderBackgroundTexture)
			super.renderBackground(matrixStack);

		// Render Colored Overlay
		float r = Cabricality.Colors.CABF_DIM_PURPLE.redFloat(),
				g = Cabricality.Colors.CABF_DIM_PURPLE.greenFloat(),
				b = Cabricality.Colors.CABF_DIM_PURPLE.blueFloat(), a = 0.57F;

		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();

		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);

		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(matrixStack.peek().getModel(), 0, height, 0.0F).color(r, g, b, a)
				.next();
		bufferBuilder.vertex(matrixStack.peek().getModel(), width, height, 0.0F).color(r, g, b, a)
				.next();
		bufferBuilder.vertex(matrixStack.peek().getModel(), width, 0, 0.0F).color(r, g, b, a)
				.next();
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

	private void modDownloadButton(CabfModDeps mod, Text text, int y) {
		this.addDrawableChild(
				new PlainTextButtonWidget(this.width / 2 - this.textRenderer.getWidth(text) / 2, y,
						this.textRenderer.getWidth(text), 10, text, buttonWidget -> mod.openUrl(),
						this.textRenderer));
	}

	private void skipAndQuitButton(Text quit, Text skip, int y, int wideness) {
		// Quit
		this.addDrawableChild(new PlainTextButtonWidget(this.width / 2 - wideness / 2, y,
				this.textRenderer.getWidth(quit), 10, quit, buttonWidget -> {
					Util.getOperatingSystem()
							.open(QuiltLoader.getGameDir().resolve("mods").toFile()); // Open mods
																						// folder
					if (this.client != null)
						client.stop();
				}, this.textRenderer));
		// Skip
		this.addDrawableChild(new PlainTextButtonWidget(
				this.width / 2 + wideness / 2 - this.textRenderer.getWidth(skip), y,
				this.textRenderer.getWidth(skip), 10, skip, buttonWidget -> this.onClose(),
				this.textRenderer));
	}

	private void quitButton(Text quit, int y) {
		this.addDrawableChild(
				new PlainTextButtonWidget(this.width / 2 - this.textRenderer.getWidth(quit) / 2, y,
						this.textRenderer.getWidth(quit), 10, quit, buttonWidget -> {
							Util.getOperatingSystem()
									.open(QuiltLoader.getGameDir().resolve("mods").toFile()); // Open
																								// mods
																								// folder
							if (this.client != null)
								client.stop();
						}, this.textRenderer));
	}
}
