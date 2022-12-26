package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.client.CabricalityClient;

import net.minecraft.client.gui.screen.world.OptimizeWorldScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptimizeWorldScreen.class)
public class OptimizeWorldScreenMixin {
	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/booleans/BooleanConsumer;accept(Z)V"), remap = false)
	private void onWorldOptimized(CallbackInfo ci) {
		CabricalityClient.finishLoading();
	}
}
