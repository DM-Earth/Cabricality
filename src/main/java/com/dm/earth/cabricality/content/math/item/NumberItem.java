package com.dm.earth.cabricality.content.math.item;

import com.dm.earth.cabricality.content.math.core.CalculationNumber;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class NumberItem extends Item implements CalculationNumber {

	public NumberItem(Settings settings) {
		super(settings);
	}

	@Override
	public int getNumber() {
		return Integer.valueOf(
				Registry.ITEM.getId(this).getPath().replaceAll("number_", "").replaceAll("x", "-"));
	}

	public static String getNumberItemName(int number) {
		return "number_" + (number < 0 ? "x" + Math.abs(number) : number);
	}

}
