package com.github.tartaricacid.twintails;

import com.github.tartaricacid.twintails.config.TwinTailsConfig;
import com.github.tartaricacid.twintails.init.TailItems;
import com.github.tartaricacid.twintails.init.TailTabs;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TwinTails.MOD_ID)
public class TwinTails {
    public static final String MOD_ID = "twintails";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TwinTails() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TwinTailsConfig.init());
        TailItems.ITEMS.register(modEventBus);
        TailTabs.TABS.register(modEventBus);
    }
}
