package com.dm.earth.cabricality.content.math.item;

import com.dm.earth.cabricality.content.math.core.Calculable;
import net.minecraft.item.Item;

public class OperatorItem extends Item implements Calculable {

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
