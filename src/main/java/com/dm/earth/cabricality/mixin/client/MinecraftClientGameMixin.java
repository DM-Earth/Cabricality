package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.client.CabricalityClient;

import net.minecraft.client.MinecraftClientGame;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClientGame.class)
public class MinecraftClientGameMixin {
	@Inject(method = "onStartGameSession", at = @At("HEAD"))
	private void onWorldLoaded(CallbackInfo ci) {
		CabricalityClient.finishLoading();
	}
}
