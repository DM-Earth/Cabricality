package com.dm.earth.cabricality.mixin.client.rei;

import me.shedaniel.rei.impl.client.gui.widget.entrylist.EntryListStackEntry;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.shedaniel.rei.impl.client.gui.widget.entrylist.EntryListWidget.entrySize;

@SuppressWarnings("UnstableApiUsage")
@Mixin(EntryListStackEntry.class)
public class EntryListStackEntryFixer extends DrawableHelper {
	@Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lme/shedaniel/rei/impl/client/gui/widget/entrylist/EntryListStackEntry;fillGradient(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
	private void fillGradient(EntryListStackEntry entry, MatrixStack matrixStack, int startX, int startY, int endX, int endY, int colorStart, int colorEnd) {
		fillGradient(matrixStack, entry.getBounds().getCenterX() - entrySize() / 2, entry.getBounds().getCenterY() - entrySize() / 2,
				entry.getBounds().getCenterX() + entrySize() / 2 + 1, entry.getBounds().getCenterY() + entrySize() / 2 + 1,
				colorStart, colorEnd);
	}
}
