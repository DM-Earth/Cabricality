package com.dm.earth.cabricality.content.core.items;

import java.util.Random;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.ModEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.github.alexnijjar.ad_astra.registry.ModSoundEvents;

import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlippableItem extends Item {
	private final String name;
	private int coolDown;

	public FlippableItem(Settings settings, String name) {
		super(settings);
		this.name = name;
		resetCoolDown();
		ClientTickEvent.CLIENT_PRE.register(client -> {
			if (coolDown > 0) coolDown--;
		});
	}

	private void resetCoolDown() {
		coolDown = 41;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		if (!player.isSneaking() && coolDown == 0) {
			boolean side = new Random().nextBoolean();
			resetCoolDown();

			MinecraftClient.getInstance().gameRenderer.showFloatingItem(ModEntry.CABF.asItem(name + (side ? "_top" : "_bottom")).getDefaultStack());
			playFlippingSound(player, name);
			broadcastFlippingResult(side);

			return TypedActionResult.success(stack);
		}
		return TypedActionResult.fail(stack);
	}

	private void playFlippingSound(PlayerEntity player, String name) {
		if (name.equals(TradingEntry.CoinTypes.SILVER.getId().getPath())) player.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 0.7F, 1.7F);
		if (name.equals(TradingEntry.CoinTypes.GOLD.getId().getPath())) player.playSound(SoundEvents.BLOCK_LODESTONE_PLACE, 1, 1);
		player.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 1);
		player.playSound(ModSoundEvents.PASSING_SPACESHIP, 0.13F, 1);
	}

	private void broadcastFlippingResult(boolean side) {
		MinecraftClient.getInstance().inGameHud.setOverlayMessage(
				new TranslatableText(
						Cabricality.genTranslationKey("event", "coin_flip"),
						ModEntry.CABF.asItem(name).getName().getString(),
						ModEntry.CABF.asItem(name + (side ? "_top" : "_bottom")).getName().getString()
				).formatted(Formatting.BOLD), false
		);
		Cabricality.logDebug("Flipped a " + name + " and got " + (side ? "top" : "bottom") + ".");
	}
}
