package com.dm.earth.cabricality.mixin.log.slf4j;

import com.dm.earth.cabricality.util.debug.Slf4jLogger;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Util.class)
public class UtilLogger {
	@Mutable
	@Shadow
	@Final
	static Logger LOGGER = new Slf4jLogger();
}
