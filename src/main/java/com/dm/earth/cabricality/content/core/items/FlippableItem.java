package com.dm.earth.cabricality.content.core.items;

import java.util.Random;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.ModEntry;
import com.dm.earth.cabricality.content.entries.CabfSounds;
import com.dm.earth.cabricality.lib.util.debug.CabfLogger;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class FlippableItem extends Item {

	public FlippableItem(Settings settings) {
		super(settings);
	}

	@Override
	@SuppressWarnings("all")
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		if (!player.isSneaking()) {
			boolean side = new Random().nextBoolean();
			player.getItemCooldownManager().set(this, 41);

			if (world.isClient())
				MinecraftClient.getInstance().gameRenderer.showFloatingItem(ModEntry.CABF
						.asItem(Registry.ITEM.getId(this).getPath() + (side ? "_top" : "_bottom"))
						.getDefaultStack());
			flip(world, player, side);

			return TypedActionResult.success(stack);
		}
		return TypedActionResult.fail(stack);
	}

	@SuppressWarnings("all")
	private void flip(World world, PlayerEntity player, boolean side) {
		String name = Registry.ITEM.getId(this).getPath();

		// Send message to client
		if (world.isClient())
			MinecraftClient.getInstance().inGameHud.setOverlayMessage(
					new TranslatableText(Cabricality.genTranslationKey("event", "coin_flip"),
							ModEntry.CABF.asItem(name).getName().getString(), ModEntry.CABF
									.asItem(name + (side ? "_top" : "_bottom")).getName().getString()),
					false);
		else
			world.playSound(null, player.getBlockPos(), CabfSounds.COIN_FLIP, SoundCategory.PLAYERS,
					1, new Random().nextFloat(0.7F, 1.3F));

		// Log to debug
		CabfLogger.logDebug("Flipped a " + name + " and got " + (side ? "top" : "bottom") + ".");
	}

}
