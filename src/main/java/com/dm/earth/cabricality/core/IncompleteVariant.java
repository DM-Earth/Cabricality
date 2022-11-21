package com.dm.earth.cabricality.core;

import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import com.simibubi.create.content.contraptions.itemAssembly.SequencedAssemblyItem;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public interface IncompleteVariant {
    default SequencedAssemblyItem newIncomplete(Rarity rarity) {
        return new SequencedAssemblyItem(this.incompleteSettings(rarity));
    }

    default Identifier getIncompleteId(Identifier id) {
        return new Identifier(id.getNamespace(), "incomplete_" + id.getPath());
    }

    default Item.Settings incompleteSettings(Rarity rarity) {
        return new QuiltItemSettings().rarity(rarity);
    }

    Identifier getIncompleteTextureId();
}
