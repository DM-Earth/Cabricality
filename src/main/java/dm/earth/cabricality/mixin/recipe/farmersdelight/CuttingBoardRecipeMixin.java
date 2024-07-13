package dm.earth.cabricality.mixin.recipe.farmersdelight;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

@Mixin(CuttingBoardRecipe.class)
public class CuttingBoardRecipeMixin {
	@Shadow
	@Final
	private DefaultedList<ChanceResult> results;

	@Inject(method = "getResult", at = @At("HEAD"), cancellable = true)
	private void getOutput(CallbackInfoReturnable<ItemStack> cir) {
		if (this.results.isEmpty())
			cir.setReturnValue(ItemStack.EMPTY);
	}
}
