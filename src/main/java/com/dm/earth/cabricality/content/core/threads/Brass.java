package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.CABF;

import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;

import net.minecraft.util.Identifier;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Brass implements TechThread {
	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		TechThread.super.addRecipes(handler);
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		TechThread.super.removeRecipes(handler);
	}

	@Override
	public void load() {

	}

	@Override
	public String getLevel() {
		return "brass";
	}

	@Contract("_, _, _ -> new")
	private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
		return MechAndSmithCraft.entry(this.getLevel(), CABF.id("brass_machine"), output, count, other);
	}
}
