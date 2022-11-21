package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.content.fluids.core.BaseFlowableFluid;

public class MoltenMetalFluid extends BaseFlowableFluid {
	public MoltenMetalFluid(String metalName, boolean isStill, int color) {
		super("molten_" + metalName, isStill);
		color(color);
	}

	@Override
	public String getTextureName() {
		return "molten_metal";
	}

	public static class Still extends MoltenMetalFluid {
		public Still(String metalName, int color) {
			super(metalName, true, color);
		}
	}

	public static class Flowing extends MoltenMetalFluid {
		public Flowing(String metalName, int color) {
			super(metalName, false, color);
		}
	}
}
