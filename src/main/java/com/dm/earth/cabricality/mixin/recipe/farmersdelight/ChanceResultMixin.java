package com.dm.earth.cabricality.mixin.recipe.farmersdelight;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import com.nhoryzon.mc.farmersdelight.recipe.ingredient.ChanceResult;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChanceResult.class)
public class ChanceResultMixin {
	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static ItemStack modifyOutput(ItemStack itemStack) {
		return ProcessItemOutputCallback.EVENT.invoker().processOutput(itemStack);
	}
}
