package com.dm.earth.cabricality.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MinecraftServer.class)
public class MinecraftServerLogger {
	@ModifyArg(method = "save", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(ZZZ)Z"), index = 0)
	private boolean silentSave(boolean suppressLogs) {
		return true;
	}

	@ModifyArg(method = "shutdown", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(ZZZ)Z"), index = 0)
	private boolean silentShutdown(boolean suppressLogs) {
		return true;
	}
}
