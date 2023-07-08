package com.dm.earth.cabricality.mixin.log;

import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.dm.earth.cabricality.lib.util.debug.Slf4jLogger;

public class Slf4jHandler {
	@Mixin(MinecraftServer.class)
	static class MinecraftServerLogger {
		@ModifyArg(method = "save", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(ZZZ)Z"), index = 0)
		private boolean silentSave(boolean suppressLogs) {
			return true;
		}

		@ModifyArg(method = "shutdown", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(ZZZ)Z"), index = 0)
		private boolean silentShutdown(boolean suppressLogs) {
			return true;
		}
	}

	@Mixin(ReloadableResourceManager.class)
	static class ReloadableResourceManagerLogger {
		@Mutable
		@Shadow
		@Final
		private static Logger LOGGER = new Slf4jLogger();
	}

	@Mixin(Util.class)
	static class UtilLogger {
		@Mutable
		@Shadow
		@Final
		static Logger LOGGER = new Slf4jLogger();
	}
}
