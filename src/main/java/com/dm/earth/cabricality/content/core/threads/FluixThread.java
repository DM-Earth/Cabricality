package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.math.RecipeBuilderUtil;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.simibubi.create.content.contraptions.components.crusher.CrushingRecipe;
import com.simibubi.create.content.contraptions.components.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.contraptions.components.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.contraptions.components.fan.SplashingRecipe;
import com.simibubi.create.content.contraptions.components.mixer.CompactingRecipe;
import com.simibubi.create.content.contraptions.components.mixer.MixingRecipe;
import com.simibubi.create.content.contraptions.fluids.actors.FillingRecipe;
import com.simibubi.create.content.contraptions.processing.EmptyingRecipe;
import com.simibubi.create.content.contraptions.processing.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.fluid.Fluids;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
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

		handler.register(recipeId("crushing", "blizz_cube"),
				id -> new CrushingRecipe(
						new FreePRP(id).setIngredient(CABF.asIngredient("blizz_cube"))
								.setResult(CABF.asProcessingOutput("blizz_powder", 1, 2),
										CABF.asProcessingOutput("blizz_powder", 0.5f, 1))
								.setProcessingTime(350)));

		handler.register(recipeId("crushing", "basalz_shard"),
				id -> new CrushingRecipe(
						new FreePRP(id).setIngredient(CABF.asIngredient("basalz_shard"))
								.setResult(CABF.asProcessingOutput("basalz_powder", 1, 2),
										CABF.asProcessingOutput("basalz_powder", 0.5f, 1))
								.setProcessingTime(350)));

		var blizz = CABF.asIngredient("blizz_powder");
		var basalz = CABF.asIngredient("basalz_powder");
		handler.register(recipeId("splashing", "sandstone"),
				id -> new SplashingRecipe(
						new FreePRP(id).setIngredient(MC.asIngredient("sandstone"))
								.setResult(CABF.asProcessingOutput("sand_ball", 0.65f))));
		handler.register(recipeId("compacting", "ice_charge"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setIngredient(blizz, blizz, blizz, blizz, blizz, blizz, blizz, blizz)
						.setResult(CABF.asProcessingOutput("ice_charge"))));
		handler.register(recipeId("compacting", "earth_charge"),
				id -> new CompactingRecipe(
						new FreePRP(id)
								.setIngredient(basalz, basalz, basalz, basalz, basalz, basalz,
										basalz, basalz)
								.setResult(CABF.asProcessingOutput("earth_charge"))));

		// Coke Processing
		handler.register(recipeId("compacting", "coal"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofTag(ItemTags.COALS))
						.setFluidResult(new FluidStack(CabfFluids.COKE, FluidConstants.INGOT))
						.setHeatRequirement(HeatCondition.HEATED)));
		handler.register(recipeId("filling", "coal"),
				id -> new FillingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofTag(ItemTags.COALS))
						.setFluidIngredient(
								FluidIngredient.fromFluid(CabfFluids.COKE, FluidConstants.BOTTLE))
						.setResult(CABF.asProcessingOutput("coal_coke"))));
		handler.register(recipeId("compacting", "coal_coke"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setIngredient(CABF.asIngredient("coal_coke"))
						.setFluidIngredient(
								FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.INGOT))
						.setResult(MC.asProcessingOutput("coal"))));

		handler.register(recipeId("emptying", "sand_ball"),
				id -> new EmptyingRecipe(new FreePRP(id)
						.setIngredient(CABF.asIngredient("sand_ball"))
						.setResult(CABF.asProcessingOutput("rough_sand")).setFluidResult(
								new FluidStack(CabfFluids.FINE_SAND, FluidConstants.BUCKET / 2))));
		handler.register(recipeId("compacting", "silicon_compound"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setIngredient(CABF.asIngredient("purified_sand"),
								CABF.asIngredient("coke_chunk"))
						.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.FINE_SAND,
								FluidConstants.BOTTLE))
						.setResult(CABF.asProcessingOutput("silicon_compound"))));

		handler.register(recipeId("mixing", "purified_sand"), id -> new MixingRecipe(new FreePRP(id)
				.setIngredient(CABF.asIngredient("rough_sand"), CABF.asIngredient("earth_charge"))
				.setResult(CABF.asProcessingOutput("purified_sand"))
				.setHeatRequirement(HeatCondition.HEATED)));
		handler.register(recipeId("mixing", "silicon"), id -> new MixingRecipe(new FreePRP(id)
				.setIngredient(CABF.asIngredient("purified_sand"), CABF.asIngredient("ice_charge"))
				.setResult(AE2.asProcessingOutput("silicon"))
				.setHeatRequirement(HeatCondition.HEATED)));

		handler.register(recipeId("item_application", "fluix_casing"),
				id -> new ManualApplicationRecipe(new FreePRP(id)
						.setIngredient(MC.asIngredient("obsidian"),
								AE2.asIngredient("fluix_crystal"))
						.setResult(CABF.asProcessingOutput("fluix_casing"))));
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), AE2.id("controller"), output, count, other);
	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		handler.removeIf(r -> RecipeTweaks.notCabf(r) && r.getOutput().isOf(AE2.asItem("silicon")));
		handler.removeIf(
				r -> RecipeTweaks.notCabf(r) && r.getOutput().isOf(AE2.asItem("controller")));
	}

}
