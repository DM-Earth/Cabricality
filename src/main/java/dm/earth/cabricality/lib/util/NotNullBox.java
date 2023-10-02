package dm.earth.cabricality.lib.util;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a value that should not be null.
 * @param <T> The type.
 */
public class NotNullBox<T> {
	@NotNull
	private T value;

	public NotNullBox(@NotNull T value) {
		this.value = Objects.requireNonNull(value, "Null value in not null boxes");
	}

	@NotNull
	public T getValue() {
		return value;
	}

	public void setValue(@NotNull T value) {
		this.value = Objects.requireNonNull(value, "Null value in not null boxes");
	}
}
