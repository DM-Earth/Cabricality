package dm.earth.cabricality.content.entries;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.math.CalculationRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class CabfRecipeSerializers {
	/*
	public static final SpecialRecipeSerializer<CalculationRecipe> CALCULATION = registerSerializer(
			"crafting_special_calculation", new SpecialRecipeSerializer<>(CalculationRecipe::new)
	);
	
	 */

	private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String name, S serializer) {
		return Registry.register(Registries.RECIPE_SERIALIZER, Cabricality.id(name), serializer);
	}

	public static void register() {}
}
