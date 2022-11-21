package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.Cabricality;
import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.item.ItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KubeJS.class)
public class KubeJSMixin {
	@Shadow
	public static ItemGroup tab;

	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ldev/latvian/mods/kubejs/script/ScriptManager;unload()V"))
	private void injected(CallbackInfo ci) {
		if (!CommonProperties.get().serverOnly) {
			tab = Cabricality.MAIN_GROUP;
		}
	}
}
