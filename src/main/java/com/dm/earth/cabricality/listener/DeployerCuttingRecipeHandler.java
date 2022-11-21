package com.dm.earth.cabricality.listener;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;
import com.nhoryzon.mc.farmersdelight.recipe.ingredient.ChanceResult;
import com.simibubi.create.content.contraptions.components.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class DeployerCuttingRecipeHandler {
	public static ArrayList<CuttingBoardRecipe> cuttingBoardRecipes = new ArrayList<>();

	private static void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		for (CuttingBoardRecipe boardRecipe : cuttingBoardRecipes) {
			Identifier id = Cabricality.id("cutting/auto/" + String.valueOf(boardRecipe.hashCode()).replaceAll("-", "x"));
			ArrayList<ProcessingOutput> outputs = new ArrayList<>();
			for (ChanceResult chanceResult : boardRecipe.getRollableResults())
				outputs.add(new ProcessingOutput(chanceResult.stack(), chanceResult.chance()));
			FreePRP params = new FreePRP(id)
					.setIngredient(boardRecipe.getTool())
					.setIngredient(boardRecipe.getIngredients())
					.setResult(outputs);
			if (boardRecipe.getIngredients().stream().anyMatch(ingredient -> Arrays.stream(ingredient.getMatchingStacks()).anyMatch(stack -> Registry.ITEM.getId(stack.getItem()).getPath().contains("slime_fern"))))
				params.keepHeldItem();
			DeployerApplicationRecipe recipe = new DeployerApplicationRecipe(params);
			handler.register(id, identifier -> recipe);
		}
	}

	public static void load() {
		RecipeManagerHelper.addRecipes(DeployerCuttingRecipeHandler::addRecipes);
	}
}
