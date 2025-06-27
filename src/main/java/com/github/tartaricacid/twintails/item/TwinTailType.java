package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;

public record TwinTailType(String name, MobEffect[] potions, DyeColor color) {
    public static TwinTailType create(DyeColor color) {
        return new TwinTailType(color.getName(), new MobEffect[0], color);
    }

    public static TwinTailType create(DyeColor color, MobEffect... potions) {
        return new TwinTailType(color.getName(), potions, color);
    }

    public ResourceLocation getModelLocation() {
        return new ResourceLocation(TwinTails.MOD_ID, "twintails/%s".formatted(name));
    }
}
