package dm.earth.cabricality.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Ingredient.StackEntry.class)
public interface StackEntryAccessor {
	@Accessor
	ItemStack getStack();
}
