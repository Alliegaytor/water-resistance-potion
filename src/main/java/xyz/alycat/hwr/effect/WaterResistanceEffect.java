package xyz.alycat.hwr.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class WaterResistanceEffect extends StatusEffect {
    protected WaterResistanceEffect(StatusEffectType category, int color) {
        super(category, color);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i;
        if (this == ModStatusEffects.WATER_RESISTANCE) {
            i = 50 >> amplifier;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}

