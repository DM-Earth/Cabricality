package com.dm.earth.cabricality.mixin.ftbquests;

import com.dm.earth.cabricality.util.debug.CabfLogger;

import net.minecraft.text.Style;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Widget;
import dev.ftb.mods.ftbquests.gui.quests.ChapterPanel;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

@Mixin(ChapterPanel.ChapterButton.class)
public class ChapterPanelMixin extends Widget {
	public ChapterPanelMixin(Panel p) {
		super(p);
	}

	@Redirect(
			method = "draw",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/text/LiteralText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;"
			)
	)
	private MutableText tintChapterIndicator(LiteralText text, Formatting formatting) {
		return text.formatted(Formatting.DARK_GRAY);
	}

	@Redirect(
			method = "draw",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/text/MutableText;fillStyle(Lnet/minecraft/text/Style;)Lnet/minecraft/text/MutableText;"
			)
	)
	private MutableText tintChapterName(MutableText text, Style style) {
		if (this.isMouseOver) return text; // Highlight chapter name when hovered
		else return text.setStyle(style);
	}

	@ModifyArg(
			method = "draw",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;withAlpha(I)Ldev/ftb/mods/ftblibrary/icon/Color4I;"
			), index = 0, remap = false
	)
	private int tintHoverBackground(int alpha) {
		return 18;
	}
}
