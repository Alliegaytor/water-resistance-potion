package xyz.alycat.hwr.util;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import xyz.alycat.hwr.Hwr;
import io.wispforest.owo.config.Option.Key;

public class ConfigUtils {
    // Tries and return an Item from the config string using Identifier,
    // returns default item from config key if InvalidIdentifierException
    // or the item is air (this happens when the item does not exist)
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
