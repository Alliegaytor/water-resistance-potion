package xyz.alycat.hwr.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;

public class ModStatusEffects {
    public static StatusEffect WATER_RESISTANCE;

    public static StatusEffect registerWaterResistanceEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(Hwr.MOD_ID, name),
                new WaterResistanceEffect(StatusEffectCategory.BENEFICIAL, 5386927));

    }
    public static void registerEffects() {
        WATER_RESISTANCE = registerWaterResistanceEffect("water_resistance");
    }
}
