package com.dm.earth.cabricality.tweak.ore_processing;

import static com.dm.earth.cabricality.ModEntry.AD;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IR;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum OreProcessingEntry {
	IRON(MC.id("iron"), MC.id("iron_ingot"), MC.id("iron_nugget"), MC.id("raw_iron"), CR.id("crushed_iron_ore"),
			IR.id("iron_dust"), TC.id("molten_iron"), MC.id("iron_ore"), MC.id("deepslate_iron_ore"),
			AD.id("moon_iron_ore"), AD.id("mars_iron_ore"), AD.id("mercury_iron_ore"), AD.id("glacio_iron_ore")),
	GOLD(MC.id("gold"), MC.id("gold_ingot"), MC.id("gold_nugget"), MC.id("raw_gold"), CR.id("crushed_gold_ore"),
			IR.id("gold_dust"), TC.id("molten_gold"), MC.id("gold_ore"), MC.id("deepslate_gold_ore"),
			AD.id("venus_gold_ore")),
	COPPER(MC.id("copper"), MC.id("copper_ingot"), CR.id("copper_nugget"), MC.id("raw_copper"),
			CR.id("crushed_copper_ore"),
			IR.id("copper_dust"), TC.id("molten_copper"), MC.id("copper_ore"), MC.id("deepslate_copper_ore"),
			AD.id("glacio_copper_ore")),
	ZINC(CR.id("zinc"), CR.id("zinc_ingot"), CR.id("zinc_nugget"), CR.id("raw_zinc"), CR.id("crushed_zinc_ore"),
			CABF.id("zinc_dust"), TC.id("molten_zinc"), CR.id("zinc_ore"), CR.id("deepslate_zinc_ore")),
	TIN(IR.id("tin"), IR.id("tin_ingot"), IR.id("tin_nugget"), IR.id("raw_tin"), CR.id("crushed_tin_ore"),
			IR.id("tin_dust"), TC.id("molten_tin"), IR.id("tin_ore"), IR.id("deepslate_tin_ore")),
	LEAD(IR.id("lead"), IR.id("lead_ingot"), IR.id("lead_nugget"), IR.id("raw_lead"), CR.id("crushed_lead_ore"),
			IR.id("lead_dust"), TC.id("molten_lead"), IR.id("lead_ore"), IR.id("deepslate_lead_ore")),
	DESH(AD.id("desh"), AD.id("desh_ingot"), AD.id("desh_nugget"), AD.id("raw_desh"), CABF.id("crushed_desh_ore"),
			CABF.id("desh_dust"), CABF.id("molten_desh"), AD.id("moon_desh_ore"), AD.id("deepslate_desh_ore")),
	OSTRUM(AD.id("ostrum"), AD.id("ostrum_ingot"), AD.id("ostrum_nugget"), AD.id("raw_ostrum"),
			CABF.id("crushed_ostrum_ore"),
			CABF.id("ostrum_dust"), CABF.id("molten_ostrum"), AD.id("mars_ostrum_ore"), AD.id("deepslate_ostrum_ore")),
	CALORITE(AD.id("calorite"), AD.id("calorite_ingot"), AD.id("calorite_nugget"), AD.id("raw_calorite"),
			CABF.id("crushed_calorite_ore"),
			CABF.id("calorite_dust"), CABF.id("molten_calorite"), AD.id("venus_calorite_ore"),
			AD.id("deepslate_calorite_ore")),
	COBALT(TC.id("cobalt"), TC.id("cobalt_ingot"), TC.id("cobalt_nugget"), TC.id("raw_cobalt"),
			CABF.id("crushed_cobalt_ore"),
			CABF.id("cobalt_dust"), TC.id("molten_cobalt"), TC.id("cobalt_ore")),
	NICKEL(CABF.id("nickel"), CABF.id("nickel_ingot"), CABF.id("nickel_nugget"), CABF.id("raw_nickel"),
			CR.id("crushed_nickel_ore"), CABF.id("nickel_dust"), TC.id("molten_nickel"));

	private final Identifier id;
	private final Identifier ingot;
	private final Identifier nugget;
	private final Identifier rawOre;
	private final Identifier crushedOre;
	private final Identifier dust;
	private final Identifier moltenMetal;
	private final List<Identifier> ores;

	OreProcessingEntry(Identifier id, Identifier ingot, Identifier nugget, Identifier raw, Identifier crushed,
			Identifier dust, Identifier moltenMetal, Identifier... ores) {
		this.id = id;
		this.ingot = ingot;
		this.nugget = nugget;
		this.rawOre = raw;
		this.crushedOre = crushed;
		this.dust = dust;
		this.moltenMetal = moltenMetal;
		this.ores = List.of(ores);
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

	public List<Identifier> getOres() {
		return ores;
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

	public List<Item> getOreItems() {
		ArrayList<Item> items = new ArrayList<>();
		for (Identifier ore : this.getOres())
			items.add(Registry.ITEM.get(ore));
		return items;
	}

	public boolean isTargetOre(Item item) {
		String id = Registry.ITEM.getId(item).getPath();
		return id.contains(this.getId().getPath()) && id.contains("ore");
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
		for (Identifier ore : this.ores)
			if (!Registry.ITEM.containsId(ore))
				throwError(ore);
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
