package com.dm.earth.cabricality.mixin;

import com.simibubi.create.content.contraptions.fluids.actors.FluidManipulationBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FluidManipulationBehaviour.class)
public interface FluidManipulationBehaviourAccessor {
	@Accessor
	boolean getInfinite();
}
