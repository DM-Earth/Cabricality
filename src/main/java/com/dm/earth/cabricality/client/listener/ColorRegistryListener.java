package com.dm.earth.cabricality.client.listener;

import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.block.SubstrateJarBlock;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import com.dm.earth.cabricality.content.trading.item.AbstractTradeCardItem;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import com.dm.earth.cabricality.content.trading.item.TradeCardItem;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class ColorRegistryListener {
	public static void load() {
		ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> {
			Item item = itemStack.getItem();
			if (item instanceof BlockItem blockItem) return getBlockTint(blockItem.getBlock(), tintIndex);
			if (item instanceof AbstractTradeCardItem && tintIndex > 0) {
				if (item instanceof TradeCardItem card) return TradingEntryRegistry.fromItem(card).getTint();
				if (item instanceof ProfessionCardItem card)
					return Objects.requireNonNull(ProfessionUtil.fromItem(card), "The profession can't be null!").tint();
			}
			return -1;
		}, getItems());

		ColorProviderRegistry.BLOCK.register((blockState, blockRenderView, blockPos, tintIndex) -> getBlockTint(blockState.getBlock(), tintIndex), getBlocks());
	}

	private static int getBlockTint(Block block, int tintIndex) {
		if (block instanceof SubstrateJarBlock jar && (tintIndex == 1 || tintIndex == 0 || tintIndex < 0) && jar.getSubstrate() != null)
			return jar.getSubstrate().getTint();
		return -1;
	}

	private static Item[] getItems() {
		ArrayList<Item> list = new ArrayList<>();
		for (Map.Entry<RegistryKey<Item>, Item> set : Registry.ITEM.getEntries()) {
			Item item = set.getValue();
			if (item instanceof AbstractTradeCardItem) list.add(item);
			if (item instanceof BlockItem blockItem && Arrays.stream(getBlocks()).anyMatch(block -> blockItem.getBlock() == block))
				list.add(item);
		}
		return list.toArray(new Item[0]);
	}

	private static Block[] getBlocks() {
		ArrayList<Block> list = new ArrayList<>();
		list.addAll(Reagents.getJarBlocks(false));
		return list.toArray(new Block[0]);
	}
}
