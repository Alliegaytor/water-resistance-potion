package xyz.alycat.hwr;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import xyz.alycat.hwr.config.Config;
import xyz.alycat.hwr.config.StartupConfig;
import xyz.alycat.hwr.effect.ModStatusEffects;
import xyz.alycat.hwr.potion.ModPotions;

import static xyz.alycat.hwr.potion.ModPotions.LONG_WATER_RESISTANCE;
import static xyz.alycat.hwr.potion.ModPotions.WATER_RESISTANCE;


@Mod(Hwr.MODID)
public class Hwr {
	public static final String MODID = "hwr";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Hwr(IEventBus modEventBus, ModContainer modContainer) {
		// Configuration
		StartupConfig.register(modContainer);
		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
		modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

		modEventBus.addListener(this::commonSetup);
		NeoForge.EVENT_BUS.register(this);

		ModStatusEffects.register(modEventBus);
		ModPotions.register(modEventBus);

//        ModLootTableModifiers.register(modEventBus);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		LOGGER.info("Water Resistance Potion colour is >> {}", StartupConfig.potion_colour);
	}

	@SubscribeEvent
	public void onServerSetup(ServerStartingEvent event) {
		LOGGER.info("Water Resistance Potion Brewing Ingredient is: {}", Config.potion_recipe.toString());
	}

	@SubscribeEvent
	public void registerPotionRecipes(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();

		builder.addMix(Potions.AWKWARD, Config.potion_recipe, WATER_RESISTANCE);
		builder.addMix(WATER_RESISTANCE, Items.REDSTONE, LONG_WATER_RESISTANCE);
	}
}
