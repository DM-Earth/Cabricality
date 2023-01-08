package com.dm.earth.cabricality.mixin.client.ftbquests;

import com.dm.earth.cabricality.client.screen.CabfCreditsScreen;

import com.dm.earth.cabricality.util.ScreenUtil;

import com.dm.earth.cabricality.util.debug.CabfLogger;
import net.minecraft.client.MinecraftClient;

import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.ftb.mods.ftbquests.events.QuestProgressEventData;
import dev.ftb.mods.ftbquests.quest.Quest;

@ClientOnly
@Mixin(Quest.class)
public abstract class QuestMixin {
	@Inject(method = "onCompleted", at = @At("HEAD"), remap = false)
	private void onCompleted(QuestProgressEventData<?> data, CallbackInfo ci) {
		if (((Quest) (Object) this).getMovableID() == 5590341946361687492L) {
			CabfLogger.logInfo("Congrats, adventurer! You've completed the Cabricality modpack!");
			ScreenUtil.openScreenInWorld(new CabfCreditsScreen(() -> MinecraftClient.getInstance().setScreen(null)));
		}
	}
}
