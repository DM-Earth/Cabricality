package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.math.CalculationRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.registry.Registry;

public class CabfRecipeSerializers {

	public static final SpecialRecipeSerializer<CalculationRecipe> CALCULATION = registerSerializer(
			"crafting_special_calculation", new SpecialRecipeSerializer<>(CalculationRecipe::new));

	private static <T extends RecipeSerializer<?>> T registerSerializer(String name, T obj) {
		return Registry.register(Registry.RECIPE_SERIALIZER, Cabricality.id(name), obj);
	}

	public static void register() {}
}
