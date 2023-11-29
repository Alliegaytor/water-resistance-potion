package xyz.alycat.hwr.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.alycat.hwr.effect.ModStatusEffectInstance;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    /**
     * Add check for WATER_RESISTANCE effect before damaging entities hurtByWater
     */
    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isWet()Z", ordinal = 0), cancellable = true)
    private void modifyTickMovement(CallbackInfo info) {
        // Cast the entity to LivingEntity
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
            info.cancel();
        }
    }
    /**
     * Apply WATER_RESISTANCE upon eating god apple
     */
    @Inject(method = "applyFoodEffects", at = @At("TAIL"))
    private void applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity, CallbackInfo ci) {
        if (stack.getItem().toString().equals("enchanted_golden_apple")) {
            ((LivingEntity) (Object) this).addStatusEffect(
                    new StatusEffectInstance(ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_5MIN)
            );
        }
    }
}


