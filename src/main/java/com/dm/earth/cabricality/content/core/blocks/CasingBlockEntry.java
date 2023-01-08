package com.dm.earth.cabricality.content.core.blocks;

import java.util.function.Supplier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.blocks.casing.SimpleCasingBlock;
import com.simibubi.create.content.contraptions.base.CasingBlock;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public enum CasingBlockEntry {
	ZINC("zinc", () -> RenderLayer.getSolid(), new SimpleCasingBlock(
			QuiltBlockSettings.of(Material.METAL, MapColor.LICHEN_GREEN), "zinc")),

	INVAR("invar", () -> RenderLayer.getSolid(),
			new SimpleCasingBlock(QuiltBlockSettings.of(Material.METAL, MapColor.GRAY), "invar")),

	FLUIX("fluix", () -> RenderLayer.getSolid(), new SimpleCasingBlock(
			QuiltBlockSettings.of(Material.METAL, MapColor.DARK_AQUA), "fluix")),

	ENDERIUM("enderium", () -> RenderLayer.getSolid(), new SimpleCasingBlock(
			QuiltBlockSettings.of(Material.METAL, MapColor.DARK_AQUA), "enderium"));

	private final String name;
	private final Supplier<Object> layer;
	private final CasingBlock block;

	CasingBlockEntry(String name, Supplier<Object> layer, CasingBlock block) {
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

	public CasingBlock getBlock() {
		return block;
	}

	public Identifier getId() {
		return Cabricality.id(name + "_casing");
	}
}
