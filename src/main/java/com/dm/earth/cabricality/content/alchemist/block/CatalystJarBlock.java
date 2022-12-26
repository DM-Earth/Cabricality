package com.dm.earth.cabricality.content.alchemist.block;

import com.dm.earth.cabricality.Cabricality;

import org.jetbrains.annotations.NotNull;

import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;

import net.minecraft.util.Identifier;

public class CatalystJarBlock extends SubstrateJarBlock {
	public CatalystJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public Substrate getSubstrate() {
		return Reagents.getCatalystFromBlock(this);
	}

	@Override
	public @NotNull Identifier getDefaultBlockId() {
		return Cabricality.id("catalyst_jar");
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block", "jar", "catalyst");
	}
}
