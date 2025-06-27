package com.github.tartaricacid.twintails;

import com.github.tartaricacid.twintails.init.TailItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TwinTails.MOD_ID)
public class TwinTails {
    public static final String MOD_ID = "twintails";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public TwinTails() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TailItems.ITEMS.register(modEventBus);
    }
}
