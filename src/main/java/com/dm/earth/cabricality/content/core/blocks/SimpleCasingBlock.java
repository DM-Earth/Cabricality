package com.dm.earth.cabricality.content.core.blocks;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.resource.ResourcedBlock;
import com.dm.earth.cabricality.resource.assets.gen.block.BlockStatesGenerator;
import com.simibubi.create.content.contraptions.base.CasingBlock;

import net.devtech.arrp.json.blockstate.JBlockStates;
import net.minecraft.util.Identifier;

public class SimpleCasingBlock extends CasingBlock implements ResourcedBlock {

	public final String name;

	public SimpleCasingBlock(Settings settings, String name) {
		super(settings);
		this.name = name;
	}

	@Override
	public boolean doBlockStates() {
		return true;
	}

	@Override
	public @Nullable JBlockStates getBlockStates() {
		return BlockStatesGenerator.simple(getBlockModelId());
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block/casing/" + this.name + "_casing");
	}

	@Override
	public boolean doItemModel() {
		return true;
	}

	@Override
	public boolean doLootTable() {
		return true;
	}

}
