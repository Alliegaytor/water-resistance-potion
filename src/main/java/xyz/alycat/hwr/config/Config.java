package xyz.alycat.hwr.config;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import xyz.alycat.hwr.Hwr;

// Client/Server side configs non restart
@EventBusSubscriber(modid = Hwr.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

	// Client side
	// Server handles the recipe config, no need to sync
	private static final ModConfigSpec.ConfigValue<String> POTION_RECIPE = BUILDER
			.comment("potion_recipe")
			.define("potion_recipe", "minecraft:sponge", Config::validateItemName);

	public static final ModConfigSpec SPEC = BUILDER.build();

	public static Item potion_recipe;

	private static boolean validateItemName(final Object obj) {
		return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
	}

	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {
		potion_recipe = BuiltInRegistries.ITEM.getValue(ResourceLocation.parse(POTION_RECIPE.get()));
	}
}
