package com.dm.earth.cabricality.mixin.recipe;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ShapedRecipe.class)
public class ShapedRecipeMixin {
	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static ItemStack modifyOutput(ItemStack itemStack) {
		return ProcessItemOutputCallback.process(itemStack);
	}
}
