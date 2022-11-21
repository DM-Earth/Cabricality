package com.dm.earth.cabricality.mixin;

import net.darktree.led.block.DiodeLampBlock;
import net.darktree.led.util.DiodeVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DiodeLampBlock.class)
public interface DiodeLampBlockAccessor {
	@Accessor
	public DiodeVariant getVariant();
}
