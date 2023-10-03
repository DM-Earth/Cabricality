package dm.earth.cabricality.content.fluids.core;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.entries.CabfItems;
import dm.earth.cabricality.lib.resource.assets.gen.item.ItemModelGenerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface IFluid {
	@Environment(EnvType.CLIENT)
	void setupRendering();

	boolean hasBucketItem();

	Identifier getId();

	Fluid getTypical();

	Fluid getFlowing();

	String getName();

	BlockState toBlockState(FluidState state);

	BlockState toBlockState();

	Block toBlock(FluidState state);

	Block toBlock();

	default void registerBucketItem(Registry<Item> registry) {
		if (this.getTypical() != this || !this.hasBucketItem()) return;
		Identifier bucketId = Cabricality.id(this.getName() + "_bucket");
		Registry.register(registry, bucketId, new BucketItem((Fluid) this, CabfItems.Suppliers.DEFAULT_SINGLE.get()));
		Cabricality.RRPs.CLIENT_RESOURCES.addModel(
				Cabricality.id("item/" + bucketId.getPath()),
				ItemModelGenerator.generated("item/bucket", bucketId.getPath())
		);
	}

	default String getTextureName() {
		return this.getName();
	}
}
