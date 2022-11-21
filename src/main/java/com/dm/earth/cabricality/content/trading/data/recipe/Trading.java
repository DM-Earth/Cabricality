package com.dm.earth.cabricality.content.trading.data.recipe;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.data.recipe.gen.JTradingRecipeGenerator;
import com.dm.earth.cabricality.content.trading.data.tag.TradeTags;
import com.dm.earth.cabricality.util.CabfDebugger;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public class Trading {
	private static void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		for (Professions rawProfession : Professions.values()) {
			Profession profession = rawProfession.get();
			Item professionCard = Registry.ITEM.get(Cabricality.id("profession_card_" + profession.hashString()));
			handler.register(Cabricality.id("crafting/dupe/card/profession_card/" + profession.hashString()), id -> genDupeRecipe(professionCard, id));
			for (TradingEntry entry : profession.entries()) {
				CabfDebugger.debug("Registering recipe handler: " + entry.hashString());
				handler.register(Cabricality.id("trading/buy/" + profession.hashString() + "/" + entry.hashString()), id -> RecipeManager.deserialize(id, JTradingRecipeGenerator.generateBuy(entry)));
				handler.register(Cabricality.id("trading/sell/" + profession.hashString() + "/" + entry.hashString()), id -> RecipeManager.deserialize(id, JTradingRecipeGenerator.generateSell(entry)));

				Item tradeCard = Registry.ITEM.get(Cabricality.id("trade_card_" + entry.hashString()));
				handler.register(Cabricality.id("crafting/dupe/card/trade_card/" + entry.hashString()), id -> genDupeRecipe(tradeCard, id));
			}
		}
	}

	private static void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		CabfDebugger.debug("Removing infuse recipes!");
		handler.removeIf(Registry.RECIPE_TYPE.get(new Identifier("indrev", "infuse")), p -> !p.getId().getNamespace().equals("cabricality"));
	}

	private static ShapelessRecipe genDupeRecipe(Item item, Identifier id) {
		DefaultedList<Ingredient> ingredients = DefaultedList.of();
		ingredients.add(Ingredient.ofItems(item));
		return new ShapelessRecipe(id, "cabricality_dupe", new ItemStack(item, 2), ingredients);
	}

	public static void load() {
		Professions.load();
		RecipeManagerHelper.addRecipes(Trading::addRecipes);
		RecipeManagerHelper.removeRecipes(Trading::removeRecipes);
		TradeTags.load();
		JarBlock.load();
	}
}
