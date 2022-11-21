package com.dm.earth.cabricality.resource;

import net.devtech.arrp.generator.BlockResourceGenerator;

public interface ResourcedBlock extends BlockResourceGenerator {
	default boolean doLootTable() {
		return false;
	}

	default boolean doModel() {
		return false;
	}

	default boolean doBlockStates() {
		return false;
	}

	default boolean doItemModel() {
		return true;
	}
}
