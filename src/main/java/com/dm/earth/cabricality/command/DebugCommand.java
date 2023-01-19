package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
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
			if (getPlayer(context) != null)
				context.getSource().getServer().getPlayerManager().getPlayerList().stream()
						.filter(p -> p != getPlayer(context)).forEach(
								p -> p.sendMessage(getPlayer(context).getDisplayName().shallowCopy()
										.formatted(Formatting.GRAY, Formatting.ITALIC)
										.append(Cabricality
												.genTranslatableText("command", "debug_info",
														CabfDebugger.enabled ? "enabled" : "disabled")
												.formatted(Formatting.GRAY, Formatting.ITALIC)),
										false));
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return SINGLE_SUCCESS;
	}

	private static ServerPlayerEntity getPlayer(CommandContext<ServerCommandSource> context) {
		try {
			return context.getSource().getPlayer();
		} catch (CommandSyntaxException e) {
			return null;
		}
	}
}
