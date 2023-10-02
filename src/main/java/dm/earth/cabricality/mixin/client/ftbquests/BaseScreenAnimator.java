package dm.earth.cabricality.mixin.client.ftbquests;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.util.PushUtil;
import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.WidgetType;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.math.geometry.flat.Box;
import net.krlite.equator.visual.animation.animated.AnimatedDouble;
import net.minecraft.client.gui.GuiGraphics;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@ClientOnly
@Mixin(BaseScreen.class)
public class BaseScreenAnimator {
	@Unique
	private static final AnimatedDouble animation = new AnimatedDouble(0, 1, 175, Curves.LINEAR);

	@Redirect(
			method = "drawBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawGui(Lnet/minecraft/client/gui/GuiGraphics;IIIILdev/ftb/mods/ftblibrary/ui/WidgetType;)V"
			)
	)
	private void drawBackground(Theme theme, GuiGraphics graphics, int x, int y, int w, int h, WidgetType type) {
		PushUtil.ANIMATE_BASE_SCREEN.pull(() -> PushUtil.ANIMATE_CHAPTER_PANEL.push(animation::replay));

		Box.fromCartesian(x, y, w, h).render(graphics,
				flat -> flat.new Rectangle(Cabricality.Colors.OLD_CABF_GRAY_PURPLE.opacity(0.85 * Math.pow(animation.value(), 1 / 3.0)))
		);
	}
}
