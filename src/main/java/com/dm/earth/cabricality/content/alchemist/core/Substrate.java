package com.dm.earth.cabricality.content.alchemist.core;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.alchemist.block.SubstrateJarBlock;
import com.dm.earth.cabricality.lib.core.HashStringable;

import net.minecraft.block.Block;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class Substrate implements HashStringable {
	private final Identifier id;
	private final int tint;

	public Substrate(Identifier id, int tint) {
		this.id = id;
		this.tint = tint;
	}

	public Identifier getId() {
		return id;
	}

	public int getTint() {
		return tint;
	}

	public String getTranslationKey() {
		return this.getType() + "." + this.getId().getNamespace() + "." + this.getId().getPath();
	}

	public Text getName() {
		return new TranslatableText(this.getTranslationKey());
	}

	public abstract String getType();

	public boolean isReagent() {
		return this instanceof Reagent;
	}

	public abstract boolean consume();

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public String toString() {
		return this.getId().toString();
	}

	public SubstrateJarBlock getJarBlock() {
		return (SubstrateJarBlock) Registry.BLOCK.get(Cabricality.id(this.getType() + "_jar_" + this.hashString()));
	}

	public static List<Block> getJarBlocks(boolean includeBlank) {
		return Registry.BLOCK.getEntries().stream()
				.filter(entry -> entry.getValue() instanceof SubstrateJarBlock
						|| (includeBlank && entry.getValue() instanceof JarBlock))
				.map(Map.Entry::getValue).collect(Collectors.toList());
	}
}
