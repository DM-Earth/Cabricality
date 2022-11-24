package com.dm.earth.cabricality.content.core;

import java.util.List;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.threads.Andesite;

import net.minecraft.util.Identifier;

public interface TechThread {
	public static final List<TechThread> THREADS = List.of(new Andesite());

	default void addRecipes(AddRecipesCallback.RecipeHandler handler) {
	}

	default void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {
	}

	default void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
	}

	void load();

	String getLevel();

	default Identifier recipeId(String type, String id) {
		return Cabricality.id("thread/" + this.getLevel() + "/" + type + "/" + id);
	}
}
