package xyz.alycat.hwr.mixin;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.alycat.hwr.effect.ModStatusEffects;

// Only use if client has origins
@Restriction(
        require = @Condition("origins")
)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinOrigins {
    /**
     * Negates player water damage if player has WATER_RESISTANCE status effect.
     * Currently, only works with the Origins hydrophobic classes
     */
    @Inject(method = "damage", at = @At(value = "HEAD", target = "Lnet/minecraft/entity/LivingEntity;isSleeping()Z", ordinal = 0), cancellable = true)
    private void injectDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (source.getName().equals("hurt_by_water") && entity.hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
            cir.setReturnValue(false);
        }
    }
}
