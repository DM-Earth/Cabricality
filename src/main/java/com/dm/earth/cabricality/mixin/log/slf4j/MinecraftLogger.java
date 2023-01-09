package com.dm.earth.cabricality.mixin.log.slf4j;

import com.dm.earth.cabricality.util.debug.Slf4jLogger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ReloadableResourceManager;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftLogger {
	@Redirect(method = "createUserApiService", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"), remap = false)
	private void cleanLog(Logger logger, String message, Throwable throwable) {

	}
}

@Mixin(ReloadableResourceManager.class)
class ReloadableResourceManagerLogger {
	@Mutable
	@Shadow
	@Final
	private static Logger LOGGER = new Slf4jLogger();
}
