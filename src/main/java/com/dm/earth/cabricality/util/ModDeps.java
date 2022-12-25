package com.dm.earth.cabricality.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public enum ModDeps {
	FTB_LIBRARY("ftblibrary", new TranslatableText("mod.ftblibrary.name"),
			"https://www.curseforge.com/minecraft/mc-mods/ftb-library-fabric/download/4210934/file", false, false),
	FTB_QUESTS("ftbquests", new TranslatableText("mod.ftbquests.name"),
			"https://www.curseforge.com/minecraft/mc-mods/ftb-quests-fabric/download/4215548/file", false, false),
	FTB_TEAMS("ftbteams", new TranslatableText("mod.ftbteams.name"),
			"https://www.curseforge.com/minecraft/mc-mods/ftb-teams-fabric/download/4229137/file", false, false),
	QUESTS_ADDITIONS("questsadditions", new TranslatableText("mod.questsadditions.name"),
			"https://www.curseforge.com/minecraft/mc-mods/quests-additions-fabric/download/3940981/file", false, false);

	final String modId;
	private final Text name;
	@Nullable
	private final URL url;
	private final boolean required;
	private final boolean isClient;

	ModDeps(String id, Text name, String url, boolean required, boolean isClient) {
		this.modId = id;
		this.name = name;
		this.required = required;
		this.url = getUrl(url);
		this.isClient = isClient;
	}

	public String getModId() {
		return modId;
	}

	public Text getName() {
		return name;
	}

	@Nullable
	public URL getUrl() {
		return url;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isClient() {
		return isClient;
	}

	public boolean isLoaded() {
		return QuiltLoader.isModLoaded(modId);
	}

	public void openUrl() {
		if (url != null)
			Util.getOperatingSystem().open(url);
	}

	public boolean matchesSide(boolean isServer) {
		return !isServer || !isClient;
	}

	public static Stream<ModDeps> stream() {
		return Arrays.stream(values());
	}

	public static Stream<ModDeps> getMissing(boolean required, boolean isServer) {
		return stream().filter(dep -> (dep.isRequired() || !required) && dep.matchesSide(isServer) && !dep.isLoaded());
	}

	public static boolean isLoaded(boolean required, boolean isServer) {
		return getMissing(required, isServer).findAny().isEmpty();
	}

	public static String asString(boolean required, boolean isServer) {
		return getMissing(required, isServer).map(dep -> dep.getName().getString()).reduce((a, b) -> a + ", " + b)
				.orElse("");
	}

	private static URL getUrl(String spec) {
		try {
			return new URL(spec);
		} catch (MalformedURLException e) {
			return null;
		}
	}

}
