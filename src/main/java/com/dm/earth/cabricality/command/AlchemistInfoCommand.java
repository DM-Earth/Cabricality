package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.alchemist.block.SubstrateJarBlock;
import com.dm.earth.cabricality.lib.util.func.Pusher;
import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class AlchemistInfoCommand implements Command<ServerCommandSource> {
	@Override
	public int run(@NotNull CommandContext<ServerCommandSource> context) {
		final Pusher pusher = new Pusher();
		final HashMap<String, ArrayList<String>> result = new HashMap<>();

		// Header
		context.getSource().sendFeedback(Cabricality.genTranslatableText("command", "alchemist", "header")
				.formatted(Formatting.GRAY, Formatting.ITALIC), false);

		// Possible reagents
		Alchemist.possibleReagentMap(context.getSource().getWorld())
				.forEach((key, value) -> {
					headerFeedback(context, pusher, key.getJarBlock());
					value.forEach(reagent -> {
						context.getSource().sendFeedback(
								reagent.getJarBlock().getName()
										.styled(style -> style.withHoverEvent(
												new HoverEvent(HoverEvent.Action.SHOW_ITEM,
														new HoverEvent.ItemStackContent(
																Registry.ITEM.get(
																		reagent.getJarBlock()
																				.getItemId())
																		.getDefaultStack())))
												.withClickEvent(new ClickEvent(
														ClickEvent.Action.SUGGEST_COMMAND,
														suggestGiveCommand(
																reagent.getJarBlock()
																		.getBlockId())))),
								false);
						putPair(result, key.getJarBlock().getName().getString(),
								reagent.getJarBlock().getName().getString());
					});
				});

		// Chaotic catalyst
		headerFeedback(context, pusher, Alchemist.CHAOTIC_CATALYST.getJarBlock());
		Alchemist.possibleChaoticCatalystList(context.getSource().getWorld())
				.forEach(catalyst -> {
					context.getSource().sendFeedback(
							catalyst.getJarBlock().getName()
									.styled(style -> style.withHoverEvent(
											new HoverEvent(HoverEvent.Action.SHOW_ITEM,
													new HoverEvent.ItemStackContent(
															Registry.ITEM.get(
																	catalyst.getJarBlock()
																			.getItemId())
																	.getDefaultStack())))
											.withClickEvent(new ClickEvent(
													ClickEvent.Action.SUGGEST_COMMAND,
													suggestGiveCommand(
															catalyst.getJarBlock()
																	.getBlockId())))),
							false);
					putPair(result, Alchemist.CHAOTIC_CATALYST.getJarBlock().getName().getString(),
							catalyst.getJarBlock().getName().getString());
				});

		// Possible special reagents(chaotic)
		Alchemist.possibleSpecialReagentChaoticMap(context.getSource().getWorld())
				.forEach((key, value) -> {
					headerFeedback(context, pusher, key.getJarBlock());
					context.getSource().sendFeedback(
							value.getJarBlock().getName()
									.styled(style -> style.withHoverEvent(
											new HoverEvent(HoverEvent.Action.SHOW_ITEM,
													new HoverEvent.ItemStackContent(
															Registry.ITEM.get(
																	value.getJarBlock()
																			.getItemId())
																	.getDefaultStack())))
											.withClickEvent(new ClickEvent(
													ClickEvent.Action.SUGGEST_COMMAND,
													suggestGiveCommand(
															value.getJarBlock()
																	.getBlockId()))))
									.append(
											Cabricality.genTranslatableText(
													"command",
													"alchemist",
													"chaotic")
													.formatted(Formatting.AQUA)),
							false);
					putPair(result, key.getJarBlock().getName().getString(),
							value.getJarBlock().getName().getString());
				});

		// Copy widget
		context.getSource().sendFeedback(
				Cabricality.genTranslatableText("command", "actions", "copy")
						.formatted(Formatting.GRAY, Formatting.ITALIC)
						.styled(style -> style.withClickEvent(new ClickEvent(
								ClickEvent.Action.COPY_TO_CLIPBOARD,
								result.toString()))),
				false);

		return SINGLE_SUCCESS;
	}

	private void headerFeedback(@NotNull CommandContext<ServerCommandSource> context, Pusher pusher,
			SubstrateJarBlock jarBlock) {
		newLine(pusher, context.getSource());
		context.getSource().sendFeedback(
				new LiteralText("● ").append(jarBlock.getName())
						.styled(style -> style.withHoverEvent(new HoverEvent(
								HoverEvent.Action.SHOW_ITEM,
								new HoverEvent.ItemStackContent(
										Registry.ITEM.get(jarBlock.getItemId())
												.getDefaultStack())))
								.withClickEvent(new ClickEvent(
										ClickEvent.Action.SUGGEST_COMMAND,
										suggestGiveCommand(jarBlock
												.getBlockId())))),
				false);
	}

	private void newLine(Pusher pusher, ServerCommandSource source) {
		pusher.safePull(() -> pusher.push(
				() -> source.sendFeedback(new LiteralText(""), false)), pusher::push);
	}

	private String suggestGiveCommand(Identifier id) {
		return "/give @s " + id.toString();
	}

	private void putPair(final HashMap<String, ArrayList<String>> result, String key, String value) {
		key = key.replaceAll("§.", "");
		value = value.replaceAll("§.", "");
		if (result.containsKey(key)) {
			ArrayList<String> values = result.get(key);
			values.add(value);
			result.put(key, values);
		} else
			result.put(key, new ArrayList<>(ImmutableList.of(value)));
	}
}
