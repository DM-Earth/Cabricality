package com.dm.earth.cabricality.content.threads;

import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.MC;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;

import net.minecraft.util.Identifier;

public interface TechThreads {
	public static final List<TechThreads> THREADS = List.of(new Andesite());

	default void addRecipes(AddRecipesCallback.RecipeHandler handler) {
	}

	default void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {
	}

	default void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
	}

	void load();

	String getLevel();

	public static class Andesite implements TechThreads {
		@Override
		public void load() {
			MechAndSmithCraft.addEntry(entry(CR.id("mechanical_press"), 1, MC.id("iron_block")));
		}

		@Override
		public String getLevel() {
			return "andesite";
		}

		private MechAndSmithCraft.Entry entry(Identifier output, int count, @Nullable Identifier other) {
			return MechAndSmithCraft.entry(this.getLevel(), CABF.id("andesite_machine"), output, count, other);
		}
	}
}
