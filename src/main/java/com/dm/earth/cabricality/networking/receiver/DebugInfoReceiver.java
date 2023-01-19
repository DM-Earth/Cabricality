package com.dm.earth.cabricality.networking.receiver;

import com.dm.earth.cabricality.Cabricality;
import io.netty.channel.EventLoop;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.thread.ThreadExecutor;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class DebugInfoReceiver implements ServerPlayNetworking.ChannelReceiver {
	/**
	 * Receives an incoming packet.
	 * <p>
	 * This method is executed on {@linkplain EventLoop netty's event loops}.
	 * Modification to the game should be {@linkplain ThreadExecutor#submit(Runnable) scheduled} using the provided Minecraft server instance.
	 * <p>
	 * An example usage of this is to create an explosion where the player is looking:
	 * <pre>{@code
	 * ServerPlayNetworking.registerReceiver(new Identifier("mymod", "boom"), (server, player, handler, buf, responseSender) -> {
	 * 	boolean fire = buf.readBoolean();
	 *
	 * 	// All operations on the server or world must be executed on the server thread
	 * 	server.execute(() -> {
	 * 		ModPacketHandler.createExplosion(player, fire);
	 *    });
	 * });
	 * }</pre>
	 *
	 * @param server         the server
	 * @param player         the player
	 * @param handler        the network handler that received this packet, representing the player/client who sent the packet
	 * @param buf            the payload of the packet
	 * @param responseSender the packet sender
	 */
	@Override
	public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		boolean enabled = buf.readBoolean();
		server.execute(() -> server.getPlayerManager().getPlayerList().stream().filter(p -> p != player).forEach(
				p -> p.sendMessage(player.getDisplayName().shallowCopy().formatted(Formatting.GRAY, Formatting.ITALIC)
										   .append(Cabricality.genTranslatableText("command", "debug_info", enabled ? "enabled" : "disabled").formatted(Formatting.GRAY, Formatting.ITALIC)), false)));
	}
}
