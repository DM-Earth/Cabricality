package com.dm.earth.cabricality.content.alchemist.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.core.Substrate;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.core.ISettableBlockItem;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public abstract class SubstrateJarBlock extends JarBlock implements ISettableBlockItem {
	public SubstrateJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public String getTranslationKey() {
		if (this.getContent() == null)
			return Cabricality.genTranslatableText("block", this.getDefaultBlockId().getPath())
					.getString();
		return this.getContent();
	}

	@Nullable
	public String getContent() {
		if (this.getSubstrate() != null)
			return new TranslatableText(this.getSubstrate().getTranslationKey()).getString()
					+ Cabricality
							.genTranslatableText("block", this.getSubstrate().getType() + "_jar")
							.getString();
		else
			return null;
	}

	@Nullable
	public abstract Substrate getSubstrate();

	@NotNull
	public abstract Identifier getDefaultBlockId();

	@Override
	public abstract Identifier getBlockModelId();

	@Override
	public Item.Settings getSettings() {
		return CabfItems.Properties.JAR.get();
	}
}
