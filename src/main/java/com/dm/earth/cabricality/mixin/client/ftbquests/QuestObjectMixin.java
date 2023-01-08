package com.dm.earth.cabricality.mixin.client.ftbquests;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftbquests.quest.QuestObject;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(QuestObject.class)
public class QuestObjectMixin {
	@Redirect(
			method = "getProgressColor(Ldev/ftb/mods/ftbquests/quest/TeamData;)Ldev/ftb/mods/ftblibrary/icon/Color4I;",
			at = @At(value = "FIELD", target = "Ldev/ftb/mods/ftblibrary/icon/Color4I;WHITE:Ldev/ftb/mods/ftblibrary/icon/Color4I;"),
			remap = false
	)
	private Color4I modifyProgressColor() {
		return Color4I.GRAY;
	}
}
