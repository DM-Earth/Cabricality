package dm.earth.cabricality.content.core.threads;

import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.content.entries.CabfItemTags;
import dm.earth.cabricality.lib.util.RecipeUtil;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import ho.artisan.lib.recipe.api.builder.VanillaRecipeBuilders;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CookingCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dm.earth.cabricality.Mod.Entry.AD_ASTRA;
import static dm.earth.cabricality.Mod.Entry.AE2;
import static dm.earth.cabricality.Mod.Entry.AP;
import static dm.earth.cabricality.Mod.Entry.CABF;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.INDREV;
import static dm.earth.cabricality.Mod.Entry.MC;

public class AndesiteThread implements TechThread {
	@Override
	public String getLevel() {
		return "andesite";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("andesite_machine"), output, count, other);
	}

	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.@NotNull RecipeHandler handler) {
		handler.register(
				recipeId("smelting", "algal_blend"),
				id -> VanillaRecipeBuilders.smeltingRecipe(
						id, "",
						Ingredient.ofItems(AP.asItem("algal_blend")),
						CookingCategory.MISC,
						AP.asItem("algal_brick").getDefaultStack(),
						0, 120)

		);

		handler.register(
				recipeId("crafting", "algal_blend"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("SS", "AA")
						.ingredient('A', Items.CLAY_BALL)
						.ingredient('S', Items.KELP, Items.SEAGRASS)
						.output(AP.asStack(2, "algal_blend"))
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "algal_blend_2"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("AA", "SS")
						.ingredient('A', Items.CLAY_BALL)
						.ingredient('S', Items.KELP, Items.SEAGRASS)
						.output(AP.asStack(2, "algal_blend"))
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "andesite_alloy"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("SS", "AA")
						.ingredient('A', Items.ANDESITE)
						.ingredient('S', AP.asItem("algal_brick"))
						.output(CREATE.asStack(2, "andesite_alloy"))
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "andesite_alloy_2"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("AA", "SS")
						.ingredient('A', Items.ANDESITE)
						.ingredient('S', AP.asItem("algal_brick"))
						.output(CREATE.asStack(2, "andesite_alloy"))
						.build(id, "")
		);

		handler.register(
				recipeId("mixing", "algal_blend"),
				id -> new MixingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(Items.CLAY_BALL), Ingredient.ofItems(Items.KELP, Items.SEAGRASS))
						.setResult(new ProcessingOutput(new ItemStack(AP.asItem("algal_blend")), 2))
				)
		);

		handler.register(
				recipeId("mixing", "andesite_alloy"),
				id -> new MixingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(AP.asItem("algal_brick")), Ingredient.ofItems(Items.ANDESITE))
						.setResult(new ProcessingOutput(new ItemStack(CREATE.asItem("andesite_alloy")), 2))
				)
		);

		handler.register(
				recipeId("crafting", "kinetic_mechanism"),
				id -> VanillaRecipeBuilders
						.shapelessRecipe(CABF.asItem("kinetic_mechanism").getDefaultStack())
						.ingredient(CREATE.asItem("cogwheel"))
						.ingredient(CREATE.asItem("andesite_alloy"))
						.ingredient(ItemTags.LOGS).ingredient(CabfItemTags.SAWS)
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "andesite_machine"),
				id -> RecipeUtil.donutRecipe(
						id,
						CREATE.asItem("andesite_casing"),
						CABF.asItem("kinetic_mechanism"),
						CABF.asItem("andesite_machine"),
						1
				)
		);

		handler.register(
				recipeId("compacting", "dripstone_block"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(CREATE.asItem("limestone")))
						.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE * 2))
						.setResult(new ProcessingOutput(MC.asItem("dripstone_block").getDefaultStack(), 1)))
		);

		handler.register(
				recipeId("sequenced_assembly", "kinetic_mechanism"),
				id -> (new SequencedAssemblyRecipeBuilder(id))
						.require(ItemTags.WOODEN_SLABS)
						.transitionTo(CABF.asItem("incomplete_kinetic_mechanism"))
						.addOutput(CABF.asItem("kinetic_mechanism"), 1.0F).loops(1)
						.addStep(DeployerApplicationRecipe::new, r -> r.require(CREATE.asItem("andesite_alloy")))
						.addStep(DeployerApplicationRecipe::new, r -> r.require(CREATE.asItem("andesite_alloy")))
						.addStep(CuttingRecipe::new, r -> r)
						.build()
		);

		handler.register(
				recipeId("crafting", "saw_blade"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("NPN", "PLP", "NPN")
						.ingredient('N', MC.asIngredient("iron_nugget"))
						.ingredient('P', CREATE.asIngredient("iron_sheet"))
						.ingredient('L', INDREV.asIngredient("lead_ingot"))
						.output(CABF.asStack("saw_blade"))
						.build(id, ""));

		handler.register(
				recipeId("crafting", "iron_drill_head"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("NN ", "NLP", " PL")
						.ingredient('N', MC.asIngredient("iron_nugget"))
						.ingredient('P', CREATE.asIngredient("iron_sheet"))
						.ingredient('L', INDREV.asIngredient("lead_ingot"))
						.output(INDREV.asStack("iron_drill_head"))
						.build(id, "")
		);
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.@NotNull RecipeHandler handler) {
		handler.remove(CREATE.id("crafting", "materials", "andesite_alloy"));
		handler.remove(CREATE.id("crafting", "materials", "andesite_alloy_from_zinc"));
		handler.remove(CREATE.id("mixing", "andesite_alloy"));
		handler.remove(CREATE.id("mixing", "andesite_alloy_from_zinc"));

		handler.removeIf(p ->
				p instanceof AbstractCookingRecipe
						&& AP.predicateOutput(handler, false, "algal_brick").test(p));
		handler.removeIf(INDREV.predicateOutput(handler, false, "iron_drill_head"));
		handler.remove(AP.id("algal_blend_shapeless"));
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_press"), 				1, MC.id("iron_block")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_mixer"), 				1, CREATE.id("whisk")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("encased_fan"), 					1, INDREV.id("fan")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_drill"), 				1, INDREV.id("iron_drill_head")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_saw"), 				1, CABF.id("saw_blade")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("deployer"), 						1, CREATE.id("brass_hand")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("andesite_tunnel"), 				4, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("andesite_funnel"), 				4, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_harvester"), 			2, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_plough"), 			2, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("portable_storage_interface"), 	2, null));
		MechAndSmithCraft.addEntry(entry(CABF.id("extractor_machine"), 			1, MC.id("bucket")));
		MechAndSmithCraft.addEntry(entry(AD_ASTRA.id("coal_generator"), 				1, INDREV.id("heat_coil")));
		MechAndSmithCraft.addEntry(entry(AE2.id("charger"), 						1, AE2.id("fluix_crystal")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("contraption_controls"), 			2, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_roller"), 			1, CREATE.id("crushing_wheel")));
	}
}
