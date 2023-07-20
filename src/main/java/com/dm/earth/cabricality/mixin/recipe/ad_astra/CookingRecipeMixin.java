package com.dm.earth.cabricality.mixin.recipe.ad_astra;

import com.dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import com.github.alexnijjar.ad_astra.recipes.CookingRecipe;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CookingRecipe.class)
public class CookingRecipeMixin {
	@ModifyVariable(method = "<init>(Lnet/minecraft/util/Identifier;Lnet/minecraft/recipe/Ingredient;Lnet/minecraft/item/ItemStack;S)V", at = @At("HEAD"), argsOnly = true)
	private static ItemStack modifyOutput(ItemStack itemStack) {
		return ProcessItemOutputCallback.process(itemStack);
	}
}
