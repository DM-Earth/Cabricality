package com.dm.earth.cabricality.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;

import com.nhoryzon.mc.farmersdelight.recipe.ingredient.ChanceResult;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CuttingBoardRecipe.class)
public class CuttingBoardRecipeMixin {
	@Shadow
	@Final
	private DefaultedList<ChanceResult> resultList;

	@Inject(method = "getOutput", at = @At("HEAD"), cancellable = true)
	private void getOutput(CallbackInfoReturnable<ItemStack> cir) {
		if (this.resultList.size() == 0) cir.setReturnValue(ItemStack.EMPTY);
	}
}
