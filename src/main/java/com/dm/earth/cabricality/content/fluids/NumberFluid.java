package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.content.fluids.core.BaseFluid;

public class NumberFluid extends BaseFluid {

	public NumberFluid(int number) {
		super("number_" + number);
	}

	@Override
	public boolean hasBucketItem() {
		return false;
	}

	@Override
	public String getTextureName() {
		return "number";
	}
}
