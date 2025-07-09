package com.github.tartaricacid.twintails.event;

import com.github.tartaricacid.twintails.TwinTails;
import com.github.tartaricacid.twintails.config.TwinTailsConfig;
import com.github.tartaricacid.twintails.item.ItemHairBand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.piglin.AbstractPiglinEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TwinTails.MOD_ID)
public class MonsterJoinLevelEvent {
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity.level.isClientSide) {
            return;
        }
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity living = (LivingEntity) entity;
        // 判断是否为僵尸、骷髅及其变种
        if (living.getRandom().nextDouble() < TwinTailsConfig.TWIN_TAILS_ADD_CHANCE.get() && isMonster(living) && !living.isBaby()) {
            int size = ItemHairBand.ALL_TWIN_TAILS.size();
            if (size > 0 && living.getItemBySlot(EquipmentSlotType.HEAD).isEmpty()) {
                ItemHairBand hairBand = ItemHairBand.ALL_TWIN_TAILS.get(living.getRandom().nextInt(size));
                living.setItemSlot(EquipmentSlotType.HEAD, hairBand.getDefaultInstance());
            }
        }
    }

    private static boolean isMonster(LivingEntity living) {
        return living instanceof ZombieEntity || living instanceof AbstractSkeletonEntity || living instanceof AbstractPiglinEntity;
    }
}
