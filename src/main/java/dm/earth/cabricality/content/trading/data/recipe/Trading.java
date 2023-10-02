package dm.earth.cabricality.content.trading.data.recipe;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.trading.Professions;
import dm.earth.cabricality.content.trading.core.Profession;
import dm.earth.cabricality.content.trading.core.TradingEntry;
import dm.earth.cabricality.content.trading.data.tag.TradeTags;
import dm.earth.cabricality.content.trading.util.ProfessionUtil;
import dm.earth.cabricality.lib.util.debug.CabfDebugger;
import me.steven.indrev.recipes.machines.InfuserRecipe;
import me.steven.indrev.recipes.machines.entries.InputEntry;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import static dm.earth.cabricality.ModEntry.CABF;

public class Trading implements AddRecipesCallback, RemoveRecipesCallback {

	@Override
	public void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		for (Professions rawProfession : Professions.values()) {
			Profession profession = rawProfession.get();
			Item professionCard = Registries.ITEM.get(Cabricality.id("profession_card_" + profession.hashString()));
			handler.register(Cabricality.id("crafting/dupe/card/profession_card/" + profession.hashString()),
					id -> genDupeRecipe(professionCard, id));
			for (TradingEntry entry : profession.entries()) {
				handler.register(Cabricality.id("trading/buy/" + profession.hashString() + "/" + entry.hashString()),
						id -> generateBuy(id, entry));
				handler.register(Cabricality.id("trading/sell/" + profession.hashString() + "/" + entry.hashString()),
						id -> generateSell(id, entry));

				handler.register(Cabricality.id("crafting/dupe/card/trade_card/" + entry.hashString()),
						id -> genDupeRecipe(Registries.ITEM.get(Cabricality.id("trade_card_" + entry.hashString())), id));
			}
		}
	}

	@Override
	public void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		CabfDebugger.debug("Removing infuse recipes!");
		handler.removeIf(Registries.RECIPE_TYPE.get(new Identifier("indrev", "infuse")),
				p -> !CABF.checkContains(handler, p));
	}

	private static ShapelessRecipe genDupeRecipe(Item item, Identifier id) {
		DefaultedList<Ingredient> ingredients = DefaultedList.of();
		ingredients.add(Ingredient.ofItems(item));
		/*
		return new ShapelessRecipe(
				"cabricality_dupe",
				CraftingCategory.MISC,
				new ItemStack(item, 2),
				ingredients
		);

		 */
		return null;
	}

	public static void load() {
		Professions.load();
		TradeTags.load();
	}

	private static InfuserRecipe generateSell(Identifier id, TradingEntry entry) {
		InputEntry[] inputs = {
				new InputEntry(Ingredient.ofItems(entry.getItem()), entry.getItemCount()),
				new InputEntry(Ingredient.ofItems(CABF.asItem(
						"profession_card_"
								+ ProfessionUtil.fromTradingEntry(entry).hashString())
				), 0)
		};

		OutputEntry[] outputs = { new OutputEntry(new ItemStack(entry.getCoin(), entry.getCoinCount()), 1) };

		return new InfuserRecipe(id, inputs, outputs, 120);
	}

	private static InfuserRecipe generateBuy(Identifier id, TradingEntry entry) {
		InputEntry[] inputs = {
				new InputEntry(Ingredient.ofItems(entry.getCoin()), entry.getCoinCount()),
				new InputEntry(Ingredient.ofItems(CABF.asItem("trade_card_" + entry.hashString())), 0)
		};

		OutputEntry[] outputs = {
				new OutputEntry(new ItemStack(entry.getItem(), entry.getItemCount()), 1)
		};

		return new InfuserRecipe(id, inputs, outputs, 120);
	}

}
