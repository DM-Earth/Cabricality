package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import com.simibubi.create.content.fluids.transfer.EmptyingRecipe;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import com.dm.earth.cabricality.lib.resource.data.core.FreePRP;
import com.dm.earth.cabricality.lib.util.ArrayUtil;
import com.dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.kinetics.fan.SplashingRecipe;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;

import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import me.steven.indrev.recipes.machines.FluidInfuserRecipe;
import me.steven.indrev.recipes.machines.entries.InputEntry;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
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

	@SuppressWarnings("UnstableApiUsage")
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
						.output(CABF.asStack(2, "circuit_scrap")).build(id, ""));

		handler.register(recipeId("deploying", "printed_silicon"),
				id -> new DeployerApplicationRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(AE2.asItem("silicon")),
								Ingredient.ofItems(AE2.asItem("silicon_press")))
						.keepHeldItem().setResult(AE2.asProcessingOutput("printed_silicon"))));

		handler.register(recipeId("crushing", "blizz_cube"),
				id -> new CrushingRecipe(
						new FreePRP(id).setIngredient(CABF.asIngredient("blizz_cube"))
								.setResult(CABF.asProcessingOutput(1, 2, "blizz_powder"),
										CABF.asProcessingOutput(0.5F, 1, "blizz_powder"))
								.setProcessingTime(350)));

		handler.register(recipeId("crushing", "basalz_shard"),
				id -> new CrushingRecipe(
						new FreePRP(id).setIngredient(CABF.asIngredient("basalz_shard"))
								.setResult(CABF.asProcessingOutput(1, 2, "basalz_powder"),
										CABF.asProcessingOutput(0.5F, 1, "basalz_powder"))
								.setProcessingTime(350)));

		var blizz = CABF.asIngredient("blizz_powder");
		var basalz = CABF.asIngredient("basalz_powder");
		handler.register(recipeId("splashing", "sandstone"),
				id -> new SplashingRecipe(
						new FreePRP(id).setIngredient(MC.asIngredient("sandstone"))
								.setResult(CABF.asProcessingOutput(0.65F, "sand_ball"))));

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
				.setIngredient(CABF.asIngredient("silicon_compound"), CABF.asIngredient("ice_charge"))
				.setResult(AE2.asProcessingOutput("silicon"))
				.setHeatRequirement(HeatCondition.HEATED)));

		handler.register(recipeId("item_application", "fluix_casing"),
				id -> new ManualApplicationRecipe(new FreePRP(id)
						.setIngredient(MC.asIngredient("obsidian"),
								AE2.asIngredient("fluix_crystal"))
						.setResult(CABF.asProcessingOutput("fluix_casing"))));

		handler.register(recipeId("fluid_infuse", "snow"),
				id -> new FluidInfuserRecipe(id, ArrayUtil.of(new InputEntry(MC.asIngredient("ice"), 0)),
						ArrayUtil.of(new OutputEntry(MC.asStack("snowball"), 0.5)),
						ArrayUtil.of(new ResourceAmount<>(FluidVariant.of(Fluids.WATER), FluidConstants.BOTTLE)),
						ArrayUtil.<ResourceAmount<FluidVariant>>of(), 120));
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), AE2.id("controller"), output, count, other);
	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		handler.removeIf(AE2.predicateOutput(false, "silicon"));
		handler.removeIf(AE2.predicateOutput(false, "controller"));
	}
}
