package dm.earth.cabricality.content.alchemist.core;

import dm.earth.cabricality.Cabricality;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class Reagent extends Substrate {
	private final Identifier item;

	private Reagent(Identifier id, Identifier item, int tint) {
		super(id, tint);
		this.item = item;
	}

	public static Reagent of(String id, Identifier item, int tint) {
		return new Reagent(Cabricality.id(id), item, tint);
	}

	@Override
	public boolean isConsumable() {
		return true;
	}

	@Override
	public String getType() {
		return "reagent";
	}

	@NotNull
	public Identifier getItemId() {
		return this.item;
	}

	public Item getItem() {
		return Objects.requireNonNull(Registries.ITEM.get(this.getItemId()));
	}
}
