package com.dm.earth.cabricality.mixin.recipe.create;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ProcessingOutput.class)
public class ProcessingOutputMixin {
	@ModifyVariable(method = "<init>(Lnet/minecraft/item/ItemStack;F)V", at = @At("HEAD"), argsOnly = true)
	private static ItemStack modifyOutput(ItemStack itemStack) {
		return ProcessItemOutputCallback.EVENT.invoker().processOutput(itemStack);
	}
}
