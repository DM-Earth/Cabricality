package com.dm.earth.cabricality.content.core.blocks;

import java.util.function.Supplier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.blocks.machine.AbstractMachineBlock;
import com.dm.earth.cabricality.content.core.blocks.machine.ComplexMachineBlock;
import com.dm.earth.cabricality.content.core.blocks.machine.CutoffMachineBlock;
import com.dm.earth.cabricality.content.core.blocks.machine.SolidMachineBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public enum MachineBlockEntry {
	ANDESITE("andesite", () -> RenderLayer.getSolid(),
			new ComplexMachineBlock(QuiltBlockSettings.of(Material.STONE, MapColor.BROWN))),

	BRASS("brass", () -> RenderLayer.getTranslucent(),
			new ComplexMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.YELLOW))),

	COPPER("copper", () -> RenderLayer.getCutout(),
			new ComplexMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.ORANGE))),

	ZINC("zinc", () -> RenderLayer.getSolid(),
			new SolidMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.LICHEN_GREEN))),

	OBSIDIAN("obsidian", () -> RenderLayer.getTranslucent(),
			new ComplexMachineBlock(QuiltBlockSettings.of(Material.STONE, MapColor.BLACK))),

	ENDERIUM("enderium", () -> RenderLayer.getCutout(),
			new CutoffMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.DARK_AQUA)));

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
