package com.dm.earth.cabricality.test;

import java.util.Arrays;
import java.util.Objects;

public class CabfTest {
	public static void main(String[] args) {
		// Colors
		String[] COLORS = {
				"black", "red", "green", "brown", "blue", "purple",
				"cyan", "light_gray", "gray", "pink", "lime",
				"yellow", "light_blue", "magenta", "orange", "white"
		};
		// Functional blocks
		Arrays.stream(new String[] { "switch", "button" }).forEach(
				block -> Arrays.stream(COLORS).forEach(
						color -> System.out.println(joinAll(block, color))));
		// Lamps
		Arrays.stream(new String[] { null, "shaded", "reinforced", "shaded_reinforced" }).forEach(
				prefix -> {
					// Sizes
					{
						final String postfix = "fixture";
						Arrays.stream(new String[] { "flat", "large", "medium", "small" }).forEach(
								size -> Arrays.stream(COLORS).forEach(
										color -> System.out.println(joinAll(prefix, size, postfix, color))));
					}
					// Clear full blocks
					{
						final String postfix = "clear_full";
						Arrays.stream(COLORS).forEach(color -> System.out.println(joinAll(prefix, postfix, color)));
					}
				});
	}

	private static String joinAll(String... subs) {
		if (subs.length < 1)
			return "";
		return Arrays.stream(subs).filter(Objects::nonNull).filter(s -> !s.isEmpty()).reduce((f, s) -> f + "_" + s)
				.orElse(subs[0]);
	}
}
