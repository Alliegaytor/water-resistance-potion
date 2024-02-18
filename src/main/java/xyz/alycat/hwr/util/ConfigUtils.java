package xyz.alycat.hwr.util;

import io.wispforest.owo.config.Option.Key;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import xyz.alycat.hwr.Hwr;

public class ConfigUtils {
    /**
     * Try and return item based on a configuration value as an Identifier string.
     * Return the configuration's default item if the current config value does not work or is an identifier for "air".
     */
    public static Item getItem(String strItem, Key key) {
        Identifier id;
        try {
            id = new Identifier(strItem);
            if (Registries.ITEM.get(id).toString().equals("air")) {
                throw new InvalidIdentifierException("");
            }
        }
        catch(InvalidIdentifierException e) {
            id = new Identifier(Hwr.CONFIG.optionForKey(key).defaultValue().toString());
        }
        return Registries.ITEM.get(id);
    }
}
