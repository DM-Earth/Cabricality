package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.util.PushUtil;
import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.WidgetType;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.math.geometry.flat.Box;
import net.krlite.equator.visual.animation.Animation;
import net.minecraft.client.util.math.MatrixStack;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@ClientOnly
@Mixin(BaseScreen.class)
public class BaseScreenAnimator {
	@Unique
	private final Animation animation = new Animation(0, 1, 175, Curves.LINEAR);

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawGui(Lnet/minecraft/client/util/math/MatrixStack;IIIILdev/ftb/mods/ftblibrary/ui/WidgetType;)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h, WidgetType widgetType) {
		PushUtil.ANIMATE_BASE_SCREEN.pull(() -> PushUtil.ANIMATE_CHAPTER_PANEL.push(animation::restart));

		Box.fromCartesian(x, y, w, h).render(matrixStack, 0,
				flat -> flat.new Rectangle(Cabricality.Colors.CABF_GRAY_PURPLE.opacity(0.85 * Math.pow(animation.value(), 1 / 3.0)))
		);
	}
}
