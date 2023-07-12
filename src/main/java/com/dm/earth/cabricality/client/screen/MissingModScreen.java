package com.dm.earth.cabricality.client.screen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import com.dm.earth.cabricality.client.CabricalityClient;
import net.krlite.equator.render.frame.FrameInfo;
import net.krlite.equator.visual.color.Palette;
import net.krlite.equator.visual.text.Paragraph;
import net.krlite.equator.visual.text.Section;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.Window;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Matrix4f;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.mod.CabfModDeps;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.PlainTextButtonWidget;
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

	public MissingModScreen(@Nullable ArrayList<CabfModDeps> missingMods, boolean renderBackgroundTexture) {
		this(missingMods, null, renderBackgroundTexture);
	}

	public MissingModScreen(@Nullable ArrayList<CabfModDeps> missingMods, @Nullable Screen parent, boolean renderBackgroundTexture) {
		super(
				CabfModDeps.getAllMissing().size() == 1
						? Cabricality.genTranslatableText("screen", "missing_mod", "title")
						: new TranslatableText(
								Cabricality.genTranslationKey("screen", "missing_mod", "title_plural"),
								CabfModDeps.getAllMissing().size()
				)
		);
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
				int y = 60 + (height - 90) * index.incrementAndGet() / ((int) missingMods.size() + 1);
				// Brackets
				modDownloadButton(mod, new LiteralText(brackets).formatted(Formatting.DARK_PURPLE), y);
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
				skipAndQuitButton(quit, skip, this.height - 30, this.textRenderer.getWidth(brackets));
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
		if (this.renderBackgroundTexture) this.renderBackground(matrixStack);

		super.render(matrixStack, mouseX, mouseY, delta);

		MutableText title = new LiteralText(this.title.getString()).formatted(Formatting.BOLD);
		MutableText subtitle = Cabricality.genTranslatableText("screen", "missing_mod", "subtitle" + (missingMods.size() == 1 ? "" : "_plural"));
		MutableText description = Cabricality.genTranslatableText("screen", "missing_mod", "description");

		FrameInfo.scaled().translate(0, -0.7).render(matrixStack,
				flat -> flat.new Text(
						section -> section
										   .appendTitle(title)
										   .append(subtitle)
										   .append(description)
				).horizontalAlignment(Paragraph.Alignment.CENTER).verticalAlignment(Section.Alignment.BOTTOM)
		);
	}

	public void renderBackground(MatrixStack matrixStack) {
		if (CabricalityClient.cubeMapRenderer() != null) {
			matrixStack.push();
			matrixStack.loadIdentity();
			matrixStack.translate(0.0F, 0.0F, -2000.0F);
			RenderSystem.applyModelViewMatrix();

			CabricalityClient.cubeMapRenderer().render(MinecraftClient.getInstance().getLastFrameDuration(), 1);

			matrixStack.pop();
			RenderSystem.applyModelViewMatrix();
		} else {
			super.renderBackground(matrixStack);
		}
	}

	private void modDownloadButton(CabfModDeps mod, Text text, int y) {
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 - this.textRenderer.getWidth(text) / 2, y,
						this.textRenderer.getWidth(text), 10, text, buttonWidget -> mod.openUrl(),
						this.textRenderer
				)
		);
	}

	private void skipAndQuitButton(Text quit, Text skip, int y, int wideness) {
		// Quit
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 - wideness / 2, y,
						this.textRenderer.getWidth(quit), 10, quit, buttonWidget -> {
					// Open mods folder
					Util.getOperatingSystem()
							.open(QuiltLoader.getGameDir().resolve("mods").toFile());
					if (this.client != null) client.stop();
				}, this.textRenderer
				)
		);

		// Skip
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 + wideness / 2 - this.textRenderer.getWidth(skip), y,
						this.textRenderer.getWidth(skip), 10, skip, buttonWidget -> this.onClose(),
						this.textRenderer
				)
		);
	}

	private void quitButton(Text quit, int y) {
		this.addDrawableChild(
				new PlainTextButtonWidget(
						this.width / 2 - this.textRenderer.getWidth(quit) / 2, y,
						this.textRenderer.getWidth(quit), 10, quit, buttonWidget -> {
					// Open mods folder
					Util.getOperatingSystem()
							.open(QuiltLoader.getGameDir().resolve("mods").toFile());
					if (this.client != null) client.stop();
				}, this.textRenderer
				)
		);
	}
}
