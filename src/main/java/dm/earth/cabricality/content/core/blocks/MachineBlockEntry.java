package dm.earth.cabricality.content.core.blocks;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.core.blocks.machine.AbstractMachineBlock;
import dm.earth.cabricality.content.core.blocks.machine.ComplexMachineBlock;
import dm.earth.cabricality.content.core.blocks.machine.CutoffMachineBlock;
import dm.earth.cabricality.content.core.blocks.machine.SolidMachineBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum MachineBlockEntry {
	ANDESITE(
			"andesite", () -> RenderLayer.getSolid(),
			new ComplexMachineBlock(FabricBlockSettings.create()
					.mapColor(MapColor.BROWN)
					.strength(1.5F, 6.0F))
	),

	BRASS(
			"brass", () -> RenderLayer.getTranslucent(),
			new ComplexMachineBlock(FabricBlockSettings.create()
					.mapColor(MapColor.YELLOW)
					.strength(3.0F, 6.0F))
	),

	COPPER(
			"copper", () -> RenderLayer.getCutout(),
			new ComplexMachineBlock(FabricBlockSettings.create()
					.mapColor(MapColor.ORANGE)
					.strength(3.0F, 6.0F)
					.sounds(BlockSoundGroup.COPPER))
	),

	ZINC(
			"zinc", () -> RenderLayer.getSolid(),
			new SolidMachineBlock(FabricBlockSettings.create()
					.mapColor(MapColor.LIME_TERRACOTTA)
					.strength(3.0F, 6.0F))
	),

	OBSIDIAN(
			"obsidian", () -> RenderLayer.getTranslucent(),
			new ComplexMachineBlock(FabricBlockSettings.create()
					.mapColor(MapColor.BLACK)
					.strength(3.0F, 6.0F))
	),

	ENDERIUM(
			"enderium", () -> RenderLayer.getCutout(),
			new CutoffMachineBlock(FabricBlockSettings.create()
					.mapColor(MapColor.WARPED_STEM)
					.strength(3.0F, 6.0F))
	);

	private final String name;
	private final Supplier<Object> layer;
	private final AbstractMachineBlock block;

	MachineBlockEntry(String name, Supplier<Object> layer, AbstractMachineBlock block) {
		this.name = name;
		this.layer = layer;
		this.block = block;
	}

	public String getName() {
		return name;
	}

	public RenderLayer getLayer() {
		return (RenderLayer) layer.get();
	}

	public AbstractMachineBlock getBlock() {
		return block;
	}

	public Identifier getId() {
		return Cabricality.id(name + "_machine");
	}
}
