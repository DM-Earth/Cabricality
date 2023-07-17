package com.dm.earth.cabricality.mixin.client.ftbquests;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;

import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dm.earth.cabricality.lib.util.PushUtil;

@ClientOnly
@Mixin(ClientQuestFile.class)
public class ClientQuestFileAnimator {
	@Inject(method = "openQuestGui", at = @At("TAIL"), remap = false)
	private void animateBaseScreen(CallbackInfo ci) {
		PushUtil.ANIMATE_BASE_SCREEN.push();
	}
}
