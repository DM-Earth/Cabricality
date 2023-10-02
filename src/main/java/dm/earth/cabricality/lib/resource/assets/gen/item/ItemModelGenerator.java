package dm.earth.cabricality.lib.resource.assets.gen.item;

import dm.earth.cabricality.Cabricality;

import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class ItemModelGenerator {
	public static ModelJsonBuilder generated(String... id) {
		return new ModelJsonBuilder().parent("item/generated").addTexture("layer0", Cabricality.id(String.join("/", id)).toString());
	}

	public static ModelJsonBuilder parented(String... parent) {
		return new ModelJsonBuilder().parent(String.join("/", parent));
	}
}
