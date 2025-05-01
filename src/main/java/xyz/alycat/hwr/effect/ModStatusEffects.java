package xyz.alycat.hwr.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;

public class ModStatusEffects {
	public static RegistryEntry<StatusEffect> WATER_RESISTANCE;

	private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect effect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Hwr.MOD_ID, name), effect);
	}

	public static void register() {
		WATER_RESISTANCE = registerStatusEffect(
				"water_resistance",
				new WaterResistanceEffect(StatusEffectCategory.BENEFICIAL, Hwr.CONFIG.potion_colour().getColour())
		);
	}
}
