package xyz.alycat.hwr.util;

import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetNbtLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import xyz.alycat.hwr.potion.ModPotions;

import java.util.HashMap;
import java.util.Map;

public class ModLootTableModifiers {
    private static final Map<Identifier, Float> CHEST_RARITIES_WATER_RESISTANCE = new HashMap<>();
    private static final Map<Identifier, Float> CHEST_RARITIES_LONG_WATER_RESISTANCE = new HashMap<>();

    static {
        // Populate the chest rarities for Water Resistance potion
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.IGLOO_CHEST_CHEST, 0.008f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.SHIPWRECK_TREASURE_CHEST, 0.21f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.BURIED_TREASURE_CHEST, 0.05f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.SIMPLE_DUNGEON_CHEST, 0.04f);
        CHEST_RARITIES_WATER_RESISTANCE.put(LootTables.END_CITY_TREASURE_CHEST, 0.03f);

        // Populate the chest rarities for the longer Water Resistance potion
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.SHIPWRECK_TREASURE_CHEST, 0.09f);
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.BURIED_TREASURE_CHEST, 0.03f);
        CHEST_RARITIES_LONG_WATER_RESISTANCE.put(LootTables.END_CITY_TREASURE_CHEST, 0.008f);
    }

    private static void appendPotionToLootTable(Map<Identifier, Float> chestRarities, Potion potion) {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            Float rarity = chestRarities.get(id);
            if (rarity != null) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(rarity))
                        .with(ItemEntry.builder(Items.POTION))
                        // There has to be a better way...
                        .apply(SetNbtLootFunction.builder(Util.make(new NbtCompound(), nbtCompound -> nbtCompound.putString("Potion", Registry.POTION.getId(potion).toString()))));
                tableBuilder.pool(poolBuilder);
            }
        });
    }

    public static void modifyLootTables() {
        appendPotionToLootTable(CHEST_RARITIES_WATER_RESISTANCE, ModPotions.WATER_RESISTANCE);
        appendPotionToLootTable(CHEST_RARITIES_LONG_WATER_RESISTANCE, ModPotions.LONG_WATER_RESISTANCE);
    }
}
