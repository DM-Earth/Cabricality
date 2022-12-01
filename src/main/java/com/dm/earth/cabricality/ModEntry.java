package com.dm.earth.cabricality;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModEntry {
	MC("minecraft"),
	C("c"),
	CR("create"),
	CABF(Cabricality.ID),
	PMD("promenade"),
	IV("indrev"),
	FD("farmersdelight"),
	AP("architects_palette"),
	TC("tconstruct"),
	MLM("malum"),
	AE2("ae2"),
	TRE("terrestria"),
	AD("ad_astra"),
	KB("kibe"),
	CX("coxinhautilities"),
	ED("extended_drawers");;

	final String modId;

	ModEntry(String modId) {
		this.modId = modId;
	}

	public Identifier id(String path) {
		return new Identifier(this.modId, path);
	}

	public Item asItem(String name) {
		return Registry.ITEM.get(id(name));
	}

	public Fluid asFluid(String name) {
		return Registry.FLUID.get(id(name));
	}

	public Block asBlock(String name) {
		return Registry.BLOCK.get(id(name));
	}
}
