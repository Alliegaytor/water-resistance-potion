package xyz.alycat.hwr.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import xyz.alycat.hwr.Hwr;

import java.util.concurrent.CompletableFuture;

import static xyz.alycat.hwr.loot.ModLootTableModifiers.CHEST_RARITIES_LONG_WATER_RESISTANCE;
import static xyz.alycat.hwr.loot.ModLootTableModifiers.CHEST_RARITIES_WATER_RESISTANCE;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
	public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, Hwr.MODID);
	}

	@Override
	protected void start() {
		CHEST_RARITIES_LONG_WATER_RESISTANCE.forEach( (key, rarity) -> {
			this.add(key.getPath() + "_add_long", new AddTableLootModifier(
					new LootItemCondition[]{
							new LootTableIdCondition.Builder(key).build(),
					},
					ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Hwr.MODID, key.getPath() + "_long"))
			));
		});

		CHEST_RARITIES_WATER_RESISTANCE.forEach( (key, rarity) -> {
			this.add(key.getPath() + "_add_short", new AddTableLootModifier(
					new LootItemCondition[]{
							new LootTableIdCondition.Builder(key).build(),
					},
					ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Hwr.MODID, key.getPath()))
			));
		});
	}
}
