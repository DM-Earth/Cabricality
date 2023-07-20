package com.dm.earth.cabricality.mixin.recipe.indrev;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(OutputEntry.class)
public class OutputEntryMixin {
	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static ItemStack modifyOutput(ItemStack itemStack) {
		return ProcessItemOutputCallback.EVENT.invoker().processOutput(itemStack);
	}
}
