package com.dm.earth.cabricality.content.trading.core;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.core.HashStringable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TradingEntry implements HashStringable {
	private final Identifier item;
	private final int itemCount;
	private final Identifier coin;
	private final int coinCount;
	private final int tint;

	public TradingEntry(Identifier item, int itemCount, Identifier coin, int coinCount, int tint) {
		this.item = item;
		this.itemCount = itemCount;
		this.coin = coin;
		this.coinCount = coinCount;
		this.tint = tint;
	}

	public static TradingEntry of(Identifier item, int itemCount, CoinTypes coin, int coinCount,
			int tint) {
		return new TradingEntry(item, itemCount, coin.getId(), coinCount, tint);
	}

	public Identifier getId() {
		return this.getItemId();
	}

	public Identifier getItemId() {
		return item;
	}

	public Item getItem() {
		return Registry.ITEM.get(this.getItemId());
	}

	public int getItemCount() {
		return itemCount;
	}

	public ItemStack asItemStack() {
		return new ItemStack(this.getItem(), this.getItemCount());
	}

	public Identifier getCoinId() {
		return coin;
	}

	public Item getCoin() {
		return Registry.ITEM.get(this.getCoinId());
	}

	public int getCoinCount() {
		return coinCount;
	}

	public ItemStack asCoinStack() {
		return new ItemStack(this.getCoin(), this.getCoinCount());
	}

	public int getTint() {
		return tint;
	}

	public enum CoinTypes {
		SILVER(Cabricality.id("silver_coin")), GOLD(Cabricality.id("gold_coin"));

		private final Identifier coin;

		CoinTypes(Identifier coin) {
			this.coin = coin;
		}

		public Identifier getId() {
			return coin;
		}

		public String getName() {
			return coin.getPath().replaceAll("_coin", "");
		}
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
