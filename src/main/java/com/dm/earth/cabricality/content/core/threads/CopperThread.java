package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.CX;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.KB;
import static com.dm.earth.cabricality.ModEntry.MC;

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
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.simibubi.create.content.contraptions.components.mixer.CompactingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import com.simibubi.create.foundation.fluid.FluidIngredient;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

@SuppressWarnings("UnstableApiUsage")
public class CopperThread implements TechThread {

	@Override
	public void removeRecipes(RemoveRecipesCallback.@NotNull RecipeHandler handler) {
		handler.remove(CR.id("crafting/kinetics/belt_connector"));
	}

	@Override
	public void addRecipes(AddRecipesCallback.@NotNull RecipeHandler handler) {
		handler.register(recipeId("crafting", "belt_connector"),
				id -> VanillaRecipeBuilders.shapedRecipe("XXX", "XXX")
						.ingredient('X', CabfItems.CURED_RUBBER)
						.output(new ItemStack(CR.asItem("belt_connector"), 3)).build(id, ""));

		handler.register(recipeId("smelting", "cured_rubber"),
				id -> VanillaRecipeBuilders.smeltingRecipe(id, "",
						Ingredient.ofItems(CabfItems.RUBBER),
						CabfItems.CURED_RUBBER.getDefaultStack(), 0.1F, 120));

		handler.register(recipeId("crafting", "sealed_mechanism"),
				id -> VanillaRecipeBuilders.shapedRecipe("XOX")
						.ingredient('X', Ingredient.ofItems(CabfItems.CURED_RUBBER))
						.ingredient('O', Ingredient.ofItems(CABF.asItem("kinetic_mechanism")))
						.output(CABF.asItem("sealed_mechanism").getDefaultStack()).build(id, ""));

		handler.register(recipeId("crafting", "copper_machine"),
				id -> RecipeBuilderUtil.donutRecipe(id, CR.asItem("copper_casing"),
						CABF.asItem("sealed_mechanism"), CABF.asItem("copper_machine"), 1));

		handler.register(recipeId("compacting", "rubber"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.RESIN, FluidConstants.BOTTLE))
						.setResult(new ProcessingOutput(CabfItems.RUBBER.getDefaultStack(), 1))));

		handler.register(recipeId("compacting", "rubber_from_flower"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE))
						.setIngredient(Ingredient.ofTag(ItemTags.FLOWERS), Ingredient.ofTag(ItemTags.FLOWERS),
								Ingredient.ofTag(ItemTags.FLOWERS), Ingredient.ofTag(ItemTags.FLOWERS))
						.setResult(new ProcessingOutput(CabfItems.RUBBER.getDefaultStack(), 1))));

		handler.register(recipeId("compacting", "rubber_from_vine"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE))
						.setIngredient(Ingredient.ofItems(Items.VINE), Ingredient.ofItems(Items.VINE),
								Ingredient.ofItems(Items.VINE), Ingredient.ofItems(Items.VINE))
						.setResult(new ProcessingOutput(CabfItems.RUBBER.getDefaultStack(), 1))));
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(CR.id("copper_backtank"), 1, MC.id("copper_block")));
		MechAndSmithCraft.addEntry(entry(CR.id("portable_fluid_interface"), 2, null));
		MechAndSmithCraft.addEntry(entry(CR.id("spout"), 1, KB.id("fluid_hopper")));
		MechAndSmithCraft.addEntry(entry(IR.id("tier_upgrade_mk2"), 1, MC.id("redstone")));
		MechAndSmithCraft.addEntry(entry(CR.id("hose_pulley"), 1, null));
		MechAndSmithCraft.addEntry(entry(CR.id("item_drain"), 1, MC.id("iron_bars")));
		MechAndSmithCraft.addEntry(entry(IR.id("heat_generator_mk4"), 1, IR.id("heat_coil")));
		MechAndSmithCraft.addEntry(entry(IR.id("fisher_mk2"), 1, MC.id("bucket")));
		MechAndSmithCraft.addEntry(entry(CR.id("steam_engine"), 2, MC.id("piston")));
		MechAndSmithCraft.addEntry(entry(CR.id("smart_fluid_pipe"), 2, CR.id("electron_tube")));
		MechAndSmithCraft.addEntry(entry(CX.id("fluid_trash_can"), 2, KB.id("trash_can")));
	}

	@Override
	public String getLevel() {
		return "copper";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("copper_machine"), output, count,
				other);
	}

}
