package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.util.math.RecipeBuilderUtil;
import com.simibubi.create.content.contraptions.components.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FluixThread implements TechThread {

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(AE2.id("condenser"), 1, AE2.id("fluix_pearl")));
		MechAndSmithCraft.addEntry(entry(AE2.id("drive"), 1, AE2.id("engineering_processor")));
		MechAndSmithCraft.addEntry(entry(AE2.id("formation_core"), 4, AE2.id("logic_processor")));
		MechAndSmithCraft
				.addEntry(entry(AE2.id("annihilation_core"), 4, AE2.id("calculation_processor")));
		MechAndSmithCraft.addEntry(entry(AE2.id("chest"), 1, MC.id("chest")));
	}

	@Override
	public String getLevel() {
		return "fluix";
	}

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		handler.register(recipeId("crafting", "flash_drive"),
				id -> VanillaRecipeBuilders.shapedRecipe("SCA")
						.ingredient('A', TC.asIngredient("cobalt_ingot"))
						.ingredient('C', AE2.asIngredient("logic_processor"))
						.ingredient('S', MC.asIngredient("iron_ingot"))
						.output(CABF.asStack("flash_drive")).build(id, ""));
		handler.register(recipeId("crafting", "controller"),
				id -> RecipeBuilderUtil.donutRecipe(id, CABF.asItem("fluix_casing"),
						CABF.asItem("calculation_mechanism"), AE2.asItem("controller"), 1));

		handler.register(recipeId("stonecutting", "silicon_press"),
				id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
						Ingredient.ofItems(CABF.asItem("circuit_scrap")),
						AE2.asStack("silicon_press")));
		handler.register(recipeId("stonecutting", "engineering_processor_press"),
				id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
						Ingredient.ofItems(CABF.asItem("circuit_scrap")),
						AE2.asStack("engineering_processor_press")));
		handler.register(recipeId("stonecutting", "calculation_processor_press"),
				id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
						Ingredient.ofItems(CABF.asItem("circuit_scrap")),
						AE2.asStack("calculation_processor_press")));
		handler.register(recipeId("stonecutting", "logic_processor_press"),
				id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
						Ingredient.ofItems(CABF.asItem("circuit_scrap")),
						AE2.asStack("logic_processor_press")));
		handler.register(recipeId("crafting", "circuit_scrap"),
				id -> VanillaRecipeBuilders.shapedRecipe(" A ", "ABA", " A ")
						.ingredient('A', CabfItems.INVAR_INGOT)
						.ingredient('B', TagKey.of(Registry.ITEM_KEY, CABF.id("circuit_press")))
						.output(CABF.asStack("circuit_scrap", 2)).build(id, ""));
		handler.register(recipeId("deploying", "printed_silicon"),
				id -> new DeployerApplicationRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(AE2.asItem("silicon")),
								Ingredient.ofItems(AE2.asItem("silicon_press")))
						.keepHeldItem().setResult(AE2.asProcessingOutput("printed_silicon"))));

		handler.register(recipeId("milling", "blizz_cube"),
				id -> new MillingRecipe(
						new FreePRP(id).setIngredient(CABF.asIngredient("blizz_cube"))
								.setResult(CABF.asProcessingOutput("blizz_powder", 1, 2),
										CABF.asProcessingOutput("blizz_powder", 0.5f, 1))
								.setProcessingTime(350)));

								handler.register(recipeId("milling", "basalz_powder"),
								id -> new MillingRecipe(
										new FreePRP(id).setIngredient(CABF.asIngredient("basalz_shard"))
												.setResult(CABF.asProcessingOutput("basalz_powder", 1, 2),
														CABF.asProcessingOutput("basalz_powder", 0.5f, 1))
												.setProcessingTime(350)));
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), AE2.id("controller"), output, count, other);
	}

}
