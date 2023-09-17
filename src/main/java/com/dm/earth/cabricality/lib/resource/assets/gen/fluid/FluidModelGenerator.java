package com.dm.earth.cabricality.lib.resource.assets.gen.fluid;

import com.dm.earth.cabricality.Cabricality;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class FluidModelGenerator {
	public static ModelJsonBuilder simple(String id, String rawId) {
		return new ModelJsonBuilder().addTexture("particle", Cabricality.id("fluid/" + rawId + "/" + id).toString());
	}
}
