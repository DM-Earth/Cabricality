package com.dm.earth.cabricality.tweak.cutting;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.simibubi.create.content.contraptions.components.saw.CuttingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CuttingRecipeTweaks {
    public static void register(AddRecipesCallback.RecipeHandler handler) {
        for (WoodCuttingEntry entry : WoodCuttingEntry.values()) {
            if (entry.isLogExist() && entry.isStrippedLogExist())
                handler.register(createId(entry.getLogId()),
                        id -> createRecipe(id, entry.getLogId(), entry.getStrippedLogId(), 1, 50));
            if (entry.isWoodExist() && entry.isStrippedWoodExist())
                handler.register(createId(entry.getWoodId()),
                        id -> createRecipe(id, entry.getWoodId(), entry.getStrippedWoodId(), 1, 50));
            if (entry.isStrippedLogExist() && entry.isPlankExist())
                handler.register(createId(entry.getStrippedLogId()),
                        id -> createRecipe(id, entry.getStrippedLogId(), entry.getPlankId(), 6, 50));
            if (entry.isStrippedWoodExist() && entry.isPlankExist())
                handler.register(createId(entry.getStrippedWoodId()),
                        id -> createRecipe(id, entry.getStrippedWoodId(), entry.getPlankId(), 6, 50));
            if (entry.isPlankExist() && entry.isPlankSlabExist())
                handler.register(createId(entry.getPlankId()),
                        id -> createRecipe(id, entry.getPlankId(), entry.getPlankSlabId(), 2, 50));
        }
    }

    private static CuttingRecipe createRecipe(Identifier id, Identifier inputId, Identifier outputId, int outputCount,
            int processingTime) {
        return new CuttingRecipe((new FreePRP(id)).setIngredient(Ingredient.ofItems(Registry.ITEM.get(inputId)))
                .setResult(new ProcessingOutput(new ItemStack(Registry.ITEM.get(outputId), outputCount), 1))
                .setProcessingTime(processingTime));
    }

    private static Identifier createId(Identifier input) {
        return Cabricality.id("tweaks/wood_cutting/" + input.getNamespace() + "/" + input.getPath());
    }
}
