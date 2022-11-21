package com.dm.earth.cabricality.client;

import java.util.HashMap;
import java.util.Map;

public class FluidColorRegistry {
	private static final Map<String, Integer> colors = new HashMap<>();

	public static void register(String fluid, int color) {
		if (!colors.containsKey(fluid)) colors.put(fluid, color);
	}

	public static int get(String name) {
		if (!colors.containsKey(name)) return -1;
		return colors.get(name);
	}
}
