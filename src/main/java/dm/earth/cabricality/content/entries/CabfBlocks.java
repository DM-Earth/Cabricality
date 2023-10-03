package dm.earth.cabricality.content.entries;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.alchemist.Reagents;
import dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import dm.earth.cabricality.content.alchemist.block.ChaoticCatalystJarBlock;
import dm.earth.cabricality.content.alchemist.block.JarBlock;
import dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import dm.earth.cabricality.content.core.blocks.CasingBlockEntry;
import dm.earth.cabricality.content.core.blocks.MachineBlockEntry;
import dm.earth.cabricality.content.extractor.ExtractorMachineBlock;
import dm.earth.cabricality.lib.core.BlockItemSettable;
import dm.earth.cabricality.lib.resource.ResourcedBlock;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.simibubi.create.AllTags.AllBlockTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class CabfBlocks implements LoadTagsCallback<Block> {
	public static Block EXTRACTOR = registerBlock(
			"extractor_machine",
			new ExtractorMachineBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.BROWN)
					.strength(1.5F, 6.0F))
	);
	public static Block JAR = registerBlock(
			"jar",
			new JarBlock(FabricBlockSettings.copyOf(Blocks.GLASS)
					.mapColor(MapColor.BROWN))
	);

	public static void register() {
		// Substrate Jars
		Arrays.stream(Reagents.values()).forEach(reagents -> {
			if (reagents == Reagents.CHAOTIC)
				registerBlock(
						"catalyst_jar_" + reagents.getCatalyst().hashString(),
						new ChaoticCatalystJarBlock(FabricBlockSettings.copyOf(Blocks.GLASS)
								.mapColor(MapColor.BLACK)
								.strength(1F)),
						Cabricality.ItemGroups.SUBSTRATES
				);
			else
				registerBlock(
						"catalyst_jar_" + reagents.getCatalyst().hashString(),
						new CatalystJarBlock(FabricBlockSettings.copyOf(Blocks.GLASS)
								.mapColor(MapColor.GOLD)
								.strength(0.4F)),
						Cabricality.ItemGroups.SUBSTRATES
				);
			reagents.getReagents()
					.forEach(reagent -> registerBlock(
							"reagent_jar_" + reagent.hashString(),
							new ReagentJarBlock(FabricBlockSettings.copyOf(Blocks.GLASS)
									.mapColor(MapColor.BROWN)
									.strength(0.4F)),
									Cabricality.ItemGroups.SUBSTRATES
							)
					);
		});

		Arrays.stream(MachineBlockEntry.values())
				.forEach(entry -> registerBlock(entry.getId().getPath(), entry.getBlock()));
		Arrays.stream(CasingBlockEntry.values())
				.forEach(entry -> registerBlock(entry.getId().getPath(), entry.getBlock()));

		LoadTagsCallback.BLOCK.register(new CabfBlocks());
	}

	public static FluidBlock registerFluidBlock(Identifier id, FlowableFluid fluid) {
		return Registry.register(
				Registries.BLOCK, id,
				new FluidBlock(fluid, FabricBlockSettings.copy(Blocks.WATER))
		);
	}

	private static Block registerBlock(String name, Block block, @Nullable ItemGroup itemGroup) {
		Block registered = Registry.register(Registries.BLOCK, Cabricality.id(name), block);

		Item blockItem = Registry.register(
				Registries.ITEM,
				Cabricality.id(name),
				new BlockItem(
						block,
						(block instanceof BlockItemSettable settable)
								? settable.getSettings()
								: CabfItems.Suppliers.DEFAULT.get()
				)
		);

		if (itemGroup != null) {
			Registries.ITEM_GROUP.getKey(itemGroup).ifPresent(key ->
					(ItemGroupEvents.modifyEntriesEvent(key)).register(content -> content.addItem(blockItem)));
		}

		if (block instanceof ResourcedBlock resourced) {
			if (resourced.doModel())
				resourced.writeBlockModel(Cabricality.RRPs.CLIENT_RESOURCES);
			if (resourced.doLootTable())
				resourced.writeLootTable(Cabricality.RRPs.SERVER_RESOURCES);
			if (resourced.doBlockStates())
				resourced.writeBlockStates(Cabricality.RRPs.CLIENT_RESOURCES);
			if (resourced.doItemModel())
				resourced.writeItemModel(Cabricality.RRPs.CLIENT_RESOURCES);
		}

		return registered;
	}

	private static Block registerBlock(String name, Block block) {
		return registerBlock(name, block, null);
	}

	@Override
	public void onTagsLoad(TagHandler<Block> handler) {
		Arrays.stream(MachineBlockEntry.values()).forEach(entry -> handler
				.register(AllBlockTags.WRENCH_PICKUP.tag, Registries.BLOCK.get(entry.getId())));

		Arrays.stream(CasingBlockEntry.values()).forEach(entry -> {
			var block = Registries.BLOCK.get(entry.getId());
			handler.register(AllBlockTags.WRENCH_PICKUP.tag, block);
			handler.register(BlockTags.PICKAXE_MINEABLE, block);
		});
	}
}
