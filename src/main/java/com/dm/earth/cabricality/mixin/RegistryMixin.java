package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.Cabricality;
import com.github.alexnijjar.ad_astra.blocks.pipes.Wrenchable;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.regex.Pattern;

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
