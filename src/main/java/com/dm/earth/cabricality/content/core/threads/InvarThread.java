package com.dm.earth.cabricality.content.core.threads;

import static com.dm.earth.cabricality.ModEntry.C;
import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.CX;
import static com.dm.earth.cabricality.ModEntry.IV;
import static com.dm.earth.cabricality.ModEntry.KB;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.TC;

import java.util.List;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.core.items.ColoredFernItem;
import com.dm.earth.cabricality.resource.data.core.FreePRP;
import com.dm.earth.cabricality.tweak.RecipeTweaks;
import com.dm.earth.cabricality.tweak.core.MechAndSmithCraft;
import com.dm.earth.cabricality.util.RecipeBuilderUtil;
import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;
import com.nhoryzon.mc.farmersdelight.recipe.ingredient.ChanceResult;
import com.simibubi.create.content.contraptions.components.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.contraptions.components.fan.HauntingRecipe;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;

import me.alphamode.forgetags.Tags;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
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
                handler.register(recipeId("mechanical_crafting", "crushing_wheel"),
                                id -> RecipeBuilderUtil.mechanicalFromShaped(
                                                VanillaRecipeBuilders
                                                                .shapedRecipe(" AAA ", "AABAA", "ABBBA", "AABAA", "AAA")
                                                                .ingredient('A', Tags.Items.COBBLESTONE)
                                                                .ingredient('B', Items.STICK)
                                                                .output(CR.asItem("crushing_wheel").getDefaultStack())
                                                                .build(id, ""),
                                                false));

                for (ColoredFernItem.Entry entry : ColoredFernItem.Entry.values()) {
                        String fern = entry.name + "_slime_fern";
                        String leaf = entry.name + "_slime_fern_leaf";
                        String paste = entry.name + "_slime_fern_paste";
                        TagKey<Item> knives = TagKey.of(Registry.ITEM_KEY, C.id("tools/kinves"));
                        handler.register(recipeId("fd_cutting", fern),
                                        id -> new CuttingBoardRecipe(id, "", TC.asIngredient(fern),
                                                        Ingredient.ofTag(knives),
                                                        DefaultedList.copyOf(new ChanceResult(
                                                                        CABF.asItem(leaf).getDefaultStack(), 1)),
                                                        "minecraft:block.grass.break"));
                        handler.register(recipeId("deploying", fern),
                                        id -> new DeployerApplicationRecipe(new FreePRP(id)
                                                        .setIngredient(TC.asIngredient(fern), Ingredient.ofTag(knives))
                                                        .setResult(CABF.asProcessingOutput(leaf)).keepHeldItem()));
                        handler.register(recipeId("haunting", leaf), id -> new HauntingRecipe(
                                        new FreePRP(id).setIngredient(CABF.asIngredient(leaf))
                                                        .setResult(TC.asProcessingOutput(fern))));
                        handler.register(recipeId("milling", leaf),
                                        id -> new MillingRecipe(new FreePRP(id).setIngredient(CABF.asIngredient(leaf))
                                                        .setResult(CABF.asProcessingOutput(paste))
                                                        .setProcessingTime(70)));
                        handler.register(recipeId("campfire_cooking", paste), id -> new CampfireCookingRecipe(id, "",
                                        CABF.asIngredient(paste), entry.getOutpitItem().getDefaultStack(), 0, 300));
                }

                handler.register(recipeId("campfire_cooking", "stick"), id -> new CampfireCookingRecipe(id, "",
                                MC.asIngredient("stick"), MC.asStack("torch"), 0, 20));
                handler.register(recipeId("blasting", "nickel_compound"), id -> VanillaRecipeBuilders.blastingRecipe(id,
                                "", CABF.asIngredient("nickel_compound"), CABF.asStack("invar_compound"), 0.1F, 400));
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
                                && REMOVE_OUTPUTS.stream().anyMatch(
                                                id -> id.equals(Registry.ITEM.getId(p.getOutput().getItem()))));
        }

}
