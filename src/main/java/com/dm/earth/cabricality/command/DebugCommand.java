package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.networking.CabfNetworking;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import com.dm.earth.cabricality.util.debug.CabfDebugger;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;
import java.lang.Exception;
import java.lang.RuntimeException;

public class DebugCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) {
		CabfDebugger.enabled = !CabfDebugger.enabled;
		context.getSource().sendFeedback(
				Cabricality.genTranslatableText("command", "debug", CabfDebugger.enabled ? "enabled" : "disabled").formatted(Formatting.GRAY, Formatting.ITALIC),
				false
		);
		try {
			context.getSource().getServer().getPlayerManager().getPlayerList().stream().filter(p -> p != context.getSource().getPlayer()).forEach(
					p -> p.sendMessage(context.getSource().getPlayer().getDisplayName().shallowCopy().formatted(Formatting.GRAY, Formatting.ITALIC)
											   .append(Cabricality.genTranslatableText("command", "debug_info", CabfDebugger.enabled? "enabled" : "disabled").formatted(Formatting.GRAY, Formatting.ITALIC)), false));
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return SINGLE_SUCCESS;
	}
}
