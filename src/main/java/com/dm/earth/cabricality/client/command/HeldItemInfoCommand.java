package com.dm.earth.cabricality.client.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.networking.CabfNetworking;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
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
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

import java.util.stream.Collectors;

public class HeldItemInfoCommand implements Command<QuiltClientCommandSource> {
	@Override
	@SuppressWarnings("deprecation")
	public int run(CommandContext<QuiltClientCommandSource> context) {
		ItemStack stack = context.getSource().getPlayer().getMainHandStack();
		Identifier itemId = Registry.ITEM.getId(stack.getItem());

		if (stack.isOf(Items.AIR)) return 0;

		// Broadcast
		ClientPlayNetworking.send(CabfNetworking.HELD_ITEM_INFO, new PacketByteBuf(Unpooled.buffer()).writeItemStack(stack));

		// Show
		MinecraftClient.getInstance().gameRenderer.showFloatingItem(stack);

		// Heading
		context.getSource().sendFeedback(stack.toHoverableText().shallowCopy().styled(style -> style.withItalic(true)
								 .withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, itemId.toString()))));

		// Tags
		if (stack.getItem().getBuiltInRegistryHolder().streamTags().findAny().isPresent())
			context.getSource().sendFeedback(streamTags(stack));

		return SINGLE_SUCCESS;
	}

	@SuppressWarnings("deprecation")
	private Text streamTags(ItemStack stack) {
		return stack.getItem().getBuiltInRegistryHolder().streamTags()
					   .map(tag -> Cabricality.genTranslatableText("command", "held_item_info", "tag_prefix")
										   .formatted(Formatting.GRAY, Formatting.ITALIC)
										   .append(new LiteralText(tag.id().toString()).styled(
												   style -> style.withColor(Formatting.YELLOW)
																	.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
																			new LiteralText(tag.id().toString()).formatted(Formatting.YELLOW)))
																	.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,
																			tag.id().toString())))))
					   .reduce((a, b) -> a.append(" ").append(b)).orElse(new LiteralText(""));
	}
}
