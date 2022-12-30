package com.dm.earth.cabricality.util.mod;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import com.dm.earth.cabricality.util.debug.CabfLogger;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public enum CabfModDeps {
	FTB_LIBRARY("ftblibrary", new TranslatableText("mod.ftblibrary.name"),
			"https://www.curseforge.com/minecraft/mc-mods/ftb-library-fabric/download/4210934/file",
			false, false), // ftb-library-fabric-1802.3.9-build.167.jar
	FTB_QUESTS("ftbquests", new TranslatableText("mod.ftbquests.name"),
			"https://www.curseforge.com/minecraft/mc-mods/ftb-quests-fabric/download/4215548/file",
			false, false), // ftb-quests-fabric-1802.3.11-build.151.jar
	FTB_TEAMS("ftbteams", new TranslatableText("mod.ftbteams.name"),
			"https://www.curseforge.com/minecraft/mc-mods/ftb-teams-fabric/download/4229137/file",
			false, false), // ftb-teams-fabric-1802.2.9-build.88.jar
	QUESTS_ADDITIONS("questsadditions", new TranslatableText("mod.questsadditions.name"),
			"https://www.curseforge.com/minecraft/mc-mods/quests-additions-fabric/download/3940981/file",
			false, false); // questsadditions-fabric-1.18.2-1.4.0.jar

	final String modId;
	private final Text name;
	@Nullable
	private final URL url;
	private final boolean required;
	private final boolean isClient;

	CabfModDeps(String id, Text name, String url, boolean required, boolean isClient) {
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

	public String getRawName() {
		return name.getString();
	}

	public boolean hasUrl() {
		return url != null;
	}

	@Nullable
	public URL getUrl() {
		if (!hasUrl())
			CabfLogger.logDebugAndError("Invalid URL for mod " + getRawName() + "!");
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
		if (hasUrl()) {
			try {
				Util.getOperatingSystem().open(url.toURI());
			} catch (URISyntaxException uriSyntaxException) {
				CabfLogger.logDebugAndError("Cannot handle URL for mod " + getRawName() + "!",
						uriSyntaxException);
			}
		} else {
			CabfLogger.logInfo("No URL found for mod " + getRawName() + " (" + modId + ")!");
		}
	}

	public boolean matchesSide(boolean isServer) {
		return !isServer || !isClient;
	}

	public static Stream<CabfModDeps> stream() {
		return Arrays.stream(values());
	}

	private static ArrayList<CabfModDeps> arrayList(Stream<CabfModDeps> stream) {
		return stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}

	public static ArrayList<CabfModDeps> getMissing(boolean required, boolean isServer) {
		return arrayList(stream().filter(dep -> (dep.isRequired() || !required)
				&& dep.matchesSide(isServer) && !dep.isLoaded()));
	}

	public static ArrayList<CabfModDeps> getAllMissing() {
		return arrayList(stream().filter(dep -> !dep.isLoaded()));
	}

	public static boolean isLoaded(boolean required, boolean isServer) {
		return getMissing(required, isServer).stream().findAny().isEmpty();
	}

	public static boolean isAllLoaded() {
		return getAllMissing().stream().findAny().isEmpty();
	}

	public static String asString(boolean required, boolean isServer) {
		return getMissing(required, isServer).stream().map(dep -> dep.getName().getString())
				.reduce((a, b) -> a + ", " + b).orElse("");
	}

	private static URL getUrl(String spec) {
		try {
			return new URL(spec);
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
