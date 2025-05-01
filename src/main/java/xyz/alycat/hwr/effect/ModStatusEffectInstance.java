package xyz.alycat.hwr.effect;

import net.minecraft.entity.effect.StatusEffectInstance;

public class ModStatusEffectInstance {
	public static final StatusEffectInstance WATER_RESISTANCE_EFFECT = new StatusEffectInstance(ModStatusEffects.WATER_RESISTANCE, 3600, 0);
	public static final StatusEffectInstance WATER_RESISTANCE_EFFECT_5MIN = new StatusEffectInstance(ModStatusEffects.WATER_RESISTANCE, 6000, 0);
	public static final StatusEffectInstance WATER_RESISTANCE_EFFECT_LONG = new StatusEffectInstance(ModStatusEffects.WATER_RESISTANCE, 9600, 0);
}
