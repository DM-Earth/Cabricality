package com.dm.earth.cabricality.tweak;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.ModifyRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;

import com.dm.earth.cabricality.tweak.cutting.CuttingRecipeTweaks;

public class RecipeTweaks implements AddRecipesCallback, RemoveRecipesCallback, ModifyRecipesCallback {

    @Override
    public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
        CuttingRecipeTweaks.register(handler);
    }

    @Override
    public void modifyRecipes(ModifyRecipesCallback.RecipeHandler handler) {

    }

    @Override
    public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {

    }

    public static void load() {
        RecipeTweaks thisObject = new RecipeTweaks();
        RecipeManagerHelper.addRecipes(thisObject);
        RecipeManagerHelper.modifyRecipes(thisObject);
        RecipeManagerHelper.removeRecipes(thisObject);
    }

}
