package com.dm.earth.cabricality.mixin.client;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.client.MinecraftClient;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
	private void getWindowTitle(CallbackInfoReturnable<String> cir) {
		cir.setReturnValue("Cabricality " + QuiltLoader.getModContainer(Cabricality.ID).get().metadata().version().raw());
	}
}
