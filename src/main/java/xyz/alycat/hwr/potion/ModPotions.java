package xyz.alycat.hwr.potion;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.effect.ModStatusEffectInstance;
import xyz.alycat.hwr.util.ConfigUtils;

public class ModPotions {
    public static RegistryEntry<Potion> WATER_RESISTANCE;
    public static RegistryEntry<Potion> LONG_WATER_RESISTANCE;

    public static Item POTION_RECIPE = ConfigUtils.getItem(Hwr.CONFIG.potion_recipe(), Hwr.CONFIG.keys.potion_recipe);

    public static RegistryEntry<Potion> registerPotion(String name, StatusEffectInstance statusEffectInstance) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Hwr.MOD_ID, name), new Potion(statusEffectInstance));
    }

    public static void registerPotions() {
        WATER_RESISTANCE = registerPotion("water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT);
        LONG_WATER_RESISTANCE = registerPotion("long_water_resistance_potion", ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_LONG);
        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, POTION_RECIPE, ModPotions.WATER_RESISTANCE);
            builder.registerPotionRecipe(ModPotions.WATER_RESISTANCE, Items.REDSTONE, ModPotions.LONG_WATER_RESISTANCE);
        });
    }
}
