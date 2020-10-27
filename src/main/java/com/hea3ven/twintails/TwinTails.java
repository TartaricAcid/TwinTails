package com.hea3ven.twintails;

import com.hea3ven.twintails.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TwinTails.MOD_ID, name = TwinTails.NAME, version = TwinTails.VERSION)
public class TwinTails {
    public static final String MOD_ID = "twintails";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Twin Tails";

    @SidedProxy(serverSide = "com.hea3ven.twintails.proxy.CommonProxy",
            clientSide = "com.hea3ven.twintails.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
