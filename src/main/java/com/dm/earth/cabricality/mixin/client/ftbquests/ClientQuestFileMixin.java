package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.util.PushUtil;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientQuestFile.class)
public class ClientQuestFileMixin {
	@Inject(method = "openQuestGui", at = @At("TAIL"), remap = false)
	private void animateBaseScreen(CallbackInfo ci) {
		PushUtil.ANIMATE_BASE_SCREEN.push();
	}
}
