package dm.earth.cabricality.client.screen;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.util.debug.CabfLogger;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tessellator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormats;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LogoRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Environment(EnvType.CLIENT)
public class CabfCreditsScreen extends Screen {
	private static final Identifier VIGNETTE_TEXTURE = new Identifier("textures/misc/vignette.png");
	private static final Text SEPARATOR_LINE = Text.literal("============").formatted(Formatting.WHITE);
	private final Runnable finishAction;
	private int creditsHeight;
	private float time;
	private float speed;
	private final float baseSpeed;
	private List<OrderedText> credits;
	private IntSet centeredLines;
	private boolean spaceKeyPressed;
	private final IntSet pressedCtrlKeys = new IntOpenHashSet();
	private final LogoRenderer logoRenderer = new LogoRenderer(false);

	public CabfCreditsScreen(Runnable finishAction) {
		super(Text.empty());
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
			this.load("credits.json", this::readCredits);
			this.load("postcredits.txt", this::readPostCredits);
			this.creditsHeight = this.credits.size() * 12;
		}
		CabfLogger.logInfo("Showing credits...");
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
		this.time += delta * this.speed;
		this.renderBackground();

		int width = this.width / 2 - 137;
		int height = this.height + 50;

		graphics.getMatrices().push();
		graphics.getMatrices().translate(0.0, -this.time, 0.0);

		logoRenderer.draw(graphics, this.width, 1.0f, height + 50);

		int y = height + 100;
		for (int line = 0; line < this.credits.size(); ++line) {
			if (line == this.credits.size() - 1) {
				float scale = y - this.time - (this.height / 2.0F - 6);
				if (scale < 0.0F) graphics.getMatrices().translate(0.0, -scale, 0.0);
			}

			if (y - this.time + 12.0F + 8.0F > 0.0F && y - this.time < this.height) {
				OrderedText orderedText = this.credits.get(line);
				if (this.centeredLines.contains(line)) {
					graphics.drawText(
							this.textRenderer, orderedText,
                            (int) (width + (274 - this.textRenderer.getWidth(orderedText)) / 2F), y,
							0xFFFFFF, true
					);
				} else {
					graphics.drawText(
							this.textRenderer, orderedText,
							width, y,
							0xFFFFFF, true
					);
				}
			}

			// Next line
			y += 12;
		}

		graphics.getMatrices().pop();

		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, VIGNETTE_TEXTURE);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBufferBuilder();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

		bufferBuilder
				.vertex(0, this.height, 0)
				.uv(0, 1)
				.color(1, 1, 1, 1)
				.next();

		bufferBuilder
				.vertex(this.width, this.height, 0)
				.uv(1, 1)
				.color(1, 1, 1, 1)
				.next();

		bufferBuilder
				.vertex(this.width, 0, 0)
				.uv(1, 0)
				.color(1, 1, 1, 1)
				.next();

		bufferBuilder
				.vertex(0, 0, 0)
				.uv(0, 0)
				.color(1, 1, 1, 1)
				.next();

		tessellator.draw();
		RenderSystem.disableBlend();

		super.render(graphics, mouseX, mouseY, delta);
	}

	private void renderBackground() {
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, OPTIONS_BACKGROUND_TEXTURE);
		float multiplier = 0.015625F;
		float color = (float) Math.pow(Math.min(this.time / this.baseSpeed * 0.02F,
				Math.min(1,
						((this.creditsHeight + this.height + this.height + 24) / this.baseSpeed
								- 20.0F - this.time / this.baseSpeed) * 0.005F)),
				2) * 96.0F / 255.0F;

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBufferBuilder();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

		bufferBuilder
				.vertex(0, this.height, 0)
				.uv(0, -this.time * 0.5F * multiplier)
				.color(color, color, color, 1)
				.next();

		bufferBuilder
				.vertex(this.width, this.height, 0)
				.uv((float) this.width * multiplier, -this.time * 0.5F * multiplier)
				.color(color, color, color, 1)
				.next();

		bufferBuilder
				.vertex(this.width, 0, 0)
				.uv((float) this.width * multiplier, (this.height - 0.5F * this.time) * multiplier)
				.color(color, color, color, 1)
				.next();

		bufferBuilder
				.vertex(0, 0, 0)
				.uv(0, (this.height - 0.5F * this.time) * multiplier)
				.color(color, color, color, 1)
				.next();

		tessellator.draw();
	}

	private void load(String fileName, CreditsReader reader) {
		if (this.client != null) {
			try (BufferedReader bufferedReader = this.client
					.getResourceManager()
					.openAsReader(Cabricality.id("texts", fileName))
			){
				reader.read(bufferedReader);
			} catch (Exception exception) {
				Cabricality.LOGGER.error("Couldn't load credits", exception);
			}
		}
	}

	private void readPostCredits(Reader reader) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(reader);
		String string;

		while ((string = bufferedReader.readLine()) != null) {
			this.addText(string);
			this.addEmptyLine();
		}

		for (int i = 0; i < 8; ++i) this.addEmptyLine();
	}

	private void readCredits(Reader reader) {
		JsonArray sectionElements = JsonHelper.deserializeArray(reader);

		for (JsonElement sectionElement : sectionElements) {
			JsonObject sectionObject = sectionElement.getAsJsonObject();
			String section = sectionObject.get("section").getAsString();

			this.addText(SEPARATOR_LINE, true);
			this.addText((Text.literal(section))
					.formatted(Formatting.YELLOW),
					true);
			this.addText(SEPARATOR_LINE, true);

			this.addEmptyLine();
			this.addEmptyLine();

			JsonArray titleElements = sectionObject.getAsJsonArray("titles");
			for (JsonElement titleElement : titleElements) {
				JsonObject titleObject = titleElement.getAsJsonObject();
				String title = titleObject.get("title").getAsString();


				this.addText((Text.literal(title))
						.formatted(Formatting.GRAY),
						false);

				JsonArray nameElements = titleObject.getAsJsonArray("names");
				for (JsonElement nameElement : nameElements) {
					PlayerEntity player = MinecraftClient.getInstance().player;
					String name = nameElement.getAsString();

					if (player != null) name = name.replaceAll("PLAYERNAME", player.getName().getString());
					this.addText((Text.literal("           "))
							.append(name)
							.formatted(Formatting.WHITE),
							false);
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
			this.credits.addAll(this.client.textRenderer.wrapLines(Text.literal(text), 274));
	}

	private void addText(Text text, boolean centered) {
		if (centered)
			this.centeredLines.add(this.credits.size());

		this.credits.add(text.asOrderedText());
	}

	@FunctionalInterface
	@Environment(EnvType.CLIENT)
	private interface CreditsReader {
		void read(Reader reader) throws IOException;
	}
}
