package dm.earth.cabricality.lib.util;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ArrayUtil {
	@SafeVarargs
	public static <T> T[] of(T... objects) {
		return objects;
	}

	public static <T> ArrayList<T> asArrayList(Stream<T> stream) {
		return stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
}
