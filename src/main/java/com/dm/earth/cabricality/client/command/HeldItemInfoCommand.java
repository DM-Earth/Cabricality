package com.dm.earth.cabricality.client.command;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.tag.TagKey;
import net.minecraft.text.TranslatableText;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.stream.Collectors;

public class HeldItemInfoCommand implements Command<QuiltClientCommandSource> {
	@Override
	@SuppressWarnings("deprecation")
	public int run(CommandContext<QuiltClientCommandSource> context) {
		ItemStack stack = context.getSource().getPlayer().getMainHandStack();
		Identifier itemId = Registry.ITEM.getId(stack.getItem());

		// Heading
		context.getSource().sendFeedback(stack.getName().shallowCopy().styled(
				style -> style.withColor(Formatting.GREEN)
								 .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
										 new TranslatableText(Cabricality.genTranslationKey("command", "held_item_info", "count"),
												 stack.getCount(), stack.getMaxCount()).formatted(Formatting.DARK_GRAY)
												 .append(new LiteralText(itemId.toString()).formatted(Formatting.GREEN))))
								 .withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,
										 itemId.toString()))));

		// Tags
		if (stack.getItem().getBuiltInRegistryHolder().streamTags().findAny().isPresent()) {
			if (stack.getItem().getBuiltInRegistryHolder().streamTags().count() == 1)
				context.getSource().sendFeedback(
						Cabricality.genTranslatableText("command", "held_item_info", "tag")
								.styled(style -> style.withClickEvent(new ClickEvent(
										ClickEvent.Action.COPY_TO_CLIPBOARD,
										stack.getItem().getBuiltInRegistryHolder().streamTags().map(t -> t.id().toString())
												.collect(Collectors.joining(", "))
								)))
								.append(streamTags(stack))
				);
			else
				context.getSource().sendFeedback(
						Cabricality.genTranslatableText("command", "held_item_info", "tags").append(streamTags(stack))
				);
		}

		return SINGLE_SUCCESS;
	}

	@SuppressWarnings("deprecation")
	private Text streamTags(ItemStack stack) {
		return stack.getItem().getBuiltInRegistryHolder().streamTags()
					   .map(tag -> Cabricality.genTranslatableText("command", "held_item_info", "tag_prefix")
										   .append(new LiteralText(tag.id().toString()).styled(
												   style -> style.withColor(Formatting.YELLOW)
																	.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
																			new LiteralText(tag.id().toString()).formatted(Formatting.YELLOW)))
																	.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,
																			tag.id().toString())))))
					   .reduce((a, b) -> a.append(" ").append(b)).orElse(new LiteralText(""));
	}
}
