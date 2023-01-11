package com.dm.earth.cabricality.mixin.log;

import com.dm.earth.cabricality.util.debug.Log4jLogger;
import mcp.mobius.waila.Waila;
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

public class Log4jHandler {
	@Mixin(RoughlyEnoughItemsState.class)
	static class RoughlyEnoughItemsStateLogger {
		@Mutable
		@Shadow(remap = false)
		@Final
		public static Logger LOGGER = new Log4jLogger();
	}

	@Mixin(Internals.class)
	static class InternalsLogger {
		@Inject(method = "getInternalLogger", at = @At("RETURN"), cancellable = true, remap = false)
		private static void getInternalLogger(CallbackInfoReturnable<InternalLogger> cir) {
			cir.setReturnValue(new com.dm.earth.cabricality.util.debug.InternalLogger());
		}
	}

	@Mixin(Waila.class)
	static class WailaLogger {
		@Mutable
		@Shadow(remap = false)
		@Final
		public static Logger LOGGER = new Log4jLogger();
	}
}
