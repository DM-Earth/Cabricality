package dm.earth.cabricality.content.core.threads;

import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.content.entries.CabfFluids;
import dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import ho.artisan.lib.recipe.api.builder.VanillaRecipeBuilders;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dm.earth.cabricality.Mod.Entry.AE2;
import static dm.earth.cabricality.Mod.Entry.CABF;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.INDREV;
import static dm.earth.cabricality.Mod.Entry.MC;
import static dm.earth.cabricality.Mod.Entry.TC;

@SuppressWarnings("UnstableApiUsage")
public class BrassThread implements TechThread {
	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		handler.register(
				recipeId("milling", "sky_stone_block"),
				id -> new MillingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(AE2.asItem("sky_stone_block")))
						.setResult(new ProcessingOutput(
								AE2.asItem("sky_stone_block").getDefaultStack(), 1),
								new ProcessingOutput(AE2.asItem("sky_dust").getDefaultStack(), 1)
						).setProcessingTime(350))
		);

		Item redstone = Items.REDSTONE;
		handler.register(
				recipeId("crafting", "rose_quartz"),
				id -> VanillaRecipeBuilders
						.shapelessRecipe(CREATE.asItem("rose_quartz").getDefaultStack())
						.ingredient(
								Items.QUARTZ, AE2.asItem("certus_quartz_crystal"),
								AE2.asItem("charged_certus_quartz_crystal")
						)
						.ingredient(redstone)
						.ingredient(redstone)
						.ingredient(redstone)
						.build(id, "")
		);

		registerCrystalProcess(
				handler,
				AE2.asItem("certus_quartz_crystal"),
				AE2.asItem("certus_crystal_seed"),
				AE2.asItem("certus_quartz_dust")
		);

		registerCrystalProcess(
				handler,
				AE2.asItem("fluix_crystal"),
				AE2.asItem("fluix_crystal_seed"),
				AE2.asItem("fluix_dust")
		);

		/*
		handler.register(
				recipeId("mixing", "sky_stone"),
				id -> new MixingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE))
						.setIngredient(ListUtil.loop(Ingredient.ofItems(AE2.asItem("sky_dust")), 3))
						.setFluidResult(new FluidStack(
								FluidVariant.of(CabfFluids.SKY_STONE),
								FluidConstants.BOTTLE)))
		);

		handler.register(
				recipeId("mixing", "redstone"),
				id -> new MixingRecipe(new FreePRP(id)
				.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.SKY_STONE, FluidConstants.INGOT * 2))
				.setIngredient(Ingredient.ofItems(AE2.asItem("charged_certus_quartz_crystal")))
				.setFluidResult(new FluidStack(
						FluidVariant.of(CabfFluids.REDSTONE),
						FluidConstants.INGOT * 2))
				.setResult(new ProcessingOutput(AE2.asItem("certus_quartz_crystal").getDefaultStack(), 1)))
		);

		 */

		handler.register(
				recipeId("mixing", "polished_rose_quartz"),
				id -> new MixingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.REDSTONE, FluidConstants.INGOT))
						.setIngredient(Ingredient.ofItems(AE2.asItem("certus_quartz_crystal")))
						.setResult(new ProcessingOutput(CREATE.asItem("polished_rose_quartz").getDefaultStack(), 1)))
		);

		handler.register(
				recipeId("filling", "electron_tube"),
				id -> new FillingRecipe(new FreePRP(id)
						.setIngredient(Ingredient.ofItems(CREATE.asItem("polished_rose_quartz")))
						.setFluidIngredient(FluidIngredient.fromFluid(TC.asFluid("molten_iron"), FluidConstants.NUGGET))
						.setResult(new ProcessingOutput(CREATE.asItem("electron_tube").getDefaultStack(), 1)))
		);

		handler.register(
				recipeId("crafting", "brass_machine"),
				id -> RecipeBuilderUtil.donutRecipe(
						id,
						CREATE.asItem("brass_casing"),
						CREATE.asItem("precision_mechanism"),
						CABF.asItem("brass_machine"),
						1
				)
		);
	}

	private void registerCrystalProcess(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler, Item crystal, Item seed, Item dust) {
		handler.register(recipeId("milling", Registries.ITEM.getId(crystal).getPath()),
				id -> new MillingRecipe(
						new FreePRP(id)
								.setIngredient(Ingredient.ofItems(crystal))
								.setResult(new ProcessingOutput(dust.getDefaultStack(), 1))
								.setProcessingTime(200)
				)
		);

		/*
		handler.register(recipeId("mechanical_crafting", Registries.ITEM.getId(seed).getPath()),
				id -> RecipeBuilderUtil.mechanicalFromShaped(
						VanillaRecipeBuilders
								.shapedRecipe("x")
								.ingredient('x', crystal)
								.output(new ItemStack(seed, 2))
								.build(id, ""),
						false
				)
		);

		 */
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		handler.removeIf(AE2.predicateOutput(handler, false, "sky_dust"));
		handler.remove(CREATE.id("crafting", "materials", "electron_tube"));
		handler.remove(CREATE.id("crafting", "materials", "rose_quartz"));
		handler.remove(CREATE.id("sequenced_assembly", "precision_mechanism"));
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_crafter"), 3, MC.id("crafting_table")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("sequenced_gearshift"), 2, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("rotation_speed_controller"), 1, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("mechanical_arm"), 1, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("stockpile_switch"), 2, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("content_observer"), 2, null));
		MechAndSmithCraft.addEntry(entry(INDREV.id("solid_infuser_mk1"), 1, MC.id("dropper")));
		MechAndSmithCraft.addEntry(entry(INDREV.id("biomass_generator_mk3"), 1, INDREV.id("heat_coil")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("brass_funnel"), 4, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("brass_tunnel"), 4, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("elevator_pulley"), 1, MC.id("dried_kelp_block")));
	}

	@Override
	public String getLevel() {
		return "brass";
	}

	@Contract("_, _, _ -> new")
	private @NotNull MechAndSmithCraft.Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("brass_machine"), output, count, other);
	}
}
