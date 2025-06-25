package com.github.tartaricacid.twintails.init;

import com.github.tartaricacid.twintails.TwinTails;
import com.github.tartaricacid.twintails.item.ItemHairBand;
import com.github.tartaricacid.twintails.item.TwinTailType;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TailItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TwinTails.MOD_ID);

    public static final TwinTailType RED = TwinTailType.create(DyeColor.RED, MobEffects.MOVEMENT_SPEED, MobEffects.JUMP);
    public static final TwinTailType BLUE = TwinTailType.create(DyeColor.BLUE, MobEffects.WATER_BREATHING, MobEffects.DAMAGE_RESISTANCE);
    public static final TwinTailType YELLOW = TwinTailType.create(DyeColor.YELLOW, MobEffects.DAMAGE_RESISTANCE, MobEffects.FIRE_RESISTANCE);
    public static final TwinTailType CYAN = TwinTailType.create(DyeColor.CYAN);
    public static final TwinTailType PINK = TwinTailType.create(DyeColor.PINK);
    public static final TwinTailType BLACK = TwinTailType.create(DyeColor.BLACK);
    public static final TwinTailType BROWN = TwinTailType.create(DyeColor.BROWN);

    public static final DeferredItem<Item> TWIN_TAILS_RED = ITEMS.register("hairband_red", () -> new ItemHairBand(RED));
    public static final DeferredItem<Item> TWIN_TAILS_BLUE = ITEMS.register("hairband_blue", () -> new ItemHairBand(BLUE));
    public static final DeferredItem<Item> TWIN_TAILS_YELLOW = ITEMS.register("hairband_yellow", () -> new ItemHairBand(YELLOW));
    public static final DeferredItem<Item> TWIN_TAILS_CYAN = ITEMS.register("hairband_cyan", () -> new ItemHairBand(CYAN));
    public static final DeferredItem<Item> TWIN_TAILS_PINK = ITEMS.register("hairband_pink", () -> new ItemHairBand(PINK));
    public static final DeferredItem<Item> TWIN_TAILS_BLACK = ITEMS.register("hairband_black", () -> new ItemHairBand(BLACK));
    public static final DeferredItem<Item> TWIN_TAILS_BROWN = ITEMS.register("hairband_brown", () -> new ItemHairBand(BROWN));
}
