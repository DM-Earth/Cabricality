package com.dm.earth.cabricality.mixin.led;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.darktree.led.block.DiodeLampBlock;
import net.darktree.led.util.DiodeVariant;

@Mixin(DiodeLampBlock.class)
public interface DiodeLampBlockAccessor {
	@Accessor(remap = false)
	public DiodeVariant getVariant();
}
