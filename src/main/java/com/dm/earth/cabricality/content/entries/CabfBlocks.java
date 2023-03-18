package com.dm.earth.cabricality.content.entries;

import java.util.Arrays;

import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ChaoticCatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.core.blocks.CasingBlockEntry;
import com.dm.earth.cabricality.content.core.blocks.MachineBlockEntry;
import com.dm.earth.cabricality.content.extractor.ExtractorMachineBlock;
import com.dm.earth.cabricality.lib.core.BlockItemSettable;
import com.dm.earth.cabricality.lib.resource.ResourcedBlock;
import com.dm.earth.tags_binder.api.LoadTagsCallback;
import com.simibubi.create.AllTags.AllBlockTags;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfBlocks implements LoadTagsCallback<Block> {
	public static Block EXTRACTOR = registerBlock("extractor_machine",
			new ExtractorMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.BROWN).strength(1.5F, 6.0F)));
	public static Block JAR = registerBlock("jar",
			new JarBlock(QuiltBlockSettings.of(Material.GLASS, MapColor.SPRUCE_BROWN).strength(0.3F)
					.sounds(BlockSoundGroup.GLASS).nonOpaque()));

	public static void register() {
		// Substrate Jars
		Arrays.stream(Reagents.values()).forEach(reagents -> {
			if (reagents == Reagents.CHAOTIC)
				registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(),
						new ChaoticCatalystJarBlock(
								QuiltBlockSettings.of(Material.GLASS, MapColor.BLACK).strength(1F)));
			else
				registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(),
						new CatalystJarBlock(
								QuiltBlockSettings.of(Material.GLASS, MapColor.GOLD).strength(0.4F)));
			reagents.getReagents()
					.forEach(reagent -> registerBlock("reagent_jar_" + reagent.hashString(),
							new ReagentJarBlock(
									QuiltBlockSettings.of(Material.GLASS, MapColor.SPRUCE_BROWN).strength(0.4F))));
		});

		Arrays.stream(MachineBlockEntry.values())
				.forEach(entry -> registerBlock(entry.getId().getPath(), entry.getBlock()));
		Arrays.stream(CasingBlockEntry.values())
				.forEach(entry -> registerBlock(entry.getId().getPath(), entry.getBlock()));

		LoadTagsCallback.BLOCK.register(new CabfBlocks());
	}

	public static FluidBlock registerFluidBlock(Identifier id, FlowableFluid fluid) {
		return Registry.register(Registry.BLOCK, id,
				new FluidBlock(fluid, QuiltBlockSettings.copy(Blocks.WATER)));
	}

	private static Block registerBlock(String name, Block block) {
		Block registered = Registry.register(Registry.BLOCK, Cabricality.id(name), block);

		Registry.register(Registry.ITEM, Cabricality.id(name),
				new BlockItem(block,
						(block instanceof BlockItemSettable settingable)
								? settingable.getSettings()
								: CabfItems.Properties.DEFAULT.get()));

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

	@Override
	public void load(TagHandler<Block> handler) {
		Arrays.stream(MachineBlockEntry.values()).forEach(entry -> handler
				.register(AllBlockTags.WRENCH_PICKUP.tag, Registry.BLOCK.get(entry.getId())));
		Arrays.stream(CasingBlockEntry.values()).forEach(entry -> {
			var block = Registry.BLOCK.get(entry.getId());
			handler.register(AllBlockTags.WRENCH_PICKUP.tag, block);
			handler.register(BlockTags.PICKAXE_MINEABLE, block);
		});
	}
}
