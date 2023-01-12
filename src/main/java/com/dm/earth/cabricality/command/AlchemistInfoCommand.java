package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.krlite.equator.util.Pusher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class AlchemistInfoCommand implements Command<ServerCommandSource> {
	@Override
	public int run(@NotNull CommandContext<ServerCommandSource> context) {
		final Pusher pusher = new Pusher();

		// Possible reagents
		Alchemist.possibleReagentMap(context.getSource().getWorld())
				.forEach((key, value) -> {
					pusher.safePull(() -> pusher.push(
							() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
					context.getSource().sendFeedback(
							new LiteralText("● ").append(key.getJarBlock().getName()), false);
					value.forEach(reagent -> context.getSource().sendFeedback(reagent.getJarBlock().getName(), false));
				});

		// Chaotic catalyst
		pusher.safePull(() -> pusher.push(
				() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
		context.getSource().sendFeedback(new LiteralText("● ").append(Alchemist.CHAOTIC_CATALYST.getJarBlock().getTranslationKey() /* Toxic */ ), false);
		Alchemist.possibleChaoticCatalystList(context.getSource().getWorld())
				.forEach(catalyst -> context.getSource().sendFeedback(catalyst.getJarBlock().getName(), false));

		// Possible special reagents(chaotic)
		Alchemist.possibleSpecialReagentChaoticMap(context.getSource().getWorld())
				.forEach((key, value) -> {
					pusher.safePull(() -> pusher.push(
							() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
					context.getSource().sendFeedback(new LiteralText("● ").append(key.getJarBlock().getName()),false);
					context.getSource().sendFeedback(
							value.getJarBlock().getName().shallowCopy().append(
									Cabricality.genTranslatableText("command", "alchemist", "chaotic").formatted(Formatting.YELLOW)),
							false);
				});
		return SINGLE_SUCCESS;
	}
}
