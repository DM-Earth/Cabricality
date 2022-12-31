package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.*;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback.RecipeHandler;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.math.CalculationRecipe;

public class MathThread implements TechThread {

	@Override
	public String getLevel() {
		return "math";
	}

	@Override
	public void addRecipes(RecipeHandler handler) {
		handler.register(recipeId("crafting", "calculation"), CalculationRecipe::new);
		CabfItems.MATH_CASTS
				.forEach(str -> handler.register(recipeId("stonecutting", str + "_cast"),
						id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
								CR.asIngredient("copper_sheet"), CABF.asStack(str + "_cast"))));
	}

}
