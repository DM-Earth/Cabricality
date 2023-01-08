package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;

import dev.ftb.mods.ftblibrary.ui.ContextMenuItem;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;

import net.krlite.equator.geometry.Rect;
import net.krlite.equator.geometry.TintedRect;
import net.krlite.equator.util.Timer;
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
	private Timer timer = new Timer(200);

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(Panel panel, List<ContextMenuItem> contextMenuItems, CallbackInfo ci) {
		timer = timer.reset();
	}

	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void draw(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawContextMenuBackground(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h) {
		new TintedRect(
				new Rect(x, y, w, h).expand(2.7 * Math.pow(1 - timer.queueAsPercentage(), 1 / 2.0)),
				Cabricality.Colors.CABF_BRIGHT_PURPLE.withOpacity(0.6 * Math.pow(timer.queueAsPercentage(), 2))
		).draw(matrixStack);
	}
}
