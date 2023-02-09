package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TradeCardItem extends AbstractTradeCardItem {
	public TradeCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public String getContentString() {
		return new TranslatableText(TradingEntryRegistry.fromItem(this).getItem().getTranslationKey()).getString();
	}

	@Override
	public String getType() {
		return "trade_card";
	}

	@Override
	public TypedActionResult<ItemStack> tradeInWorld(World world, PlayerEntity player, Hand hand) {
		ItemStack cardStack = player.getStackInHand(hand);
		if (hand == Hand.OFF_HAND || player.getItemCooldownManager().isCoolingDown(cardStack.getItem()))
			return TypedActionResult.fail(cardStack);
		ItemStack stack = player.getStackInHand(Hand.OFF_HAND);
		TradingEntry entry = TradingEntryRegistry
				.fromItem((AbstractTradeCardItem) player.getStackInHand(hand).getItem());
		if (stack.getItem() != entry.getCoin() || stack.getCount() < entry.getCoinCount())
			return TypedActionResult.fail(cardStack);
		ItemStack item = entry.asItemStack();
		if (stack.getCount() == entry.getCoinCount())
			player.setStackInHand(Hand.OFF_HAND, item);
		else {
			stack.decrement(entry.getCoinCount());
			player.giveItemStack(item);
		}
		player.getItemCooldownManager().set(cardStack.getItem(), 20);
		return TypedActionResult.success(cardStack);
	}
}
