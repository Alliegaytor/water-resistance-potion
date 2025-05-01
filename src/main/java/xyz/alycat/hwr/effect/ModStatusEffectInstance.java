package xyz.alycat.hwr.effect;

import net.minecraft.world.effect.MobEffectInstance;

public class ModStatusEffectInstance {
	public static final MobEffectInstance WATER_RESISTANCE_EFFECT = new MobEffectInstance(ModStatusEffects.WATER_RESISTANCE, 3600, 0);
	public static final MobEffectInstance WATER_RESISTANCE_EFFECT_5MIN = new MobEffectInstance(ModStatusEffects.WATER_RESISTANCE, 6000, 0);
	public static final MobEffectInstance WATER_RESISTANCE_EFFECT_LONG = new MobEffectInstance(ModStatusEffects.WATER_RESISTANCE, 9600, 0);

}
