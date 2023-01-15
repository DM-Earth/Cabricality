package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.PushUtil;

import net.krlite.equator.geometry.Rect;
import net.krlite.equator.geometry.TintedRect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftbquests.gui.quests.QuestScreen;
import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(QuestScreen.class)
public class QuestScreenMixin {
	@Inject(method = "addWidgets", at = @At("TAIL"), remap = false)
	private void init(CallbackInfo ci) {
		PushUtil.ANIMATE_VIEW_QUEST_PANEL.push();
	}

	@Redirect(
			method = "drawBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
					ordinal = 0
			)
	)
	private void drawLeftBorder(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(
			method = "drawBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
					ordinal = 1
			)
	)
	private void drawLeftSide(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {
		new TintedRect(
				new Rect(x - 1, y - 1, w + 2, h + 2),
				Cabricality.Colors.CABF_BLACK.withOpacity(0.127)
		).draw(matrixStack);
	}

	@Redirect(
			method = "drawBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
					ordinal = 2
			)
	)
	private void drawRightBorder(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(
			method = "drawBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
					ordinal = 3
			)
	)
	private void drawRightSide(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {
		new TintedRect(
				new Rect(x - 1, y - 1, w + 2, h + 2),
				Cabricality.Colors.CABF_BLACK.withOpacity(0.127)
		).draw(matrixStack);
	}
}
