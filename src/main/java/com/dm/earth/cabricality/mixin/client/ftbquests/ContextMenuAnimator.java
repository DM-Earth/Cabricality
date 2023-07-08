package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.Cabricality;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.ContextMenu;
import dev.ftb.mods.ftblibrary.ui.ContextMenuItem;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import net.krlite.equator.math.algebra.Curves;
import net.krlite.equator.math.geometry.flat.Box;
import net.krlite.equator.visual.animation.Animation;
import net.minecraft.client.util.math.MatrixStack;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@ClientOnly
@Mixin(ContextMenu.class)
public class ContextMenuAnimator {
	@Unique
	private final Animation animation = new Animation(0, 1, 200, Curves.LINEAR);

	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void init(Panel panel, List<ContextMenuItem> contextMenuItems, CallbackInfo ci) {
		animation.restart();
	}

	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;draw(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void draw(Color4I color4I, MatrixStack matrixStack, int x, int y, int w, int h) {}

	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftblibrary/ui/Theme;drawContextMenuBackground(Lnet/minecraft/client/util/math/MatrixStack;IIII)V"))
	private void drawBackground(Theme theme, MatrixStack matrixStack, int x, int y, int w, int h) {
		Box.fromCartesian(x, y, w, h).scaleCenter(2.7 * Math.pow(1 - animation.value(), 1 / 2.0))
				.render(matrixStack, 0,
						flat -> flat.new Rectangle(Cabricality.Colors.CABF_BRIGHT_PURPLE.opacity(0.32 * Math.pow(animation.value(), 2)))
				);
	}
}
