package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class CabfSounds {
	public static final SoundEvent COIN_FLIP = registerSound("coin_flip");

	private static SoundEvent registerSound(String name) {
		return Registry.register(Registry.SOUND_EVENT, Cabricality.id(name),
				new SoundEvent(Cabricality.id(name)));
	}

	public static void register() {

	}
}
