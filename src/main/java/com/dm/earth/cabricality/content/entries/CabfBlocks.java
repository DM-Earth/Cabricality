package com.dm.earth.cabricality.content.entries;

import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ChaoticCatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import com.dm.earth.cabricality.content.machine.extractor.ExtractorMachineBlock;
import com.dm.earth.cabricality.content.threads.blocks.MachineBlockEntry;
import com.dm.earth.cabricality.core.ISettingableBlockItem;
import com.dm.earth.cabricality.resource.ResourcedBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfBlocks {
	public static ExtractorMachineBlock EXTRACTOR = new ExtractorMachineBlock(
			QuiltBlockSettings.of(Material.METAL, MapColor.BROWN));
	public static JarBlock JAR = new JarBlock(QuiltBlockSettings.of(Material.METAL, MapColor.SPRUCE_BROWN));

	public static void register() {
		registerBlock("extractor_machine", EXTRACTOR);

		// Substrate Jars
		registerBlock("jar", JAR);
		for (Reagents reagents : Reagents.values()) {
			if (reagents == Reagents.CHAOTIC)
				registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(),
						new ChaoticCatalystJarBlock(QuiltBlockSettings.of(Material.GLASS, MapColor.BROWN)));
			else
				registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(),
						new CatalystJarBlock(QuiltBlockSettings.of(Material.GLASS, MapColor.BROWN)));
			for (Reagent reagent : reagents.getReagents())
				registerBlock("reagent_jar_" + reagent.hashString(),
						new ReagentJarBlock(QuiltBlockSettings.of(Material.GLASS, MapColor.SPRUCE_BROWN)));
		}

		// Machine Blocks
		for (MachineBlockEntry entry : MachineBlockEntry.values())
			registerBlock(entry.getId().getPath(), entry.getBlock());
	}

	public static void registerFluidBlock(Identifier id, FlowableFluid fluid) {
		Registry.register(Registry.BLOCK, id, new FluidBlock(fluid, QuiltBlockSettings.copy(Blocks.WATER)));
	}

	private static void registerBlock(String name, Block block) {
		Registry.register(Registry.BLOCK, Cabricality.id(name), block);

		Registry.register(Registry.ITEM, Cabricality.id(name),
				new BlockItem(block, (block instanceof ISettingableBlockItem settingable) ? settingable.getSettings()
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
	}
}
