package xyz.alycat.hwr.mixin;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.alycat.hwr.effect.ModStatusEffectInstance;

@Mixin(ConsumableComponent.class)
public class ConsumableComponentMixin {
	/**
	 * Apply WATER_RESISTANCE to user upon consumption of ENCHANTED_GOLDEN_APPLE
	 */
	@Inject(method = "finishConsumption", at = @At("TAIL"))
	private void finishConsumption(World world, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
		if (stack.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
			user.addStatusEffect(
					new StatusEffectInstance(ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_5MIN)
			);
		}
	}
}
