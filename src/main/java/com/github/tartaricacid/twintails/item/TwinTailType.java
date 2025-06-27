package com.github.tartaricacid.twintails.item;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.item.DyeColor;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;

public class TwinTailType {
    private final String name;
    private final Effect[] potions;
    private final DyeColor color;

    public TwinTailType(String name, Effect[] potions, DyeColor color) {
        this.name = name;
        this.potions = potions;
        this.color = color;
    }

    public static TwinTailType create(DyeColor color) {
        return new TwinTailType(color.getName(), new Effect[0], color);
    }

    public static TwinTailType create(DyeColor color, Effect... potions) {
        return new TwinTailType(color.getName(), potions, color);
    }

    public String getName() {
        return name;
    }

    public Effect[] getPotions() {
        return potions;
    }

    public DyeColor getColor() {
        return color;
    }

    public ResourceLocation getModelLocation() {
        return new ResourceLocation(TwinTails.MOD_ID, String.format("twintails/%s", name));
    }
}
