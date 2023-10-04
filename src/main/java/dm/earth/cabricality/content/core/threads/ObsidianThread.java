package dm.earth.cabricality.content.core.threads;

import dm.earth.cabricality.content.core.TechThread;
import dm.earth.cabricality.lib.util.RecipeUtil;
import dm.earth.cabricality.tweak.base.MechAndSmithCraft;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dm.earth.cabricality.Mod.Entry.CABF;
import static dm.earth.cabricality.Mod.Entry.CREATE;
import static dm.earth.cabricality.Mod.Entry.MC;

public class ObsidianThread implements TechThread {
	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		handler.register(
				recipeId("crafting", "obsidian_machine"),
				id -> RecipeUtil.donutRecipe(
						id,
						CREATE.asItem("railway_casing"),
						CABF.asItem("sturdy_mechanism"),
						CABF.asItem("obsidian_machine"),
						1
				)
		);
	}

	@Override
	public void load() {
		MechAndSmithCraft.addEntry(entry(CREATE.id("track_station"), 1, null));
		MechAndSmithCraft.addEntry(entry(CREATE.id("track_signal"), 1, CREATE.id("electron_tube")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("track_observer"), 1, MC.id("observer")));
		MechAndSmithCraft.addEntry(entry(CREATE.id("controls"), 1, MC.id("lever")));
	}

	@Override
	public String getLevel() {
		return "obsidian";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("obsidian_machine"), output, count, other);
	}
}
