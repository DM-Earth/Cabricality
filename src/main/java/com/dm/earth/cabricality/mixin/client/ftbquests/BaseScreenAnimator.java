package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.PushUtil;
import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.WidgetType;
import net.krlite.equator.geometry.Rect;
import net.krlite.equator.render.Equator;
import net.krlite.equator.util.Timer;
import net.minecraft.client.util.math.MatrixStack;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@ClientOnly
@Mixin(BaseScreen.class)
public class BaseScreenAnimator {
	private final Timer timer = new Timer(175);

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawGui(Lnet/minecraft/client/util/math/MatrixStack;IIIILdev/ftb/mods/ftblibrary/ui/WidgetType;)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h, WidgetType widgetType) {
		PushUtil.ANIMATE_BASE_SCREEN.pull(() -> PushUtil.ANIMATE_CHAPTER_PANEL.push(timer::reset));

		new Equator.Painter(matrixStack).paintRect(new Rect(x, y, w, h).tint(Cabricality.Colors.CABF_GRAY_PURPLE.withOpacity(0.85 * Math.pow(timer.queueAsPercentage(), 1 / 3.0))));
	}
}
