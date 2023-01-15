package com.dm.earth.cabricality.mixin.log.log4j;

import com.dm.earth.cabricality.util.debug.Log4jLogger;
import mcp.mobius.waila.Waila;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Waila.class)
public class WailaLogger {
	@Mutable
	@Shadow(remap = false)
	@Final
	public static Logger LOGGER = new Log4jLogger();
}
