package com.dm.earth.cabricality.tweak;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.tweak.cutting.CuttingRecipeTweaks;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingTweaks;

import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;

public class RecipeTweaks implements AddRecipesCallback, RemoveRecipesCallback, ModifyRecipesCallback {

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		CuttingRecipeTweaks.register(handler);
		OreProcessingTweaks.register(handler);
	}

	@Override
	public void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {

	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		OreProcessingTweaks.register(handler);
	}

	public static boolean notCabf(Identifier id) {
		return !id.getNamespace().equals(Cabricality.ID);
	}

	public static boolean notCabf(Recipe<?> recipe) {
		return notCabf(recipe.getId());
	}

}
