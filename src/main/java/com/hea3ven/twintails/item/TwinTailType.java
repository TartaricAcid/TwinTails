package com.hea3ven.twintails.item;

import com.hea3ven.twintails.client.model.ModelTwinTails;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TwinTailType {
    private final String name;
    private final int ordinal;
    private final Potion[] potions;
    private final Object recipeIngredient;
    private final EnumDyeColor color;
    private ModelTwinTails model;

    public TwinTailType(int ordinal, String name, Potion[] potions, Object recipeIngredient,
                        EnumDyeColor color) {
        this.ordinal = ordinal;
        this.name = name;
        this.potions = potions;
        this.recipeIngredient = recipeIngredient;
        this.color = color;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public String getName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public ModelTwinTails getModel() {
        if (model == null) {
            model = new ModelTwinTails();
        }
        return model;
    }

    public Potion[] getPotions() {
        return potions;
    }

    public Object getRecipeIngredient() {
        return recipeIngredient;
    }

    public EnumDyeColor getColor() {
        return color;
    }
}
