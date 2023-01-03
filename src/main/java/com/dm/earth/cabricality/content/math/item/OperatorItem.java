package com.dm.earth.cabricality.content.math.item;

import com.dm.earth.cabricality.content.core.items.GlintedItem;
import com.dm.earth.cabricality.content.math.core.Calculable;

public class OperatorItem extends GlintedItem implements Calculable {

	protected final String operator;

	public OperatorItem(String operator, Settings settings) {
		super(settings);
		this.operator = operator;
	}

	@Override
	public String asCalculateToken() {
		return operator;
	}

}
