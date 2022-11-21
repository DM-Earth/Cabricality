package com.dm.earth.cabricality.core;

public interface IHashStringable {
	default String hashString() {
		return (this.hashCode() + "").replaceAll("-", "x");
	}
}
