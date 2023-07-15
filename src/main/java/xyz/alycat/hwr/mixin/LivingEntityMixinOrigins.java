package xyz.alycat.hwr.mixin;

import xyz.alycat.hwr.mixinplugin.Condition;
import xyz.alycat.hwr.mixinplugin.Restriction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
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
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void injectDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cfr) {
        // Cast the entity to LivingEntity
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
            // Reduce CPU overhead by checking if player first. Most entities are not players.
            if (entity instanceof PlayerEntity) {
                if (!entity.isInvulnerableTo(source)) {
                    if (source.getName().equals("hurt_by_water")) {
                        // Negate damage to entity
                        cfr.setReturnValue(false);
                        cfr.cancel();
                    }
                }
            }
        }
    }
}