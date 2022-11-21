package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.content.fluids.core.BaseFlowableFluid;

public class PoweredWater extends BaseFlowableFluid {
	public PoweredWater(boolean isStill) {
		super("powered_water", isStill);
		if (isStill) color(0x64C8F1);
	}

	public static class Flowing extends PoweredWater {
		public Flowing() {
			super(false);
		}
	}

	public static class Still extends PoweredWater {
		public Still() {
			super(true);
		}
	}
}
