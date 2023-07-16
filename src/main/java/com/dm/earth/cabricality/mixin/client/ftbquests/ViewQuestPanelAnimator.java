package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.PushUtil;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.ui.BlankPanel;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.Widget;
import dev.ftb.mods.ftbquests.gui.quests.ViewQuestPanel;
import dev.ftb.mods.ftbquests.quest.theme.property.ThemeProperties;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.math.geometry.flat.Box;
import net.krlite.equator.math.geometry.flat.Vector;
import net.krlite.equator.render.renderer.Flat;
import net.krlite.equator.visual.animation.animated.AnimatedDouble;
import net.krlite.equator.visual.animation.base.Animation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@ClientOnly
@Mixin(ViewQuestPanel.class)
public abstract class ViewQuestPanelAnimator extends Widget {
	@Shadow(remap = false)
	public BlankPanel panelText;

	public ViewQuestPanelAnimator(Panel panel) {
		super(panel);
	}

	@Unique
	private static final AnimatedDouble animation = new AnimatedDouble(0, 1, 720, Curves.Sinusoidal.EASE);

	@ModifyArg(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setWidth(I)V"), remap = false)
	private int modifyWidth(int width) {
		return width + 16;
	}

	@ModifyArg(method = "addWidgets", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/ViewQuestPanel;setHeight(I)V"), remap = false)
	private int modifyHeight(int height) {
		return height + (this.panelText.widgets.isEmpty() ? 18 : 12);
	}

	@Unique
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
		PushUtil.ANIMATE_VIEW_QUEST_PANEL.pull(animation::replay);
		double lerp = Math.pow(animation.value(), 1 / 3.0);

		Box.fromCartesian(x, y, w, h).render(matrixStack,
				flat -> flat.new Rectangle()
								.colorTop(Cabricality.Colors.CABF_PURPLE)
								.colorBottom(Cabricality.Colors.CABF_MID_PURPLE)
								.new Outlined(Vector.fromCartesian(240 * lerp, 240 * lerp),
						Flat.Rectangle.Outlined.OutliningMode.NORMAL,
						Flat.Rectangle.Outlined.OutliningStyle.EDGE_FADED)
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

	@Unique
	private double sinusoidal(double percentage, double origin, double shift) {
		return origin + -shift / 2 * (Math.cos(Math.PI * MathHelper.clamp(percentage, 0, 1)) - 1);
	}
}
