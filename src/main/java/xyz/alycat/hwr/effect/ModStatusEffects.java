package xyz.alycat.hwr.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;

public class ModStatusEffects {
    public static StatusEffect WATER_RESISTANCE;

    public static StatusEffect registerWaterResistanceEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Hwr.MOD_ID, name),
                new WaterResistanceEffect(StatusEffectCategory.BENEFICIAL, Hwr.CONFIG.potion_colour().getColour()));
    }
    public static void registerEffects() {
        WATER_RESISTANCE = registerWaterResistanceEffect("water_resistance");
    }
}
