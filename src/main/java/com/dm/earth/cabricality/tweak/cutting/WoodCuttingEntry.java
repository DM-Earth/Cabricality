package com.dm.earth.cabricality.tweak.cutting;

import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.MLM;
import static com.dm.earth.cabricality.ModEntry.PMD;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// TODO: Terrestria
public enum WoodCuttingEntry {
    OAK(MC.id("oak"), "oak_planks", "oak_slab", null, null, null,
            null),
    SPRUCE(MC.id("spruce"), "spruce_planks", "spruce_slab", null, null, null,
            null),
    BIRCH(MC.id("birch"), "birch_planks", "birch_slab", null, null, null,
            null),
    JUNGLE(MC.id("jungle"), "jungle_planks", "jungle_slab", null, null, null,
            null),
    ACACIA(MC.id("acacia"), "acacia_planks", "acacia_slab", null, null, null,
            null),
    DARK_OAK(MC.id("dark_oak"), "dark_oak_planks", "dark_oak_slab", null, null, null,
            null),
    CRIMSON(MC.id("crimson"), "crimson_planks", "crimson_slab", null, null, null,
            null),
    WARPED(MC.id("warped"), "warped_planks", "warped_slab", null, null, null,
            null),
    CHERRY_OAK(PMD.id("cherry_oak"), "cherry_oak_planks", "cherry_oak_slab", "cherry_oak_log",
            "stripped_cherry_oak_log", "cherry_oak_wood",
            "stripped_cherry_oak_wood"),
    PALM(PMD.id("palm"), "palm_planks", "palm_slab", "palm_log", "stripped_palm_log", "palm_wood",
            "stripped_palm_wood"),
    RUNEWOOD(MLM.id("runewood"), "runewood_planks", "runewood_planks_slab", "runewood_log", "stripped_runewood_log",
            "runewood",
            "stripped_runewood"),
    SOULWOOD(MLM.id("soulwood"), "soulwood_planks", "soulwood_planks_slab", "soulwood_log", "stripped_soulwood_log",
            "soulwood",
            "stripped_soulwood");

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

    WoodCuttingEntry(Identifier id, String plank, String plankSlab, String log, String strippedLog, String wood,
            String strippedWood) {
        this.id = id;
        this.plankId = (plank == null) ? null : new Identifier(id.getNamespace(), plank);
        this.plankSlabId = (plankSlab == null) ? null : new Identifier(id.getNamespace(), plankSlab);
        this.logId = (log == null) ? null : new Identifier(id.getNamespace(), log);
        this.strippedLogId = (strippedLog == null) ? null : new Identifier(id.getNamespace(), strippedLog);
        this.woodId = (wood == null) ? null : new Identifier(id.getNamespace(), wood);
        this.strippedWoodId = (strippedWood == null) ? null : new Identifier(id.getNamespace(), strippedWood);
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
