package com.dm.earth.cabricality.mixin.client.malum;

import ca.rttv.malum.client.screen.ProgressionBookScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.BackgroundHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import org.davidmoten.text.utils.WordWrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.regex.Pattern;

@Mixin(ProgressionBookScreen.class)
public abstract class ProgressionBookScreenTextWrapper {
	@Inject(method = "renderWrappingText", at = @At("HEAD"), cancellable = true)
	private static void renderWrappingText(MatrixStack matrixStack, String text, int x, int y, int w, CallbackInfo ci) {
		String content = new TranslatableText(text).getString();
		String[] lines = WordWrap.from(content)
								 .breakWords(true)
								 .insertHyphens(false)
								 .maxWidth(w)
								 .newLine("\n")
								 .includeExtraWordChars("0123456789")
								 .includeExtraWordChars("§")
								 .includeExtraWordChars("()[]{}<>!?:;\"'.,~·")
								 .includeExtraWordChars("（）【】「」《》！？：；“”‘’、，。—～·")
								 .stringWidth(charSequence -> MinecraftClient.getInstance().textRenderer.getWidth(
										 new LiteralText(Pattern.compile("§(?<code>[0-9a-fk-or])")
																 .matcher(charSequence)
																 .replaceAll("")))
								 ).wrap().split("\n");

		for(int index = 0; index < lines.length; index++) {
			String currentLine = lines[index];
			renderRawText(matrixStack, currentLine, x, y + index * (9 + 1), ProgressionBookScreen.glow((float) index / 4));
		}

		ci.cancel();
	}

	@Unique
	private static void renderRawText(MatrixStack matrices, String text, int x, int y, float glow) {
		// Implementation of TextRenderer#drawWithShadow
		TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
		int r = (int) MathHelper.lerp(glow, 182.0F, 227.0F);
		int g = (int)MathHelper.lerp(glow, 61.0F, 39.0F);
		int b = (int)MathHelper.lerp(glow, 183.0F, 228.0F);
		textRenderer.draw(matrices, text, (float)(x - 1), (float)y, BackgroundHelper.ColorMixer.getArgb(96, 255, 210, 243));
		textRenderer.draw(matrices, text, (float)(x + 1), (float)y, BackgroundHelper.ColorMixer.getArgb(128, 240, 131, 232));
		textRenderer.draw(matrices, text, (float)x, (float)(y - 1), BackgroundHelper.ColorMixer.getArgb(128, 255, 183, 236));
		textRenderer.draw(matrices, text, (float)x, (float)(y + 1), BackgroundHelper.ColorMixer.getArgb(96, 236, 110, 226));
		textRenderer.draw(matrices, text, (float)x, (float)y, BackgroundHelper.ColorMixer.getArgb(255, r, g, b));
	}
}
