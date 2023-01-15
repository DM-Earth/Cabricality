package com.dm.earth.cabricality.tweak.core;

import java.util.ArrayList;

import com.dm.earth.cabricality.Cabricality;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.dm.earth.cabricality.math.RecipeBuilderUtil;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MechAndSmithCraft {
	private static final ArrayList<Entry> entries = new ArrayList<>();

	/**
	 * Add a MechAndSmithCraft entry for the given item.
	 *
	 * @param entry the entry to add
	 */
	public static void addEntry(Entry entry) {
		entries.add(entry);
	}

	public static void register(AddRecipesCallback.RecipeHandler handler) {
		entries.forEach(entry -> {
			if (entry.isSmithing()) {
				handler.register(createId(entry, "smithing"),
						id -> new SmithingRecipe(id, Ingredient.ofItems(entry.getInputItem()),
								Ingredient.ofItems(entry.getOtherItem()), entry.getOutputStack()));
				handler.register(createId(entry, "mechanical_crafting"),
						id -> RecipeBuilderUtil
								.mechanicalFromShaped(
										VanillaRecipeBuilders.shapedRecipe("AB")
												.ingredient('A',
														Ingredient.ofItems(entry.getInputItem()))
												.ingredient('B',
														Ingredient.ofItems(entry.getOtherItem()))
												.output(entry.getOutputStack()).build(id, ""),
										true));
			} else
				handler.register(createId(entry, "stonecutting"), id -> new StonecuttingRecipe(id,
						"", Ingredient.ofItems(entry.getInputItem()), entry.getOutputStack()));
		});
	}

	public static void register(RemoveRecipesCallback.RecipeHandler handler) {
		entries.forEach(entry -> handler.removeIf(
				p -> RecipeTweaks.notCabf(p) && p.getOutput().isOf(entry.getOutputItem())));
	}

	private static Identifier createId(Entry entry, String type) {
		return Cabricality
				.id("threads/" + entry.level() + "/tweak/" + type + "/" + entry.output().getPath());
	}

	public static Entry entry(String level, Identifier input, Identifier output, int count,
			@Nullable Identifier other) {
		return new Entry(level, input, output, count, other);
	}

	public record Entry(String level, Identifier input, Identifier output, int count,
			@Nullable Identifier other) {
		public boolean isSmithing() {
			return other != null;
		}

		public Item getInputItem() {
			return Registry.ITEM.get(input);
		}

		public Item getOutputItem() {
			return Registry.ITEM.get(output);
		}

		public Item getOtherItem() {
			return Registry.ITEM.get(other);
		}

		public ItemStack getOutputStack() {
			return new ItemStack(getOutputItem(), count);
		}
	}
}
