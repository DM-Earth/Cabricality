package com.dm.earth.cabricality.content.math.core;

import java.util.List;
import net.objecthunter.exp4j.ExpressionBuilder;

public interface Calculable {
	String asCalculateToken();

	static double calculate(List<Calculable> calculates) throws IllegalArgumentException {
		try {
			return new ExpressionBuilder(calculates.stream().map(Calculable::asCalculateToken)
					.reduce("", String::concat)).build().evaluate();
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid expression");
		}
	}
}
