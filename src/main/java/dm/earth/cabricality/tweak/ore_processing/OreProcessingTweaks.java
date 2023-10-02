package dm.earth.cabricality.tweak.ore_processing;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import dm.earth.cabricality.mixin.StackEntryAccessor;
import dm.earth.cabricality.mixin.TagEntryAccessor;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.fan.SplashingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import me.steven.indrev.recipes.machines.PulverizerRecipe;
import me.steven.indrev.recipes.machines.entries.InputEntry;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.CookingCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static dm.earth.cabricality.ModEntry.CABF;
import static dm.earth.cabricality.ModEntry.TC;

@SuppressWarnings("UnstableApiUsage")
public class OreProcessingTweaks {
	public static void register(AddRecipesCallback.RecipeHandler handler) {
		for (OreProcessingEntry entry : OreProcessingEntry.values()) {
			// Crushed -> Nugget
			handler.register(
					id(entry, entry.getCrushedOre(), "smelting"),
					id -> new SmeltingRecipe(
							id, "",
							CookingCategory.MISC,
							Ingredient.ofItems(entry.getCrushedOreItem()),
							new ItemStack(entry.getNuggetItem(), 3),
							0.1F, 100
					)
			);
			handler.register(
					id(entry, entry.getCrushedOre(), "blasting"),
					id -> new BlastingRecipe(
							id, "",
							CookingCategory.MISC,
							Ingredient.ofItems(entry.getCrushedOreItem()),
							new ItemStack(entry.getNuggetItem(), 3),
							0.1F, 50
					)
			);

			// Crushed -> Dust
			handler.register(
					id(entry, entry.getCrushedOre(), "milling"),
					id -> new MillingRecipe(new FreePRP(id)
							.setIngredient(Ingredient.ofItems(entry.getCrushedOreItem()))
							.setResult(new ProcessingOutput(new ItemStack(entry.getDustItem(), 3), 1))
							.setProcessingTime(200))
			);
			handler.register(
					id(entry, entry.getCrushedOre(), "crushing"),
					id -> new CrushingRecipe(
							new FreePRP(id).setIngredient(
									Ingredient.ofItems(entry.getCrushedOreItem()))
									.setResult(
											new ProcessingOutput(new ItemStack(entry.getDustItem(), 3), 1),
											new ProcessingOutput(new ItemStack(entry.getDustItem(), 3), 0.5F)
									)
									.setProcessingTime(200)
					)
			);

			{
				InputEntry[] inputs = { new InputEntry(Ingredient.ofItems(entry.getCrushedOreItem()), 1) };
				OutputEntry[] outputs = { new OutputEntry(new ItemStack(entry.getDustItem(), 6), 1) };
				handler.register(
						id(entry, entry.getCrushedOre(), "pulverizing"),
						id -> new PulverizerRecipe(id, inputs, outputs, 45)
				);
			}

			// Dust -> Nugget
			handler.register(id(entry, entry.getNugget(), "smelting"),
					id -> new SmeltingRecipe(
							id, "",
							CookingCategory.MISC,
							Ingredient.ofItems(entry.getDustItem()),
							new ItemStack(entry.getNuggetItem()), 0, 40
					)
			);
			handler.register(
					id(entry, entry.getNugget(), "blasting"),
					id -> new BlastingRecipe(id, "",
							CookingCategory.MISC,
							Ingredient.ofItems(entry.getDustItem()),
							new ItemStack(entry.getNuggetItem()), 0, 20
					)
			);
			handler.register(
					id(entry, entry.getNugget(), "splashing"),
					id -> new SplashingRecipe(new FreePRP(id)
							.setIngredient(Ingredient.ofItems(entry.getDustItem()))
							.setResult(new ProcessingOutput(new ItemStack(entry.getNuggetItem(), 2), 1)))
			);

			// Dust -> Molten Metal
			handler.register(
					id(entry, entry.getMoltenMetal(), "melting"),
					id -> RecipeManager.deserialize(id, RecipeBuilderUtil.generateMelting(entry.getDust(),
							entry.getMoltenMetal(),
							FluidConstants.NUGGET * 3,
							getByProduct(entry).getMoltenMetal(),
							FluidConstants.NUGGET / 4, 500, 60))
			);

			// Ingot -> Dust
			handler.register(
					id(entry, entry.getIngot(), "crushing"),
					id -> new CrushingRecipe(new FreePRP(id)
							.setIngredient(Ingredient.ofItems(entry.getIngotItem()))
							.setResult(new ProcessingOutput(new ItemStack(entry.getDustItem()), 1))
							.setProcessingTime(400))
			);
		}
	}

	public static void register(RemoveRecipesCallback.RecipeHandler handler) {
		for (OreProcessingEntry entry : OreProcessingEntry.values()) {
			handler.removeIf(
					Registries.RECIPE_TYPE.get(new Identifier("tconstruct", "melting")),
					p -> !CABF.checkContains(p)
							&& p.getIngredients().stream()
									.anyMatch(i -> shouldRemoveIngredient(i, entry))
			);

			handler.removeIf(p ->
					p instanceof AbstractCookingRecipe cooking
							&& (cooking.getIngredients().stream()
							.anyMatch(i -> shouldRemoveIngredient(i, entry))
							|| (p.getId().getPath().contains(entry.getId().getPath())))
							&& cooking.getResult(handler.getRegistryManager()).getItem() == entry.getIngotItem()
							&& !CABF.checkContains(cooking)
			);

			handler.removeIf(p ->
					p instanceof ProcessingRecipe<?> recipe
							&& recipe.getIngredients().stream()
							.anyMatch(i -> shouldRemoveIngredient(i, entry))
							&& !CABF.checkContains(recipe));

			Identifier dustSmelt = TC.id("smeltery", "melting", "metal", entry.getId().getPath(), "dust");
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
				if (
						entryT instanceof Ingredient.StackEntry stackEntry
								&& ((StackEntryAccessor) stackEntry).getStack().getItem() == item
				) returnValue.set(true);

				if (
						entryT instanceof Ingredient.TagEntry tagEntry
								&& Registries.ITEM.getTag(((TagEntryAccessor) tagEntry).getTag()).stream()
								.anyMatch(set -> set.stream()
								.anyMatch(itemHolder -> itemHolder.value() == item))
				) returnValue.set(true);

				return returnValue.get();
			})) return true;
		}
		return false;
	}

	private static Identifier id(OreProcessingEntry entry, Identifier input, String type) {
		return Cabricality.id("tweaks", "ore_processing", entry.getId().getPath(), type, input.getPath());
	}

	private static OreProcessingEntry getByProduct(OreProcessingEntry entry) {
		ArrayList<OreProcessingEntry> entries = new ArrayList<>();

		for (OreProcessingEntry entry2 : OreProcessingEntry.values())
			if (entry2 != entry) entries.add(entry2);

		Random random = new Random(entry.getHashCode());
		return entries.get(random.nextInt(entries.size()));
	}
}
