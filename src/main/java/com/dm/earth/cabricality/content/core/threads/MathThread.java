package com.dm.earth.cabricality.content.core.threads;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback.RecipeHandler;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.math.CalculationRecipe;

public class MathThread implements TechThread {

	@Override
	public String getLevel() {
		return "math";
	}

	@Override
	public void addRecipes(RecipeHandler handler) {
		handler.register(recipeId("crafting", "calculation"), CalculationRecipe::new);;
	}

}
