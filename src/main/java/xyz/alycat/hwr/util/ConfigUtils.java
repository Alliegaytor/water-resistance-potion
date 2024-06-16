package xyz.alycat.hwr.util;

import io.wispforest.owo.config.Option.Key;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import xyz.alycat.hwr.Hwr;

import static java.util.Objects.nonNull;

public class ConfigUtils {
    /**
     * Return item that corresponds to the configuration value as an Identifier string.
     * Return the configuration's default item if the current config value is not parseable
     * or if it is interpreted as "air".
     */
    public static Item getItem(String strItem, Key key) {
        @Nullable Identifier id = Identifier.tryParse(strItem);
        if (nonNull(id) && !Registries.ITEM.get(id).asItem().equals(Items.AIR)) {
            return Registries.ITEM.get(id);
        }
        return Registries.ITEM.get(Identifier.of(Hwr.CONFIG.optionForKey(key).defaultValue().toString()));
    }
}
