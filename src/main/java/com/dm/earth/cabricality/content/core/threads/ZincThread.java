package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AD;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.ED;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.KB;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;

import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.math.RecipeBuilderUtil;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.simibubi.create.content.contraptions.components.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.contraptions.components.mixer.MixingRecipe;
import com.simibubi.create.content.contraptions.processing.HeatCondition;

import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

@SuppressWarnings("UnstableApiUsage")
public class ZincThread implements TechThread {

	@Override
	public void addRecipes(AddRecipesCallback.@NotNull RecipeHandler handler) {
		handler.register(recipeId("mixing", "liquid_soul"), id -> new MixingRecipe(new FreePRP(id)
				.setIngredient(Ingredient.ofItems(Items.WEEPING_VINES),
						Ingredient.ofItems(Items.TWISTING_VINES))
				.setFluidResult(new FluidStack(TC.asFluid("liquid_soul"), FluidConstants.BOTTLE))
				.setHeatRequirement(HeatCondition.HEATED)));

		handler.register(recipeId("crafting", "zinc_machine"),
				id -> RecipeBuilderUtil.donutRecipe(id, CABF.asItem("zinc_casing"),
						CABF.asItem("infernal_mechanism"), CABF.asItem("zinc_machine"), 1));

		handler.register(recipeId("item_application", "zinc_casing"),
				id -> new ManualApplicationRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofTag(ItemTags.STONE_CRAFTING_MATERIALS),
								CABF.asIngredient("zinc_sheet"))
						.setResult(CABF.asProcessingOutput("zinc_casing"))));
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(KB.id("cobblestone_generator_mk1"), 1, MC.id("piston")));
		MechAndSmithCraft.addEntry(entry(KB.id("basalt_generator_mk1"), 1, MC.id("blue_ice")));
		MechAndSmithCraft.addEntry(entry(KB.id("trash_can"), 1, MC.id("lava_bucket")));
		MechAndSmithCraft.addEntry(entry(KB.id("vacuum_hopper"), 1, CR.id("nozzle")));
		MechAndSmithCraft.addEntry(entry(KB.id("big_torch"), 1, MC.id("torch")));
		MechAndSmithCraft.addEntry(entry(AD.id("solar_panel"), 1, MC.id("glass")));
		MechAndSmithCraft.addEntry(entry(IR.id("tier_upgrade_mk3"), 1, MC.id("redstone")));
		MechAndSmithCraft.addEntry(entry(ED.id("controller"), 1, ED.id("connector")));
	}

	@Override
	public String getLevel() {
		return "zinc";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("zinc_machine"), output, count,
				other);
	}

}
