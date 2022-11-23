package com.dm.earth.cabricality.tweak.ore_processing;

import static com.dm.earth.cabricality.util.JRecipeUtil.fluidEntry;
import static com.dm.earth.cabricality.util.JRecipeUtil.itemEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.content.contraptions.components.crusher.CrushingRecipe;
import com.simibubi.create.content.contraptions.components.fan.SplashingRecipe;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipe;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("UnstableApiUsage")
public class OreProcessingTweaks {
	public static void register(AddRecipesCallback.RecipeHandler handler) {
		for (OreProcessingEntry entry : OreProcessingEntry.values()) {
			// Crushed -> Dust
			handler.register(createId(entry, entry.getCrushedOre(), "smelting"),
					id -> new SmeltingRecipe(id, "",
							Ingredient.ofItems(entry.getCrushedOreItem()),
							new ItemStack(entry.getDustItem(), 3), 0.25F, 100));
			handler.register(createId(entry, entry.getCrushedOre(), "blasting"),
					id -> new BlastingRecipe(id, "",
							Ingredient.ofItems(entry.getCrushedOreItem()),
							new ItemStack(entry.getDustItem(), 3), 0.25F, 50));
			handler.register(createId(entry, entry.getCrushedOre(), "milling"),
					id -> new MillingRecipe(new FreePRP(id)
							.setIngredient(Ingredient.ofItems(entry.getCrushedOreItem()))
							.setResult(new ProcessingOutput(
									new ItemStack(entry.getDustItem(), 3), 1))
							.setProcessingTime(200)));
			handler.register(createId(entry, entry.getCrushedOre(), "crushing"),
					id -> new CrushingRecipe(
							new FreePRP(id).setIngredient(
									Ingredient.ofItems(entry.getCrushedOreItem()))
									.setResult(new ProcessingOutput(new ItemStack(
											entry.getDustItem(), 3), 1),
											new ProcessingOutput(
													new ItemStack(entry
															.getDustItem(),
															3),
													0.5F))
									.setProcessingTime(200)));
			// Dust -> Nugget
			handler.register(createId(entry, entry.getNugget(), "smelting"),
					id -> new SmeltingRecipe(id, "",
							Ingredient.ofItems(entry.getDustItem()),
							new ItemStack(entry.getNuggetItem()), 0, 40));
			handler.register(createId(entry, entry.getNugget(), "blasting"),
					id -> new BlastingRecipe(id, "",
							Ingredient.ofItems(entry.getDustItem()),
							new ItemStack(entry.getNuggetItem()), 0, 20));
			handler.register(createId(entry, entry.getNugget(), "splashing"),
					id -> new SplashingRecipe(new FreePRP(id)
							.setIngredient(Ingredient.ofItems(entry.getDustItem()))
							.setResult(new ProcessingOutput(
									new ItemStack(entry.getNuggetItem(), 2), 1))));
			// Dust -> Molten Metal
			handler.register(createId(entry, entry.getMoltenMetal(), "melting"),
					id -> RecipeManager.deserialize(id,
							generateMelting(entry.getDust(), entry.getMoltenMetal(), FluidConstants.NUGGET * 3,
									getByProduct(entry).getMoltenMetal(), FluidConstants.NUGGET / 4)));
		}
	}

	public static void register(RemoveRecipesCallback.RecipeHandler handler) {
		for (OreProcessingEntry entry : OreProcessingEntry.values()) {
			handler.removeIf(Registry.RECIPE_TYPE.get(new Identifier("tconstruct", "melting")),
					p -> RecipeTweaks.notCabf(p)
							&& p.getIngredients().stream()
									.anyMatch(i -> shouldRemoveIngredient(i,
											entry)));
			handler.removeIf(p -> p instanceof AbstractCookingRecipe cooking
					&& cooking.getIngredients().stream().anyMatch(i -> shouldRemoveIngredient(i, entry))
					&& cooking.getOutput().getItem() == entry.getIngotItem()
					&& RecipeTweaks.notCabf(cooking));
			handler.removeIf(p -> p instanceof ProcessingRecipe<?> recipe
					&& recipe.getIngredients().stream().anyMatch(i -> shouldRemoveIngredient(i, entry))
					&& RecipeTweaks.notCabf(recipe));
		}
	}

	private static boolean shouldRemoveIngredient(Ingredient ingredient, OreProcessingEntry entry) {
		return Arrays.stream(ingredient.getMatchingStacks())
				.anyMatch(stack -> {
					Item item = stack
							.getItem();
					if (item == entry
							.getDustItem())
						return true;
					if (entry.isTargetOre(
							item))
						return true;
					if (item == entry
							.getCrushedOreItem())
						return true;
					if (item == entry
							.getRawOreItem())
						return true;
					return false;
				});
	}

	private static JsonObject generateMelting(Identifier input, Identifier fluid, long amount, Identifier byProduct,
			long byAmount) {
		JsonObject json = new JsonObject();
		json.addProperty("type", (new Identifier("tconstruct", "melting")).toString());
		json.add("ingredient", itemEntry(input));
		json.add("result", fluidEntry(fluid, amount));
		json.addProperty("temperature", 500);
		json.addProperty("time", 20);
		if (byProduct != null) {
			JsonArray byproducts = new JsonArray();
			byproducts.add(fluidEntry(byProduct, byAmount));
			json.add("byproducts", byproducts);
		}
		return json;
	}

	private static Identifier createId(OreProcessingEntry entry, Identifier input, String type) {
		return Cabricality.id("tweaks/ore_processing/" + entry.getId().getPath() + "/" + type + "/"
				+ input.getPath());
	}

	private static OreProcessingEntry getByProduct(OreProcessingEntry entry) {
		ArrayList<OreProcessingEntry> entries = new ArrayList<>();
		for (OreProcessingEntry entry2 : OreProcessingEntry.values())
			if (entry2 != entry)
				entries.add(entry2);
		Random random = new Random(entry.getHashCode());
		return entries.get(random.nextInt(entries.size()));
	}
}
