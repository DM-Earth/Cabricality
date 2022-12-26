package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback.RecipeHandler;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
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

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count,
			@Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), AE2.id("controller"), output, count, other);
	}

	@Override
	public void modifyRecipes(RecipeHandler handler) {
	}

}
