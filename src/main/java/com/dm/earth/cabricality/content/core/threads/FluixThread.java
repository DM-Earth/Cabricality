package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.util.math.RecipeBuilderUtil;
import net.minecraft.util.Identifier;

public class FluixThread implements TechThread {

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(AE2.id("condenser"), 1, AE2.id("fluix_pearl")));
		MechAndSmithCraft.addEntry(entry(AE2.id("drive"), 1, AE2.id("engineering_processor")));
		MechAndSmithCraft.addEntry(entry(AE2.id("formation_core"), 4, AE2.id("logic_processor")));
		MechAndSmithCraft
				.addEntry(entry(AE2.id("annihilation_core"), 4, AE2.id("calculation_processor")));
		MechAndSmithCraft.addEntry(entry(AE2.id("chest"), 1, MC.id("chest")));
	}

	@Override
	public String getLevel() {
		return "fluix";
	}

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		handler.register(recipeId("crafting", "flash_drive"),
				id -> VanillaRecipeBuilders.shapedRecipe("SCA")
						.ingredient('A', TC.asIngredient("cobalt_ingot"))
						.ingredient('C', AE2.asIngredient("logic_processor"))
						.ingredient('S', MC.asIngredient("iron_ingot"))
						.output(CABF.asStack("flash_drive")).build(id, ""));
		handler.register(recipeId("crafting", "controller"),
				id -> RecipeBuilderUtil.donutRecipe(id, CABF.asItem("fluix_casing"),
						CABF.asItem("calculation_mechanism"), AE2.asItem("controller"), 1));
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), AE2.id("controller"), output, count, other);
	}

}
