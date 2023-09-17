package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class CabfSounds {
	public static final SoundEvent COIN_FLIP = registerSound("coin_flip");

	private static SoundEvent registerSound(String name) {
		return Registry.register(
				Registries.SOUND_EVENT, Cabricality.id(name),
				SoundEvent.createVariableRangeEvent(Cabricality.id(name))
		);
	}

	public static void register() {
	}
}
