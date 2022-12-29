package com.dm.earth.cabricality.mixin.ftbquests;

import com.dm.earth.cabricality.math.Timer;

import com.dm.earth.cabricality.util.PushUtil;
import com.dm.earth.cabricality.util.debug.CabfLogger;

import dev.ftb.mods.ftbquests.quest.theme.property.ThemeProperties;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.func.CabfRenderer;
import com.dm.earth.cabricality.math.Rect;

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
	@Shadow(remap = false)
	public BlankPanel panelText;

	public ViewQuestPanelMixin(Panel p) {
		super(p);
	}

	private Timer timer = new Timer(1320);

	@ModifyArg(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setWidth(I)V"), remap = false)
	private int modifyWidth(int width) {
		return width + 16;
	}

	@ModifyArg(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setHeight(I)V"), remap = false)
	private int modifyHeight(int height) {
		CabfLogger.logInfo(panelText.widgets.toString());
		return height + (this.panelText.widgets.isEmpty() ? 18 : 12);
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
		PushUtil.ANIMATE_VIEW_QUEST_PANEL.run(() -> timer = timer.reset());

		CabfRenderer.Drawer drawer = new CabfRenderer.Drawer(matrixStack);
		Rect rect = new Rect(x, y, w, h);

		drawer.rect(rect, CabfRenderer.castOpacity(Cabricality.CABF_DIM_PURPLE, 0.87F));
		drawer.rectGradiantFromMiddleWithScissor(
				new Rect().min(rect.expand(90)).max(rect.expand(20)), rect, CabfRenderer.castOpacity(Cabricality.CABF_PURPLE, 0.45F * (float) Math.pow(timer.queueAsPercentage(), 1 / 3.0)),
				CabfRenderer.castOpacity(Cabricality.CABF_MID_PURPLE), 0.45 * Math.pow(timer.queueAsPercentage(), 1 / 3.0)
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

	@SuppressWarnings("all")
	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Icon;withTint(Ldev/ftb/mods/ftblibrary/icon/Color4I;)Ldev/ftb/mods/ftblibrary/icon/Icon;"), remap = false)
	private Icon tintLeftArrow(Icon icon, Color4I color4I) {
		Color4I color = ThemeProperties.QUEST_VIEW_TITLE.get()
							   .withAlphaf((float) sinusoidal((Math.abs(MinecraftClient.getInstance().world.getTime() % 40 - 20) - 6) / 8.0, 0.03, 0.97));
		return icon.withColor(color);
	}

	private double sinusoidal(double percentage, double origin, double shift) {
		return origin + -shift / 2 * (Math.cos(Math.PI * MathHelper.clamp(percentage, 0, 1)) - 1);
	}
}
