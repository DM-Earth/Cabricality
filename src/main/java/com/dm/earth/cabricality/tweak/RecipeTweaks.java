package com.dm.earth.cabricality.tweak;

import static com.dm.earth.cabricality.ModEntry.AD;
import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.C;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import java.util.Arrays;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import com.dm.earth.cabricality.lib.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.tweak.cutting.CuttingRecipeTweaks;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingTweaks;
import com.github.alexnijjar.ad_astra.recipes.CompressingRecipe;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.contraptions.components.crusher.CrushingRecipe;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import com.simibubi.create.content.contraptions.components.mixer.CompactingRecipe;
import com.simibubi.create.content.contraptions.components.press.PressingRecipe;
import com.simibubi.create.content.contraptions.fluids.actors.FillingRecipe;
import com.simibubi.create.content.contraptions.processing.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;

import me.alphamode.forgetags.Tags;
import me.steven.indrev.recipes.machines.CompressorRecipe;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeTweaks
		implements AddRecipesCallback, ModifyRecipesCallback, RemoveRecipesCallback {
	private static final String[] AD_ASTRA_MATERIALS = { "steel", "desh", "ostrum", "calorite", "iron" };
	private static final String[] AD_ASTRA_DECOR_TYPES = { "pillar", "plating" };

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
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
									matterBall, matterBall, matterBall, matterBall, matterBall)
							.setResult(CABF.asProcessingOutput("matter_plastics"))
							.setHeatRequirement(HeatCondition.SUPERHEATED)));
		}

		// Ad Astra
		{
			Arrays.stream(AD_ASTRA_MATERIALS).forEach(material -> Arrays.stream(AD_ASTRA_DECOR_TYPES).forEach(
					type -> handler.register(AD.id("stonecutting", material + "_" + type),
							id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "", Ingredient.ofTag(
									TagKey.of(Registry.ITEM_KEY, C.id(material + "_plates"))),
									AD.asStack(material + "_" + type, 2)))));

			final String[] AD_ASTRA_COMPRESSED_MATERIALS = { "desh", "ostrum", "calorite" };
			Arrays.stream(AD_ASTRA_COMPRESSED_MATERIALS).forEach(material -> handler.register(
					AD.id("pressing", "compressed_" + material),
					id -> new PressingRecipe(new FreePRP(id)
							.setIngredient(AD.asIngredient(material + "ingot"))
							.setResult(AD.asProcessingOutput("compressed_" + material)))));
		}

		// Indrev
		{
			final String[] INDREV_PLATES = { "bronze", "electrum", "lead", "silver", "steel", "tin", "tungsten" };

			Arrays.stream(INDREV_PLATES).forEach(plate -> handler.register(recipeId("pressing", plate + "_plate"),
					id -> new PressingRecipe(new FreePRP(id).setIngredient(IR.asIngredient(plate + "ingot"))
							.setResult(IR.asProcessingOutput(plate + "plate")))));
		}

		// Dusts
		handler.register(recipeId("milling", "emerald"),
				id -> new MillingRecipe(new FreePRP(id).setIngredient(MC.asIngredient("emerald"))
						.setResult(CABF.asProcessingOutput("emerald_dust"))
						.setProcessingTime(450)));

		handler.register(recipeId("crushing", "diamond"),
				id -> new CrushingRecipe(new FreePRP(id).setIngredient(MC.asIngredient("diamond"))
						.setResult(CABF.asProcessingOutput("diamond_dust"))
						.setProcessingTime(650)));

		// Nickel compound
		handler.register(recipeId("filling", "nickel_compound"),
				id -> new FillingRecipe(
						new FreePRP(id).setIngredient(CABF.asIngredient("nickel_ingot"))
								.setFluidIngredient(FluidIngredient.fromFluid(
										TC.asFluid("molten_iron"), FluidConstants.NUGGET * 6))
								.setResult(CABF.asProcessingOutput("nickel_compound"))));

		// Saws
		handler.register(recipeId("crafting", "stone_rod"),
				id -> VanillaRecipeBuilders.shapedRecipe("S", "S")
						.ingredient('S', Tags.Items.COBBLESTONE).output(CABF.asStack("stone_rod"))
						.build(id, ""));

		handler.register(recipeId("crafting", "stone_saw"),
				id -> VanillaRecipeBuilders.shapedRecipe("SRR", "SMR")
						.ingredient('S', MC.asIngredient("stick"))
						.ingredient('R', CABF.asIngredient("stone_rod"))
						.ingredient('M', MC.asIngredient("flint")).output(CABF.asStack("stone_saw"))
						.build(id, ""));

		handler.register(recipeId("crafting", "iron_saw"),
				id -> VanillaRecipeBuilders.shapedRecipe("SRR", "SMR")
						.ingredient('S', MC.asIngredient("stick"))
						.ingredient('R', CABF.asIngredient("stone_rod"))
						.ingredient('M', TagKey.of(Registry.ITEM_KEY, C.id("iron_ingots")))
						.output(CABF.asStack("iron_saw")).build(id, ""));

		handler.register(recipeId("crafting", "diamond_saw"),
				id -> VanillaRecipeBuilders.shapedRecipe("SRR", "SMR")
						.ingredient('S', MC.asIngredient("stick"))
						.ingredient('R', CABF.asIngredient("stone_rod"))
						.ingredient('M', MC.asIngredient("diamond"))
						.output(CABF.asStack("diamond_saw")).build(id, ""));

		handler.register(recipeId("smithing", "netherite_saw"),
				id -> new SmithingRecipe(id, CABF.asIngredient("diamond_saw"),
						MC.asIngredient("netherite_ingot"), CABF.asStack("netherite_saw")));

		// Gems
		handler.register(recipeId("crafting", "ruby"), id -> RecipeBuilderUtil.donutRecipe(id,
				Items.EMERALD, Items.RED_DYE, CABF.asItem("ruby"), 1));

		handler.register(recipeId("crafting", "sapphire"), id -> RecipeBuilderUtil.donutRecipe(id,
				Items.EMERALD, Items.BLUE_DYE, CABF.asItem("sapphire"), 1));

		// Misc
		handler.register(recipeId("pressing", "zinc_sheet"),
				id -> new PressingRecipe(
						new FreePRP(id).setIngredient(CR.asIngredient("zinc_ingot"))
								.setResult(CABF.asProcessingOutput("zinc_sheet"))));

		handler.register(IR.id("pressing", "steel_plate"),
				id -> new PressingRecipe(
						new FreePRP(id).setIngredient(AD.asIngredient("steel_ingot"))
								.setResult(IR.asProcessingOutput("steel_plate"))));
		handler.register(IR.id("pressing", "tin_plate"),
				id -> new PressingRecipe(
						new FreePRP(id).setIngredient(IR.asIngredient("tin_ingot"))
								.setResult(IR.asProcessingOutput("tin_plate"))));
		handler.register(IR.id("pressing", "lead_plate"),
				id -> new PressingRecipe(
						new FreePRP(id).setIngredient(IR.asIngredient("lead_ingot"))
								.setResult(IR.asProcessingOutput("lead_plate"))));

		handler.register(recipeId("crafting", "nickel_ingot_from_nugget"),
				id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AAA", "AAA")
						.ingredient('A', CABF.asIngredient("nickel_nugget"))
						.output(CABF.asStack("nickel_ingot")).build(id, ""));

		// Redstone
		handler.register(recipeId("melting", "redstone"),
				id -> RecipeManager.deserialize(id,
						RecipeBuilderUtil.generateMelting(MC.id("redstone"), CABF.id("redstone"),
								FluidConstants.INGOT, null, 0, 250, 15)));

		handler.register(recipeId("melting", "redstone_block"),
				id -> RecipeManager.deserialize(id,
						RecipeBuilderUtil.generateMelting(MC.id("redstone_block"),
								CABF.id("redstone"), FluidConstants.BLOCK, null, 0, 250, 135)));
	}

	@Override
	public void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {
		TechThread.THREADS.forEach(thread -> thread.modifyRecipes(handler));

		handler.getRecipes().values().stream().flatMap(m -> m.values().stream())
				.forEach(recipe -> {
					// Ad Astra!
					if (recipe instanceof CompressingRecipe) {
						if (recipe.getOutput().isOf(AD.asItem("iron_plate")))
							handler.replace(new CompressingRecipe(
									recipe.getId(), ((CompressingRecipe) recipe).getInputIngredient(),
									CR.asStack("iron_sheet"), (short) 200));
						if (recipe.getOutput().isOf(AD.asItem("compressed_steel")))
							handler.replace(new CompressingRecipe(
									recipe.getId(), ((CompressingRecipe) recipe).getInputIngredient(),
									IR.asStack("steel_plate"), (short) 200));
					}

					if (recipe instanceof ShapelessRecipe)
						if (recipe.getOutput().isOf(AD.asItem("iron_plate")))
							handler.replace(RecipeBuilderUtil.swapShapelessRecipeOutput((ShapelessRecipe) recipe,
									CR.asStack("iron_sheet")));

					// Indrev
					if (recipe instanceof CompressorRecipe) {
						if (recipe.getOutput().isOf(IR.asItem("gold_plate")))
							handler.replace(new CompressorRecipe(
									recipe.getId(), ((CompressorRecipe) recipe).getInput(),
									new OutputEntry[] { new OutputEntry(CR.asStack("golden_sheet"), 1) }, 400));
						if (recipe.getOutput().isOf(IR.asItem("iron_plate")))
							handler.replace(new CompressorRecipe(
									recipe.getId(), ((CompressorRecipe) recipe).getInput(),
									new OutputEntry[] { new OutputEntry(CR.asStack("iron_sheet"), 1) }, 400));
						if (recipe.getOutput().isOf(IR.asItem("copper_plate")))
							handler.replace(new CompressorRecipe(
									recipe.getId(), ((CompressorRecipe) recipe).getInput(),
									new OutputEntry[] { new OutputEntry(CR.asStack("copper_sheet"), 1) }, 400));
					}

					if (recipe instanceof ShapelessRecipe)
						if (recipe.getOutput().isOf(IR.asItem("copper_plate")))
							handler.replace(RecipeBuilderUtil.swapShapelessRecipeOutput((ShapelessRecipe) recipe,
									CR.asStack("copper_sheet")));
				});
	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
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

		// Remove wrenches except Create's and AE2's
		handler.removeIf(r -> notCabf(r) &&
				!r.getOutput().getItem().getRegistryName().getNamespace().equals("create") &&
				!r.getOutput().getItem().getRegistryName().getNamespace().equals("ae2") &&
				r.getOutput().getItem().getRegistryName().getPath().contains("wrench"));

		handler.removeIf(r -> notCabf(r) && r.getOutput().isOf(IR.asItem("controller")));

		// Ad Astra!
		Arrays.stream(AD_ASTRA_MATERIALS).forEach(material -> Arrays.stream(AD_ASTRA_DECOR_TYPES).forEach(
				type -> handler.removeIf(r -> notCabf(r) && r.getOutput().isOf(AD.asItem(material + "_" + type)))));

		// AE2
		handler.removeIf(r -> r.getType().equals(RecipeType.CRAFTING) && notCabf(r)
				&& r.getOutput().isOf(AE2.asItem("certus_crystal_seed")));
		handler.removeIf(r -> r.getType().equals(AllRecipeTypes.MILLING.getType()) && notCabf(r)
				&& !contains(r, AE2.asIngredient("certus_quartz_crystal")));

		// Indrev's toxic tools
		handler.removeIf(r -> r.getId().getNamespace().equals(IR.getModId())
				&& Registry.ITEM.getId(r.getOutput().getItem()).getPath()
						.matches(".*_(pickaxe|axe|shovel|hoe|sword|helmet|chestplate|leggings|boots)$"));

		handler.removeIf(r -> notCabf(r) && r.getOutput().isOf(AllItems.PROPELLER.get()));
	}

	public static boolean notCabf(Identifier id) {
		return !id.getNamespace().equals(Cabricality.ID);
	}

	public static boolean notCabf(Recipe<?> recipe) {
		return notCabf(recipe.getId());
	}

	public static boolean contains(Recipe<?> recipe, Ingredient ingredient) {
		return recipe.getIngredients().stream().anyMatch(i -> i.equals(ingredient));
	}

	private static Identifier recipeId(String type, String name) {
		return Cabricality.id(type, name);
	}
}
