package dm.earth.cabricality.mixin.recipe.farmersdelight;

import dm.earth.cabricality.lib.resource.data.recipe.ProcessItemOutputCallback;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

@Mixin(ChanceResult.class)
public class ChanceResultMixin {
	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static ItemStack modifyOutput(ItemStack itemStack) {
		return ProcessItemOutputCallback.EVENT.invoker().processOutput(itemStack);
	}
}
