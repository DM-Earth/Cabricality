package dm.earth.cabricality.lib.resource;

import pers.solid.brrp.v1.generator.BlockResourceGenerator;

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
