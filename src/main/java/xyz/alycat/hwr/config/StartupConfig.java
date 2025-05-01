package xyz.alycat.hwr.config;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class StartupConfig {

	public enum Colours {
		purple(5386927),
		blue(1582940),
		green(1112940);

		private final int colour;

		Colours(int colour) {
			this.colour = colour;
		}

		public int getColour() {
			return this.colour;
		}
	}

	public static ModConfigSpec.ConfigValue<Colours> potion_colour;

	private static void onLoad() {}

	public static void register(ModContainer container) {

		ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

		potion_colour = BUILDER
				.comment("potion_colour")
				.defineEnum("potion_colour", Colours.purple);


		container.registerConfig(ModConfig.Type.STARTUP, BUILDER.build());
		onLoad();
	}

	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {

	}
}