package com.github.tartaricacid.twintails.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TwinTailsConfig {
    public static ModConfigSpec.DoubleValue TWIN_TAILS_ADD_CHANCE;

    public static ModConfigSpec init() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("Chance to add twin tails when monster spawn, default 0.05");
        builder.comment("怪物生成时添加双马尾的几率，默认 0.05");
        TWIN_TAILS_ADD_CHANCE = builder.defineInRange("TwinTailsAddChance", 0.05, 0, 1);

        return builder.build();
    }
}
