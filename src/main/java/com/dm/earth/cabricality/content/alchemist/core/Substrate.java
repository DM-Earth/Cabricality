package com.dm.earth.cabricality.content.alchemist.core;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.core.IHashStringable;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public abstract class Substrate implements IHashStringable {
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
		return Cabricality.genTranslatableText(
				this.getType(),
				this.isReagent()
						? ((Reagent) this).getItemId().getNamespace()
						: "",
				this.getId().getPath()
		).getString() + Cabricality.genTranslatableText("block", this.getType() + "_jar").getString() + "§r";
	}
}
