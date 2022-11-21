package com.dm.earth.cabricality.resource.assets.gen.fluid;

import com.dm.earth.cabricality.Cabricality;
import net.devtech.arrp.json.blockstate.JBlockStates;

public class FluidBlockStatesGenerator {
	public static JBlockStates simple(String id) {
		return JBlockStates.simple(Cabricality.id("block/fluid/" + id));
	}
}
