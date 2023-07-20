package xyz.alycat.hwr.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.effect.ModStatusEffectInstance;
import xyz.alycat.hwr.mixin.BrewingRecipeRegistryMixin;

public class ModPotions {
    public static Potion WATER_RESISTANCE;
    public static Potion LONG_WATER_RESISTANCE;

    public static Potion registerPotion(String name, StatusEffectInstance statusEffectInstance) {
        return Registry.register(Registries.POTION, new Identifier(Hwr.MOD_ID, name), new Potion(statusEffectInstance));
    }

    public static void registerPotions() {
        WATER_RESISTANCE = registerPotion("water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT);
        LONG_WATER_RESISTANCE = registerPotion("long_water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_LONG);
        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.SPONGE, ModPotions.WATER_RESISTANCE);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.WATER_RESISTANCE, Items.REDSTONE, ModPotions.LONG_WATER_RESISTANCE);
    }
}
