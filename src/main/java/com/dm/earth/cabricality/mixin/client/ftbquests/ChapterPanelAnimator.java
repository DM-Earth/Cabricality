package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.PushUtil;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.Widget;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.gui.quests.ChapterPanel;
import dev.ftb.mods.ftbquests.gui.quests.QuestScreen;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.math.geometry.flat.Box;
import net.krlite.equator.visual.animation.animated.AnimatedDouble;
import net.krlite.equator.visual.animation.base.Animation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ClientOnly
@Mixin(ChapterPanel.class)
public abstract class ChapterPanelAnimator {
	@Shadow(remap = false)
	public boolean expanded;

	@Shadow(remap = false)
	abstract boolean isPinned();

	@Unique
	private static final AnimatedDouble animation = new AnimatedDouble(0, 1, 400, Curves.Bounce.OUT);

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawContextMenuBackground(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h) {
		PushUtil.ANIMATE_CHAPTER_PANEL.or((!this.expanded && !this.isPinned()), animation::replay);

		Box chapterBox = Box.fromCartesian(x, y, w * animation.value(), h);

		chapterBox.render(matrixStack,
				flat -> flat.new Rectangle()
								.colors(Cabricality.Colors.CABF_BLACK)
								.opacityMultiplier(0.73)
		);

		chapterBox.render(matrixStack,
				flat -> flat.new Rectangle()
								.colorLeft(Cabricality.Colors.CABF_PURPLE.opacity(0.2))
								.colorRight(Cabricality.Colors.CABF_MID_PURPLE.opacity(1 - animation.value()))
		);
	}
}

@Mixin(ChapterPanel.ChapterButton.class)
class ChapterButtonAnimator extends Widget {
	public ChapterButtonAnimator(Panel p) {
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
class ModpackButtonAnimator {
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
