package com.hea3ven.twintails.proxy;

import com.hea3ven.twintails.TwinTails;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(TwinTails.MOD_ID);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}
