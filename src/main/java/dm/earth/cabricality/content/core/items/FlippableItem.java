package dm.earth.cabricality.content.core.items;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.Mod;
import dm.earth.cabricality.content.entries.CabfSounds;
import dm.earth.cabricality.lib.util.log.CabfLogger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;

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
				MinecraftClient.getInstance().gameRenderer.showFloatingItem(Mod.Entry.CABF
						.asItem(Registries.ITEM.getId(this).getPath() + (side ? "_top" : "_bottom"))
						.getDefaultStack());
			flip(world, player, side);

			return TypedActionResult.success(stack);
		}
		return TypedActionResult.fail(stack);
	}

	@SuppressWarnings("all")
	private void flip(World world, PlayerEntity player, boolean side) {
		String name = Registries.ITEM.getId(this).getPath();

		// Send message to client
		if (world.isClient())
			MinecraftClient.getInstance().inGameHud.setOverlayMessage(
					Text.translatable(Cabricality.genTranslationKey("event", "coin_flip"),
							Mod.Entry.CABF.asItem(name).getName().getString(), Mod.Entry.CABF
									.asItem(name + (side ? "_top" : "_bottom")).getName().getString()),
					false);
		else
			world.playSound(null, player.getBlockPos(), CabfSounds.COIN_FLIP, SoundCategory.PLAYERS,
					1, new Random().nextFloat(0.7F, 1.3F));

		// Log to debug
		CabfLogger.debug("Flipped a " + name + " and got " + (side ? "top" : "bottom") + ".");
	}

}
