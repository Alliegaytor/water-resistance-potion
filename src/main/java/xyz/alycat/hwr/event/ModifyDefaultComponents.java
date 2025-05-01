package xyz.alycat.hwr.event;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

import static xyz.alycat.hwr.effect.ModStatusEffectInstance.WATER_RESISTANCE_EFFECT_5MIN;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModifyDefaultComponents {
	/**
	 * Apply WATER_RESISTANCE_EFFECT to ENCHANTED_GOLDEN_APPLE consumeEffects
	 */
	@SubscribeEvent
	public static void modifyAppleComponents(ModifyDefaultComponentsEvent event) {
		final Consumable APPLE = Consumables.ENCHANTED_GOLDEN_APPLE;
		APPLE.onConsumeEffects().addLast(new ApplyStatusEffectsConsumeEffect(WATER_RESISTANCE_EFFECT_5MIN));

		event.modify(Items.ENCHANTED_GOLDEN_APPLE, builder ->
				builder.set(DataComponents.CONSUMABLE, APPLE)
		);
	}
}
