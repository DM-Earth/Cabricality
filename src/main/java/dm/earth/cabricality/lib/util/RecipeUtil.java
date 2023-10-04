package dm.earth.cabricality.lib.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingRecipe;
import ho.artisan.lib.recipe.api.builder.VanillaRecipeBuilders;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

public class RecipeUtil {
	public static ShapedRecipe donutRecipe(Identifier id, Item center, Item other, Item output, int count) {
		return VanillaRecipeBuilders.shapedRecipe("OOO", "OXO", "OOO").ingredient('X', Ingredient.ofItems(center))
				.ingredient('O', Ingredient.ofItems(other)).output(new ItemStack(output, count)).build(id, "");
	}

	public static MechanicalCraftingRecipe mechanicalFromShaped(
			Identifier id, DynamicRegistryManager registryManager,
			ShapedRecipe recipe, boolean acceptMirrored
	) {
		return new MechanicalCraftingRecipe(
				id, recipe.getGroup(), recipe.getWidth(), recipe.getHeight(),
				recipe.getIngredients(), recipe.getResult(registryManager), acceptMirrored
		);
	}

	public static JsonObject generateMelting(
			Identifier input, Identifier fluid,
			long amount,
			@Nullable Identifier byproduct,
			long byAmount, int temperature, int time
	) {
		JsonObject json = new JsonObject();

		json.addProperty("type", (new Identifier("tconstruct", "melting")).toString());
		json.add("ingredient", JsonBuilder.itemEntry(input));
		json.add("result", JsonBuilder.fluidEntry(fluid, amount));
		json.addProperty("temperature", temperature);
		json.addProperty("time", time);
		if (byproduct != null) {
			JsonArray byproducts = new JsonArray();
			byproducts.add(JsonBuilder.fluidEntry(byproduct, byAmount));
			json.add("byproducts", byproducts);
		}
		return json;
	}

	public static Recipe<?> swapRecipeOutput(
			Recipe<?> recipe,
			ItemStack output, CraftingCategory category
	) {
		if (recipe instanceof ShapelessRecipe)
			return swapShapelessRecipeOutput((ShapelessRecipe) recipe, output, category);
		if (recipe instanceof ShapedRecipe)
			return swapShapedRecipeOutput((ShapedRecipe) recipe, output, category);
		return recipe;
	}

	@Deprecated
	public static Recipe<?> swapRecipeIngredient(
			DynamicRegistryManager registryManager, Recipe<?> recipe,
			Ingredient from, Ingredient to,
			CraftingCategory category
	) {
		if (recipe instanceof ShapelessRecipe)
			return swapShapelessRecipeIngredient(registryManager, (ShapelessRecipe) recipe, from, to, category);
		if (recipe instanceof ShapedRecipe)
			return swapShapedRecipeIngredient(registryManager, (ShapedRecipe) recipe, from, to, category);
		return recipe;
	}

	public static ShapelessRecipe swapShapelessRecipeOutput(
			ShapelessRecipe recipe,
			ItemStack output, CraftingCategory category
	) {
		return new ShapelessRecipe(recipe.getId(), recipe.getGroup(), category, output, recipe.getIngredients());
	}

	@Deprecated
	public static ShapelessRecipe swapShapelessRecipeIngredient(
			DynamicRegistryManager registryManager, ShapelessRecipe recipe,
			Ingredient from, Ingredient to, CraftingCategory category
	) {
		DefaultedList<Ingredient> ingredients = recipe.getIngredients();
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredients.get(i).equals(from)) {
				ingredients.set(i, to);
			}
		}
		return new ShapelessRecipe(
				recipe.getId(), recipe.getGroup(), category,
				recipe.getResult(registryManager), ingredients
		);
	}

	public static ShapedRecipe swapShapedRecipeOutput(
			ShapedRecipe recipe, ItemStack output,
			CraftingCategory category
	) {
		return new ShapedRecipe(
				recipe.getId(), recipe.getGroup(),
				category,
				recipe.getWidth(), recipe.getHeight(),
				recipe.getIngredients(), output
		);
	}

	@Deprecated
	public static ShapedRecipe swapShapedRecipeIngredient(
			DynamicRegistryManager registryManager, ShapedRecipe recipe,
			Ingredient from, Ingredient to,
			CraftingCategory category
	) {
		DefaultedList<Ingredient> ingredients = recipe.getIngredients();
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredients.get(i).equals(from)) {
				ingredients.set(i, to);
			}
		}

		return new ShapedRecipe(
				recipe.getId(), recipe.getGroup(),
				category,
				recipe.getWidth(), recipe.getHeight(),
				ingredients, recipe.getResult(registryManager)
		);
	}

	public static class JsonBuilder {
		public static JsonObject itemEntry(Identifier id, int count) {
			JsonObject json = new JsonObject();
			json.addProperty("item", id.toString());
			json.addProperty("count", count);
			return json;
		}

		public static JsonObject itemEntry(Identifier id) {
			JsonObject json = new JsonObject();
			json.addProperty("item", id.toString());
			return json;
		}

		public static JsonObject itemEntry(Identifier id, int count, double chance) {
			JsonObject json = itemEntry(id, count);
			json.addProperty("chance", chance);
			return json;
		}

		public static JsonObject fluidEntry(Identifier id, int amount) {
			JsonObject json = new JsonObject();
			json.addProperty("fluid", id.toString());
			json.addProperty("amount", amount);
			return json;
		}

		public static JsonObject fluidEntry(Identifier id, long amount) {
			return fluidEntry(id, (int) amount);
		}
	}
}
