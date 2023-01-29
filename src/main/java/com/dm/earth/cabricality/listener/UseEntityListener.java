package com.dm.earth.cabricality.listener;

import static com.dm.earth.cabricality.lib.util.ItemStackUtil.replaceItemStack;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class UseEntityListener {
	public static void load() {
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof BlazeEntity blaze && blaze.getHealth() >= 10) {
				ItemStack stack = player.getStackInHand(hand);
				if (stack.isOf(Items.BUCKET)) {
					ItemStack bucket = new ItemStack(Registry.ITEM.get(new Identifier("tconstruct", "blazing_blood_bucket")));
					bucket.setCount(1);
					replaceItemStack(stack, bucket, 1, player, hand);
					blaze.damage(DamageSource.player(player), 10);
					return ActionResult.SUCCESS;
				}
				if (stack.isOf(Items.WATER_BUCKET)) {
					ItemStack bucket = Items.BUCKET.getDefaultStack();
					bucket.setCount(1);
					replaceItemStack(stack, bucket, 1, player, hand);
					blaze.damage(DamageSource.player(player), 10);
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.PASS;
		});
	}
}
