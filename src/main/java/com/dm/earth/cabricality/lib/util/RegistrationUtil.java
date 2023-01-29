package com.dm.earth.cabricality.lib.util;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class RegistrationUtil {
	private static final List<Identifier> list = new ArrayList<>();

	public static void banRegistration(Identifier id) {
		if (!list.contains(id)) {
			list.add(id);
		}
	}

	public static Boolean shouldBan(String id) {
		boolean returnValue = false;
		for (Identifier idLoop : list) {
			if (idLoop.toString().equals(id)) returnValue = true;
		}
		return returnValue;
	}

	public static Boolean shouldBan(Identifier id) {
		return shouldBan(id.toString());
	}
}
