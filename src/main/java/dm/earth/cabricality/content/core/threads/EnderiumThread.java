package dm.earth.cabricality.content.core.threads;

import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import ho.artisan.lib.recipe.api.builder.VanillaRecipeBuilders;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dm.earth.cabricality.ModEntry.AE2;
import static dm.earth.cabricality.ModEntry.CABF;
import static dm.earth.cabricality.ModEntry.CREATE;
import static dm.earth.cabricality.ModEntry.INDREV;
import static dm.earth.cabricality.ModEntry.KIBE;
import static dm.earth.cabricality.ModEntry.MC;

public class EnderiumThread implements TechThread {
	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(KIBE.id("entangled_chest"), 1, MC.id("chest")));
		MechAndSmithCraft.addEntry(entry(KIBE.id("entangled_tank"), 1, CREATE.id("fluid_tank")));
		MechAndSmithCraft.addEntry(entry(INDREV.id("tier_upgrade_mk4"), 1, MC.id("redstone")));
		MechAndSmithCraft.addEntry(entry(AE2.id("quantum_ring"), 1, AE2.id("energy_cell")));
		MechAndSmithCraft.addEntry(entry(AE2.id("quantum_link"), 1, AE2.id("fluix_pearl")));
	}

	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		handler.register(
				recipeId("crafting", "enderium_machine"),
				id -> VanillaRecipeBuilders
						.shapedRecipe("SSS", "SCS", "SSS")
						.ingredient('S', CABF.asIngredient("abstruse_mechanism"))
						.ingredient('C', CABF.asIngredient("enderium_casing"))
						.output(CABF.asStack("enderium_machine"))
						.build(id, "")
		);

		/*
		handler.register(
				recipeId("melting", "dark_amaranth_fungus"),
				id -> RecipeManager.deserialize(id, RecipeBuilderUtil.generateMelting(
						PM.id("dark_amaranth_fungus"),
						TC.id("ender_slime"),
						FluidConstants.BOTTLE,
						null, 0, 100, 10
				)).value()
		);

		 */

		handler.register(
				recipeId("item_application", "enderium_casing"),
				id -> new ManualApplicationRecipe(new FreePRP(id)
						.setIngredient(MC.asIngredient("obsidian"), CABF.asIngredient("enderium_ingot"))
						.setResult(CABF.asProcessingOutput("enderium_casing")))
		);
	}

	@Override
	public String getLevel() {
		return "enderium";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("enderium_machine"), output, count, other);
	}
}
