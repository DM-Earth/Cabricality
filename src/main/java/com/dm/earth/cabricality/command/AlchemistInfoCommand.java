package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;
import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.krlite.equator.util.Pusher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class AlchemistInfoCommand implements Command<ServerCommandSource> {
	@Override
	public int run(@NotNull CommandContext<ServerCommandSource> context) {
		final Pusher pusher = new Pusher();
		final HashMap<String, ArrayList<String>> result = new HashMap<>();

		// Possible reagents
		Alchemist.possibleReagentMap(context.getSource().getWorld())
				.forEach((key, value) -> {
					pusher.safePull(() -> pusher.push(
							() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
					context.getSource().sendFeedback(
							new LiteralText("● ").append(key.getJarBlock().getName()), false);
					value.forEach(reagent -> {
						context.getSource().sendFeedback(reagent.getJarBlock().getName(), false);
						putPair(result, key.getJarBlock().getName().getString(), reagent.getJarBlock().getName().getString());
					});
				});

		// Chaotic catalyst
		pusher.safePull(() -> pusher.push(
				() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
		context.getSource().sendFeedback(new LiteralText("● ").append(Alchemist.CHAOTIC_CATALYST.getJarBlock().getTranslationKey() /* Toxic */ ), false);
		Alchemist.possibleChaoticCatalystList(context.getSource().getWorld())
				.forEach(catalyst -> {
					context.getSource().sendFeedback(catalyst.getJarBlock().getName(), false);
					putPair(result, Alchemist.CHAOTIC_CATALYST.getJarBlock().getName().getString(), catalyst.getJarBlock().getName().getString());
				});

		// Possible special reagents(chaotic)
		Alchemist.possibleSpecialReagentChaoticMap(context.getSource().getWorld())
				.forEach((key, value) -> {
					pusher.safePull(() -> pusher.push(
							() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
					context.getSource().sendFeedback(new LiteralText("● ").append(key.getJarBlock().getName()),false);
					context.getSource().sendFeedback(
							value.getJarBlock().getName().shallowCopy().append(
									Cabricality.genTranslatableText("command", "alchemist", "chaotic").formatted(Formatting.AQUA)),
							false);
					putPair(result, key.getJarBlock().getName().getString(), value.getJarBlock().getName().getString());
				});

		// Copy
		pusher.safePull(() -> pusher.push(
				() -> context.getSource().sendFeedback(new LiteralText(""), false)), pusher::push);
		context.getSource().sendFeedback(
				Cabricality.genTranslatableText("command", "alchemist", "copy")
						.styled(style -> style.withColor(Formatting.YELLOW)
												 .withClickEvent(new ClickEvent(
														 ClickEvent.Action.COPY_TO_CLIPBOARD, result.toString()))),
				false
		);

		return SINGLE_SUCCESS;
	}

	private void putPair(final HashMap<String, ArrayList<String>> result, String key, String value) {
		key = key.replaceAll("§.", "");
		value = value.replaceAll("§.", "");
		if (result.containsKey(key)) {
			ArrayList<String> values = result.get(key);
			values.add(value);
			result.put(key, values);
		}
		else result.put(key, new ArrayList<>(ImmutableList.of(value)));
	}
}
