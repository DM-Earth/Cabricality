package com.dm.earth.cabricality.mixin.log.log4j;

import com.dm.earth.cabricality.util.debug.Log4jLogger;
import me.shedaniel.rei.RoughlyEnoughItemsState;
import me.shedaniel.rei.impl.Internals;
import me.shedaniel.rei.impl.common.InternalLogger;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RoughlyEnoughItemsState.class)
public class RoughlyEnoughItemsStateLogger {
	@Mutable
	@Shadow(remap = false)
	@Final
	public static Logger LOGGER = new Log4jLogger();
}

@Mixin(Internals.class)
class InternalsLogger {
	@Inject(method = "getInternalLogger", at = @At("RETURN"), cancellable = true, remap = false)
	private static void getInternalLogger(CallbackInfoReturnable<InternalLogger> cir) {
		cir.setReturnValue(new com.dm.earth.cabricality.util.debug.InternalLogger());
	}
}
