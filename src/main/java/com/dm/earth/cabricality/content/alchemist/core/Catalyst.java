package com.dm.earth.cabricality.content.alchemist.core;

import com.dm.earth.cabricality.Cabricality;

import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.SubstrateJarBlock;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

public class Catalyst extends Substrate {
	private Catalyst(Identifier id, int tint) {
		super(id, tint);
	}

	public static Catalyst of(String name, int tint) {
		return new Catalyst(Cabricality.id(name), tint);
	}

	@Override
	public String getType() {
		return "catalyst";
	}

	@Override
	public boolean consume() {
		return false;
	}
}
