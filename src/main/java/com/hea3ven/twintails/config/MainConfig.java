package com.hea3ven.twintails.config;

import com.hea3ven.twintails.TwinTails;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = TwinTails.MOD_ID)
public final class MainConfig {
    @Config.Comment("Enable to get effects while wearing certain twintails")
    @Config.Name("TwinTailsEffects")
    public static boolean TWIN_TAILS_EFFECTS = false;

    @Mod.EventBusSubscriber(modid = TwinTails.MOD_ID)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(TwinTails.MOD_ID)) {
                ConfigManager.sync(TwinTails.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
