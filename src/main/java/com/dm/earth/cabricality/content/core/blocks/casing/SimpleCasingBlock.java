package com.dm.earth.cabricality.content.core.blocks.casing;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.lib.resource.ResourcedBlock;
import com.dm.earth.cabricality.lib.resource.assets.gen.block.BlockStatesGenerator;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import net.minecraft.data.client.model.BlockStateSupplier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

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
	public @Nullable BlockStateSupplier getBlockStates() {
		return BlockStatesGenerator.simple(getBaseBlock(), getBlockModelId());
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block", "casing", this.name + "_casing");
	}

	@Override
	public boolean doItemModel() {
        return ResourcedBlock.super.doItemModel();
    }

	@Override
	public boolean doLootTable() {
		return true;
	}
}
