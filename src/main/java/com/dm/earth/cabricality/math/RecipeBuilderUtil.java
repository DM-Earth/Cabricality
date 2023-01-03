package com.dm.earth.cabricality.math;

import static com.dm.earth.cabricality.util.JRecipeUtil.fluidEntry;
import static com.dm.earth.cabricality.util.JRecipeUtil.itemEntry;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.content.contraptions.components.crafter.MechanicalCraftingRecipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;

public class RecipeBuilderUtil {
	public static ShapedRecipe donutRecipe(Identifier id, Item center, Item other, Item output, int count) {
		return VanillaRecipeBuilders.shapedRecipe("OOO", "OXO", "OOO").ingredient('X', Ingredient.ofItems(center))
				.ingredient('O', Ingredient.ofItems(other)).output(new ItemStack(output, count)).build(id, "");
	}

	public static MechanicalCraftingRecipe mechanicalFromShaped(ShapedRecipe recipe, boolean acceptMirrored) {
		return new MechanicalCraftingRecipe(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(),
				recipe.getIngredients(), recipe.getOutput(), acceptMirrored);
	}

	public static JsonObject generateMelting(Identifier input, Identifier fluid, long amount, @Nullable Identifier byProduct,
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
}
