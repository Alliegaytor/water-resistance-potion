package xyz.alycat.hwr.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	/**
	 * Add check for WATER_RESISTANCE effect before damaging entities hurtByWater
	 */
	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSensitiveToWater()Z", ordinal = 0), cancellable = true)
	private void modifyTickMovement(CallbackInfo info) {
		// Cast the entity to LivingEntity
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity.hasEffect(ModStatusEffects.WATER_RESISTANCE)) {
			info.cancel();
		}
	}
}
