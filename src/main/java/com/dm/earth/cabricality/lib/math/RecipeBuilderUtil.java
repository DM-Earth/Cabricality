package com.dm.earth.cabricality.lib.math;

import static com.dm.earth.cabricality.lib.util.JRecipeUtil.fluidEntry;
import static com.dm.earth.cabricality.lib.util.JRecipeUtil.itemEntry;

import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingRecipe;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class RecipeBuilderUtil {
	public static ShapedRecipe donutRecipe(Identifier id, Item center, Item other, Item output, int count) {
		return VanillaRecipeBuilders.shapedRecipe("OOO", "OXO", "OOO").ingredient('X', Ingredient.ofItems(center))
				.ingredient('O', Ingredient.ofItems(other)).output(new ItemStack(output, count)).build(id, "");
	}

	public static MechanicalCraftingRecipe mechanicalFromShaped(ShapedRecipe recipe, boolean acceptMirrored) {
		return new MechanicalCraftingRecipe(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(),
				recipe.getIngredients(), recipe.getOutput(), acceptMirrored);
	}

	public static JsonObject generateMelting(Identifier input, Identifier fluid, long amount,
			@Nullable Identifier byProduct,
			long byAmount, int temperature, int time) {
		JsonObject json = new JsonObject();
		json.addProperty("type", (new Identifier("tconstruct", "melting")).toString());
		json.add("ingredient", itemEntry(input));
		json.add("result", fluidEntry(fluid, amount));
		json.addProperty("temperature", temperature);
		json.addProperty("time", time);
		if (byProduct != null) {
			JsonArray byproducts = new JsonArray();
			byproducts.add(fluidEntry(byProduct, byAmount));
			json.add("byproducts", byproducts);
		}
		return json;
	}

	public static Recipe<?> swapRecipeOutput(Recipe<?> recipe, ItemStack output) {
		if (recipe instanceof ShapelessRecipe)
			return swapShapelessRecipeOutput((ShapelessRecipe) recipe, output);
		if (recipe instanceof ShapedRecipe)
			return swapShapedRecipeOutput((ShapedRecipe) recipe, output);
		return recipe;
	}

	@Deprecated
	public static Recipe<?> swapRecipeIngredient(Recipe<?> recipe, Ingredient from, Ingredient to) {
		if (recipe instanceof ShapelessRecipe)
			return swapShapelessRecipeIngredient((ShapelessRecipe) recipe, from, to);
		if (recipe instanceof ShapedRecipe)
			return swapShapedRecipeIngredient((ShapedRecipe) recipe, from, to);
		return recipe;
	}

	public static ShapelessRecipe swapShapelessRecipeOutput(ShapelessRecipe recipe, ItemStack output) {
		return new ShapelessRecipe(recipe.getId(), recipe.getGroup(), output, recipe.getIngredients());
	}

	@Deprecated
	public static ShapelessRecipe swapShapelessRecipeIngredient(ShapelessRecipe recipe, Ingredient from,
			Ingredient to) {
		DefaultedList<Ingredient> ingredients = recipe.getIngredients();
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredients.get(i).equals(from)) {
				ingredients.set(i, to);
			}
		}
		return new ShapelessRecipe(recipe.getId(), recipe.getGroup(), recipe.getOutput(), ingredients);
	}

	public static ShapedRecipe swapShapedRecipeOutput(ShapedRecipe recipe, ItemStack output) {
		return new ShapedRecipe(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(),
				recipe.getIngredients(), output);
	}

	@Deprecated
	public static ShapedRecipe swapShapedRecipeIngredient(ShapedRecipe recipe, Ingredient from, Ingredient to) {
		DefaultedList<Ingredient> ingredients = recipe.getIngredients();
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredients.get(i).equals(from)) {
				ingredients.set(i, to);
			}
		}
		return new ShapedRecipe(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), ingredients,
				recipe.getOutput());
	}
}
