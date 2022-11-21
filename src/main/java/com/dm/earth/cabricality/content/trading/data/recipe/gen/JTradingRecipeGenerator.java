package com.dm.earth.cabricality.content.trading.data.recipe.gen;

import static com.dm.earth.cabricality.util.JRecipeUtil.itemEntry;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * This util class in used to generate the JSON objects for the trading recipes.
 */
public class JTradingRecipeGenerator {
	/*
	 * This method is used to generate a json object for a trading sell recipe.
	 * @param entry The trading entry to generate the json object for.
	 */
	public static JsonObject generateSell(TradingEntry entry) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "indrev:infuse");

		JsonArray ingredients = new JsonArray();
		ingredients.add(itemEntry(entry.getItemId(), entry.getItemCount()));
		ingredients.add(itemEntry(Cabricality.id("profession_card_" + ProfessionUtil.fromTradingEntry(entry).hashString()), 0));
		json.add("ingredients", ingredients);

		json.add("output", itemEntry(entry.getCoinId(), entry.getCoinCount()));

		json.addProperty("processTime", 125);
		return json;
	}

	/*
	 * This method is used to generate a json object for a trading buy recipe.
	 * @param entry The trading entry to generate the json object for.
	 */
	public static JsonObject generateBuy(TradingEntry entry) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "indrev:infuse");

		JsonArray ingredients = new JsonArray();
		ingredients.add(itemEntry(entry.getCoinId(), entry.getCoinCount()));
		ingredients.add(itemEntry(Cabricality.id("trade_card_" + entry.hashString()), 0));
		json.add("ingredients", ingredients);

		json.add("output", itemEntry(entry.getItemId(), entry.getItemCount()));

		json.addProperty("processTime", 125);
		return json;
	}
}
