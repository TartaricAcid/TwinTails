package com.github.tartaricacid.twintails.init;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TailTabs {
    public static CreativeModeTab TWIN_TAILS_TAB = new CreativeModeTab("twintails") {
        private ItemStack icon = null;
        private Component displayName = null;

        @Override
        public ItemStack makeIcon() {
            if (icon == null) {
                icon = TailItems.TWIN_TAILS_RED.get().getDefaultInstance();
            }
            return icon;
        }

        @Override
        public Component getDisplayName() {
            if (displayName == null) {
                displayName = new TranslatableComponent("item_group.twintails.name");
            }
            return displayName;
        }
    };
}
