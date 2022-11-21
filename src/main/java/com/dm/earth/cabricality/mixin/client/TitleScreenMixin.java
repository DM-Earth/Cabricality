package com.dm.earth.cabricality.mixin.client;

import java.util.ArrayList;
import java.util.Objects;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.util.ModChecker;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	@Shadow
	@Final
	private RotatingCubeMapRenderer backgroundRenderer;

	protected TitleScreenMixin(Text title) {
		super(title);
	}

	@Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/TitleScreen;doBackgroundFade:Z"))
	public boolean renderRedirected(TitleScreen instance) {
		return false;
	}

	@Inject(method = "render", at = @At("TAIL"))
	public void renderInjectedTail(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (!ModChecker.isFullLoaded() && client != null) {
			Screen screen = Objects.requireNonNull(client.currentScreen);
			TextRenderer textRenderer = ((ScreenAccessor) screen).getTextRenderer();

			ArrayList<Text> TEXTS = new ArrayList<>();
			TEXTS.add(CabricalityClient.genTranslatableText("util", "warn"));

			for (String mod : ModChecker.missingModList)
				TEXTS.add(new LiteralText("§c<" + mod + ">"));

			int yMultiple = 0;
			float xyMultiple = 1.7f;

			for (Text text : TEXTS) {
				matrices.push();
				matrices.translate(width / 2.0, yMultiple += height / (TEXTS.size() + 1), 0.0);
				matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-20.0f));
				float h = 1.8f - MathHelper.abs(
						MathHelper.sin((float) (Util.getMeasuringTimeMs() % 1000L) / 1000.0f * ((float) Math.PI * 2))
								* 0.1f);
				h = h * 100.0f / (float) (textRenderer.getWidth(text) + 32);
				matrices.scale(h * xyMultiple, h * xyMultiple, h * xyMultiple);
				TitleScreen.drawCenteredText(matrices, textRenderer, text, 0, -8, 0xFF555555);
				matrices.pop();
			}
		}
	}
}
