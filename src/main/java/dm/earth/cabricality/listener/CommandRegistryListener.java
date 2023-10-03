package dm.earth.cabricality.listener;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.function.Predicate;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.command.AlchemistInfoCommand;
import dm.earth.cabricality.command.DebugCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandBuildContext;
import net.minecraft.server.command.CommandManager;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistryListener implements CommandRegistrationCallback {
    @Override
    public void register(
			CommandDispatcher<ServerCommandSource> dispatcher,
			CommandBuildContext commandBuildContext,
			CommandManager.RegistrationEnvironment registrationEnvironment
	) {
        dispatcher.register(
				literal(Cabricality.ID)
						.then(literal("alchemistInfo")
								.executes(new AlchemistInfoCommand())
								.requires(requirePermission(2)))
						.then(literal("debug")
								.executes(new DebugCommand())
								.requires(requirePermission(2)))
		);
    }

    private Predicate<ServerCommandSource> requirePermission(int level) {
        return source -> source.hasPermissionLevel(level);
    }
}
