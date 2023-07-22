package xyz.alycat.hwr.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(BackgroundRenderer.class)
public class BackGroundRendererMixin {
    /**
     * Increase water visibility under water resistance effect. Scales fog start and end distance by a factor of 1.75
     */
    @Inject(method = "applyFog", at = @At(value = "TAIL"))
    private static void waterResistanceDecreaseWaterFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        final CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        final Entity entity = camera.getFocusedEntity();

        if (cameraSubmersionType == CameraSubmersionType.WATER) {
            if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
                final float fogScale = 1.75f;

                RenderSystem.setShaderFogStart(RenderSystem.getShaderFogStart() * fogScale);
                RenderSystem.setShaderFogEnd(RenderSystem.getShaderFogEnd() * fogScale);
            }
        }
    }
}
