package xyz.alycat.hwr.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import xyz.alycat.hwr.Hwr;

@Modmenu(modId = Hwr.MOD_ID)
@Config(name = Hwr.MOD_ID, wrapperName = "ModConfig")
public class HwrConfigModel {
    public boolean toggle = false;

    public String foo = "bar";
}
