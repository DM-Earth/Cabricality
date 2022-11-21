package com.dm.earth.cabricality.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;

import com.google.common.collect.ImmutableMap;

public class ModChecker {
	public static final List<String> missingModList = new ArrayList<>();
	public static final Map<String, String> requiredModList = new HashMap<>();

	public static void check() {
		if (isFullLoaded()) {
			if (requiredModList.isEmpty()) {
				//Write mods here
				requiredModList.putAll(ImmutableMap.of(
						"ftblibrary", "FTB Library",
						"ftbquests", "FTB Quests",
						"ftbteams", "FTB Teams",
						"questsadditions", "Quests Additions"
				));
			}
			for (Map.Entry<String, String> modEntry : requiredModList.entrySet()) {
				if (!QuiltLoader.isModLoaded(modEntry.getKey()) && !missingModList.contains(modEntry.getValue()))
					missingModList.add(modEntry.getValue());
			}
		}
	}

	@Nullable
	public static String getMods() {
		if (isFullLoaded()) return null;
		StringBuilder mods = new StringBuilder();
		for (String mod : missingModList) {
			if (!mods.toString().equals("")) {
				mods.append(", ").append(mod);
			} else {
				mods = new StringBuilder(mod);
			}
		}
		return mods.toString();
	}

	public static boolean checkMissingMod(String modName) {
		return missingModList.contains(modName);
	}

	public static int missingModCount() {
		return missingModList.size();
	}

	public static boolean isFullLoaded() {
		return missingModList.isEmpty();
	}
}
