package xyz.alycat.hwr.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;
import xyz.alycat.hwr.Hwr;

@Modmenu(modId = Hwr.MOD_ID)
@Config(name = Hwr.MOD_ID, wrapperName = "ModConfig")
public class HwrConfigModel {
	// Client side
	// Server handles the recipe config, no need to sync
	@RestartRequired
	public String potion_recipe = "minecraft:sponge";

	// Client side
	// Purely cosmetic change
	@RestartRequired
	public Colours potion_colour = Colours.purple;

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
}
