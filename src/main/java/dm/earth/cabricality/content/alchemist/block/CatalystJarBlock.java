package dm.earth.cabricality.content.alchemist.block;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.alchemist.Reagents;
import dm.earth.cabricality.content.alchemist.core.Substrate;

import net.minecraft.util.Identifier;

public class CatalystJarBlock extends SubstrateJarBlock {
	public CatalystJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public Identifier getDefaultBlockId() {
		return Cabricality.id("catalyst_jar");
	}

	@Override
	public Substrate getSubstrate() {
		return Reagents.getCatalystFromBlock(this);
	}
}
