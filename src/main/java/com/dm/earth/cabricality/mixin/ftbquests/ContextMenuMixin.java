package com.dm.earth.cabricality.mixin.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.math.Rect;
import com.dm.earth.cabricality.math.Timer;
import com.dm.earth.cabricality.util.ColorUtil;

import dev.ftb.mods.ftblibrary.ui.ContextMenuItem;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.ContextMenu;
import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ContextMenu.class)
public class ContextMenuMixin {
	private Timer timer = new Timer(380);

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(Panel panel, List<ContextMenuItem> contextMenuItems, CallbackInfo ci) {
		timer = timer.reset();
	}

	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void draw(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawContextMenuBackground(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h) {
		ColorUtil.Drawer drawer = new ColorUtil.Drawer(matrixStack);
		Rect rect = new Rect(x, y, w, h);

		drawer.rect(rect, ColorUtil.castOpacity(Cabricality.CABF_BRIGHT_PURPLE, 0.27F));
		drawer.rectGradiantFromMiddleWithScissor(
				rect, ColorUtil.castOpacity(Cabricality.CABF_BRIGHT_PURPLE, 0.14F * (float) Math.pow(timer.queueAsPercentage(), 1 / 3.0)),
				ColorUtil.castOpacity(Cabricality.CABF_BRIGHT_PURPLE), 0.17 * Math.pow(timer.queueAsPercentage(), 1 / 3.0)
		);
	}
}
