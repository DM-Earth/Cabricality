package com.dm.earth.cabricality.mixin.ftbquests;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.math.Rect;
import com.dm.earth.cabricality.math.Timer;
import com.dm.earth.cabricality.util.ColorUtil;
import com.dm.earth.cabricality.util.PusherUtil;

import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.WidgetType;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(BaseScreen.class)
public class BaseScreenMixin {
	private Timer timer = new Timer(175);

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawGui(Lnet/minecraft/client/util/math/MatrixStack;IIIILdev/ftb/mods/ftblibrary/ui/WidgetType;)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h, WidgetType widgetType) {
		if (PusherUtil.ANIMATE_BASE_SCREEN.pull()) timer = timer.reset();
		new ColorUtil.Drawer(matrixStack).rect(new Rect(x, y, w, h), ColorUtil.castOpacity(Cabricality.CABF_GRAY_PURPLE, 0.85F * (float) Math.pow(timer.queueAsPercentage(), 1 / 3.0)));
	}
}
