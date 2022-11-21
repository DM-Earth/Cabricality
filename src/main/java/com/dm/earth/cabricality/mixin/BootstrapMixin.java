package com.dm.earth.cabricality.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dm.earth.cabricality.listener.DataFixerListener;

import net.minecraft.Bootstrap;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
	@Inject(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/Registry;freezeBuiltins()V", shift = At.Shift.AFTER))
	private static void initialize(CallbackInfo ci) {
		DataFixerListener.registerDataFixers();
	}
}
