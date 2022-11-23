package com.dm.earth.cabricality.tweak;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.tweak.cutting.WoodCuttingEntry;
import com.simibubi.create.AllTags.AllItemTags;

import net.devtech.arrp.json.tags.JTag;
import net.minecraft.util.Identifier;

public class TagTweaks {
    public static void load() {
        initStrippedTags();
    }

    private static void initStrippedTags() {
        JTag strippedLogs = new JTag();
        JTag strippedWoods = new JTag();
        for (WoodCuttingEntry entry : WoodCuttingEntry.values()) {
            if (entry.isStrippedLogExist()) strippedLogs.add(entry.getStrippedLogId());
            if (entry.isStrippedWoodExist()) strippedWoods.add(entry.getStrippedWoodId());
        }
        Cabricality.SERVER_RESOURCES.addTag(new Identifier(AllItemTags.MODDED_STRIPPED_LOGS.tag.id().getNamespace(), "items/" + AllItemTags.MODDED_STRIPPED_LOGS.tag.id().getPath()), strippedLogs);
        Cabricality.SERVER_RESOURCES.addTag(new Identifier(AllItemTags.MODDED_STRIPPED_WOOD.tag.id().getNamespace(), "items/" + AllItemTags.MODDED_STRIPPED_WOOD.tag.id().getPath()), strippedWoods);
    }
}
