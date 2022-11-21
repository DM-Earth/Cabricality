package com.dm.earth.cabricality.resource.assets.gen.fluid;

import com.dm.earth.cabricality.Cabricality;
import net.devtech.arrp.json.models.JModel;

public class FluidModelGenerator {
	public static JModel simple(String id, String rawId) {
		return new JModel().addTexture("particle", Cabricality.id("fluid/" + rawId + "/" + id).toString());
	}
}
