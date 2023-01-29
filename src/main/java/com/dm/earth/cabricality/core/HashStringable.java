package com.dm.earth.cabricality.core;

public interface HashStringable {
	default String hashString() {
		return String.valueOf(this.hashCode()).replaceAll("-", "x");
	}
}
