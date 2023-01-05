package com.dm.earth.cabricality.content.fluids.core;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.resource.assets.gen.item.ItemModelGenerator;
import com.dm.earth.cabricality.content.entries.CabfItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.minecraft.ClientOnly;

public interface IFluid {
	@ClientOnly void setupRendering();

	boolean hasBucketItem();

	Identifier getId();

	Fluid getTypical();

	Fluid getFlowing();

	String getName();

	default void registerBucketItem(Registry<Item> registry) {
		if (this.getTypical() != this || !this.hasBucketItem()) return;
		Identifier bucketId = Cabricality.id(this.getName() + "_bucket");
		Registry.register(registry, bucketId, new BucketItem((Fluid) this, CabfItems.Properties.DEFAULT_SINGLE.get()));
		Cabricality.RRPs.CLIENT_RESOURCES.addModel(ItemModelGenerator.generated("item/bucket", bucketId.getPath()), Cabricality.id("item/" + bucketId.getPath()));
	}

	default String getTextureName() {
		return this.getName();
	}
}
