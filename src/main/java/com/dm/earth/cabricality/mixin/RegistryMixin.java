package com.dm.earth.cabricality.mixin;

import me.steven.indrev.tools.IRToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

@Mixin(Registry.class)
public abstract class RegistryMixin {
	@Inject(method = "register(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;", at = @At("HEAD"), cancellable = true)
	private static <V, T extends V> void register(Registry<V> registry, RegistryKey<V> key, T entry, CallbackInfoReturnable<T> cir) {
		Identifier id = key.getValue();

		// Remove Indrev silver ore
		if (entry instanceof PlacedFeature || entry instanceof ConfiguredFeature<?, ?>) {
			if (id.getNamespace().equals("indrev") && id.getPath().contains("ore") && id.getPath().contains("silver"))
				cir.setReturnValue(entry);
		}
	}
}
