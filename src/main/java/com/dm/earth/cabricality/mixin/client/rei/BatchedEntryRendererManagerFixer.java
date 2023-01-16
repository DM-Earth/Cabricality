package com.dm.earth.cabricality.mixin.client.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.entry.renderer.BatchedEntryRenderer;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.widget.BatchedEntryRendererManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BatchedEntryRendererManager.class)
public class BatchedEntryRendererManagerFixer {
	@Redirect(method = "renderBatched", at = @At(value = "INVOKE", target = "Lme/shedaniel/rei/api/client/entry/renderer/BatchedEntryRenderer;renderBase(Lme/shedaniel/rei/api/common/entry/EntryStack;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lme/shedaniel/math/Rectangle;IIF)V"))
	private static <T, E> void renderBase(
			BatchedEntryRenderer<T, E> renderer, EntryStack<T> currentEntry, E extraData,
			MatrixStack matrixStack, VertexConsumerProvider.Immediate immediate, Rectangle rectangle, int mouseX, int mouseY, float delta
	) {
		currentEntry.setZ(rectangle.contains(mouseX, mouseY) ? 150 : 100); // Make sure the hovered entry is always on top
		renderer.renderBase(currentEntry, extraData, matrixStack, immediate, rectangle, mouseX, mouseY, delta);
	}
}
