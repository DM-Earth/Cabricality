package com.dm.earth.cabricality.util;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.QuiltLoader;

import com.google.common.collect.ImmutableMap;

public class ModChecker {
	public static final Map<String, String>
			requiredModList = new HashMap<>(),
			requiredModUrlList = new HashMap<>(), // Download Url
			requiredModDedicatedUrlList = new HashMap<>(); // Dedicated Url

	public static void check() {
		if (isFullLoaded()) {
			if (requiredModList.isEmpty()) {
				/* Append Mods Here */
				requiredModList.putAll(ImmutableMap.of(
						"ftblibrary", "FTB Library",
						"ftbquests", "FTB Quests",
						"ftbteams", "FTB Teams",
						"questsadditions", "Quests Additions"
				));
				/* Add Download Url */
				requiredModUrlList.putAll(ImmutableMap.of(
						"ftblibrary", "https://www.curseforge.com/minecraft/mc-mods/ftb-library-fabric/download/4210934/file", // ftb-library-fabric-1802.3.9-build.167.jar
						"ftbquests", "https://www.curseforge.com/minecraft/mc-mods/ftb-quests-fabric/download/4215548/file", // ftb-quests-fabric-1802.3.11-build.151.jar
						"ftbteams", "https://www.curseforge.com/minecraft/mc-mods/ftb-teams-fabric/download/4229137/file", // ftb-teams-fabric-1802.2.9-build.88.jar
						"questsadditions", "https://www.curseforge.com/minecraft/mc-mods/quests-additions-fabric/download/3940981/file" // questsadditions-fabric-1.18.2-1.4.0.jar
				));
				/* Add Dedicated Url, to Download Pages */
				requiredModDedicatedUrlList.putAll(ImmutableMap.of(
						"ftblibrary", "https://www.curseforge.com/minecraft/mc-mods/ftb-library-fabric/download/4210934",
						"ftbquests", "https://www.curseforge.com/minecraft/mc-mods/ftb-quests-fabric/download/4215548",
						"ftbteams", "https://www.curseforge.com/minecraft/mc-mods/ftb-teams-fabric/download/4229137",
						"questsadditions", "https://www.curseforge.com/minecraft/mc-mods/quests-additions-fabric/download/3940981"
				));
			}
		}
	}

	@NotNull
	public static Map<String, String> getMissingModList() {
		return requiredModList.entrySet().stream()
					   .filter(entry -> !QuiltLoader.isModLoaded(entry.getKey()))
					   .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), HashMap::putAll);
	}

	@NotNull
	public static String asString() {
		if (isFullLoaded()) return "";
		StringBuilder result = getMissingModList().values().stream()
									 .reduce(new StringBuilder(), (builder, mod) -> builder.append(mod).append(", "), StringBuilder::append);
		return result.delete(result.length() - 2, result.length()).toString();
	}

	@Deprecated
	@NotNull
	public static Map<String, String> getModUrlList() {
		if (isFullLoaded()) return new HashMap<>();
		return requiredModUrlList.entrySet().stream()
					   .filter(entry -> getMissingModList().containsKey(entry.getKey()))
					   .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), HashMap::putAll);
	}

	@NotNull
	public static Map<String, String> getModDedicatedUrlList() {
		if (isFullLoaded()) return new HashMap<>();
		return requiredModDedicatedUrlList.entrySet().stream()
					   .filter(entry -> getMissingModList().containsKey(entry.getKey()))
					   .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), HashMap::putAll);
	}

	public static boolean isMissing(String modid) {
		return getMissingModList().containsKey(modid);
	}

	public static int countMissing() {
		return getMissingModList().size();
	}

	public static boolean isFullLoaded() {
		return getMissingModList().isEmpty();
	}
}
