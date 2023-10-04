package dm.earth.cabricality.mixin.client.ftbquests;

import dm.earth.cabricality.client.screen.CabfCreditsScreen;
import dm.earth.cabricality.lib.util.ScreenUtil;

import dm.earth.cabricality.lib.util.log.CabfLogger;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.ftb.mods.ftbquests.events.QuestProgressEventData;
import dev.ftb.mods.ftbquests.quest.Quest;

@Environment(EnvType.CLIENT)
@Mixin(Quest.class)
public abstract class CreditsTrigger {
	@Inject(method = "onCompleted", at = @At("HEAD"), remap = false)
	private void onCompleted(QuestProgressEventData<?> data, CallbackInfo ci) {
		if (((Quest) (Object) this).getMovableID() == 5590341946361687492L) { // 幔缝重生
			CabfLogger.message("Congrats adventurer! You have completed Cabricality!");
			ScreenUtil.openScreenInWorld(new CabfCreditsScreen(() -> MinecraftClient.getInstance().setScreen(null)), false);
		}
	}
}
