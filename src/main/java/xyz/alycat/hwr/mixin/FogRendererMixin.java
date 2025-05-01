package xyz.alycat.hwr.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
	/**
	 * Increase water visibility from water resistance effect. Scales fog start and end distance by a factor of 1.75
	 */
	@Inject(method = "setupFog", at = @At(value = "RETURN"), cancellable = true)
	private static void waterResistanceDecreaseWaterFog(Camera camera, FogRenderer.FogMode fogMode, Vector4f fogColor, float renderDistance, boolean isFoggy, float partialTick, CallbackInfoReturnable<FogParameters> cir) {
		final FogType fogType = camera.getFluidInCamera();
		final Entity entity = camera.getEntity();

		if (fogType == FogType.WATER) {
			if (entity instanceof LivingEntity && ((LivingEntity) entity).hasEffect(ModStatusEffects.WATER_RESISTANCE)) {
				final float fogScale = 1.75f;

				final FogParameters fogData = cir.getReturnValue();

				cir.setReturnValue(new FogParameters(
						fogData.start() * fogScale,
						fogData.end() * fogScale,
						fogData.shape(),
						fogData.red(),
						fogData.green(),
						fogData.blue(),
						fogData.alpha())
				);
			}
		}
	}
}
