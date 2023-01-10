package com.dm.earth.cabricality.tweak.ore_processing;

import static com.dm.earth.cabricality.ModEntry.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dm.earth.cabricality.Cabricality;

import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.dm.earth.cabricality.math.RecipeBuilderUtil;
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
			// Crushed -> Nugget
			handler.register(createId(entry, entry.getCrushedOre(), "smelting"),
					id -> new SmeltingRecipe(id, "",
							Ingredient.ofItems(entry.getCrushedOreItem()),
							new ItemStack(entry.getNuggetItem(), 3), 0.1F, 100));
			handler.register(createId(entry, entry.getCrushedOre(), "blasting"),
					id -> new BlastingRecipe(id, "",
							Ingredient.ofItems(entry.getCrushedOreItem()),
							new ItemStack(entry.getNuggetItem(), 3), 0.1F, 50));
			// Crushed -> Dust
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
							RecipeBuilderUtil.generateMelting(entry.getDust(),
									entry.getMoltenMetal(),
									FluidConstants.NUGGET * 3,
									getByProduct(entry).getMoltenMetal(),
									FluidConstants.NUGGET / 4, 500, 60)));
			// Ingot -> Dust
			handler.register(createId(entry, entry.getIngot(), "crushing"),
					id -> new CrushingRecipe(new FreePRP(id)
							.setIngredient(Ingredient.ofItems(entry.getIngotItem()))
							.setResult(new ProcessingOutput(
									new ItemStack(entry.getDustItem()), 1))
							.setProcessingTime(400)));
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
					&& (cooking.getIngredients().stream()
							.anyMatch(i -> shouldRemoveIngredient(i, entry))
							|| (p.getId().getPath().contains(entry.getId().getPath())))
					&& cooking.getOutput().getItem() == entry.getIngotItem()
					&& RecipeTweaks.notCabf(cooking));
			handler.removeIf(p -> p instanceof ProcessingRecipe<?> recipe
					&& recipe.getIngredients().stream()
							.anyMatch(i -> shouldRemoveIngredient(i, entry))
					&& RecipeTweaks.notCabf(recipe));

			Identifier dustSmelt = TC.id("smeltery/melting/metal/" + entry.getId().getPath() + "/dust");
			handler.remove(dustSmelt);
		}
	}

	private static boolean shouldRemoveIngredient(Ingredient ingredient, @NotNull OreProcessingEntry entry) {
		ArrayList<Item> matchItems = new ArrayList<>();
		matchItems.addAll(List.of(entry.getDustItem(), entry.getCrushedOreItem(), entry.getRawOreItem()));
		matchItems.addAll(entry.getOreItems());
		AtomicBoolean returnValue = new AtomicBoolean(false);
		for (Item item : matchItems) {
			if (Arrays.stream(ingredient.entries).allMatch(entryT -> {
				if (entryT instanceof Ingredient.StackEntry stackEntry
						&& stackEntry.stack.getItem() == item)
					returnValue.set(true);
				if (entryT instanceof Ingredient.TagEntry tagEntry && Registry.ITEM.getTag(tagEntry.tag)
						.stream()
						.anyMatch(set -> set.stream()
								.anyMatch(itemHolder -> itemHolder.value() == item)))
					returnValue.set(true);
				return returnValue.get();
			}))
				return true;
		}
		return false;
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
