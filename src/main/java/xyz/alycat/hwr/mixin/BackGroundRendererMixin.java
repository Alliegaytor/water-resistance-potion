package xyz.alycat.hwr.mixin;

import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(BackgroundRenderer.class)
public class BackGroundRendererMixin {
    /**
     * Increase water visibility from water resistance effect. Scales fog start and end distance by a factor of 1.75
     */
    @Inject(method = "applyFog", at = @At(value = "RETURN"), cancellable = true)
    private static void waterResistanceDecreaseWaterFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        final CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        final Entity entity = camera.getFocusedEntity();

        if (cameraSubmersionType == CameraSubmersionType.WATER) {
            if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
                final float fogScale = 1.75f;

                final Fog fogData = cir.getReturnValue();

                cir.setReturnValue(new Fog(
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
