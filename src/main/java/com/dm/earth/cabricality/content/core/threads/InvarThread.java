package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.CX;
import static com.dm.earth.cabricality.ModEntry.IV;
import static com.dm.earth.cabricality.ModEntry.KB;
import static com.dm.earth.cabricality.ModEntry.MC;

import java.util.List;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InvarThread implements TechThread {

    private static final List<Identifier> REMOVE_OUTPUTS = List.of(IV.id("compressor_mk1"), IV.id("chopper_mk1"),
            IV.id("farmer_mk1"), IV.id("slaughter_mk1"), IV.id("rancher_mk1"), IV.id("pump_mk1"),
            IV.id("mining_rig_mk4"), IV.id("data_card_writer_mk4"), IV.id("drain_mk1"));

    @Override
    public void load() {
        MechAndSmithCraft.addEntry(entry(IV.id("electric_furnace_mk1"), 1, MC.id("furnace")));
        MechAndSmithCraft.addEntry(entry(IV.id("smelter_mk4"), 1, MC.id("blast_furnace")));
        MechAndSmithCraft.addEntry(entry(IV.id("pulverizer_mk1"), 1, MC.id("flint")));
        MechAndSmithCraft.addEntry(entry(IV.id("sawmill_mk1"), 1, CABF.id("saw_blade")));
        MechAndSmithCraft.addEntry(entry(IV.id("recycler_mk2"), 1, MC.id("composter")));
        MechAndSmithCraft.addEntry(entry(IV.id("condenser_mk4"), 1, MC.id("packed_ice")));
        MechAndSmithCraft.addEntry(entry(IV.id("fluid_infuser_mk1"), 1, CR.id("whisk")));
        MechAndSmithCraft.addEntry(entry(IV.id("modular_workbench_mk4"), 1, MC.id("crafting_table")));
        MechAndSmithCraft.addEntry(entry(IV.id("lazuli_flux_container_mk1"), 1, MC.id("redstone_block")));
        MechAndSmithCraft.addEntry(entry(IV.id("laser_emitter_mk4"), 1, MC.id("lightning_rod")));
        MechAndSmithCraft.addEntry(entry(CX.id("energy_trash_can"), 1, KB.id("trash_can")));
    }

    @Override
    public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
    }

    @Override
    public String getLevel() {
        return "invar";
    }

    @Contract("_, _, _ -> new")
    private MechAndSmithCraft.@NotNull Entry entry(Identifier output, int count, @Nullable Identifier other) {
        return MechAndSmithCraft.entry(this.getLevel(), IV.id("machine_block"), output, count, other);
    }

    @Override
    public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
        handler.remove(CR.id("mechanical_crafting/crushing_wheel"));
        handler.removeIf(p -> RecipeTweaks.notCabf(p) && p.getOutput().isOf(IV.asItem("machine_block")));
        handler.removeIf(p -> RecipeTweaks.notCabf(p)
                && REMOVE_OUTPUTS.stream().anyMatch(id -> id.equals(Registry.ITEM.getId(p.getOutput().getItem()))));
    }

}
