package xyz.alycat.hwr.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.effect.ModStatusEffectInstance;
import xyz.alycat.hwr.mixin.BrewingRecipeRegistryMixin;

public class ModPotions {
    public static Potion WATER_RESISTANCE;
    public static Potion LONG_WATER_RESISTANCE;
    public static Item POTION_RECIPE = Items.SPONGE;

    public static Potion registerPotion(String name, StatusEffectInstance effect) {
        return Registry.register(Registry.POTION, new Identifier(Hwr.MOD_ID, name), new Potion(new StatusEffectInstance(effect)));
    }

    public static void registerPotions() {
        WATER_RESISTANCE = registerPotion("water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT);
        LONG_WATER_RESISTANCE = registerPotion("long_water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_LONG);
        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, POTION_RECIPE, ModPotions.WATER_RESISTANCE);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.WATER_RESISTANCE, Items.REDSTONE, ModPotions.LONG_WATER_RESISTANCE);
    }
}
