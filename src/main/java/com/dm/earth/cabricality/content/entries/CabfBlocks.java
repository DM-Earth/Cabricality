package com.dm.earth.cabricality.content.entries;

import java.util.Arrays;

import com.dm.earth.cabricality.Cabricality;

import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ChaoticCatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.core.blocks.CasingBlockEntry;
import com.dm.earth.cabricality.content.core.blocks.MachineBlockEntry;
import com.dm.earth.cabricality.content.extractor.ExtractorMachineBlock;
import com.dm.earth.cabricality.core.ISettingableBlockItem;
import com.dm.earth.cabricality.resource.ResourcedBlock;
import com.simibubi.create.AllTags.AllBlockTags;

import net.devtech.arrp.json.tags.JTag;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfBlocks {
	public static Block EXTRACTOR = registerBlock("extractor_machine",
			new ExtractorMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.BROWN)));
	public static Block JAR = registerBlock("jar",
			new JarBlock(QuiltBlockSettings.of(Material.METAL, MapColor.SPRUCE_BROWN)));

	public static void register() {
		JTag wrenchAbleTag = new JTag();
		JTag pickaxeMineableTag = new JTag();

		// Substrate Jars
		Arrays.stream(Reagents.values()).forEach(reagents -> {
			if (reagents == Reagents.CHAOTIC)
				registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(),
						new ChaoticCatalystJarBlock(
								QuiltBlockSettings.of(Material.GLASS, MapColor.BROWN)));
			else
				registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(),
						new CatalystJarBlock(
								QuiltBlockSettings.of(Material.GLASS, MapColor.BROWN)));
			reagents.getReagents()
					.forEach(reagent -> registerBlock("reagent_jar_" + reagent.hashString(),
							new ReagentJarBlock(
									QuiltBlockSettings.of(Material.GLASS, MapColor.SPRUCE_BROWN))));
		});

		// Machines
		Arrays.stream(MachineBlockEntry.values()).forEach(entry -> {
			registerBlock(entry.getId().getPath(), entry.getBlock());
			wrenchAbleTag.add(entry.getId());
		});

		// Casings
		Arrays.stream(CasingBlockEntry.values()).forEach(entry -> {
			registerBlock(entry.getId().getPath(), entry.getBlock());
			wrenchAbleTag.add(entry.getId());
			pickaxeMineableTag.add(entry.getId());
		});

		Cabricality.SERVER_RESOURCES
				.addTag(new Identifier(AllBlockTags.WRENCH_PICKUP.tag.id().getNamespace(),
						"blocks/" + AllBlockTags.WRENCH_PICKUP.tag.id().getPath()), wrenchAbleTag);
		Cabricality.SERVER_RESOURCES
				.addTag(new Identifier(BlockTags.PICKAXE_MINEABLE.id().getNamespace(),
						"blocks/" + BlockTags.PICKAXE_MINEABLE.id().getPath()), pickaxeMineableTag);
	}

	public static FluidBlock registerFluidBlock(Identifier id, FlowableFluid fluid) {
		return Registry.register(Registry.BLOCK, id,
				new FluidBlock(fluid, QuiltBlockSettings.copy(Blocks.WATER)));
	}

	private static Block registerBlock(String name, Block block) {
		Block block2 = Registry.register(Registry.BLOCK, Cabricality.id(name), block);

		Registry.register(Registry.ITEM, Cabricality.id(name),
				new BlockItem(block,
						(block instanceof ISettingableBlockItem settingable)
								? settingable.getSettings()
								: CabfItems.Properties.DEFAULT));

		if (block instanceof ResourcedBlock resourced) {
			if (resourced.doModel())
				resourced.writeBlockModel(Cabricality.CLIENT_RESOURCES);
			if (resourced.doLootTable())
				resourced.writeLootTable(Cabricality.SERVER_RESOURCES);
			if (resourced.doBlockStates())
				resourced.writeBlockStates(Cabricality.CLIENT_RESOURCES);
			if (resourced.doItemModel())
				resourced.writeItemModel(Cabricality.CLIENT_RESOURCES);
		}

		return block2;
	}
}
