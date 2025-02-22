package xyz.alycat.hwr;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.alycat.hwr.config.ModConfig;
import xyz.alycat.hwr.effect.ModStatusEffects;
import xyz.alycat.hwr.potion.ModPotions;
import xyz.alycat.hwr.util.ModLootTableModifiers;

public class Hwr implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "hwr";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ModConfig CONFIG = ModConfig.createAndLoad();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Water Resistance Potion initializing...");

		ModStatusEffects.registerEffects();
		ModPotions.registerPotions();
		ModLootTableModifiers.modifyLootTables();
	}
}
