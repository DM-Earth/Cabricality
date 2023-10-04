package dm.earth.cabricality.content.core.threads;

import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dm.earth.cabricality.Mod.Entry.AD_ASTRA;
import static dm.earth.cabricality.Mod.Entry.CABF;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.EXT_DRAWERS;
import static dm.earth.cabricality.Mod.Entry.INDREV;
import static dm.earth.cabricality.Mod.Entry.KIBE;
import static dm.earth.cabricality.Mod.Entry.MC;

@SuppressWarnings("UnstableApiUsage")
public class ZincThread implements TechThread {
	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.@NotNull RecipeHandler handler) {
		/*
		handler.register(
				recipeId("mixing", "liquid_soul"),
				id -> new MixingRecipe(new FreePRP(id)
						.setIngredient(
								Ingredient.ofItems(Items.WEEPING_VINES),
								Ingredient.ofItems(Items.TWISTING_VINES)
						)
						.setFluidResult(new FluidStack(TC.asFluid("liquid_soul"), FluidConstants.BOTTLE))
						.setHeatRequirement(HeatCondition.HEATED))
		);

		 */

		handler.register(
				recipeId("crafting", "zinc_machine"),
				id -> RecipeBuilderUtil.donutRecipe(
						id,
						CABF.asItem("zinc_casing"),
						CABF.asItem("infernal_mechanism"),
						CABF.asItem("zinc_machine"),
						1
				)
		);

		handler.register(
				recipeId("item_application", "zinc_casing"),
				id -> new ManualApplicationRecipe(new FreePRP(id)
						.setIngredient(
								Ingredient.ofTag(ItemTags.STONE_CRAFTING_MATERIALS),
								CABF.asIngredient("zinc_sheet")
						)
						.setResult(CABF.asProcessingOutput("zinc_casing")))
		);
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(KIBE.id("cobblestone_generator_mk1"), 1, MC.id("piston")));
		MechAndSmithCraft.addEntry(entry(KIBE.id("basalt_generator_mk1"), 1, MC.id("blue_ice")));
		MechAndSmithCraft.addEntry(entry(KIBE.id("trash_can"), 1, MC.id("lava_bucket")));
		MechAndSmithCraft.addEntry(entry(KIBE.id("vacuum_hopper"), 1, CREATE.id("nozzle")));
		MechAndSmithCraft.addEntry(entry(KIBE.id("big_torch"), 1, MC.id("torch")));
		MechAndSmithCraft.addEntry(entry(AD_ASTRA.id("solar_panel"), 1, MC.id("glass")));
		MechAndSmithCraft.addEntry(entry(INDREV.id("tier_upgrade_mk3"), 1, MC.id("redstone")));
		MechAndSmithCraft.addEntry(entry(EXT_DRAWERS.id("controller"), 1, EXT_DRAWERS.id("connector")));
	}

	@Override
	public String getLevel() {
		return "zinc";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("zinc_machine"), output, count, other);
	}
}
