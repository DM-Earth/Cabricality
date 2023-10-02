package dm.earth.cabricality.tweak.base;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.ModEntry;
import dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.recipe.TransformSmithingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import java.util.ArrayList;

public class MechAndSmithCraft {
	private static final ArrayList<Entry> entries = new ArrayList<>();

	/**
	 * Add a MechAndSmithCraft entry for the given item.
	 * @param entry the entry to add
	 */
	public static void addEntry(Entry entry) {
		entries.add(entry);
	}

	public static void register(AddRecipesCallback.RecipeHandler handler) {
		/*
		entries.forEach(entry -> {
			if (entry.isSmithing()) {
				handler.register(
						id(entry, "smithing"),
						id -> new TransformSmithingRecipe(
								Ingredient.EMPTY,
								Ingredient.ofItems(entry.getInputItem()),
								Ingredient.ofItems(entry.getOtherItem()),
								entry.getOutputStack())
				);
				handler.register(
						id(entry, "mechanical_crafting"),
						id -> RecipeBuilderUtil.mechanicalFromShaped(VanillaRecipeBuilders
										.shapedRecipe("AB")
										.ingredient('A', Ingredient.ofItems(entry.getInputItem()))
										.ingredient('B', Ingredient.ofItems(entry.getOtherItem()))
										.output(entry.getOutputStack())
										.build(id, ""),
								true)
				);
			} else
				handler.register(
						id(entry, "stonecutting"),
						id -> new StonecuttingRecipe("",
								Ingredient.ofItems(entry.getInputItem()),
								entry.getOutputStack())
				);
		});

		 */
	}

	public static void register(RemoveRecipesCallback.RecipeHandler handler) {
		entries.forEach(entry -> handler.removeIf(p ->
				!ModEntry.CABF.checkContains(handler, p)
						&& p.getResult(handler.getRegistryManager()).isOf(entry.getOutputItem())
		));
	}

	private static Identifier id(Entry entry, String type) {
		return Cabricality.id("threads", entry.level(), "tweak", type, entry.output().getPath());
	}

	public static Entry entry(String level, Identifier input, Identifier output, int count, @Nullable Identifier other) {
		return new Entry(level, input, output, count, other);
	}

	public record Entry(String level, Identifier input, Identifier output, int count, @Nullable Identifier other) {
		public boolean isSmithing() {
			return other != null;
		}

		public Item getInputItem() {
			return Registries.ITEM.get(input);
		}

		public Item getOutputItem() {
			return Registries.ITEM.get(output);
		}

		public Item getOtherItem() {
			return Registries.ITEM.get(other);
		}

		public ItemStack getOutputStack() {
			return new ItemStack(getOutputItem(), count);
		}
	}
}
