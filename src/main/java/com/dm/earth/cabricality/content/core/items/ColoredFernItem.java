package com.dm.earth.cabricality.content.core.items;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.MC;

import com.dm.earth.cabricality.content.entries.CabfItems;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class ColoredFernItem extends Item {
	public final int tint;

	public ColoredFernItem(int tint) {
		super(CabfItems.Properties.DEFAULT.get());
		this.tint = tint;
	}

	public abstract String getType();

	public static class SlimeFernLeaf extends ColoredFernItem {

		public SlimeFernLeaf(int tint) {
			super(tint);
		}

		@Override
		public String getType() {
			return "leaf";
		}

	}

	public static class SlimeFernPaste extends ColoredFernItem {

		public SlimeFernPaste(int tint) {
			super(tint);
		}

		@Override
		public String getType() {
			return "paste";
		}

	}

	public static enum Entry {
		EARTH("earth", 0x8FDB84, MC.id("gunpowder")),
		SKY("sky", 0x00F9DE, MC.id("bone_meal")),
		ENDER("ender", 0xAC2EFC, AE2.id("ender_dust"));

		public final String name;
		public final int tint;
		public final Identifier output;

		Entry(String name, int tint, Identifier output) {
			this.name = name;
			this.tint = tint;
			this.output = output;
		}

		public Item getOutputItem() {
			return Registry.ITEM.get(output);
		}
	}
}
