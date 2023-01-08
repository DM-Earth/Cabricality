package com.dm.earth.cabricality.tweak.cutting;

import static com.dm.earth.cabricality.ModEntry.AD;
import static com.dm.earth.cabricality.ModEntry.AP;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.MLM;
import static com.dm.earth.cabricality.ModEntry.PMD;
import static com.dm.earth.cabricality.ModEntry.TC;
import static com.dm.earth.cabricality.ModEntry.TRE;
import org.jetbrains.annotations.Nullable;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum WoodCuttingEntry {
	OAK(MC.id("oak"), "oak_planks", "oak_slab", null, null, null, null),

	SPRUCE(MC.id("spruce"), "spruce_planks", "spruce_slab", null, null, null, null),

	BIRCH(MC.id("birch"), "birch_planks", "birch_slab", null, null, null, null),

	JUNGLE(MC.id("jungle"), "jungle_planks", "jungle_slab", null, null, null, null),

	ACACIA(MC.id("acacia"), "acacia_planks", "acacia_slab", null, null, null, null),

	DARK_OAK(MC.id("dark_oak"), "dark_oak_planks", "dark_oak_slab", null, null, null, null),

	CRIMSON(MC.id("crimson"), "crimson_planks", "crimson_slab", null, null, null, null),

	WARPED(MC.id("warped"), "warped_planks", "warped_slab", null, null, null, null),

	CHERRY_OAK(PMD.id("cherry_oak"), "cherry_oak_planks", "cherry_oak_slab", "cherry_oak_log",
			"stripped_cherry_oak_log", "cherry_oak_wood", "stripped_cherry_oak_wood"),

	PALM(PMD.id("palm"), "palm_planks", "palm_slab", "palm_log", "stripped_palm_log", "palm_wood",
			"stripped_palm_wood"),

	RUNEWOOD(MLM.id("runewood"), "runewood_planks", "runewood_planks_slab", "runewood_log",
			"stripped_runewood_log", "runewood", "stripped_runewood"),

	SOULWOOD(MLM.id("soulwood"), "soulwood_planks", "soulwood_planks_slab", "soulwood_log",
			"stripped_soulwood_log", "soulwood", "stripped_soulwood"),

	BLOODSHROOM(TC.id("bloodshroom"), "bloodshroom_planks", "bloodshroom_planks_slab", null, null,
			null, null),

	SKYROOT(TC.id("skyroot"), "skyroot_planks", "skyroot_planks_slab", null, null, null, null),

	GREENHEART(TC.id("greenheart"), "greenheart_planks", "greenheart_planks_slab", null, null, null,
			null),

	GLACIAN(AD.id("glacian"), "glacian_planks", "glacian_slab", "glacian_log",
			"stripped_glacian_log", null, null),

	TWISTED(AP.id("twisted"), "twisted_planks", "twisted_slab", "twisted_log",
			"stripped_twisted_log", "twisted_wood", "stripped_twisted_wood"),

	RAINBOW_EUCALYPTUS(TRE.id("rainbow_eucalyptus"), "rainbow_eucalyptus_planks",
			"rainbow_eucalyptus_slab", "rainbow_eucalyptus_log", "stripped_rainbow_eucalyptus_log",
			"rainbow_eucalyptus_wood", "stripped_rainbow_eucalyptus_wood"),

	CYPRESS(TRE.id("cypress"), "cypress_planks", "cypress_slab", "cypress_log",
			"stripped_cypress_log", "cypress_wood", "stripped_cypress_wood"),

	HEMLOCK(TRE.id("hemlock"), "hemlock_planks", "hemlock_slab", "hemlock_log",
			"stripped_hemlock_log", "hemlock_wood", "stripped_hemlock_wood"),

	REDWOOD(TRE.id("redwood"), "redwood_planks", "redwood_slab", "redwood_log",
			"stripped_redwood_log", "redwood_wood", "stripped_redwood_wood"),

	JAPANESE_MAPLE(TRE.id("japanese_maple"), "japanese_maple_planks", "japanese_maple_slab",
			"japanese_maple_log", "stripped_japanese_maple_log", "japanese_maple_wood",
			"stripped_japanese_maple_wood"),

	WILLOW(TRE.id("willow"), "willow_planks", "willow_slab", "willow_log", "stripped_willow_log",
			"willow_wood", "stripped_willow_wood"),

	RUBBER(TRE.id("rubber"), "rubber_planks", "rubber_slab", "rubber_log", "stripped_rubber_log",
			"rubber_wood", "stripped_rubber_wood");

	private final Identifier id;
	@Nullable
	private final Identifier plankId;
	@Nullable
	private final Identifier plankSlabId;
	@Nullable
	private final Identifier logId;
	@Nullable
	private final Identifier strippedLogId;
	@Nullable
	private final Identifier woodId;
	@Nullable
	private final Identifier strippedWoodId;

	WoodCuttingEntry(Identifier id, String plank, String plankSlab, String log, String strippedLog,
			String wood, String strippedWood) {
		this.id = id;
		this.plankId = (plank == null) ? null : new Identifier(id.getNamespace(), plank);
		this.plankSlabId =
				(plankSlab == null) ? null : new Identifier(id.getNamespace(), plankSlab);
		this.logId = (log == null) ? null : new Identifier(id.getNamespace(), log);
		this.strippedLogId =
				(strippedLog == null) ? null : new Identifier(id.getNamespace(), strippedLog);
		this.woodId = (wood == null) ? null : new Identifier(id.getNamespace(), wood);
		this.strippedWoodId =
				(strippedWood == null) ? null : new Identifier(id.getNamespace(), strippedWood);
	}

	public Identifier getId() {
		return id;
	}

	@Nullable
	public Identifier getPlankId() {
		return plankId;
	}

	@Nullable
	public Identifier getPlankSlabId() {
		return plankSlabId;
	}

	@Nullable
	public Identifier getLogId() {
		return logId;
	}

	@Nullable
	public Identifier getStrippedLogId() {
		return strippedLogId;
	}

	@Nullable
	public Identifier getWoodId() {
		return woodId;
	}

	@Nullable
	public Identifier getStrippedWoodId() {
		return strippedWoodId;
	}

	public boolean isPlankExist() {
		return plankId != null;
	}

	public boolean isPlankSlabExist() {
		return plankSlabId != null;
	}

	public boolean isLogExist() {
		return logId != null;
	}

	public boolean isStrippedLogExist() {
		return strippedLogId != null;
	}

	public boolean isWoodExist() {
		return woodId != null;
	}

	public boolean isStrippedWoodExist() {
		return strippedWoodId != null;
	}

	public void check() {
		if (!Registry.ITEM.containsId(this.getLogId()) && this.getLogId() != null)
			throwError(this.getLogId());
		if (!Registry.ITEM.containsId(this.getStrippedLogId()) && this.getStrippedLogId() != null)
			throwError(this.getStrippedLogId());
		if (!Registry.ITEM.containsId(this.getWoodId()) && this.getWoodId() != null)
			throwError(this.getWoodId());
		if (!Registry.ITEM.containsId(this.getStrippedWoodId()) && this.getStrippedWoodId() != null)
			throwError(this.getStrippedWoodId());
		if (!Registry.ITEM.containsId(this.getPlankId()) && this.getPlankId() != null)
			throwError(this.getPlankId());
		if (!Registry.ITEM.containsId(this.getPlankSlabId()) && this.getPlankSlabId() != null)
			throwError(this.getPlankSlabId());
	}

	private static void throwError(Identifier id) {
		throw new RuntimeException("WoodCuttingEntry requires " + id + " item which is not valid!");
	}

	public static WoodCuttingEntry get(Identifier id) {
		for (WoodCuttingEntry entry : values())
			if (entry.getId().equals(id))
				return entry;
		return null;
	}

	public static void checkAll() {
		for (WoodCuttingEntry entry : WoodCuttingEntry.values())
			entry.check();
	}
}
