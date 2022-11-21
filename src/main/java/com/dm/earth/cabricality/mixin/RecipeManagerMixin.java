package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.listener.DeployerCuttingRecipeHandler;
import com.google.gson.JsonObject;
import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
	@Inject(method = "deserialize", at = @At("RETURN"))
	private static void read(Identifier id, JsonObject json, CallbackInfoReturnable<Recipe<?>> cir) {
		if (cir.getReturnValue() instanceof CuttingBoardRecipe boardRecipe)
			DeployerCuttingRecipeHandler.cuttingBoardRecipes.add(boardRecipe);
	}
}
