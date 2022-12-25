package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.screen.MissingModScreen;
import com.dm.earth.cabricality.util.ModCheckerDec;
import com.dm.earth.cabricality.util.ModDeps;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;

import net.minecraft.client.gui.widget.PlainTextButtonWidget;
import net.minecraft.text.Text;

import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		Stream<ModDeps> missing = ModDeps.getMissing(true, false);
		if (missing.count() > 0) {
			Text warning = (missing.count() == 1
					? Cabricality.genTranslatableText("screen", "title_screen", "warning_missing_mod")
					: new TranslatableText(
							Cabricality.genTranslationKey("screen", "title_screen", "warning_missing_mods"),
							missing.count()))
					.formatted(Formatting.RED);
			this.addDrawableChild(
					new PlainTextButtonWidget(
							this.width / 2 - this.textRenderer.getWidth(warning) / 2, 2,
							this.textRenderer.getWidth(warning), 10, warning,
							buttonWidget -> {
								if (this.client != null)
									this.client.setScreen(new MissingModScreen(ModCheckerDec.getMissingModList(),
											ModCheckerDec.getModDedicatedUrlList(), this.client.currentScreen));
							}, this.textRenderer));
		}
	}
}
