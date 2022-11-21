package com.dm.earth.cabricality.content.alchemist.substrate;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.util.Identifier;

public class Catalyst extends Substrate {
	private Catalyst(Identifier id, int tint) {
		super(id, tint);
	}

	public static Catalyst of(String name, int tint) {
		return new Catalyst(Cabricality.id(name), tint);
	}

	@Override
	public String getType() {
		return "catalyst";
	}

	@Override
	public boolean consume() {
		return false;
	}
}
