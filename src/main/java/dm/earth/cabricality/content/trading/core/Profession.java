package dm.earth.cabricality.content.trading.core;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.core.HashStringable;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public record Profession(Identifier id, List<TradingEntry> entries, int tint) implements HashStringable {
	@Contract("_, _, _ -> new")
	@NotNull
	public static Profession of(String name, int tint, TradingEntry... entries) {
		return new Profession(Cabricality.id(name), List.of(entries), tint);
	}

	@Contract("_, _ -> new")
	@NotNull
	public static Profession of(String name, TradingEntry... entries) {
		return of(name, -1, entries);
	}

	@Override
	@Unmodifiable
	public List<TradingEntry> entries() {
		return List.copyOf(entries);
	}

	@Override
	@Unmodifiable
	public int tint() {
		return tint;
	}

	@Override
	public int hashCode() {
		return this.id().hashCode();
	}
}
