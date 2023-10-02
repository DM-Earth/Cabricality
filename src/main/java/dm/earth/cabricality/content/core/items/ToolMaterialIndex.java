package dm.earth.cabricality.content.core.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

public enum ToolMaterialIndex {
    STONE("stone", ToolMaterials.STONE),
    IRON("iron", ToolMaterials.IRON),
    DIAMOND("diamond", ToolMaterials.DIAMOND),
    NETHERITE("netherite", ToolMaterials.NETHERITE);

    private final String name;
    private final ToolMaterial material;

    ToolMaterialIndex(String id, ToolMaterial material) {
        this.name = id;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public ToolMaterial getMaterial() {
        return material;
    }
}
