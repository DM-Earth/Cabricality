package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.C;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.CX;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.KB;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import java.util.List;

import com.simibubi.create.content.fluids.transfer.EmptyingRecipe;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.core.items.ColoredFernItem;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import com.dm.earth.cabricality.lib.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;
import com.nhoryzon.mc.farmersdelight.recipe.ingredient.ChanceResult;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.kinetics.fan.HauntingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;

import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import me.alphamode.forgetags.Tags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public class InvarThread implements TechThread {
	private static final List<Identifier> REMOVE_OUTPUTS = List.of(IR.id("compressor_mk1"), IR.id("chopper_mk1"),
			IR.id("farmer_mk1"), IR.id("slaughter_mk1"), IR.id("rancher_mk1"), IR.id("pump_mk1"),
			IR.id("mining_rig_mk4"), IR.id("data_card_writer_mk4"), IR.id("drain_mk1"));

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(IR.id("electric_furnace_mk1"), 1, MC.id("furnace")));
		MechAndSmithCraft.addEntry(entry(IR.id("compressor_mk1"), 1, MC.id("iron_block")));
		MechAndSmithCraft.addEntry(entry(IR.id("smelter_mk4"), 1, MC.id("blast_furnace")));
		MechAndSmithCraft.addEntry(entry(IR.id("pulverizer_mk1"), 1, MC.id("flint")));
		MechAndSmithCraft.addEntry(entry(IR.id("sawmill_mk1"), 1, CABF.id("saw_blade")));
		MechAndSmithCraft.addEntry(entry(IR.id("recycler_mk2"), 1, MC.id("composter")));
		MechAndSmithCraft.addEntry(entry(IR.id("condenser_mk4"), 1, MC.id("packed_ice")));
		MechAndSmithCraft.addEntry(entry(IR.id("fluid_infuser_mk1"), 1, CR.id("whisk")));
		MechAndSmithCraft.addEntry(entry(IR.id("modular_workbench_mk4"), 1, MC.id("crafting_table")));
		MechAndSmithCraft.addEntry(entry(IR.id("lazuli_flux_container_mk1"), 1, MC.id("redstone_block")));
		MechAndSmithCraft.addEntry(entry(IR.id("laser_emitter_mk4"), 1, MC.id("lightning_rod")));
		MechAndSmithCraft.addEntry(entry(CX.id("energy_trash_can"), 1, KB.id("trash_can")));
	}

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		handler.register(recipeId("mechanical_crafting", "crushing_wheel"),
				id -> RecipeBuilderUtil.mechanicalFromShaped(VanillaRecipeBuilders
						.shapedRecipe(" AAA ", "AABAA", "ABBBA", "AABAA", " AAA")
						.ingredient('A', Tags.Items.COBBLESTONE).ingredient('B', Items.STICK)
						.output(CR.asItem("crushing_wheel").getDefaultStack()).build(id, ""),
						false));

		for (ColoredFernItem.Entry entry : ColoredFernItem.Entry.values()) {
			String fern = entry.name + "_slime_fern";
			String leaf = entry.name + "_slime_fern_leaf";
			String paste = entry.name + "_slime_fern_paste";
			TagKey<Item> knives = TagKey.of(Registry.ITEM_KEY, C.id("tools/knives"));
			DefaultedList<ChanceResult> results = DefaultedList.of();
			results.add(new ChanceResult(CABF.asStack(2, leaf), 1));
			handler.register(recipeId("fd_cutting", fern),
					id -> new CuttingBoardRecipe(id, "", TC.asIngredient(fern),
							Ingredient.ofTag(knives), results, "minecraft:block.grass.break"));
			handler.register(recipeId("deploying", fern),
					id -> new DeployerApplicationRecipe(
							new FreePRP(id).setIngredient(TC.asIngredient(fern), Ingredient.ofTag(knives))
									.setResult(CABF.asProcessingOutput(1, 2, leaf)).keepHeldItem()));
			handler.register(recipeId("haunting", leaf),
					id -> new HauntingRecipe(new FreePRP(id).setIngredient(CABF.asIngredient(leaf))
							.setResult(TC.asProcessingOutput(fern))));
			handler.register(recipeId("milling", leaf),
					id -> new MillingRecipe(new FreePRP(id).setIngredient(CABF.asIngredient(leaf))
							.setResult(CABF.asProcessingOutput(paste)).setProcessingTime(70)));
			handler.register(recipeId("campfire_cooking", paste), id -> new CampfireCookingRecipe(id, "",
					CABF.asIngredient(paste), entry.getOutputItem().getDefaultStack(), 0, 300));
		}

		handler.register(recipeId("campfire_cooking", "stick"), id -> new CampfireCookingRecipe(id,
				"", MC.asIngredient("stick"), MC.asStack("torch"), 0, 20));

		handler.register(recipeId("blasting", "nickel_compound"),
				id -> VanillaRecipeBuilders.blastingRecipe(id, "",
						CABF.asIngredient("nickel_compound"), CABF.asStack("invar_compound"), 0.1F,
						400));

		handler.register(recipeId("crushing", "crushing_wheel"),
				id -> new CrushingRecipe(new FreePRP(id)
						.setIngredient(CR.asIngredient("crushing_wheel"))
						.setResult(AE2.asProcessingOutput("singularity")).setProcessingTime(250)));

		handler.register(recipeId("compacting", "dye_entangled_singularity"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofTag(Tags.Items.DYES),
								AE2.asIngredient("quantum_entangled_singularity"))
						.setResult(CABF.asProcessingOutput("dye_entangled_singularity"))));

		List<Item> balls = List.of(AE2.asItem("red_paint_ball"), AE2.asItem("yellow_paint_ball"),
				AE2.asItem("green_paint_ball"), AE2.asItem("blue_paint_ball"),
				AE2.asItem("magenta_paint_ball"));

		handler.register(recipeId("crushing", "dye_entangled_singularity"),
				id -> new CrushingRecipe(new FreePRP(id)
						.setIngredient(CABF.asIngredient("dye_entangled_singularity"))
						.setResult(balls.stream()
								.map(item -> new ProcessingOutput(item.getDefaultStack(),
										0.9f - 0.1f * balls.indexOf(item)))
								.toList())
						.setProcessingTime(50)));

		for (Item item : balls) {
			int index = balls.indexOf(item);
			Item output = index < balls.size() - 1 ? balls.get(index + 1)
					: AE2.asItem("black_paint_ball");
			handler.register(recipeId("emptying", Registry.ITEM.getId(item).getPath()),
					id -> new EmptyingRecipe(new FreePRP(id).setIngredient(Ingredient.ofItems(item))
							.setFluidResult(new FluidStack(CabfFluids.WASTE, FluidConstants.BOTTLE))
							.setResult(new ProcessingOutput(output.getDefaultStack(), 1))));
		}

		handler.register(recipeId("pressing", "refined_radiance"),
				id -> new PressingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(CR.asItem("refined_radiance")))
						.setResult(CABF.asProcessingOutput("radiant_sheet"))));

		handler.register(recipeId("mechanical_crafting", "radiant_coil"),
				id -> RecipeBuilderUtil.mechanicalFromShaped(
						VanillaRecipeBuilders.shapedRecipe("A")
								.ingredient('A', CabfItems.RADIANT_SHEET)
								.output(CabfItems.RADIANT_COIL.getDefaultStack()).build(id, ""),
						true));

		handler.register(recipeId("mechanical_crafting", "chromatic_compound"),
				id -> RecipeBuilderUtil
						.mechanicalFromShaped(
								VanillaRecipeBuilders.shapedRecipe("AA", "AA")
										.ingredient('A', AE2.asIngredient("magenta_paint_ball"))
										.output(CR.asStack("chromatic_compound")).build(id, ""),
								false));

		handler.register(recipeId("crafting", "chromatic_resonator"), id -> VanillaRecipeBuilders
				.shapedRecipe(" R ", "R S", "LS ").ingredient('R', CABF.asItem("ruby"))
				.ingredient('L', IR.asItem("lead_ingot")).ingredient('S', CABF.asItem("sapphire"))
				.output(CABF.asStack("chromatic_resonator")).build(id, ""));

		handler.register(recipeId("crafting", "machine_block"),
				id -> VanillaRecipeBuilders.shapedRecipe("SSS", "SCS", "SSS")
						.ingredient('C', CABF.asItem("invar_casing"))
						.ingredient('S', CABF.asItem("inductive_mechanism"))
						.output(IR.asStack("machine_block")).build(id, ""));

		handler.register(recipeId("item_application", "invar_casing"),
				id -> new ManualApplicationRecipe(new FreePRP(id)
						.setIngredient(MC.asIngredient("calcite"), CABF.asIngredient("invar_ingot"))
						.setResult(CABF.asProcessingOutput("invar_casing"))));

		handler.register(recipeId("melting", "ender_dust"), id -> RecipeManager.deserialize(id, RecipeBuilderUtil
				.generateMelting(AE2.id("ender_dust"), TC.id("molten_ender"), FluidConstants.INGOT, null, 0, 350, 35)));
	}

	@Override
	public String getLevel() {
		return "invar";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), IR.id("machine_block"), output, count, other);
	}

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		handler.remove(CR.id("mechanical_crafting", "crushing_wheel"));
		handler.removeIf(IR.predicateOutput(false, "machine_block"));
		handler.removeIf(p -> !CABF.checkContains(p) && REMOVE_OUTPUTS.stream()
				.anyMatch(id -> id.equals(Registry.ITEM.getId(p.getOutput().getItem()))));
	}
}
