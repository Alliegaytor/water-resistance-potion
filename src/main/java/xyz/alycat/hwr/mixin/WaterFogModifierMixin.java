package xyz.alycat.hwr.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.WaterFogModifier;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.alycat.hwr.effect.ModStatusEffects;

@Mixin(WaterFogModifier.class)
public class WaterFogModifierMixin {
	/**
	 * Increase water visibility from water resistance effect. Scales fog start and end distance by a factor of 1.75
	 */
	@Inject(method = "applyStartEndModifier", at = @At(value = "RETURN"))
	private static void waterResistanceDecreaseWaterFog(FogData data, Entity cameraEntity, BlockPos cameraPos, ClientWorld world, float viewDistance, RenderTickCounter tickCounter, CallbackInfo ci) {
			if (cameraEntity instanceof ClientPlayerEntity && ((ClientPlayerEntity) cameraEntity).hasStatusEffect(ModStatusEffects.WATER_RESISTANCE)) {
				final float fogScale = 1.75f;

				data.environmentalStart = data.environmentalStart * fogScale;
				data.environmentalEnd = data.environmentalEnd * fogScale;


				data.skyEnd = data.environmentalEnd;
				data.cloudEnd = data.environmentalEnd;
			}
	}
}
