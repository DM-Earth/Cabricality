package dm.earth.cabricality.content.alchemist.data;

import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.alchemist.Reagents;
import dm.earth.cabricality.content.alchemist.core.Catalyst;
import dm.earth.cabricality.content.alchemist.core.Reagent;
import dm.earth.cabricality.content.entries.CabfItemTags;
import dm.earth.cabricality.lib.math.RandomMath;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import ho.artisan.lib.recipe.api.RecipeManagerHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;

import static dm.earth.cabricality.lib.util.RecipeUtil.JsonBuilder.fluidEntry;
import static dm.earth.cabricality.lib.util.RecipeUtil.JsonBuilder.itemEntry;

public class JarData implements RecipeLoadingEvents.AddRecipesCallback, LoadTagsCallback<Item> {
	public static void load() {
		JarData thisL = new JarData();
		LoadTagsCallback.ITEM.register(thisL);
		RecipeManagerHelper.addRecipes(thisL);
	}

	private static JsonObject generateAlchemistProcess(Reagent reagent, ArrayList<Reagent> reagents, boolean smelt) {
		ArrayList<Reagent> reagentsList = new ArrayList<>();
		Reagents targetReagents = Reagents.get(reagent);
		long seed = reagent.hashCode();
		reagents.stream().filter(r -> !targetReagents.getReagents().contains(r)).forEach(reagentsList::add);

		Reagent reagentFirst = RandomMath.randomSelect(reagentsList, 1, seed - 1).get(0);
		Reagent reagentSecond = RandomMath.randomSelect(reagentsList, 1, seed + 1).get(0);
		Catalyst catalyst = targetReagents.getCatalyst();
		int count = targetReagents.getPrice();

		Identifier recipeType = new Identifier("create", smelt ? "compacting" : "mixing");

		// Generate Json
		JsonObject json = new JsonObject();
		json.addProperty("type", recipeType.toString());
		JsonArray ingredients = new JsonArray();
		ingredients.add(itemEntry(Cabricality.id("reagent_jar_" + reagentFirst.hashString())));
		ingredients.add(itemEntry(Cabricality.id("reagent_jar_" + reagentSecond.hashString())));
		ingredients.add(itemEntry(Cabricality.id("catalyst_jar_" + catalyst.hashString())));
		json.add("ingredients", ingredients);
		JsonArray results = new JsonArray();
		results.add(itemEntry(Cabricality.id("reagent_jar_" + reagent.hashString()), count));
		results.add(itemEntry(Cabricality.id("catalyst_jar_" + catalyst.hashString())));
		json.add("results", results);
		json.addProperty("heatRequirement", "superheated");

		return json;
	}

	private static JsonObject generateInfuse(Reagent reagent) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "indrev:fluid_infuse");
		JsonArray ingredients = new JsonArray();
		ingredients.add(itemEntry(reagent.getItemId(), 1));
		json.add("ingredients", ingredients);
		json.add("fluidInput", fluidEntry(new Identifier("tconstruct", "molten_glass"), FluidConstants.BOTTLE));
		json.add("output", itemEntry(Cabricality.id("reagent_jar_" + reagent.hashString()), 1));
		json.addProperty("processTime", 350);
		return json;
	}

	private static JsonObject generateReagentToItem(Reagent reagent) {
		Identifier reagentId = Cabricality.id("reagent_jar_" + reagent.hashString());
		JsonObject json = new JsonObject();
		json.addProperty("type", "indrev:sawmill");
		json.add("ingredients", itemEntry(reagentId, 1));
		JsonArray outputs = new JsonArray();
		outputs.add(itemEntry(reagent.getItemId(), 1));
		json.add("output", outputs);
		json.addProperty("processTime", 64);
		return json;
	}

	@Override
	public void onTagsLoad(TagHandler<Item> handler) {
		Arrays.stream(Reagents.values()).forEach(reagents -> {
			Identifier catalystId = Cabricality.id("catalyst_jar_" + reagents.getCatalyst().hashString());
			handler.register(CabfItemTags.JARS, Registries.ITEM.get(catalystId));
			handler.register(CabfItemTags.CATALYST_JARS, Registries.ITEM.get(catalystId));

			reagents.getReagents().forEach(reagent -> {
				Identifier reagentId = Cabricality.id("reagent_jar_" + reagent.hashString());
				handler.register(CabfItemTags.JARS, Registries.ITEM.get(reagentId));
				handler.register(CabfItemTags.REAGENT_JARS, Registries.ITEM.get(reagentId));
			});
		});
	}

	@Override
	public void addRecipes(RecipeHandler handler) {
		ArrayList<Reagent> reagents = new ArrayList<>();

		/*
		// Scan substrates
		for (Reagents reagentsT : Reagents.values())
			for (Reagent reagent : reagentsT.getReagents()) {
				if (reagentsT.isLinked())
					reagents.add(reagent);
				handler.register(Cabricality.id("alchemist", "fluid_infuse", "reagent_jar", reagent.hashString()),
						id -> RecipeManager.deserialize(id, generateInfuse(reagent)).value());
				handler.register(Cabricality.id("alchemist", "sawmill", "reagent_jar", reagent.hashString()),
						id -> RecipeManager.deserialize(id, generateReagentToItem(reagent)).value());
			}

		// Alchemist Recipes
		final AtomicBoolean smelt = new AtomicBoolean(false);
		Arrays.stream(Reagents.values()).filter(Reagents::isLinked).forEach(r -> {
			r.getReagents().forEach(reagent -> {
				Identifier recipeId = Cabricality.id(
						"alchemist", "alchemist_smelt",
						r.getCatalyst().hashString() + "/" + reagent.hashString());
				var recipe = RecipeManager.deserialize(recipeId,
						generateAlchemistProcess(reagent, reagents, smelt.get()));
				handler.register(recipeId, id -> recipe.value());
			});
			smelt.set(!smelt.get());
		});

		 */
	}
}
