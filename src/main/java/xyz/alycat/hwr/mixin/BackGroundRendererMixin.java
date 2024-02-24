package xyz.alycat.hwr.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(BackgroundRenderer.class)
public class BackGroundRendererMixin {
    /**
     * Increase water visibility from water resistance effect. Scales fog start and end distance by a factor of 1.75
     */
    @Inject(method = "applyFog", at = @At(value = "TAIL"))
    private static void waterResistanceDecreaseWaterFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        final FluidState fluidState = camera.getSubmergedFluidState();
        final Entity entity = camera.getFocusedEntity();

        if (fluidState.isIn(FluidTags.WATER)) {
            if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
                final float fogScale = 0.01f;

                RenderSystem.fogDensity(fogScale);
            }
        }
    }
}
