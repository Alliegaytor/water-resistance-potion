package xyz.alycat.hwr.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import xyz.alycat.hwr.potion.ModPotions;

import java.util.HashMap;
import java.util.Map;

public class ModLootTableModifiers {
    private static final Map<RegistryKey<LootTable>, Float> CHEST_RARITIES_WATER_RESISTANCE = new HashMap<>();
    private static final Map<RegistryKey<LootTable>, Float> CHEST_RARITIES_LONG_WATER_RESISTANCE = new HashMap<>();

    static {
        // Populate the chest rarities for Water Resistance potion
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.IGLOO_CHEST_CHEST, 0.005f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.ANCIENT_CITY_CHEST, 0.025f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.SHIPWRECK_TREASURE_CHEST, 0.20f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.BURIED_TREASURE_CHEST, 0.05f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.SIMPLE_DUNGEON_CHEST, 0.03f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.END_CITY_TREASURE_CHEST, 0.03f);

        // Populate the chest rarities for the longer Water Resistance potion
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.ANCIENT_CITY_CHEST, 0.09f);
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.SHIPWRECK_TREASURE_CHEST, 0.09f);
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.BURIED_TREASURE_CHEST, 0.023f);
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.END_CITY_TREASURE_CHEST, 0.005f);
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.TRIAL_CHAMBERS_SUPPLY_CHEST, 0.12f);
    }

    private static void appendPotionToLootTable(Map<RegistryKey<LootTable>, Float> chestRarities, RegistryEntry<Potion> potion) {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            Float rarity = chestRarities.get(key);
            if (rarity != null) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(rarity))
                        .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(potion)));
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }

    public static void modifyLootTables() {
        appendPotionToLootTable(CHEST_RARITIES_WATER_RESISTANCE, ModPotions.WATER_RESISTANCE);
        appendPotionToLootTable(CHEST_RARITIES_LONG_WATER_RESISTANCE, ModPotions.LONG_WATER_RESISTANCE);
    }
}
