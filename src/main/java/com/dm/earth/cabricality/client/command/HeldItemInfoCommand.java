package com.dm.earth.cabricality.client.command;

import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HeldItemInfoCommand implements Command<QuiltClientCommandSource> {

	@Override
	@SuppressWarnings("deprecation")
	public int run(CommandContext<QuiltClientCommandSource> context) throws CommandSyntaxException {
		ItemStack stack = context.getSource().getPlayer().getMainHandStack();
		Identifier itemId = Registry.ITEM.getId(stack.getItem());
		MutableText heading = ((MutableText) stack.getName()).styled(style -> style
				.withColor(Formatting.GREEN)
				.withHoverEvent(
						new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.of(itemId.toString())))
				.withClickEvent(
						new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, itemId.toString())));
		context.getSource().sendFeedback(heading);
		LiteralText tags = new LiteralText("Tags:");
		stack.getItem().getBuiltInRegistryHolder().streamTags()
				.forEach(tag -> tags.append(new LiteralText(" #" + tag.id().toString())
						.styled(style -> style.withColor(Formatting.YELLOW)
								.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
										Text.of(tag.id().toString())))
								.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,
										tag.id().toString())))));
		context.getSource().sendFeedback(tags);
		return SINGLE_SUCCESS;
	}

}
