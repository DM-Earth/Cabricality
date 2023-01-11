package com.dm.earth.cabricality.mixin.client.ftbquests;

import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.dm.earth.cabricality.Cabricality;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftbquests.gui.quests.QuestPanel;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;
import dev.ftb.mods.ftbquests.quest.theme.property.ColorProperty;

@ClientOnly
@Mixin(QuestPanel.class)
public class QuestPanelAnimator {
	@Redirect(
			method = "drawOffsetBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftbquests/quest/theme/property/ColorProperty;get(Ldev/ftb/mods/ftbquests/quest/QuestObjectBase;)Ljava/lang/Object;",
					ordinal = 0
			), remap = false
	)
	private Object questDependencyColor(ColorProperty colorProperty, QuestObjectBase questObjectBase) {
		return Color4I.rgb(Cabricality.Colors.QUEST_DEPENDENCY.toColor().getRGB()); // This doesn't work. Maybe a bug in FTB Quests.
	}

	@Redirect(
			method = "drawOffsetBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftbquests/quest/theme/property/ColorProperty;get(Ldev/ftb/mods/ftbquests/quest/QuestObjectBase;)Ljava/lang/Object;",
					ordinal = 1
			), remap = false
	)
	private Object questDependentColor(ColorProperty colorProperty, QuestObjectBase questObjectBase) {
		return Color4I.rgb(Cabricality.Colors.QUEST_DEPENDENT.toColor().getRGB());
	}

	@Redirect(
			method = "drawOffsetBackground",
			at = @At(
					value = "INVOKE",
					target = "Ldev/ftb/mods/ftbquests/quest/theme/property/ColorProperty;get(Ldev/ftb/mods/ftbquests/quest/QuestObjectBase;)Ljava/lang/Object;",
					ordinal = 2
			), remap = false
	)
	private Object questCompletedColor(ColorProperty colorProperty, QuestObjectBase questObjectBase) {
		return Color4I.rgb(Cabricality.Colors.QUEST_DEPENDENCY.toColor().getRGB());
	}
}
