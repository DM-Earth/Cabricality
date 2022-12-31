package com.dm.earth.cabricality.content.core;

import java.util.List;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.threads.AndesiteThread;
import com.dm.earth.cabricality.content.core.threads.BrassThread;
import com.dm.earth.cabricality.content.core.threads.CopperThread;
import com.dm.earth.cabricality.content.core.threads.EnderiumThread;
import com.dm.earth.cabricality.content.core.threads.FluixThread;
import com.dm.earth.cabricality.content.core.threads.InvarThread;
import com.dm.earth.cabricality.content.core.threads.MathThread;
import com.dm.earth.cabricality.content.core.threads.ObsidianThread;
import com.dm.earth.cabricality.content.core.threads.ZincThread;
import net.minecraft.util.Identifier;

public interface TechThread {
	List<TechThread> THREADS = List.of(new AndesiteThread(), new BrassThread(), new CopperThread(),
			new ZincThread(), new ObsidianThread(), new InvarThread(), new EnderiumThread(),
			new FluixThread(), new MathThread());

	default void addRecipes(AddRecipesCallback.RecipeHandler handler) {}

	default void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {}

	default void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {}

	default void load() {};

	String getLevel();

	default Identifier recipeId(String type, String id) {
		return Cabricality.id("thread/" + this.getLevel() + "/" + type + "/" + id);
	}
}
