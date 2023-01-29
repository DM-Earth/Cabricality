package com.dm.earth.cabricality.lib.core;

public interface HashStringable {
	default String hashString() {
		return String.valueOf(this.hashCode()).replaceAll("-", "x");
	}
}
