package dm.earth.cabricality.client.command;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.network.CabfNetworking;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class HeldItemInfoCommand implements Command<QuiltClientCommandSource> {
	@Override
	@SuppressWarnings("all")
	public int run(CommandContext<QuiltClientCommandSource> context) {
		ItemStack stack = context.getSource().getPlayer().getMainHandStack();
		Identifier itemId = Registries.ITEM.getId(stack.getItem());

		if (stack.isOf(Items.AIR))
			return 0;

		// Broadcast
		ClientPlayNetworking.send(CabfNetworking.HELD_ITEM_INFO,
				new PacketByteBuf(Unpooled.buffer()).writeItemStack(stack));

		// Heading
		context.getSource().sendFeedback(((MutableText) stack.toHoverableText()).styled(style -> style.withItalic(true)
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
						.append(Text.literal(tag.id().toString())
								.styled(style -> style
										.withColor(Formatting.YELLOW)
										.withHoverEvent(new HoverEvent(
												HoverEvent.Action.SHOW_TEXT,
												Text.literal(tag.id().toString()).formatted(Formatting.YELLOW)
										))
										.withClickEvent(new ClickEvent(
												ClickEvent.Action.COPY_TO_CLIPBOARD,
												tag.id().toString())
										))
						))
				.reduce((a, b) -> a.append(" ").append(b)).orElse(Text.empty());
	}
}
