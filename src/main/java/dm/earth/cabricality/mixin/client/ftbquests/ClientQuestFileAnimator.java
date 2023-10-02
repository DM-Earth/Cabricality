package dm.earth.cabricality.mixin.client.ftbquests;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;

import dev.ftb.mods.ftbquests.client.gui.quests.QuestScreen;
import dm.earth.cabricality.lib.util.PushUtil;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ClientOnly
@Mixin(ClientQuestFile.class)
public class ClientQuestFileAnimator {
	@Inject(method = "openQuestGui", at = @At("TAIL"), remap = false)
	private void animateBaseScreen(CallbackInfoReturnable<QuestScreen> cir) {
		PushUtil.ANIMATE_BASE_SCREEN.push();
	}
}
