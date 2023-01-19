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
		CabfDebugger.enabled = !CabfDebugger.enabled;
		context.getSource().sendFeedback(
				Cabricality.genTranslatableText("command", "debug", CabfDebugger.enabled ? "enabled" : "disabled").formatted(Formatting.GRAY, Formatting.ITALIC),
				false
		);
		ClientPlayNetworking.send(CabfNetworking.DEBUG_INFO, new PacketByteBuf(Unpooled.buffer()).writeBoolean(CabfDebugger.enabled));
		return SINGLE_SUCCESS;
	}
}
