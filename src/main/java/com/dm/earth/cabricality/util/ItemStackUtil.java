package com.dm.earth.cabricality.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class ItemStackUtil {
	public static void replaceItemStack(ItemStack oldStack, ItemStack newStack, int count, PlayerEntity player, Hand hand) {
		if (oldStack.getCount() == count) {
			player.setStackInHand(hand, newStack);
		} else if (oldStack.getCount() > count) {
			oldStack.setCount(oldStack.getCount() - count);
			player.giveItemStack(newStack);
		}
	}
}
