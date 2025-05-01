package xyz.alycat.hwr;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.alycat.hwr.config.ModConfig;
import xyz.alycat.hwr.effect.ModStatusEffects;
import xyz.alycat.hwr.potion.ModPotions;
import xyz.alycat.hwr.loot.ModLootTableModifiers;

public class Hwr implements ModInitializer {
	public static final String MOD_ID = "hwr";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ModConfig CONFIG = ModConfig.createAndLoad();

	@Override
	public void onInitialize() {
		LOGGER.info("Water Resistance Potion initializing...");

		ModStatusEffects.register();
		ModPotions.register();
		ModLootTableModifiers.modifyLootTables();
	}
}
