package com.dm.earth.cabricality.client.screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.apache.commons.io.IOUtils;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.debug.CabfLogger;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.Resource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

@ClientOnly
public class CabfCreditsScreen extends Screen {
	private static final Identifier VIGNETTE_TEXTURE = new Identifier("textures/misc/vignette.png");
	private static final Text SEPARATOR_LINE =
			new LiteralText("============").formatted(Formatting.WHITE);
	private final Runnable finishAction;
	private int creditsHeight;
	private float time;
	private float speed;
	private final float baseSpeed;
	private List<OrderedText> credits;
	private IntSet centeredLines;
	private boolean spaceKeyPressed;
	private final IntSet pressedCtrlKeys = new IntOpenHashSet();

	public CabfCreditsScreen(Runnable finishAction) {
		super(NarratorManager.EMPTY);
		this.finishAction = finishAction;
		this.speed = this.baseSpeed = 0.35F;
	}

	private float getSpeed() {
		return this.spaceKeyPressed
				? this.baseSpeed * (5.0F + (float) this.pressedCtrlKeys.size() * 15.0F)
				: this.baseSpeed;
	}

	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode != 341 && keyCode != 345) {
			if (keyCode == 32) {
				this.spaceKeyPressed = true;
			}
		} else {
			this.pressedCtrlKeys.add(keyCode);
		}

		this.speed = this.getSpeed();
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 32) {
			this.spaceKeyPressed = false;
		} else if (keyCode == 341 || keyCode == 345) {
			this.pressedCtrlKeys.remove(keyCode);
		}

		this.speed = this.getSpeed();
		return super.keyReleased(keyCode, scanCode, modifiers);
	}

	public void tick() {
		if (this.client != null) {
			this.client.getMusicTracker().tick();
			this.client.getSoundManager().tick(false);
			if (this.time > (float) (this.creditsHeight + this.height + this.height + 24))
				this.close();
		}
	}

	public void onClose() {
		this.close();
	}

	private void close() {
		this.finishAction.run();
		if (this.client != null)
			this.client.setScreen(null);
	}

	protected void init() {
		if (this.credits == null) {
			this.credits = Lists.newArrayList();
			this.centeredLines = new IntOpenHashSet();
			this.load("credits.json", this::addCreditsFile);
			this.load("postcredits.txt", this::addPostCreditsFile);
			this.creditsHeight = this.credits.size() * 12;
		}
		CabfLogger.logInfo("Showing credits...");
	}

	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
		this.time += delta * this.speed;
		this.renderBackground();

		int width = this.width / 2 - 137;
		int height = this.height + 50;

		matrixStack.push();
		matrixStack.translate(0.0, -this.time, 0.0);

		RenderSystem.setShaderTexture(0, Cabricality.Textures.CABRICALITY_TITLE.identifier());
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.enableBlend();

		this.drawWithOutline(width, height, (x, y) -> {
			this.drawTexture(matrixStack, x, y, 0, 0, 155, 44);
			this.drawTexture(matrixStack, x + 155, y, 0, 45, 155, 44);
		});

		RenderSystem.disableBlend();
		RenderSystem.setShaderTexture(0, Cabricality.Textures.MINECRAFT_SUBTITLE.identifier());

		drawTexture(matrixStack, width + 88, height + 37, 0.0F, 0.0F, 98, 14, 128, 16);

		int y = height + 100;
		for (int line = 0; line < this.credits.size(); ++line) {
			if (line == this.credits.size() - 1) {
				float scale = y - this.time - (this.height / 2.0F - 6);
				if (scale < 0.0F) {
					matrixStack.translate(0.0, -scale, 0.0);
				}
			}

			if (y - this.time + 12.0F + 8.0F > 0.0F && y - this.time < this.height) {
				OrderedText orderedText = this.credits.get(line);
				if (this.centeredLines.contains(line)) {
					this.textRenderer.drawWithShadow(matrixStack, orderedText,
							width + (274 - this.textRenderer.getWidth(orderedText)) / 2.0F, y,
							0xFFFFFF);
				} else {
					this.textRenderer.drawWithShadow(matrixStack, orderedText, width, y, 0xFFFFFF);
				}
			}

			// Next line
			y += 12;
		}

		matrixStack.pop();

		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, VIGNETTE_TEXTURE);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO,
				GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

		bufferBuilder.vertex(0, this.height, this.getZOffset()).texture(0, 1).color(1, 1, 1, 1)
				.next();

		bufferBuilder.vertex(this.width, this.height, this.getZOffset()).texture(1, 1)
				.color(1, 1, 1, 1).next();

		bufferBuilder.vertex(this.width, 0, this.getZOffset()).texture(1, 0).color(1, 1, 1, 1)
				.next();

		bufferBuilder.vertex(0, 0, this.getZOffset()).texture(0, 0).color(1, 1, 1, 1).next();

		tessellator.draw();
		RenderSystem.disableBlend();
		super.render(matrixStack, mouseX, mouseY, delta);
	}

	private void renderBackground() {
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, DrawableHelper.OPTIONS_BACKGROUND_TEXTURE);
		float multiplier = 0.015625F;
		float color = (float) Math.pow(Math.min(this.time / this.baseSpeed * 0.02F,
				Math.min(1,
						((this.creditsHeight + this.height + this.height + 24) / this.baseSpeed
								- 20.0F - this.time / this.baseSpeed) * 0.005F)),
				2) * 96.0F / 255.0F;

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

		bufferBuilder.vertex(0, this.height, this.getZOffset())
				.texture(0, -this.time * 0.5F * multiplier).color(color, color, color, 1).next();

		bufferBuilder.vertex(this.width, this.height, this.getZOffset())
				.texture((float) this.width * multiplier, -this.time * 0.5F * multiplier)
				.color(color, color, color, 1).next();

		bufferBuilder.vertex(this.width, 0, this.getZOffset())
				.texture((float) this.width * multiplier,
						(this.height - 0.5F * this.time) * multiplier)
				.color(color, color, color, 1).next();

		bufferBuilder.vertex(0, 0, this.getZOffset())
				.texture(0, (this.height - 0.5F * this.time) * multiplier)
				.color(color, color, color, 1).next();

		tessellator.draw();
	}

	private void load(String fileName, CreditsReader reader) {
		Resource resource = null;

		if (client != null) {
			try {
				resource = this.client.getResourceManager()
						.getResource(Cabricality.id("texts", fileName));
				InputStreamReader inputStreamReader =
						new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
				reader.read(inputStreamReader);
			} catch (Exception exception) {
				CabfLogger.logError("Couldn't load credits", exception);
			} finally {
				IOUtils.closeQuietly(resource);
			}
		}
	}

	private void addPostCreditsFile(InputStreamReader reader) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(reader);
		String string;
		while ((string = bufferedReader.readLine()) != null) {
			this.addText(string);
			this.addEmptyLine();
		}
		for (int i = 0; i < 8; ++i)
			this.addEmptyLine();
	}

	private void addCreditsFile(InputStreamReader reader) {
		// Implemented from CreditsScreen so it may be a mess
		JsonArray jsonArray = JsonHelper.m_lxlopfmi(reader);

		for (JsonElement jsonElement : jsonArray) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			String string = jsonObject.get("section").getAsString();
			this.addText(SEPARATOR_LINE, true);
			this.addText((new LiteralText(string)).formatted(Formatting.YELLOW), true);
			this.addText(SEPARATOR_LINE, true);
			this.addEmptyLine();
			this.addEmptyLine();
			JsonArray jsonArray2 = jsonObject.getAsJsonArray("titles");

			for (JsonElement jsonElement2 : jsonArray2) {
				JsonObject jsonObject2 = jsonElement2.getAsJsonObject();
				String string2 = jsonObject2.get("title").getAsString();
				JsonArray jsonArray3 = jsonObject2.getAsJsonArray("names");
				this.addText((new LiteralText(string2)).formatted(Formatting.GRAY), false);

				for (JsonElement jsonElement3 : jsonArray3) {
					PlayerEntity player = MinecraftClient.getInstance().player;
					String string3 = jsonElement3.getAsString();
					if (player != null) string3 = string3.replaceAll("PLAYERNAME", player.getName().asString());
					this.addText((new LiteralText("           ")).append(string3)
							.formatted(Formatting.WHITE), false);
				}

				this.addEmptyLine();
				this.addEmptyLine();
			}
		}
	}

	private void addEmptyLine() {
		this.credits.add(OrderedText.EMPTY);
	}

	private void addText(String text) {
		if (this.client != null)
			this.credits.addAll(this.client.textRenderer.wrapLines(new LiteralText(text), 274));
	}

	private void addText(Text text, boolean centered) {
		if (centered)
			this.centeredLines.add(this.credits.size());

		this.credits.add(text.asOrderedText());
	}

	@FunctionalInterface
	@ClientOnly
	private interface CreditsReader {
		void read(InputStreamReader inputStreamReader) throws IOException;
	}
}
