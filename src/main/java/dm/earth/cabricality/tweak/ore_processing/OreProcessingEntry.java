package dm.earth.cabricality.tweak.ore_processing;

import dm.earth.cabricality.ModEntry;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OreProcessingEntry {
	IRON(
			ModEntry.MC.id("iron"), ModEntry.MC.id("iron_ingot"), ModEntry.MC.id("iron_nugget"), ModEntry.MC.id("raw_iron"),
			ModEntry.CREATE.id("crushed_raw_iron"), ModEntry.INDREV.id("iron_dust"), ModEntry.TC.id("molten_iron"),
			ModEntry.MC.id("iron_ore"), ModEntry.MC.id("deepslate_iron_ore"), ModEntry.AD_ASTRA.id("moon_iron_ore"),
			ModEntry.AD_ASTRA.id("mars_iron_ore"), ModEntry.AD_ASTRA.id("mercury_iron_ore"), ModEntry.AD_ASTRA.id("glacio_iron_ore")
	),
	GOLD(
			ModEntry.MC.id("gold"), ModEntry.MC.id("gold_ingot"), ModEntry.MC.id("gold_nugget"), ModEntry.MC.id("raw_gold"),
			ModEntry.CREATE.id("crushed_raw_gold"), ModEntry.INDREV.id("gold_dust"), ModEntry.TC.id("molten_gold"),
			ModEntry.MC.id("gold_ore"), ModEntry.MC.id("deepslate_gold_ore"), ModEntry.AD_ASTRA.id("venus_gold_ore")
	),
	COPPER(
			ModEntry.MC.id("copper"), ModEntry.MC.id("copper_ingot"), ModEntry.CREATE.id("copper_nugget"), ModEntry.MC.id("raw_copper"),
			ModEntry.CREATE.id("crushed_raw_copper"), ModEntry.INDREV.id("copper_dust"), ModEntry.TC.id("molten_copper"),
			ModEntry.MC.id("copper_ore"), ModEntry.MC.id("deepslate_copper_ore"), ModEntry.AD_ASTRA.id("glacio_copper_ore")
	),
	ZINC(
			ModEntry.CREATE.id("zinc"), ModEntry.CREATE.id("zinc_ingot"), ModEntry.CREATE.id("zinc_nugget"), ModEntry.CREATE.id("raw_zinc"),
			ModEntry.CREATE.id("crushed_raw_zinc"), ModEntry.CABF.id("zinc_dust"), ModEntry.TC.id("molten_zinc"),
			ModEntry.CREATE.id("zinc_ore"), ModEntry.CREATE.id("deepslate_zinc_ore")
	),
	TIN(
			ModEntry.INDREV.id("tin"), ModEntry.INDREV.id("tin_ingot"), ModEntry.INDREV.id("tin_nugget"), ModEntry.INDREV.id("raw_tin"),
			ModEntry.CREATE.id("crushed_raw_tin"), ModEntry.INDREV.id("tin_dust"), ModEntry.TC.id("molten_tin"), ModEntry.INDREV.id("tin_ore"),
			ModEntry.INDREV.id("deepslate_tin_ore")
	),
	LEAD(
			ModEntry.INDREV.id("lead"), ModEntry.INDREV.id("lead_ingot"), ModEntry.INDREV.id("lead_nugget"), ModEntry.INDREV.id("raw_lead"),
			ModEntry.CREATE.id("crushed_raw_lead"), ModEntry.INDREV.id("lead_dust"), ModEntry.TC.id("molten_lead"),
			ModEntry.INDREV.id("lead_ore"), ModEntry.INDREV.id("deepslate_lead_ore")
	),
	DESH(
			ModEntry.AD_ASTRA.id("desh"), ModEntry.AD_ASTRA.id("desh_ingot"), ModEntry.AD_ASTRA.id("desh_nugget"), ModEntry.AD_ASTRA.id("raw_desh"),
			ModEntry.CABF.id("crushed_raw_desh"), ModEntry.CABF.id("desh_dust"), ModEntry.CABF.id("molten_desh"),
			ModEntry.AD_ASTRA.id("moon_desh_ore"), ModEntry.AD_ASTRA.id("deepslate_desh_ore")
	),
	OSTRUM(
			ModEntry.AD_ASTRA.id("ostrum"), ModEntry.AD_ASTRA.id("ostrum_ingot"), ModEntry.AD_ASTRA.id("ostrum_nugget"), ModEntry.AD_ASTRA.id("raw_ostrum"),
			ModEntry.CABF.id("crushed_raw_ostrum"), ModEntry.CABF.id("ostrum_dust"), ModEntry.CABF.id("molten_ostrum"),
			ModEntry.AD_ASTRA.id("mars_ostrum_ore"), ModEntry.AD_ASTRA.id("deepslate_ostrum_ore")
	),
	CALORITE(
			ModEntry.AD_ASTRA.id("calorite"), ModEntry.AD_ASTRA.id("calorite_ingot"), ModEntry.AD_ASTRA.id("calorite_nugget"),
			ModEntry.AD_ASTRA.id("raw_calorite"), ModEntry.CABF.id("crushed_raw_calorite"), ModEntry.CABF.id("calorite_dust"),
			ModEntry.CABF.id("molten_calorite"), ModEntry.AD_ASTRA.id("venus_calorite_ore"), ModEntry.AD_ASTRA.id("deepslate_calorite_ore")
	),
	COBALT(
			ModEntry.TC.id("cobalt"), ModEntry.TC.id("cobalt_ingot"), ModEntry.TC.id("cobalt_nugget"), ModEntry.TC.id("raw_cobalt"),
			ModEntry.CABF.id("crushed_raw_cobalt"), ModEntry.CABF.id("cobalt_dust"), ModEntry.TC.id("molten_cobalt"),
			ModEntry.TC.id("cobalt_ore")
	),
	NICKEL(
			ModEntry.CABF.id("nickel"), ModEntry.CABF.id("nickel_ingot"), ModEntry.CABF.id("nickel_nugget"),
			ModEntry.CABF.id("raw_nickel"), ModEntry.CREATE.id("crushed_raw_nickel"), ModEntry.CABF.id("nickel_dust"),
			ModEntry.TC.id("molten_nickel")
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
