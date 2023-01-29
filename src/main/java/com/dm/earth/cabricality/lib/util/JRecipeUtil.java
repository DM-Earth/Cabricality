package com.dm.earth.cabricality.lib.util;

import com.google.gson.JsonObject;

import net.minecraft.util.Identifier;

public class JRecipeUtil {
    public static JsonObject itemEntry(Identifier id, int count) {
		JsonObject json = new JsonObject();
		json.addProperty("item", id.toString());
		json.addProperty("count", count);
		return json;
	}

    public static JsonObject itemEntry(Identifier id) {
		JsonObject json = new JsonObject();
		json.addProperty("item", id.toString());
		return json;
	}

    public static JsonObject itemEntry(Identifier id, int count, double chance) {
		JsonObject json = itemEntry(id, count);
        json.addProperty("chance", chance);
		return json;
	}

    public static JsonObject fluidEntry(Identifier id, int amount) {
        JsonObject json = new JsonObject();
        json.addProperty("fluid", id.toString());
        json.addProperty("amount", amount);
        return json;
    }

    public static JsonObject fluidEntry(Identifier id , long amount) {
        return fluidEntry(id, (int) amount);
    }
}
