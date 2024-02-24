package xyz.alycat.hwr.mixinplugin;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.service.MixinService;
import xyz.alycat.hwr.Hwr;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Boilerplate, yay :3
 * Why do I even need to write this?!??!
 */
public final class MixinConfigPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        Class<?> mixinClass = getClass(mixinClassName);
        if (mixinClass != null) {
            if (mixinClass.isAnnotationPresent(Restriction.class)) {
                Condition[] conditions = mixinClass.getAnnotation(Restriction.class).require();
                for (Condition condition : conditions) {
                    if (!isModLoaded(condition.value())) {
                        Hwr.LOGGER.info("Mod '" + condition.value() + "' is not present, skipping mixin: " + mixinClassName);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    // Helper method to check if a mod is loaded
    private static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    private static Class<?> getClass(String mixinClassName) {
        // This mixin plugin BREAKS this mixin if it so much as looks at it
        if (mixinClassName.equals("xyz.alycat.hwr.mixin.BrewingRecipeRegistryMixin")) {
            return null;
        }
        try {
            return Class.forName(mixinClassName);
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }
}
