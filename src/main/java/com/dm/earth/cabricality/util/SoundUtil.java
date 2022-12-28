package com.dm.earth.cabricality.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;

import java.util.Random;

public class SoundUtil {
	public static void playSound(SoundEvent sound) {
		playSound(sound, new Random().nextFloat(0.91F, 1.37F), 2.3F);
	}

	public static void playSound(SoundEvent sound, float pitch, float volume) {
		playSound(PositionedSoundInstance.master(sound, pitch, volume));
	}

	public static void playSound(SoundInstance sound) {
		if (MinecraftClient.getInstance() != null) MinecraftClient.getInstance().getSoundManager().play(sound);
	}
}
