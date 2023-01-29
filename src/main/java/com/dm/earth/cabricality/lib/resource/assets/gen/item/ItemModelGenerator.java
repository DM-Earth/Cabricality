package com.dm.earth.cabricality.lib.resource.assets.gen.item;

import com.dm.earth.cabricality.Cabricality;

import net.devtech.arrp.json.models.JModel;

public class ItemModelGenerator {
	public static JModel generated(String... id) {
		return new JModel().parent("item/generated").addTexture("layer0", Cabricality.id(String.join("/", id)).toString());
	}

	public static JModel parented(String... parent) {
		return new JModel().parent(String.join("/", parent));
	}
}
