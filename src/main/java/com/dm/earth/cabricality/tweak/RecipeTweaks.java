package com.dm.earth.cabricality.tweak;

import com.dm.earth.cabricality.Cabricality;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import static com.dm.earth.cabricality.ModEntry.*;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.tweak.cutting.CuttingRecipeTweaks;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingTweaks;
import com.simibubi.create.content.contraptions.components.mixer.CompactingRecipe;
import com.simibubi.create.content.contraptions.processing.HeatCondition;

import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeTweaks implements AddRecipesCallback, RemoveRecipesCallback, ModifyRecipesCallback {

	private static final String[] astra_materials = { "steel", "desh", "ostrum", "calorite", "iron" };
	private static final String[] astra_decor_types = { "pillar", "plating" };

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.addRecipes(handler);
		CuttingRecipeTweaks.register(handler);
		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);

		{
			var matter_ball = AE2.asIngredient("matter_ball");
			handler.register(recipeId("compacting", "matter_plastics"),
					id -> new CompactingRecipe(new FreePRP(id)
							.setIngredient(matter_ball, matter_ball, matter_ball,
									matter_ball, matter_ball,
									matter_ball, matter_ball, matter_ball,
									matter_ball)
							.setResult(CABF.asProcessingOutput("matter_plastics"))
							.setHeatRequirement(HeatCondition.SUPERHEATED)));
		}
		{
			for (String material : astra_materials)
				for (String decor : astra_decor_types)
					handler.register(recipeId("stonecutting", material + "_" + decor),
							id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
									Ingredient.ofTag(TagKey.of(Registry.ITEM_KEY,
											C.id(material + "_plates"))),
									AD.asStack(material + "_" + decor, 2)));
		}
	}

	@Override
	public void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.modifyRecipes(handler);
	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.removeRecipes(handler);
		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);

		handler.remove(TC.id("smeltery/casting/metal/silver/coin_silver_cast"));
		handler.remove(TC.id("smeltery/casting/metal/gold/coin_gold_cast"));

		handler.removeIf(r -> notCabf(r) && r.getOutput().isOf(IR.asItem("controller")));

		for (String material : astra_materials)
			for (String decor : astra_decor_types)
				handler.removeIf(r -> notCabf(r)
						&& r.getOutput().isOf(AD.asItem(material + "_" + decor)));
	}

	public static boolean notCabf(Identifier id) {
		return !id.getNamespace().equals(Cabricality.ID);
	}

	public static boolean notCabf(Recipe<?> recipe) {
		return notCabf(recipe.getId());
	}

	private static Identifier recipeId(String type, String name) {
		return Cabricality.id(type + "/" + name);
	}

}
