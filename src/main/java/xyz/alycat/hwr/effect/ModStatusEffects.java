package xyz.alycat.hwr.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.alycat.hwr.Hwr;
import xyz.alycat.hwr.config.StartupConfig;


public class ModStatusEffects {
	public static Holder<MobEffect> WATER_RESISTANCE;

	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Hwr.MODID);

	private static Holder<MobEffect> registerStatusEffect(String name, MobEffect effect) {
		return MOB_EFFECTS.register(name, () -> effect);
	};

	public static void register(IEventBus eventBus) {
		WATER_RESISTANCE = registerStatusEffect(
				"water_resistance",
				new WaterResistanceEffect(MobEffectCategory.BENEFICIAL, StartupConfig.potion_colour.get().getColour())
		);

		MOB_EFFECTS.register(eventBus);
	}
}
