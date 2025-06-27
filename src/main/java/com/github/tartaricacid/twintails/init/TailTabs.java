package com.github.tartaricacid.twintails.init;


import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TailTabs {
    public static ItemGroup TWIN_TAILS_TAB = new ItemGroup("twintails") {
        private ItemStack icon = null;
        private ITextComponent displayName = null;

        @Override
        public ItemStack makeIcon() {
            if (icon == null) {
                icon = TailItems.TWIN_TAILS_RED.get().getDefaultInstance();
            }
            return icon;
        }

        @Override
        public ITextComponent getDisplayName() {
            if (displayName == null) {
                displayName = new TranslationTextComponent("item_group.twintails.name");
            }
            return displayName;
        }
    };
}
