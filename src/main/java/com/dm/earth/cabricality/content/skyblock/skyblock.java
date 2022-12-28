package com.dm.earth.cabricality.content.skyblock;

public class Skyblock {
	public static Boolean isSkyblock;

	public Skyblock(Boolean isSkyblock) {
		Skyblock.isSkyblock = isSkyblock;
	}
	public static void load(){
		WorldStatus.load();
	}
}
