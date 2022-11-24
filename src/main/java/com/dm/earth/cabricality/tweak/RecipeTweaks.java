package com.dm.earth.cabricality.tweak;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.tweak.cutting.CuttingRecipeTweaks;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingTweaks;

import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;

public class RecipeTweaks implements AddRecipesCallback, RemoveRecipesCallback, ModifyRecipesCallback {

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.addRecipes(handler);
		CuttingRecipeTweaks.register(handler);
		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);
	}

	@Override
	public void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.modifyRecipes(handler);
	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.removeRecipes(handler);
		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);
	}

	public static boolean notCabf(Identifier id) {
		return !id.getNamespace().equals(Cabricality.ID);
	}

	public static boolean notCabf(Recipe<?> recipe) {
		return notCabf(recipe.getId());
	}

}
