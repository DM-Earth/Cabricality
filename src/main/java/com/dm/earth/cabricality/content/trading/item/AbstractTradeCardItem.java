package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.client.CabricalityClient;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class AbstractTradeCardItem extends Item {

	public AbstractTradeCardItem(Settings settings) {
		super(settings);
	}

	public abstract String getContentString();

	@Override
	public String getTranslationKey() {
		return CabricalityClient.genTranslatableText("item", this.getType()).getString() + " §r- "
				+ this.getContentString();
	}

	public abstract String getType();

	public abstract TypedActionResult<ItemStack> tradeInWorld(World world, PlayerEntity player, Hand hand);

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		TypedActionResult<ItemStack> result = this.tradeInWorld(world, player, hand);
		int tradeCostFoodLevel = 3;
		if ((result.getResult() == ActionResult.SUCCESS || result.getResult() == ActionResult.CONSUME)
				&& isPlayerTradeAble(player, tradeCostFoodLevel)) {
			reducePlayerFood(player, tradeCostFoodLevel);
			player.getItemCooldownManager().set(this, tradeCostFoodLevel * 25);
			return new TypedActionResult<>(world.isClient() ? ActionResult.SUCCESS : ActionResult.CONSUME,
					result.getValue());
		} else
			return TypedActionResult.fail(player.getStackInHand(hand));
	}

	private static boolean isPlayerTradeAble(PlayerEntity player, int level) {
		return player.getHungerManager().getFoodLevel() >= level || player.isCreative()
				|| player.getHungerManager().getFoodLevel() >= level;
	}

	private static void reducePlayerFood(PlayerEntity player, int cost) {
		if (player.getWorld().isClient())
			return;
		if (player.getHungerManager().getSaturationLevel() >= cost * 3)
			player.getHungerManager().add(0, -3 * cost);
		else if (player.getHungerManager().getFoodLevel() >= cost)
			player.getHungerManager().add(-1 * cost, 0.0F);
	}
}
