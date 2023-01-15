package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Formatting;

public class DebugCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) {
		if (CabfDebugger.debug) {
			CabfDebugger.debug = false;
			context.getSource().sendFeedback(Cabricality.genTranslatableText("command", "debug", "disabled").formatted(Formatting.GRAY, Formatting.ITALIC), true);
			Cabricality.LOGGER.error(Cabricality.genTranslatableText("command", "debug", "disabled").getString());
		} else {
			CabfDebugger.debug = true;
			context.getSource().sendFeedback(Cabricality.genTranslatableText("command", "debug", "enabled").formatted(Formatting.GRAY, Formatting.ITALIC), true);
		}
		return SINGLE_SUCCESS;
	}
}
