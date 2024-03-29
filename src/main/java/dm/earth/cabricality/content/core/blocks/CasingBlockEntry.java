package dm.earth.cabricality.content.core.blocks;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.core.blocks.casing.SimpleCasingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum CasingBlockEntry {
	ZINC(
			"zinc", () -> RenderLayer.getSolid(),
			new SimpleCasingBlock(FabricBlockSettings.create()
					.mapColor(MapColor.LIME_TERRACOTTA)
					.strength(2.0F, 6.0F),
					"zinc")
	),

	INVAR(
			"invar", () -> RenderLayer.getSolid(),
			new SimpleCasingBlock(FabricBlockSettings.create()
					.mapColor(MapColor.GRAY)
					.strength(2.0F, 6.0F),
					"invar")
	),

	FLUIX(
			"fluix", () -> RenderLayer.getSolid(),
			new SimpleCasingBlock(FabricBlockSettings.create()
					.mapColor(MapColor.LAPIS)
					.strength(2.0F, 6.0F),
					"fluix")
	),

	ENDERIUM(
			"enderium", () -> RenderLayer.getSolid(),
			new SimpleCasingBlock(FabricBlockSettings.create()
					.mapColor(MapColor.WARPED_STEM)
					.strength(2.0F, 6.0F),
					"enderium")
	);

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
