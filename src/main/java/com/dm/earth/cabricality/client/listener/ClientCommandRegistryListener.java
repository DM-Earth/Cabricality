package com.dm.earth.cabricality.client.listener;

import static org.quiltmc.qsl.command.api.client.ClientCommandManager.literal;

import com.dm.earth.cabricality.client.CabricalityClient;
import org.quiltmc.qsl.command.api.client.ClientCommandRegistrationCallback;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;
import com.dm.earth.cabricality.client.command.HeldItemInfoCommand;
import com.dm.earth.cabricality.client.command.GenTradingQuestsCommand;
import com.mojang.brigadier.CommandDispatcher;

public class ClientCommandRegistryListener implements ClientCommandRegistrationCallback {
	@Override
	public void registerCommands(CommandDispatcher<QuiltClientCommandSource> dispatcher) {
		dispatcher.register(
				literal(CabricalityClient.ID)
						.then(literal("genTradingQuests").executes(new GenTradingQuestsCommand()))
						.then(literal("heldItemInfo").executes(new HeldItemInfoCommand()))
		);

		dispatcher.register(literal("i").executes(new HeldItemInfoCommand())); // Alias
	}
}
