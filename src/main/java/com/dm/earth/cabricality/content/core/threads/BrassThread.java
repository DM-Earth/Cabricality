package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.util.math.ListUtil;
import com.dm.earth.cabricality.util.math.RecipeBuilderUtil;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import com.simibubi.create.content.contraptions.components.mixer.MixingRecipe;
import com.simibubi.create.content.contraptions.fluids.actors.FillingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("UnstableApiUsage")
public class BrassThread implements TechThread {
	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		handler.register(recipeId("milling", "sky_stone_block"),
				id -> new MillingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(AE2.asItem("sky_stone_block")))
						.setResult(
								new ProcessingOutput(
										AE2.asItem("sky_stone_block").getDefaultStack(), 1),
								new ProcessingOutput(AE2.asItem("sky_dust").getDefaultStack(), 1))
						.setProcessingTime(350)));

		Item redstone = Items.REDSTONE;
		handler.register(recipeId("crafting", "rose_quartz"),
				id -> VanillaRecipeBuilders
						.shapelessRecipe(CR.asItem("rose_quartz").getDefaultStack())
						.ingredient(Items.QUARTZ, AE2.asItem("certus_quartz_crystal"),
								AE2.asItem("charged_certus_quartz_crystal"))
						.ingredient(redstone).ingredient(redstone).ingredient(redstone)
						.build(id, ""));

		registerCrystalProcess(handler, AE2.id("certus_quartz_crystal"),
				AE2.id("certus_crystal_seed"), AE2.id("fluix_dust"), MC.id("water"));
		registerCrystalProcess(handler, AE2.id("fluix_crystal"), AE2.id("fluix_crystal_seed"),
				AE2.id("fluix_dust"), CABF.id("waste"));

		handler.register(recipeId("mixing", "sky_stone"), id -> new MixingRecipe(new FreePRP(id)
				.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE))
				.setIngredient(ListUtil.loop(Ingredient.ofItems(AE2.asItem("sky_dust")), 3))
				.setFluidResult(new FluidStack(FluidVariant.of(CabfFluids.SKY_STONE),
						FluidConstants.BOTTLE))));

		handler.register(recipeId("mixing", "redstone"), id -> new MixingRecipe(new FreePRP(id)
				.setFluidIngredient(
						FluidIngredient.fromFluid(CabfFluids.SKY_STONE, FluidConstants.INGOT * 2))
				.setIngredient(Ingredient.ofItems(AE2.asItem("charged_certus_quartz_crystal")))
				.setFluidResult(new FluidStack(FluidVariant.of(CabfFluids.REDSTONE),
						FluidConstants.INGOT * 2))
				.setResult(new ProcessingOutput(
						AE2.asItem("certus_quartz_crystal").getDefaultStack(), 1))));

		handler.register(recipeId("mixing", "polished_rose_quartz"),
				id -> new MixingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.REDSTONE,
								FluidConstants.INGOT))
						.setIngredient(Ingredient.ofItems(AE2.asItem("certus_quartz_crystal")))
						.setResult(new ProcessingOutput(
								CR.asItem("polished_rose_quartz").getDefaultStack(), 1))));

		handler.register(recipeId("filling", "electron_tube"),
				id -> new FillingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(CR.asItem("polished_rose_quartz")))
						.setFluidIngredient(FluidIngredient.fromFluid(TC.asFluid("molten_iron"),
								FluidConstants.NUGGET))
						.setResult(new ProcessingOutput(
								CR.asItem("electron_tube").getDefaultStack(), 1))));

		handler.register(recipeId("crafting", "brass_machine"),
				id -> RecipeBuilderUtil.donutRecipe(id, CR.asItem("brass_casing"),
						CR.asItem("precision_mechanism"), CABF.asItem("brass_machine"), 1));
	}

	private void registerCrystalProcess(AddRecipesCallback.RecipeHandler handler,
			Identifier crystal, Identifier seed, Identifier dust, Identifier fluid) {
		Item crystalItem = Registry.ITEM.get(crystal);
		Item seedItem = Registry.ITEM.get(seed);
		Item dustItem = Registry.ITEM.get(dust);

		handler.register(recipeId("milling", crystal.getPath()),
				id -> new MillingRecipe(
						new FreePRP(id).setIngredient(Ingredient.ofItems(crystalItem))
								.setResult(new ProcessingOutput(dustItem.getDefaultStack(), 1))
								.setProcessingTime(200)));

		handler.register(recipeId("mechanical_crafting", seed.getPath()),
				id -> RecipeBuilderUtil.mechanicalFromShaped(
						VanillaRecipeBuilders.shapedRecipe("x").ingredient('x', crystalItem)
								.output(seedItem.getDefaultStack()).build(id, ""),
						false));
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		handler.removeIf(
				p -> RecipeTweaks.notCabf(p) && p.getOutput().isOf(AE2.asItem("sky_dust")));
		handler.remove(CR.id("crafting/materials/electron_tube"));
		handler.remove(CR.id("crafting/materials/rose_quartz"));
		handler.remove(CR.id("sequenced_assembly/precision_mechanism"));
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(CR.id("mechanical_crafter"), 3, MC.id("crafting_table")));
		MechAndSmithCraft.addEntry(entry(CR.id("sequenced_gearshift"), 2, null));
		MechAndSmithCraft.addEntry(entry(CR.id("rotation_speed_controller"), 1, null));
		MechAndSmithCraft.addEntry(entry(CR.id("mechanical_arm"), 1, null));
		MechAndSmithCraft.addEntry(entry(CR.id("stockpile_switch"), 2, null));
		MechAndSmithCraft.addEntry(entry(CR.id("content_observer"), 2, null));
		MechAndSmithCraft.addEntry(entry(IR.id("solid_infuser_mk1"), 1, MC.id("dropper")));
		MechAndSmithCraft.addEntry(entry(IR.id("biomass_generator_mk3"), 1, IR.id("heat_coil")));
		MechAndSmithCraft.addEntry(entry(CR.id("brass_funnel"), 4, null));
		MechAndSmithCraft.addEntry(entry(CR.id("brass_tunnel"), 4, null));
	}

	@Override
	public String getLevel() {
		return "brass";
	}

	@Contract("_, _, _ -> new")
	private @NotNull MechAndSmithCraft.Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("brass_machine"), output, count,
				other);
	}
}
