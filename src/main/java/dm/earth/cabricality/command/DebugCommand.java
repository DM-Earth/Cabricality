package dm.earth.cabricality.command;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.command.helper.BroadcastContent;
import dm.earth.cabricality.config.CabfConfig;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;

public class DebugCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) {
		Cabricality.CONFIG.debugInfoEnabled = !Cabricality.CONFIG.debugInfoEnabled;
		Cabricality.CONFIG_HOLDER.save();

		context.getSource().sendFeedback(
				() -> Cabricality.genTranslatableText("command", "debug", Cabricality.CONFIG.debugInfoEnabled ? "enabled" : "disabled")
						.formatted(Formatting.GRAY, Formatting.ITALIC),
				false
		);

		ServerPlayerEntity player = context.getSource().getPlayer();
		if (player != null) {
			context.getSource().getServer().getPlayerManager().getPlayerList().forEach(new BroadcastContent(
					player,
					Cabricality.genTranslatableText(
							"command", "debug_info",
							Cabricality.CONFIG.debugInfoEnabled ? "enabled" : "disabled"
					)
			));
		}

		return SINGLE_SUCCESS;
	}
}
