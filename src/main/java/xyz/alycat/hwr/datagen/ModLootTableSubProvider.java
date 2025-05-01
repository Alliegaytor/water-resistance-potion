package xyz.alycat.hwr.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.potion.ModPotions;

import java.util.function.BiConsumer;

import static xyz.alycat.hwr.loot.ModLootTableModifiers.CHEST_RARITIES_LONG_WATER_RESISTANCE;
import static xyz.alycat.hwr.loot.ModLootTableModifiers.CHEST_RARITIES_WATER_RESISTANCE;

public class ModLootTableSubProvider implements LootTableSubProvider {

	public ModLootTableSubProvider(HolderLookup.Provider provider) {
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
		CHEST_RARITIES_LONG_WATER_RESISTANCE.forEach( (key, rarity) -> {
			output.accept(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Hwr.MODID, key.getPath() + "_long")), LootTable.lootTable().withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1))
					.when(LootItemRandomChanceCondition.randomChance(rarity))
					.add(LootItem.lootTableItem(Items.POTION).apply(SetPotionFunction.setPotion(ModPotions.LONG_WATER_RESISTANCE))))
			);
		});


		CHEST_RARITIES_WATER_RESISTANCE.forEach( (key, rarity) -> {
			output.accept(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Hwr.MODID, key.getPath())), LootTable.lootTable().withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1))
					.when(LootItemRandomChanceCondition.randomChance(rarity))
					.add(LootItem.lootTableItem(Items.POTION).apply(SetPotionFunction.setPotion(ModPotions.WATER_RESISTANCE))))
			);
		});
	}
}
