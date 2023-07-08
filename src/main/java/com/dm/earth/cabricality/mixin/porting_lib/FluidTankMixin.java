package com.dm.earth.cabricality.mixin.porting_lib;

import io.github.fabricators_of_create.porting_lib.transfer.fluid.FluidTank;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FluidTank.class, remap = false)
public class FluidTankMixin {
	@Inject(method = "insert(Lnet/fabricmc/fabric/api/transfer/v1/fluid/FluidVariant;JLnet/fabricmc/fabric/api/transfer/v1/transaction/TransactionContext;)J", at = @At("HEAD"), cancellable = true, remap = false)
	private void patch1(FluidVariant insertedVariant, long maxAmount, TransactionContext transaction, CallbackInfoReturnable<Long> cir) {
		try {
			StoragePreconditions.notBlankNotNegative(insertedVariant, maxAmount);
		} catch (Throwable e) {
			cir.setReturnValue(0L);
		}
	}

	@Inject(method = "extract(Lnet/fabricmc/fabric/api/transfer/v1/fluid/FluidVariant;JLnet/fabricmc/fabric/api/transfer/v1/transaction/TransactionContext;)J", at = @At("HEAD"), cancellable = true, remap = false)
	private void patch2(FluidVariant extractedVariant, long maxAmount, TransactionContext transaction, CallbackInfoReturnable<Long> cir) {
		try {
			StoragePreconditions.notBlankNotNegative(extractedVariant, maxAmount);
		} catch (Throwable e) {
			cir.setReturnValue(0L);
		}
	}
}
