package com.dm.earth.cabricality.mixin.ftbquests;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.ColorUtil;
import com.dm.earth.cabricality.util.math.Node;
import com.dm.earth.cabricality.util.math.Rect;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.BlankPanel;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.Widget;
import dev.ftb.mods.ftbquests.gui.quests.ViewQuestPanel;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(ViewQuestPanel.class)
public abstract class ViewQuestPanelMixin extends Widget {
	public ViewQuestPanelMixin(Panel p) {
		super(p);
	}

	@ModifyArg(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setWidth(I)V"), remap = false)
	private int modifyWidth(int width) {
		return width + 16;
	}

	@ModifyArg(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setHeight(I)V"), remap = false)
	private int modifyHeight(int height) {
		return height + 12;
	}

	private void modifyPos(Args args, int xIndex, int yIndex) {
		args.set(xIndex, (int) args.get(xIndex) + 8);
		args.set(yIndex, (int) args.get(yIndex) + 6);
	}

	@ModifyArgs(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/TextField;setPosAndSize(IIII)Ldev/ftb/mods/ftblibrary/ui/Widget;", ordinal = 0), remap = false)
	private void modifyTitlePos(Args args) {
		modifyPos(args, 0, 1);
	}

	@ModifyArgs(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Button;setPosAndSize(IIII)Ldev/ftb/mods/ftblibrary/ui/Widget;"), remap = false)
	private void modifyButtonPos(Args args) {
		modifyPos(args, 0, 1);
	}

	@ModifyArgs(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/BlankPanel;setPosAndSize(IIII)Ldev/ftb/mods/ftblibrary/ui/Widget;", ordinal = 0), remap = false)
	private void modifyContentPanelPos(Args args) {
		modifyPos(args, 0, 1);
	}

	@ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Icon;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V", ordinal = 1))
	private void modifyQuestPanelIconPos(Args args) {
		modifyPos(args, 1, 2);
		args.set(1, (int) args.get(1) - 3); // Fix x position
	}

	@Inject(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V", ordinal = 0))
	private void drawQuestPanelBackground(MatrixStack matrixStack, Theme theme, int x, int y, int w, int h, CallbackInfo ci) {
		Color4I.rgb(Cabricality.CABF_DIM_PURPLE.getRGB()).withAlpha(210).draw(matrixStack, x, y, w, h);
		new ColorUtil.Drawer(matrixStack).rectGradiantFromMiddleWithScissor(
				new Rect(new Node(x, y), new Node(x + w, y + h)),
				ColorUtil.castAlpha(Cabricality.CABF_PURPLE, 65),
				ColorUtil.castAlpha(Cabricality.CABF_MID_PURPLE)
		);
	}

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Icon;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V", ordinal = 0))
	private void drawQuestPanelBackgroundTexture(Icon icon, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V", ordinal = 1))
	private void drawQuestPanelBorder(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(
			method = "addWidgets",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/ui/BlankPanel;add(Ldev/ftb/mods/ftblibrary/ui/Widget;)V"
			),
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/SimpleTextButton;setHeight(I)V"),
					to = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setPos(II)V")
			), remap = false
	)
	private void drawBorder(BlankPanel panel, Widget widget) {}
}
