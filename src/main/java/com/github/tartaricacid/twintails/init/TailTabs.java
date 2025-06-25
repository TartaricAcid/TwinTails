package com.github.tartaricacid.twintails.init;

import com.github.tartaricacid.twintails.TwinTails;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TailTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TwinTails.MOD_ID);

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TWIN_TAILS_TAB = TABS.register("twintails", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.twintails.name"))
            .icon(() -> TailItems.TWIN_TAILS_RED.get().getDefaultInstance())
            .displayItems((par, output) -> {
                output.accept(TailItems.TWIN_TAILS_RED.get());
                output.accept(TailItems.TWIN_TAILS_BLUE.get());
                output.accept(TailItems.TWIN_TAILS_YELLOW.get());
                output.accept(TailItems.TWIN_TAILS_CYAN.get());
                output.accept(TailItems.TWIN_TAILS_PINK.get());
                output.accept(TailItems.TWIN_TAILS_BLACK.get());
                output.accept(TailItems.TWIN_TAILS_BROWN.get());
            }).build());
}
