package com.dm.earth.cabricality.tweak.cutting;

import static com.dm.earth.cabricality.ModEntry.MC;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.Identifier;

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
            null);

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

    public static WoodCuttingEntry get(Identifier id) {
        for (WoodCuttingEntry entry : values())
            if (entry.getId().equals(id))
                return entry;
        return null;
    }
}
