package com.dm.earth.cabricality.content.threads.items;

import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.core.IncompleteVariant;
import com.simibubi.create.content.contraptions.itemAssembly.SequencedAssemblyItem;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class MechanismItem extends Item implements IncompleteVariant {

    private final String mechanismName;

    public MechanismItem(String name, Settings settings) {
        super(settings);
        this.mechanismName = name;
    }

    @Override
    public Identifier getIncompleteTextureId() {
        return Cabricality.id("item/mechanism/incomplete/incomplete_" + mechanismName + "_mechanism");
    }

    public Identifier getTextureId() {
        return Cabricality.id("item/mechanism/" + mechanismName + "_mechanism");
    }

    public static enum Type {
        KINETIC("kinetic"),
        SEALED("sealed"),
        INFERNAL("infernal"),
        STURDY("sturdy"),
        INDUCTIVE("inductive"),
        ABSTRUSE("abstruse", Rarity.UNCOMMON),
        CALCULATION("calculation", Rarity.UNCOMMON);

        private final String name;
        private final MechanismItem item;
        private final SequencedAssemblyItem incompleteItem;

        Type(String name, Rarity rarity) {
            this.name = name;
            MechanismItem newItem = new MechanismItem(name, new QuiltItemSettings().rarity(rarity));
            this.item = newItem;
            this.incompleteItem = newItem.newIncomplete(rarity);
        }

        Type(String name) {
            this(name, Rarity.COMMON);
        }

        public String getName() {
            return name;
        }

        public Identifier getItemId() {
            return Cabricality.id(name + "_mechanism");
        }

        public Identifier getIncompleteItemId() {
            return Cabricality.id("incomplete_" + name + "_mechanism");
        }

        public MechanismItem getItem() {
            return item;
        }

        public SequencedAssemblyItem getIncompleteItem() {
            return incompleteItem;
        }
    }
}
