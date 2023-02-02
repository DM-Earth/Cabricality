package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.command.helper.BroadcastContent;
import com.dm.earth.cabricality.lib.util.debug.CabfDebugger;
import com.dm.earth.cabricality.lib.util.debug.CabfLogger;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;

public class DebugCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) {
		CabfDebugger.enabled = !CabfDebugger.enabled;
		context.getSource().sendFeedback(
				Cabricality.genTranslatableText("command", "debug", CabfDebugger.enabled ? "enabled" : "disabled")
						.formatted(Formatting.GRAY, Formatting.ITALIC),
				false);
		try {
			ServerPlayerEntity player = context.getSource().getPlayer();
			if (player != null)
				context.getSource().getServer().getPlayerManager().getPlayerList()
						.forEach(new BroadcastContent(player, Cabricality.genTranslatableText("command", "debug_info",
								CabfDebugger.enabled ? "enabled" : "disabled")));
		} catch (CommandSyntaxException commandSyntaxException) {
			CabfLogger.logDebugAndError("Failed to get player from command source", commandSyntaxException);
		}
		return SINGLE_SUCCESS;
	}
}
