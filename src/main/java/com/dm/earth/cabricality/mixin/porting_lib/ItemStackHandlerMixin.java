package com.dm.earth.cabricality.mixin.porting_lib;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStackHandler.class, remap = false)
public class ItemStackHandlerMixin {
	@Inject(method = "insert(Lnet/fabricmc/fabric/api/transfer/v1/item/ItemVariant;JLnet/fabricmc/fabric/api/transfer/v1/transaction/TransactionContext;)J", at = @At("HEAD"), cancellable = true, remap = false)
	private void patch1(ItemVariant resource, long maxAmount, TransactionContext transaction, CallbackInfoReturnable<Long> cir) {
		try {
			StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		} catch (Throwable e) {
			cir.setReturnValue(0L);
		}
	}

	@Inject(method = "extract(Lnet/fabricmc/fabric/api/transfer/v1/item/ItemVariant;JLnet/fabricmc/fabric/api/transfer/v1/transaction/TransactionContext;)J", at = @At("HEAD"), cancellable = true, remap = false)
	private void patch2(ItemVariant resource, long maxAmount, TransactionContext transaction, CallbackInfoReturnable<Long> cir) {
		try {
			StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		} catch (Throwable e) {
			cir.setReturnValue(0L);
		}
	}
}
