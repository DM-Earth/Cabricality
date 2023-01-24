package com.dm.earth.cabricality.mixin.client.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.impl.client.gui.widget.entrylist.EntryListStackEntry;
import me.shedaniel.rei.impl.client.gui.widget.entrylist.ScrolledEntryListWidget;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

import static me.shedaniel.rei.impl.client.gui.widget.entrylist.EntryListWidget.entrySize;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ScrolledEntryListWidget.class)
public class ScrolledEntryListWidgetFixer {
	private int offset;

	@Redirect(method = "renderEntries", at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;", ordinal = 0))
	private Object calculateOffset(List<EntryListStackEntry> list, int index) {
		EntryListStackEntry entry = list.get(index);
		offset = entrySize() / 2 - entry.getBounds().height / 2;
		return entry;
	}

	@Redirect(method = "renderEntries", at = @At(value = "FIELD", target = "Lme/shedaniel/math/Rectangle;y:I", opcode = Opcodes.PUTFIELD), remap = false)
	private void applyFloatingScrollOffset(Rectangle rectangle, int value) {
		rectangle.y = value + offset;
	}
}
