package xyz.alycat.hwr.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xyz.alycat.hwr.Hwr;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Hwr.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new ModGlobalLootModifierProvider(packOutput, lookupProvider));

		generator.addProvider(true, new LootTableProvider(
				packOutput, Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(
						ModLootTableSubProvider::new,
						LootContextParamSets.EMPTY
				)),
				lookupProvider
		));
	}
}
