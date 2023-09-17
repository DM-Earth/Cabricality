package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class ProfessionCardItem extends AbstractTradeCardItem {
	public ProfessionCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public String getContentString() {
		return Cabricality.genTranslatableText(
				"profession",
				Objects.requireNonNull(ProfessionUtil.fromItem(this)).id().getPath()
		).getString();
	}

	@Override
	public String getType() {
		return "profession_card";
	}

	@Override
	public TypedActionResult<ItemStack> tradeInWorld(World world, PlayerEntity player, Hand hand) {
		ItemStack cardStack = player.getStackInHand(hand);
		if (hand == Hand.OFF_HAND || player.getItemCooldownManager().isCoolingDown(cardStack.getItem()))
			return TypedActionResult.fail(cardStack);
		ItemStack stack = player.getStackInHand(Hand.OFF_HAND);
		Profession profession = ProfessionUtil.fromItem(cardStack.getItem());

		Identifier itemId = Registries.ITEM.getId(stack.getItem());
		assert profession != null;
		TradingEntry entry = TradingEntryRegistry
				.fromHashString(String.valueOf(itemId.hashCode()).replaceAll("-", "x"));
		if (entry == null || !profession.entries().contains(entry))
			return TypedActionResult.fail(cardStack);

		if (stack.getItem() != entry.getItem() || stack.getCount() < entry.getItemCount())
			return TypedActionResult.fail(cardStack);
		ItemStack coin = entry.asCoinStack();
		if (stack.getCount() == entry.getItemCount())
			player.setStackInHand(Hand.OFF_HAND, coin);
		else {
			stack.decrement(entry.getItemCount());
			player.giveItemStack(coin);
		}
		player.getItemCooldownManager().set(cardStack.getItem(), 20);
		return TypedActionResult.success(cardStack);
	}
}
