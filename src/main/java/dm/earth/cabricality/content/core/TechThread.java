package dm.earth.cabricality.content.core;

import java.util.List;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.core.threads.AndesiteThread;
import dm.earth.cabricality.content.core.threads.BrassThread;
import dm.earth.cabricality.content.core.threads.CopperThread;
import dm.earth.cabricality.content.core.threads.EnderiumThread;
import dm.earth.cabricality.content.core.threads.FluixThread;
import dm.earth.cabricality.content.core.threads.InvarThread;
import dm.earth.cabricality.content.core.threads.MathThread;
import dm.earth.cabricality.content.core.threads.ObsidianThread;
import dm.earth.cabricality.content.core.threads.ZincThread;
import net.minecraft.util.Identifier;

public interface TechThread {
	List<TechThread> THREADS = List.of(
			new AndesiteThread(),
			new BrassThread(),
			new CopperThread(),
			new ZincThread(),
			new ObsidianThread(),
			new InvarThread(),
			new EnderiumThread(),
			new FluixThread(),
			new MathThread()
	);

	default void addRecipes(AddRecipesCallback.RecipeHandler handler) {}

	default void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {}

	default void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {}

	default void load() {};

	String getLevel();

	default Identifier recipeId(String type, String id) {
		return Cabricality.id("thread/" + this.getLevel() + "/" + type + "/" + id);
	}
}
