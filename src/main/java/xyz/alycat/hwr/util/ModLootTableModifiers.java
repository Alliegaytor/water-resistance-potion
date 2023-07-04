package xyz.alycat.hwr.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.potion.ModPotions;

import java.util.Map;

import static java.util.Map.entry;

public class ModLootTableModifiers {
    private static final Identifier IGLOO_STRUCTURE_CHEST_ID = new Identifier("minecraft", "chests/igloo_chest");
    private static final Identifier SHIPWRECK_TREASURE_CHEST_ID = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier BURIED_TREASURE_CHEST_ID = new Identifier("minecraft", "chests/buried_treasure");
    private static final Identifier PILLAGER_OUTPOST_CHEST_ID = new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier SIMPLE_DUNGEON_CHEST_ID = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier END_CITY_CHEST_ID = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier PIGLIN_BARTERING_GAMEPLAY_ID = new Identifier("minecraft", "gameplay/piglin_bartering");

    private static void appendPotionToLootTable(Map<Identifier, Float> chestRarities, Potion potion) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            Float rarity = chestRarities.get(id);
            if (rarity != null) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(rarity))
                        .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(potion)));
                tableBuilder.pool(poolBuilder);
            }
        });
    }

    public static void modifyLootTables() {
        final Map<Identifier, Float> chestWeightsWaterResistance = Map.ofEntries(
                entry(IGLOO_STRUCTURE_CHEST_ID, 0.008f),
                entry(SHIPWRECK_TREASURE_CHEST_ID, 0.21f),
                entry(BURIED_TREASURE_CHEST_ID, 0.05f),
                entry(PILLAGER_OUTPOST_CHEST_ID, 0.02f),
                entry(SIMPLE_DUNGEON_CHEST_ID, 0.04f),
                entry(END_CITY_CHEST_ID, 0.03f)
        );

        appendPotionToLootTable(chestWeightsWaterResistance, ModPotions.WATER_RESISTANCE);

        final Map<Identifier, Float> chestWeightsLongWaterResistance = Map.ofEntries(
                entry(SHIPWRECK_TREASURE_CHEST_ID, 0.09f),
                entry(BURIED_TREASURE_CHEST_ID, 0.03f),
                entry(END_CITY_CHEST_ID, 0.008f)
        );

        appendPotionToLootTable(chestWeightsLongWaterResistance, ModPotions.LONG_WATER_RESISTANCE);
    }
}

