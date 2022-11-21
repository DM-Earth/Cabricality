package com.dm.earth.cabricality.util;

import org.quiltmc.loader.api.QuiltLoader;

import com.dm.earth.cabricality.Cabricality;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CabfDebugger {
	private static boolean debug = true;

	public static void debug(String bug) {
		if (debug || QuiltLoader.isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[Cabricality/DEBUG] " + bug);
	}

	public static class DebugCommand implements Command<ServerCommandSource> {

		@Override
		public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
			if (debug) {
				debug = false;
				context.getSource().sendFeedback(Text.of("Debug mode disabled"), true);
			} else {
				debug = true;
				context.getSource().sendFeedback(Text.of("Debug mode enabled"), true);
			}
			return SINGLE_SUCCESS;
		}

	}
}
