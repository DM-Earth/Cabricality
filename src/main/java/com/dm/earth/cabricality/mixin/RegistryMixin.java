package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.util.RegistrationUtil;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Registry.class)
public class RegistryMixin {
	@Inject(method = "register(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;", at = @At("HEAD"), cancellable = true)
	private static <V, T extends V> void injected(Registry<V> registry, RegistryKey<V> key, T entry, CallbackInfoReturnable<T> cir) {
		if (RegistrationUtil.shouldBan(key.getValue())) cir.cancel();
	}
}
