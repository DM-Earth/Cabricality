package dm.earth.cabricality.lib.core;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

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
        return new FabricItemSettings().rarity(rarity);
    }

    Identifier getIncompleteTextureId();
}
