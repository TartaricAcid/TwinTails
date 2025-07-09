package com.github.tartaricacid.twintails.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TwinTailsConfig {
    public static ForgeConfigSpec.DoubleValue TWIN_TAILS_ADD_CHANCE;

    public static ForgeConfigSpec init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Chance to add twin tails when monster spawn, default 0.01");
        builder.comment("怪物生成时添加双马尾的几率，默认 0.01");
        TWIN_TAILS_ADD_CHANCE = builder.defineInRange("TwinTailsAddChance", 0.01, 0, 1);

        return builder.build();
    }
}
