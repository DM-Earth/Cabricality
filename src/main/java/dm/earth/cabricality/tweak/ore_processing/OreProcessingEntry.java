package dm.earth.cabricality.tweak.ore_processing;

import dm.earth.cabricality.Mod;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OreProcessingEntry {
	IRON(
			Mod.Entry.MC.id("iron"), Mod.Entry.MC.id("iron_ingot"), Mod.Entry.MC.id("iron_nugget"), Mod.Entry.MC.id("raw_iron"),
			Mod.Entry.CREATE.id("crushed_raw_iron"), Mod.Entry.INDREV.id("iron_dust"), Mod.Entry.TC.id("molten_iron"),
			Mod.Entry.MC.id("iron_ore"), Mod.Entry.MC.id("deepslate_iron_ore"), Mod.Entry.AD_ASTRA.id("moon_iron_ore"),
			Mod.Entry.AD_ASTRA.id("mars_iron_ore"), Mod.Entry.AD_ASTRA.id("mercury_iron_ore"), Mod.Entry.AD_ASTRA.id("glacio_iron_ore")
	),
	GOLD(
			Mod.Entry.MC.id("gold"), Mod.Entry.MC.id("gold_ingot"), Mod.Entry.MC.id("gold_nugget"), Mod.Entry.MC.id("raw_gold"),
			Mod.Entry.CREATE.id("crushed_raw_gold"), Mod.Entry.INDREV.id("gold_dust"), Mod.Entry.TC.id("molten_gold"),
			Mod.Entry.MC.id("gold_ore"), Mod.Entry.MC.id("deepslate_gold_ore"), Mod.Entry.AD_ASTRA.id("venus_gold_ore")
	),
	COPPER(
			Mod.Entry.MC.id("copper"), Mod.Entry.MC.id("copper_ingot"), Mod.Entry.CREATE.id("copper_nugget"), Mod.Entry.MC.id("raw_copper"),
			Mod.Entry.CREATE.id("crushed_raw_copper"), Mod.Entry.INDREV.id("copper_dust"), Mod.Entry.TC.id("molten_copper"),
			Mod.Entry.MC.id("copper_ore"), Mod.Entry.MC.id("deepslate_copper_ore"), Mod.Entry.AD_ASTRA.id("glacio_copper_ore")
	),
	ZINC(
			Mod.Entry.CREATE.id("zinc"), Mod.Entry.CREATE.id("zinc_ingot"), Mod.Entry.CREATE.id("zinc_nugget"), Mod.Entry.CREATE.id("raw_zinc"),
			Mod.Entry.CREATE.id("crushed_raw_zinc"), Mod.Entry.CABF.id("zinc_dust"), Mod.Entry.TC.id("molten_zinc"),
			Mod.Entry.CREATE.id("zinc_ore"), Mod.Entry.CREATE.id("deepslate_zinc_ore")
	),
	TIN(
			Mod.Entry.INDREV.id("tin"), Mod.Entry.INDREV.id("tin_ingot"), Mod.Entry.INDREV.id("tin_nugget"), Mod.Entry.INDREV.id("raw_tin"),
			Mod.Entry.CREATE.id("crushed_raw_tin"), Mod.Entry.INDREV.id("tin_dust"), Mod.Entry.TC.id("molten_tin"), Mod.Entry.INDREV.id("tin_ore"),
			Mod.Entry.INDREV.id("deepslate_tin_ore")
	),
	LEAD(
			Mod.Entry.INDREV.id("lead"), Mod.Entry.INDREV.id("lead_ingot"), Mod.Entry.INDREV.id("lead_nugget"), Mod.Entry.INDREV.id("raw_lead"),
			Mod.Entry.CREATE.id("crushed_raw_lead"), Mod.Entry.INDREV.id("lead_dust"), Mod.Entry.TC.id("molten_lead"),
			Mod.Entry.INDREV.id("lead_ore"), Mod.Entry.INDREV.id("deepslate_lead_ore")
	),
	DESH(
			Mod.Entry.AD_ASTRA.id("desh"), Mod.Entry.AD_ASTRA.id("desh_ingot"), Mod.Entry.AD_ASTRA.id("desh_nugget"), Mod.Entry.AD_ASTRA.id("raw_desh"),
			Mod.Entry.CABF.id("crushed_raw_desh"), Mod.Entry.CABF.id("desh_dust"), Mod.Entry.CABF.id("molten_desh"),
			Mod.Entry.AD_ASTRA.id("moon_desh_ore"), Mod.Entry.AD_ASTRA.id("deepslate_desh_ore")
	),
	OSTRUM(
			Mod.Entry.AD_ASTRA.id("ostrum"), Mod.Entry.AD_ASTRA.id("ostrum_ingot"), Mod.Entry.AD_ASTRA.id("ostrum_nugget"), Mod.Entry.AD_ASTRA.id("raw_ostrum"),
			Mod.Entry.CABF.id("crushed_raw_ostrum"), Mod.Entry.CABF.id("ostrum_dust"), Mod.Entry.CABF.id("molten_ostrum"),
			Mod.Entry.AD_ASTRA.id("mars_ostrum_ore"), Mod.Entry.AD_ASTRA.id("deepslate_ostrum_ore")
	),
	CALORITE(
			Mod.Entry.AD_ASTRA.id("calorite"), Mod.Entry.AD_ASTRA.id("calorite_ingot"), Mod.Entry.AD_ASTRA.id("calorite_nugget"),
			Mod.Entry.AD_ASTRA.id("raw_calorite"), Mod.Entry.CABF.id("crushed_raw_calorite"), Mod.Entry.CABF.id("calorite_dust"),
			Mod.Entry.CABF.id("molten_calorite"), Mod.Entry.AD_ASTRA.id("venus_calorite_ore"), Mod.Entry.AD_ASTRA.id("deepslate_calorite_ore")
	),
	COBALT(
			Mod.Entry.TC.id("cobalt"), Mod.Entry.TC.id("cobalt_ingot"), Mod.Entry.TC.id("cobalt_nugget"), Mod.Entry.TC.id("raw_cobalt"),
			Mod.Entry.CABF.id("crushed_raw_cobalt"), Mod.Entry.CABF.id("cobalt_dust"), Mod.Entry.TC.id("molten_cobalt"),
			Mod.Entry.TC.id("cobalt_ore")
	),
	NICKEL(
			Mod.Entry.CABF.id("nickel"), Mod.Entry.CABF.id("nickel_ingot"), Mod.Entry.CABF.id("nickel_nugget"),
			Mod.Entry.CABF.id("raw_nickel"), Mod.Entry.CREATE.id("crushed_raw_nickel"), Mod.Entry.CABF.id("nickel_dust"),
			Mod.Entry.TC.id("molten_nickel")
	);

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
		return Registries.ITEM.get(this.getIngot());
	}

	public Item getNuggetItem() {
		return Registries.ITEM.get(this.getNugget());
	}

	public Item getRawOreItem() {
		return Registries.ITEM.get(this.getRawOre());
	}

	public Item getCrushedOreItem() {
		return Registries.ITEM.get(this.getCrushedOre());
	}

	public Item getDustItem() {
		return Registries.ITEM.get(this.getDust());
	}

	public Fluid getMoltenMetalFluid() {
		return Registries.FLUID.get(this.getMoltenMetal());
	}

	public List<Item> getOreItems() {
		ArrayList<Item> items = new ArrayList<>();
		for (Identifier ore : this.getOres())
			items.add(Registries.ITEM.get(ore));
		return items;
	}

	public boolean isTargetOre(Item item) {
		String id = Registries.ITEM.getId(item).getPath();
		return id.contains(this.getId().getPath()) && id.contains("ore");
	}

	public static OreProcessingEntry get(Identifier id) {
		return Arrays.stream(values()).filter(entry -> entry.getId().equals(id)).findFirst().orElse(null);
	}

	public void check() {
		if (!Registries.ITEM.containsId(this.getIngot()))
			throwError(this.getIngot());
		if (!Registries.ITEM.containsId(this.getNugget()))
			throwError(this.getNugget());
		if (!Registries.ITEM.containsId(this.getRawOre()))
			throwError(this.getRawOre());
		if (!Registries.ITEM.containsId(this.getCrushedOre()))
			throwError(this.getCrushedOre());
		if (!Registries.ITEM.containsId(this.getDust()))
			throwError(this.getDust());
		if (!Registries.FLUID.containsId(this.getMoltenMetal()))
			throwError(this.getMoltenMetal());
		for (Identifier ore : this.ores)
			if (!Registries.ITEM.containsId(ore))
				throwError(ore);
	}

	public int getHashCode() {
		return this.getId().hashCode();
	}

	private static void throwError(Identifier id) {
		throw new RuntimeException("OreProcessingEntry requires " + id + " which is not valid!");
	}

	public static void checkAll() {
		Arrays.stream(values()).forEach(OreProcessingEntry::check);
	}
}
