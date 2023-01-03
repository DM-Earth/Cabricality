package com.dm.earth.cabricality.math;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	public static <T> List<T> loop(T object, int times) {
		ArrayList<T> list = new ArrayList<>();
		for (int i = 0; i < times; i++)
			list.add(object);
		return list;
	}
}
