package com.dm.earth.cabricality.command.helper;

import net.krlite.equator.visual.color.AccurateColor;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A {@link Consumer} that broadcasts a message to all players on the server.
 */
public record BroadcastContent(
		@NotNull ServerPlayerEntity sender,
		@NotNull Text message,
		boolean exclude, boolean actionBar,
		@NotNull Style style
) implements Consumer<ServerPlayerEntity> {
	public BroadcastContent(ServerPlayerEntity sender, Text message) {
		this(sender, message, true, false, Style.EMPTY.withFormatting(Formatting.GRAY, Formatting.ITALIC));
	}

	public BroadcastContent(ServerPlayerEntity sender, Text message, boolean actionBar) {
		this(sender, message, true, actionBar, Style.EMPTY.withFormatting(Formatting.GRAY, Formatting.ITALIC));
	}

	public BroadcastContent(ServerPlayerEntity sender, Text message, boolean exclude, boolean actionBar, boolean bold, boolean italic, AccurateColor color) {
		this(sender, message, exclude, actionBar, Style.EMPTY.withBold(bold).withItalic(italic).withColor(color.toInt()));
	}

	@Override
	public void accept(ServerPlayerEntity player) {
		if (!exclude || player != sender)
			player.sendMessage(sender.getDisplayName().shallowCopy().append(message).setStyle(style), actionBar);
	}
}
