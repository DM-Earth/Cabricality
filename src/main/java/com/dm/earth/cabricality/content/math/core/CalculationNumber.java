package com.dm.earth.cabricality.content.math.core;

public interface CalculationNumber extends Calculable {
	int getNumber();

	@Override
	public default String asCalculateToken() {
		return String.valueOf(getNumber());
	}
}
