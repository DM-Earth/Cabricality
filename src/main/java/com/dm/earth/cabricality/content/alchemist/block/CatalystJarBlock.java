package com.dm.earth.cabricality.content.alchemist.block;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;

import net.minecraft.util.Identifier;

public class CatalystJarBlock extends SubstrateJarBlock {
	public CatalystJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public Identifier getDefaultBlockId() {
		return Cabricality.id("catalyst_jar");
	}

	@Override
	public Substrate getSubstrate() {
		return Reagents.getCatalystFromBlock(this);
	}
}
