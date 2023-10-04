package dm.earth.cabricality.content.core.threads;

import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.content.entries.CabfFluids;
import dm.earth.cabricality.content.entries.CabfItems;
import dm.earth.cabricality.lib.util.RecipeUtil;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import ho.artisan.lib.recipe.api.builder.VanillaRecipeBuilders;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CookingCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dm.earth.cabricality.Mod.Entry.CABF;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.COXINHA;
import static dm.earth.cabricality.Mod.Entry.INDREV;
import static dm.earth.cabricality.Mod.Entry.KIBE;
import static dm.earth.cabricality.Mod.Entry.MC;

@SuppressWarnings("UnstableApiUsage")
public class CopperThread implements TechThread {
	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.@NotNull RecipeHandler handler) {
		handler.register(
				recipeId("crafting", "belt_connector"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("XXX", "XXX")
						.ingredient('X', CabfItems.CURED_RUBBER)
						.output(new ItemStack(CREATE.asItem("belt_connector"), 3))
						.build(id, "")
		);

		handler.register(
				recipeId("smelting", "cured_rubber"),
				id -> VanillaRecipeBuilders.smeltingRecipe(
						id, "",
						Ingredient.ofItems(CabfItems.RUBBER),
						CookingCategory.MISC,
						CabfItems.CURED_RUBBER.getDefaultStack(),
						0.1F, 120
				)
		);

		handler.register(
				recipeId("crafting", "sealed_mechanism"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("XOX")
						.ingredient('X', Ingredient.ofItems(CabfItems.CURED_RUBBER))
						.ingredient('O', Ingredient.ofItems(CABF.asItem("kinetic_mechanism")))
						.output(CABF.asItem("sealed_mechanism").getDefaultStack())
						.build(id, "")
		);

		handler.register(
				recipeId("crafting", "copper_machine"),
				id -> RecipeUtil.donutRecipe(
						id,
						CREATE.asItem("copper_casing"),
						CABF.asItem("sealed_mechanism"),
						CABF.asItem("copper_machine"),
						1
				)
		);

		handler.register(
				recipeId("compacting", "rubber"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(CabfFluids.RESIN, FluidConstants.BOTTLE))
						.setResult(new ProcessingOutput(CabfItems.RUBBER.getDefaultStack(), 1)))
		);

		handler.register(
				recipeId("compacting", "rubber_from_flower"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE))
						.setIngredient(
								Ingredient.ofTag(ItemTags.FLOWERS), Ingredient.ofTag(ItemTags.FLOWERS),
								Ingredient.ofTag(ItemTags.FLOWERS), Ingredient.ofTag(ItemTags.FLOWERS)
						)
						.setResult(new ProcessingOutput(CabfItems.RUBBER.getDefaultStack(), 1)))
		);

		handler.register(
				recipeId("compacting", "rubber_from_vine"),
				id -> new CompactingRecipe(new FreePRP(id)
						.setFluidIngredient(FluidIngredient.fromFluid(Fluids.WATER, FluidConstants.BOTTLE))
						.setIngredient(
								Ingredient.ofItems(Items.VINE), Ingredient.ofItems(Items.VINE),
								Ingredient.ofItems(Items.VINE), Ingredient.ofItems(Items.VINE)
						)
						.setResult(new ProcessingOutput(CabfItems.RUBBER.getDefaultStack(), 1)))
		);
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.@NotNull RecipeHandler handler) {
		handler.remove(CREATE.id("crafting", "kinetics", "belt_connector"));
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(CREATE.id("copper_backtank"), 1, MC.id("copper_block")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("portable_fluid_interface"), 2, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("spout"), 1, KIBE.id("fluid_hopper")));
		MechAndSmithCraft.addEntry(entry(INDREV.id("tier_upgrade_mk2"), 1, MC.id("redstone")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("hose_pulley"), 1, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("item_drain"), 1, MC.id("iron_bars")));
		MechAndSmithCraft.addEntry(entry(INDREV.id("heat_generator_mk4"), 1, INDREV.id("heat_coil")));
		MechAndSmithCraft.addEntry(entry(INDREV.id("fisher_mk2"), 1, MC.id("bucket")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("steam_engine"), 2, MC.id("piston")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("smart_fluid_pipe"), 2, CREATE.id("electron_tube")));
		MechAndSmithCraft.addEntry(entry(COXINHA.id("fluid_trash_can"), 2, KIBE.id("trash_can")));
	}

	@Override
	public String getLevel() {
		return "copper";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("copper_machine"), output, count, other);
	}
}
