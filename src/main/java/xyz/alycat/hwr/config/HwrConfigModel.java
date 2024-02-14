package xyz.alycat.hwr.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;
import io.wispforest.owo.config.annotation.Sync;
import xyz.alycat.hwr.Hwr;

@Modmenu(modId = Hwr.MOD_ID)
@Config(name = Hwr.MOD_ID, wrapperName = "ModConfig")
public class HwrConfigModel {
    @RestartRequired
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public String potion_recipe = "minecraft:sponge";
}
