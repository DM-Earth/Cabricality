package com.dm.earth.cabricality.mixin.log.slf4j;

import com.dm.earth.cabricality.util.debug.Slf4jLogger;
import net.minecraft.resource.ReloadableResourceManager;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ReloadableResourceManager.class)
public class ReloadableResourceManagerLogger {
	@Mutable
	@Shadow
	@Final
	private static Logger LOGGER = new Slf4jLogger();
}
