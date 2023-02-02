package com.dm.earth.cabricality.content.alchemist.block;

import net.minecraft.text.MutableText;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.lib.core.BlockItemSettable;

import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public abstract class SubstrateJarBlock extends JarBlock implements BlockItemSettable {
	public SubstrateJarBlock(Settings settings) {
		super(settings);
	}

	// Super toxic
	@Override
	public String getTranslationKey() {
		return getName().getString();
	}

	@Override
	public MutableText getName() {
		return new TranslatableText(this.getSubstrate().getTranslationKey())
					   .append(Cabricality.genTranslatableText("block", this.getSubstrate().getType() + "_jar"));
	}

	public abstract Substrate getSubstrate();

	public abstract Identifier getDefaultBlockId();

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block", "jar", this.getSubstrate().getType());
	}

	@Override
	public Item.Settings getSettings() {
		return CabfItems.Properties.JAR.get();
	}
}
