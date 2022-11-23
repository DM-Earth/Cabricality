package com.dm.earth.cabricality.tweak.ore_processing;

import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IV;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import org.jetbrains.annotations.Nullable;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// TODO: fill this out
public enum OreProcessingEntry {
	IRON(MC.id("iron"), MC.id("iron_ingot"), MC.id("iron_nugget"), MC.id("raw_iron"), CR.id("crushed_iron_ore"),
			IV.id("iron_dust"), TC.id("molten_iron"), ItemTags.IRON_ORES),
	GOLD(MC.id("gold"), MC.id("gold_ingot"), MC.id("gold_nugget"), MC.id("raw_gold"), CR.id("crushed_gold_ore"),
			IV.id("gold_dust"), TC.id("molten_gold"), ItemTags.GOLD_ORES);

	private final Identifier id;
	private final Identifier ingot;
	private final Identifier nugget;
	private final Identifier rawOre;
	private final Identifier crushedOre;
	private final Identifier dust;
	private final Identifier moltenMetal;
	@Nullable
	private final TagKey<Item> oreTag;

	OreProcessingEntry(Identifier id, Identifier ingot, Identifier nugget, Identifier raw, Identifier crushed,
			Identifier dust, Identifier moltenMetal, TagKey<Item> oreTag) {
		this.id = id;
		this.ingot = ingot;
		this.nugget = nugget;
		this.rawOre = raw;
		this.crushedOre = crushed;
		this.dust = dust;
		this.moltenMetal = moltenMetal;
		this.oreTag = oreTag;
	}

	public Identifier getId() {
		return id;
	}

	public Identifier getIngot() {
		return ingot;
	}

	public Identifier getNugget() {
		return nugget;
	}

	public Identifier getRawOre() {
		return rawOre;
	}

	public Identifier getCrushedOre() {
		return crushedOre;
	}

	public Identifier getDust() {
		return dust;
	}

	public Identifier getMoltenMetal() {
		return moltenMetal;
	}

	public Item getIngotItem() {
		return Registry.ITEM.get(this.getIngot());
	}

	public Item getNuggetItem() {
		return Registry.ITEM.get(this.getNugget());
	}

	public Item getRawOreItem() {
		return Registry.ITEM.get(this.getRawOre());
	}

	public Item getCrushedOreItem() {
		return Registry.ITEM.get(this.getCrushedOre());
	}

	public Item getDustItem() {
		return Registry.ITEM.get(this.getDust());
	}

	public Fluid getMoltenMetalFluid() {
		return Registry.FLUID.get(this.getMoltenMetal());
	}

	public TagKey<Item> getOreTag() {
		return oreTag;
	}

	public boolean isTargetOre(Item item) {
		String id = Registry.ITEM.getId(item).getPath();
		return (this.oreTag != null && Registry.ITEM.getTagKeys().anyMatch(key -> key.id().equals(this.oreTag.id()))
				&& Registry.ITEM.getTag(this.oreTag).get().stream().anyMatch(itemHolder -> itemHolder.value() == item))
				|| (id.contains(this.getId().getPath()) && id.contains("ore"));
	}

	public static OreProcessingEntry get(Identifier id) {
		for (OreProcessingEntry entry : values())
			if (entry.getId().equals(id))
				return entry;
		return null;
	}

	public void check() {
		if (!Registry.ITEM.containsId(this.getIngot()))
			throwError(this.getIngot());
		if (!Registry.ITEM.containsId(this.getNugget()))
			throwError(this.getNugget());
		if (!Registry.ITEM.containsId(this.getRawOre()))
			throwError(this.getRawOre());
		if (!Registry.ITEM.containsId(this.getCrushedOre()))
			throwError(this.getCrushedOre());
		if (!Registry.ITEM.containsId(this.getDust()))
			throwError(this.getDust());
		if (!Registry.FLUID.containsId(this.getMoltenMetal()))
			throwError(this.getMoltenMetal());
	}

	public int getHashCode() {
		return this.getId().hashCode();
	}

	private static void throwError(Identifier id) {
		throw new RuntimeException("OreProcessingEntry requires " + id + " which is not valid!");
	}

	public static void checkAll() {
		for (OreProcessingEntry entry : OreProcessingEntry.values())
			entry.check();
	}
}
