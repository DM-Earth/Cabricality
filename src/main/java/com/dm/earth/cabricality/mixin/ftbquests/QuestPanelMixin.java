package com.dm.earth.cabricality.mixin.ftbquests;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import dev.ftb.mods.ftbquests.gui.quests.QuestButton;
import dev.ftb.mods.ftbquests.gui.quests.QuestPanel;

@Mixin(QuestPanel.class)
public class QuestPanelMixin {
	@Redirect(method = "drawOffsetBackground", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbquests/gui/quests/QuestButton;getDependencies()Ljava/util/Collection;"), remap = false)
	private Collection<QuestButton> fixDependencyLineRendering(QuestButton questButton) {
		return questButton.getDependencies().stream().filter(QuestButton::shouldDraw).toList(); // Fix dependency line rendering
	}
}
