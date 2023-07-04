package xyz.alycat.hwr.potion;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.effect.ModStatusEffects;
import xyz.alycat.hwr.mixin.BrewingRecipeRegistryMixin;

public class ModPotions {
    public static Potion WATER_RESISTANCE_POTION;
    public static Potion LONG_WATER_RESISTANCE_POTION;

    public static Potion registerPotion(String name, StatusEffect effect, int duration, int amplifier) {
        return Registry.register(Registries.POTION, new Identifier(Hwr.MOD_ID, name), new Potion(new StatusEffectInstance(effect, duration, amplifier)));
    }

    public static void registerPotions() {
        WATER_RESISTANCE_POTION = registerPotion("water_resistance_potion", ModStatusEffects.WATER_RESISTANCE, 3600, 0);
        LONG_WATER_RESISTANCE_POTION = registerPotion("long_water_resistance_potion", ModStatusEffects.WATER_RESISTANCE, 9600, 0);
        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.SPONGE, ModPotions.WATER_RESISTANCE_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.WATER_RESISTANCE_POTION, Items.REDSTONE, ModPotions.LONG_WATER_RESISTANCE_POTION);
    }
}
