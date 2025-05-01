package xyz.alycat.hwr.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.effect.ModStatusEffectInstance;

import static xyz.alycat.hwr.effect.ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_LONG;

public class ModPotions {
	public static Holder<Potion> WATER_RESISTANCE;
	public static Holder<Potion> LONG_WATER_RESISTANCE;

	public static final DeferredRegister<Potion> MOD_POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, Hwr.MODID);

	private static Holder<Potion> registerPotion(String name, MobEffectInstance mobEffectInstance) {
		return MOD_POTIONS.register(name, registryName -> new Potion(
				registryName.getPath(),
				mobEffectInstance
		));
	}

	public static void register(IEventBus eventBus) {
		WATER_RESISTANCE = registerPotion("water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT);
		LONG_WATER_RESISTANCE = registerPotion("long_water_resistance_potion", WATER_RESISTANCE_EFFECT_LONG);

		MOD_POTIONS.register(eventBus);
	}
}
