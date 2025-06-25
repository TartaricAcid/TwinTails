package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;

public record TwinTailType(String name, @Nullable Holder<MobEffect>[] potions, DyeColor color) {
    public static TwinTailType create(DyeColor color) {
        return new TwinTailType(color.getName(), null, color);
    }

    @SafeVarargs
    public static TwinTailType create(DyeColor color, Holder<MobEffect>... potions) {
        return new TwinTailType(color.getName(), potions, color);
    }

    public ResourceLocation getModelLocation() {
        return ResourceLocation.fromNamespaceAndPath(TwinTails.MOD_ID, "twintails/%s".formatted(name));
    }
}
