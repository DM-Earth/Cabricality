package com.dm.earth.cabricality.mixin.client.create;

import com.dm.earth.tags_binder.api.ResourceConditionCheckTagCallback;
import com.simibubi.create.foundation.item.TagDependentIngredientItem;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TagDependentIngredientItem.class)
public class TagDependentIngredientItemMixin {
	@Shadow(remap = false)
	private TagKey<Item> tag;

	@Inject(method = "shouldHide", at = @At("HEAD"), cancellable = true, remap = false)
	private void inserted(CallbackInfoReturnable<Boolean> cir) {
		ActionResult result = ResourceConditionCheckTagCallback.ITEM.invoker().apply(this.tag);
		if (result.isAccepted())
			cir.setReturnValue(false);
		else if (result == ActionResult.FAIL)
			cir.setReturnValue(true);
	}
}
