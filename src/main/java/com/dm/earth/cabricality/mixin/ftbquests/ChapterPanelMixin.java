package com.dm.earth.cabricality.mixin.ftbquests;

import com.dm.earth.cabricality.util.ColorUtil;

import com.dm.earth.cabricality.util.math.Node;
import com.dm.earth.cabricality.util.math.Rect;

import dev.ftb.mods.ftbquests.gui.quests.QuestScreen;

import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.dm.earth.cabricality.Cabricality;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.Widget;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.gui.quests.ChapterPanel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChapterPanel.class)
public class ChapterPanelMixin {
	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawContextMenuBackground(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h) {
		Color4I.rgb(Cabricality.CABF_BLACK.getRGB()).withAlpha(170).draw(matrixStack, x, y, w, h);
		new ColorUtil.Drawer(matrixStack).horizontalGradiant(
				new Rect(new Node(x, y), new Node(x + w - 7, y + h)),
				ColorUtil.castAlpha(Cabricality.CABF_PURPLE, 45), ColorUtil.castAlpha(Cabricality.CABF_MID_PURPLE)
		);
	}
}

@Mixin(ChapterPanel.ChapterButton.class)
class ChapterButtonMixin extends Widget {
	public ChapterButtonMixin(Panel p) {
		super(p);
	}

	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/LiteralText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;"))
	private MutableText modifyChapterIndicator(LiteralText text, Formatting formatting) {
		return new LiteralText("  ●");
	}

	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;fillStyle(Lnet/minecraft/text/Style;)Lnet/minecraft/text/MutableText;"))
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

@Mixin(ChapterPanel.ModpackButton.class)
class ModpackButtonMixin {
	@Redirect(method = "addMouseOverText", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/util/TooltipList;string(Ljava/lang/String;)V"), remap = false)
	private void translatePin(TooltipList list, String text) {
		list.translate(ClientQuestFile.INSTANCE.self.isChapterPinned() ? "ftbquests.gui.unpin" : "ftbquests.gui.pin");
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

	@Redirect(
			method = "draw",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
					ordinal = 1
			)
	)
	private void drawLine(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Inject(method = "getActualWidth", at = @At("RETURN"), remap = false, cancellable = true)
	private void getActualWidth(QuestScreen screen, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(cir.getReturnValue() + 20);
	}
}
