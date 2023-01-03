package com.dm.earth.cabricality.client.listener;

import static org.quiltmc.qsl.command.api.client.ClientCommandManager.literal;
import org.quiltmc.qsl.command.api.client.ClientCommandRegistrationCallback;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;
import com.dm.earth.cabricality.client.command.HeldItemInfoCommand;
import com.dm.earth.cabricality.content.trading.quest.command.BumpQuestCommand;
import com.mojang.brigadier.CommandDispatcher;

public class ClientCommandRegistryListener implements ClientCommandRegistrationCallback {
	@Override
	public void registerCommands(CommandDispatcher<QuiltClientCommandSource> dispatcher) {
		dispatcher.register(literal("cabricalityClient")
				.then(literal("dumpTradingQuests").executes(new BumpQuestCommand()))
				.then(literal("handHeld").executes(new HeldItemInfoCommand())));
	}
}
