package xyz.alycat.hwr.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.HashMap;
import java.util.Map;

public class ModLootTableModifiers {

	public static final Map<ResourceLocation, Float> CHEST_RARITIES_WATER_RESISTANCE = new HashMap<>();
	public static final Map<ResourceLocation, Float> CHEST_RARITIES_LONG_WATER_RESISTANCE = new HashMap<>();

	static {
		// Populate the chest rarities for Water Resistance potion
		CHEST_RARITIES_WATER_RESISTANCE.put(BuiltInLootTables.IGLOO_CHEST.location(), 0.005f);
		CHEST_RARITIES_WATER_RESISTANCE.put(BuiltInLootTables.ANCIENT_CITY.location(), 0.025f);
		CHEST_RARITIES_WATER_RESISTANCE.put(BuiltInLootTables.SHIPWRECK_TREASURE.location(), 0.20f);
		CHEST_RARITIES_WATER_RESISTANCE.put(BuiltInLootTables.BURIED_TREASURE.location(), 0.05f);
		CHEST_RARITIES_WATER_RESISTANCE.put(BuiltInLootTables.SIMPLE_DUNGEON.location(), 0.03f);
		CHEST_RARITIES_WATER_RESISTANCE.put(BuiltInLootTables.END_CITY_TREASURE.location(), 0.03f);

		// Populate the chest rarities for the longer Water Resistance potion
		CHEST_RARITIES_LONG_WATER_RESISTANCE.put(BuiltInLootTables.ANCIENT_CITY.location(), 0.09f);
		CHEST_RARITIES_LONG_WATER_RESISTANCE.put(BuiltInLootTables.SHIPWRECK_TREASURE.location(), 0.09f);
		CHEST_RARITIES_LONG_WATER_RESISTANCE.put(BuiltInLootTables.BURIED_TREASURE.location(), 0.023f);
		CHEST_RARITIES_LONG_WATER_RESISTANCE.put(BuiltInLootTables.END_CITY_TREASURE.location(), 0.005f);
		CHEST_RARITIES_LONG_WATER_RESISTANCE.put(BuiltInLootTables.TRIAL_CHAMBERS_SUPPLY.location(), 0.12f);
	}
}
