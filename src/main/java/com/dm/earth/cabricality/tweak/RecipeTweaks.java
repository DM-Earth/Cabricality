package com.dm.earth.cabricality.tweak;

import static com.dm.earth.cabricality.ModEntry.AD;
import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.C;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.math.RecipeBuilderUtil;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.tweak.cutting.CuttingRecipeTweaks;
import com.dm.earth.cabricality.tweak.ore_processing.OreProcessingTweaks;
import com.simibubi.create.content.contraptions.components.crusher.CrushingRecipe;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import com.simibubi.create.content.contraptions.components.mixer.CompactingRecipe;
import com.simibubi.create.content.contraptions.components.press.PressingRecipe;
import com.simibubi.create.content.contraptions.fluids.actors.FillingRecipe;
import com.simibubi.create.content.contraptions.processing.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import me.alphamode.forgetags.Tags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeTweaks
		implements AddRecipesCallback, RemoveRecipesCallback, ModifyRecipesCallback {

	private static final String[] ASTRA_MATERIALS = {"steel", "desh", "ostrum", "calorite", "iron"};
	private static final String[] ASTRA_DECOR_TYPES = {"pillar", "plating"};

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		for (TechThread thread : TechThread.THREADS)
			thread.addRecipes(handler);
		CuttingRecipeTweaks.register(handler);
		OreProcessingTweaks.register(handler);
		MechAndSmithCraft.register(handler);

		// Recipe Additions
		{
			var matter_ball = AE2.asIngredient("matter_ball");
			handler.register(recipeId("compacting", "matter_plastics"),
					id -> new CompactingRecipe(new FreePRP(id)
							.setIngredient(matter_ball, matter_ball, matter_ball, matter_ball,
									matter_ball, matter_ball, matter_ball, matter_ball, matter_ball)
							.setResult(CABF.asProcessingOutput("matter_plastics"))
							.setHeatRequirement(HeatCondition.SUPERHEATED)));
		}

		for (String material : ASTRA_MATERIALS)
			for (String decor : ASTRA_DECOR_TYPES)
				handler.register(recipeId("stonecutting", material + "_" + decor),
						id -> VanillaRecipeBuilders.stonecuttingRecipe(id, "",
								Ingredient.ofTag(
										TagKey.of(Registry.ITEM_KEY, C.id(material + "_plates"))),
								AD.asStack(material + "_" + decor, 2)));

		handler.register(recipeId("milling", "emerald"),
				id -> new MillingRecipe(new FreePRP(id).setIngredient(MC.asIngredient("emerald"))
						.setResult(CABF.asProcessingOutput("emerald_dust"))
						.setProcessingTime(450)));
		handler.register(recipeId("crushing", "diamond"),
				id -> new CrushingRecipe(new FreePRP(id).setIngredient(MC.asIngredient("diamond"))
						.setResult(CABF.asProcessingOutput("diamond_dust"))
						.setProcessingTime(650)));

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

		handler.register(recipeId("crafting", "ruby"), id -> RecipeBuilderUtil.donutRecipe(id,
				Items.EMERALD, Items.RED_DYE, CABF.asItem("ruby"), 1));
		handler.register(recipeId("crafting", "sapphire"), id -> RecipeBuilderUtil.donutRecipe(id,
				Items.EMERALD, Items.BLUE_DYE, CABF.asItem("sapphire"), 1));
		handler.register(recipeId("pressing", "zinc_sheet"),
				id -> new PressingRecipe(
						new FreePRP(id).setIngredient(CR.asIngredient("zinc_ingot"))
								.setResult(CABF.asProcessingOutput("zinc_sheet"))));
		handler.register(recipeId("pressing", "steel_plate"),
				id -> new PressingRecipe(
						new FreePRP(id).setIngredient(AD.asIngredient("steel_ingot"))
								.setResult(IR.asProcessingOutput("steel_plate"))));
		handler.register(recipeId("crafting", "nickel_ingot_from_nugget"),
				id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AAA", "AAA")
						.ingredient('A', CABF.asIngredient("nickel_nugget"))
						.output(CABF.asStack("nickel_ingot")).build(id, ""));

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

		handler.remove(TC.id("smeltery/alloys/molten_enderium"));
		handler.remove(TC.id("smeltery/alloys/molten_brass"));
		handler.remove(TC.id("smeltery/alloys/molten_invar"));

		handler.removeIf(r -> notCabf(r) && r.getOutput().isOf(IR.asItem("controller")));
		handler.removeIf(r -> notCabf(r) && r.getOutput().isOf(AD.asItem("wrench")));

		for (String material : ASTRA_MATERIALS)
			for (String decor : ASTRA_DECOR_TYPES)
				handler.removeIf(
						r -> notCabf(r) && r.getOutput().isOf(AD.asItem(material + "_" + decor)));
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
