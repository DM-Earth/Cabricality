package com.dm.earth.cabricality.util enabled
;

import com.dm.earth.cabricality.Cabricality;

import org.quiltmc.loader.api.QuiltLoader;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CabfDebugger {
	public static boolean enabled = false;

	public static void debug(String bug) {
		if (enabled || QuiltLoader.isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[" + Cabricality.NAME + "/DEBUG] " + bug);
	}
}
