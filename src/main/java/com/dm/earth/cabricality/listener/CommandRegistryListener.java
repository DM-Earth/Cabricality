package com.dm.earth.cabricality.listener;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.function.Predicate;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.command.AlchemistInfoCommand;
import com.dm.earth.cabricality.command.DebugCommand;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistryListener implements CommandRegistrationCallback {
    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean integrated,
            boolean dedicated) {
        dispatcher.register(
				literal(Cabricality.ID)
						.then(literal("alchemistInfo").executes(new AlchemistInfoCommand())
									  .requires(requirePermission(2)))
						.then(literal("debug").executes(new DebugCommand())
									  .requires(requirePermission(2)))
		);
    }

    private Predicate<ServerCommandSource> requirePermission(int level) {
        return source -> source.hasPermissionLevel(level);
    }
}
