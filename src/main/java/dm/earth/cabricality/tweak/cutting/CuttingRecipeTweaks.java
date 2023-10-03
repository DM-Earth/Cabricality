package dm.earth.cabricality.tweak.cutting;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import dm.earth.cabricality.ModEntry;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class CuttingRecipeTweaks {
	public static void register(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		Arrays.stream(WoodCuttingEntry.values()).forEach(entry -> {
			if (entry.isLogExist() && entry.isStrippedLogExist() && entry.getLogId() != null) {
				handler.register(createId(entry.getLogId()),
						id -> createRecipe(id, entry.getLogId(), entry.getStrippedLogId(), 1, 50));
			}

			if (entry.isWoodExist() && entry.isStrippedWoodExist() && entry.getWoodId() != null) {
				handler.register(createId(entry.getWoodId()), id -> createRecipe(id,
						entry.getWoodId(), entry.getStrippedWoodId(), 1, 50));
			}

			if (entry.isStrippedLogExist() && entry.isPlankExist() && entry.getStrippedLogId() != null) {
				handler.register(createId(entry.getStrippedLogId()), id -> createRecipe(id,
						entry.getStrippedLogId(), entry.getPlankId(), 6, 50));
			}

			if (entry.isStrippedWoodExist() && entry.isPlankExist() && entry.getStrippedWoodId() != null) {
				handler.register(createId(entry.getStrippedWoodId()), id -> createRecipe(id,
						entry.getStrippedWoodId(), entry.getPlankId(), 6, 50));
			}

			if (entry.isPlankExist() && entry.isPlankSlabExist() && entry.getPlankId() != null) {
				handler.register(createId(entry.getPlankId()),
						id -> createRecipe(id, entry.getPlankId(), entry.getPlankSlabId(), 2, 50));
			}
		});

		handler.register(createId(ModEntry.TRE.id("small_oak_log")),
				id -> createRecipe(id, ModEntry.TRE.id("small_oak_log"), ModEntry.TRE.id("stripped_small_oak_log"), 1, 50));
		handler.register(createId(ModEntry.TRE.id("stripped_small_oak_log")),
				id -> createRecipe(id, ModEntry.TRE.id("stripped_small_oak_log"), ModEntry.MC.id("oak_planks"), 6, 50));
	}

	private static CuttingRecipe createRecipe(
			Identifier id, Identifier inputId,
			Identifier outputId, int outputCount, int processingTime
	) {
		return new CuttingRecipe(
				new FreePRP(id)
						.setIngredient(Ingredient.ofItems(Registries.ITEM.get(inputId)))
						.setResult(new ProcessingOutput(new ItemStack(Registries.ITEM.get(outputId), outputCount), 1))
						.setProcessingTime(processingTime)
		);
	}

	private static Identifier createId(Identifier input) {
		return Cabricality
				.id("tweaks/wood_cutting/" + input.getNamespace() + "/" + input.getPath());
	}
}
