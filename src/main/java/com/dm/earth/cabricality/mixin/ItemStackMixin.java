package com.dm.earth.cabricality.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Shadow
	private boolean empty;

	@Inject(method = "getItem", at = @At("RETURN"), cancellable = true)
	private void fix(CallbackInfoReturnable<Item> cir) {
		if (cir.getReturnValue() == null) {
			this.empty = true;
			cir.setReturnValue(Items.AIR);
		}
	}
}
