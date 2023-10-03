package dm.earth.cabricality.tweak;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import dm.earth.cabricality.tweak.ore_processing.OreProcessingTweaks;
import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllRecipeTypes;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import me.steven.indrev.blocks.machine.pipes.FluidPipeBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;

import static dm.earth.cabricality.ModEntry.AD_ASTRA;
import static dm.earth.cabricality.ModEntry.AE2;
import static dm.earth.cabricality.ModEntry.BNC;
import static dm.earth.cabricality.ModEntry.CATWALKS;
import static dm.earth.cabricality.ModEntry.CREATE;
import static dm.earth.cabricality.ModEntry.INDREV;
import static dm.earth.cabricality.ModEntry.TC;

public class RecipeTweaks implements RecipeLoadingEvents.AddRecipesCallback, RecipeLoadingEvents.ModifyRecipesCallback, RecipeLoadingEvents.RemoveRecipesCallback {
	public static final Collection<ItemConvertible> DEPRECATED_ITEMS = ImmutableList.of(
			// Wrenches
			AD_ASTRA.asItem("wrench"), INDREV.asItem("wrench"), CATWALKS.asItem("wrench"), BNC.asItem("wrench"),
			// Hammers
			AD_ASTRA.asItem("hammer"), INDREV.asItem("hammer"),
			// Indrev
			INDREV.asItem("gold_plate"), INDREV.asItem("iron_plate"), INDREV.asItem("copper_plate"), INDREV.asItem("fan"),
			// Ad Astra
			AD_ASTRA.asItem("compressed_steel"), AD_ASTRA.asItem("iron_plate"));
	private static final String[] AD_ASTRA_MATERIALS = { "steel", "desh", "ostrum", "calorite", "iron" };
	private static final String[] AD_ASTRA_DECOR_TYPES = { "pillar", "plating" };

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		/*
		TechThread.THREADS.forEach(thread -> thread.addRecipes(handler));

		CuttingRecipeTweaks.register(handler);
		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);

		// AE2
		{
			Ingredient matterBall = AE2.asIngredient("matter_ball");
			handler.register(AE2.id("compacting", "matter_plastics"),
					id -> new CompactingRecipe(new FreePRP(id)
							.setIngredient(matterBall, matterBall, matterBall, matterBall,
									matterBall, matterBall, matterBall, matterBall,
									matterBall)
							.setResult(CABF.asProcessingOutput("matter_plastics"))
							.setHeatRequirement(HeatCondition.SUPERHEATED)));
		}

		// Ad Astra
		{
			Arrays.stream(AD_ASTRA_MATERIALS).forEach(material -> Arrays.stream(AD_ASTRA_DECOR_TYPES).forEach(
					type -> handler.register(
							recipeId("stonecutting", material + "_" + type),
							id -> VanillaRecipeBuilders.stonecuttingRecipe(
									id, "",
									Ingredient.ofTag(TagKey.of(
											RegistryKeys.ITEM,
											C.id(material + "_plates")
									)),
									AD.asStack(2, material + "_" + type)
							)
					)
			));

			final String[] AD_ASTRA_COMPRESSED_MATERIALS = { "desh", "ostrum", "calorite" };
			Arrays.stream(AD_ASTRA_COMPRESSED_MATERIALS).forEach(material -> handler.register(
					recipeId("pressing", "compressed_" + material),
					id -> new PressingRecipe(new FreePRP(id)
							.setIngredient(AD.asIngredient(material + "_ingot"))
							.setResult(AD.asProcessingOutput("compressed_" + material)))
			));
		}

		// Indrev
		{
			final String[] INDREV_PLATES = { "bronze", "electrum", "lead", "silver", "steel", "tin",
					"tungsten" };

			Arrays.stream(INDREV_PLATES).forEach(plate -> handler.register(
					recipeId("pressing", plate + "_plate"),
					id -> new PressingRecipe(new FreePRP(id)
							.setIngredient(IR.asIngredient(plate + "_ingot"))
							.setResult(IR.asProcessingOutput(plate + "_plate")))
			));

			handler.register(
					recipeId("compacting", "aquamarine_quartz"),
					id -> new CompactingRecipe(new FreePRP(id)
							.setIngredient(IR.asIngredient("nikolite_dust"))
							.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.REDSTONE, FluidConstants.NUGGET))
							.setResult(CABF.asProcessingOutput("aquamarine_quartz"))
							.setHeatRequirement(HeatCondition.HEATED))
			);

			handler.register(
					recipeId("sandpaper_polishing", "aquamarine_quartz"),
					id -> new SandPaperPolishingRecipe(new FreePRP(id)
							.setIngredient(CABF.asIngredient("aquamarine_quartz"))
							.setResult(IR.asProcessingOutput("nikolite_ingot")))
			);
		}

		// Dusts
		handler.register(
				recipeId("milling", "emerald"),
				id -> new MillingRecipe(new FreePRP(id)
						.setIngredient(MC.asIngredient("emerald"))
						.setResult(CABF.asProcessingOutput("emerald_dust"))
						.setProcessingTime(450))
		);

		handler.register(
				recipeId("crushing", "diamond"),
				id -> new CrushingRecipe(new FreePRP(id)
						.setIngredient(MC.asIngredient("diamond"))
						.setResult(CABF.asProcessingOutput("diamond_dust"))
						.setProcessingTime(650))
		);

		// Nickel compound
		handler.register(
				recipeId("filling", "nickel_compound"),
				id -> new FillingRecipe(new FreePRP(id)
						.setIngredient(CABF.asIngredient("nickel_ingot"))
						.setFluidIngredient(FluidIngredient.fromFluid(
								TC.asFluid("molten_iron"), FluidConstants.NUGGET * 6)
						)
						.setResult(CABF.asProcessingOutput("nickel_compound")))
		);

		// Saws
		handler.register(
				recipeId("crafting", "stone_rod"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("S", "S")
						.ingredient('S', Tags.Items.COBBLESTONE)
						.output(CABF.asStack("stone_rod"))
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "stone_saw"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("SRR", "SMR")
						.ingredient('S', MC.asIngredient("stick"))
						.ingredient('R', CABF.asIngredient("stone_rod"))
						.ingredient('M', MC.asIngredient("flint"))
						.output(CABF.asStack("stone_saw"))
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "iron_saw"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("SRR", "SMR")
						.ingredient('S', MC.asIngredient("stick"))
						.ingredient('R', CABF.asIngredient("stone_rod"))
						.ingredient('M', TagKey.of(RegistryKeys.ITEM, C.id("iron_ingots")))
						.output(CABF.asStack("iron_saw"))
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "diamond_saw"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("SRR", "SMR")
						.ingredient('S', MC.asIngredient("stick"))
						.ingredient('R', CABF.asIngredient("stone_rod"))
						.ingredient('M', MC.asIngredient("diamond"))
						.output(CABF.asStack("diamond_saw"))
						.build(id, "")
		);

		handler.register(
				recipeId("smithing", "netherite_saw"),
				id -> new TransformSmithingRecipe(id,
						Ingredient.EMPTY,
						CABF.asIngredient("diamond_saw"),
						MC.asIngredient("netherite_ingot"),
						CABF.asStack("netherite_saw"))
		);

		// Gems
		handler.register(
				recipeId("crafting", "ruby"),
				id -> RecipeBuilderUtil.donutRecipe(
						id,
						Items.EMERALD, Items.RED_DYE, CABF.asItem("ruby"),
						1
				)
		);

		handler.register(
				recipeId("crafting", "sapphire"),
				id -> RecipeBuilderUtil.donutRecipe(
						id,
						Items.EMERALD, Items.BLUE_DYE, CABF.asItem("sapphire"),
						1
				)
		);

		// Misc
		handler.register(
				recipeId("pressing", "zinc_sheet"),
				id -> new PressingRecipe(new FreePRP(id)
						.setIngredient(CR.asIngredient("zinc_ingot"))
						.setResult(CABF.asProcessingOutput("zinc_sheet")))
		);

		handler.register(
				recipeId("crafting", "nickel_ingot_from_nugget"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("AAA", "AAA", "AAA")
						.ingredient('A', CABF.asIngredient("nickel_nugget"))
						.output(CABF.asStack("nickel_ingot"))
						.build(id, "")
		);

		// Redstone
		handler.register(
				recipeId("melting", "redstone"),
				id -> RecipeManager.deserialize(id, RecipeBuilderUtil.generateMelting(
						MC.id("redstone"),
						CABF.id("redstone"),
						FluidConstants.INGOT,
						null, 0, 250, 15
				))
		);

		handler.register(
				recipeId("melting", "redstone_block"),
				id -> RecipeManager.deserialize(id, RecipeBuilderUtil.generateMelting(
						MC.id("redstone_block"),
						CABF.id("redstone"),
						FluidConstants.BLOCK,
						null, 0, 250, 135
				))
		);

		 */
	}

	@Override
	public void modifyRecipes(RecipeLoadingEvents.ModifyRecipesCallback.RecipeHandler handler) {
		TechThread.THREADS.forEach(thread -> thread.modifyRecipes(handler));
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		TechThread.THREADS.forEach(thread -> thread.removeRecipes(handler));

		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);

		handler.remove(TC.id("smeltery", "casting", "metal", "gold", "coin_sand_cast"));
		handler.remove(TC.id("smeltery", "casting", "metal", "gold", "coin_gold_cast"));
		handler.remove(TC.id("smeltery", "casting", "metal", "silver", "coin_sand_cast"));
		handler.remove(TC.id("smeltery", "casting", "metal", "silver", "coin_gold_cast"));

		handler.remove(TC.id("smeltery", "alloys", "molten_enderium"));
		handler.remove(TC.id("smeltery", "alloys", "molten_brass"));
		handler.remove(TC.id("smeltery", "alloys", "molten_invar"));

		handler.removeIf(r ->
				r.getId().getNamespace().equals(TC.modid())
						&& r.getId().getPath().startsWith("compat/create")
		);

		// Remove wrenches except Create's and AE2's
		handler.removeIf(recipe ->
				!CREATE.checkContains(handler, recipe) && !AE2.checkContains(handler, recipe)
						&& Registries.ITEM.getId(recipe.getResult(handler.getRegistryManager()).getItem()).getPath().contains("wrench")
		);
		handler.removeIf(INDREV.predicateOutput(handler, "controller"));

		// Ad Astra!
		Arrays.stream(AD_ASTRA_MATERIALS).forEach(material -> Arrays.stream(AD_ASTRA_DECOR_TYPES).forEach(
				type -> handler.removeIf(AD_ASTRA.predicateOutput(handler, material + "_" + type))
		));
		handler.remove(AD_ASTRA.id("recipes/nasa_workbench"));

		// AE2
		handler.removeIf(AllRecipeTypes.MILLING.getType(), AE2.predicateOutput(handler, "certus_quartz_dust").and(
				AE2.predicateIngredient(handler, "certus_quartz_crystal").negate()
		));

		// Indrev
		handler.removeIf(recipe ->
				INDREV.checkContains(handler, recipe)
						&& Registries.ITEM.getId(recipe.getResult(handler.getRegistryManager()).getItem()).getPath()
						.matches(".*_(pickaxe|axe|shovel|hoe|sword)$")
		);
		handler.removeIf(INDREV.predicateIngredient(handler, "fan"));
		handler.removeIf(recipe ->
				recipe.getResult(handler.getRegistryManager()).getItem() instanceof BlockItem blockItem
						&& blockItem.getBlock() instanceof FluidPipeBlock
		);
		handler.remove(INDREV.id("shaped/coal_generator_mk1"));
		handler.remove(INDREV.id("shaped/solar_generator_mk1"));
		handler.remove(INDREV.id("shaped/solar_generator_mk3"));
	}

	private static Identifier recipeId(String type, String name) {
		return Cabricality.id(type, name);
	}
}
