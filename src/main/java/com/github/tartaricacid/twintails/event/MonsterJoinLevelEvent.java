package com.github.tartaricacid.twintails.event;

import com.github.tartaricacid.twintails.TwinTails;
import com.github.tartaricacid.twintails.config.TwinTailsConfig;
import com.github.tartaricacid.twintails.item.ItemHairBand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = TwinTails.MOD_ID)
public class MonsterJoinLevelEvent {
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.loadedFromDisk()) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity.level().isClientSide) {
            return;
        }
        if (!(entity instanceof LivingEntity living)) {
            return;
        }

        // 判断是否为僵尸、骷髅及其变种
        if (living.getRandom().nextDouble() < TwinTailsConfig.TWIN_TAILS_ADD_CHANCE.get() && isMonster(living) && !living.isBaby()) {
            int size = ItemHairBand.ALL_TWIN_TAILS.size();
            if (size > 0 && living.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                ItemHairBand hairBand = ItemHairBand.ALL_TWIN_TAILS.get(living.getRandom().nextInt(size));
                living.setItemSlot(EquipmentSlot.HEAD, hairBand.getDefaultInstance());
            }
        }
    }

    private static boolean isMonster(LivingEntity living) {
        return living instanceof Zombie || living instanceof AbstractSkeleton || living instanceof AbstractPiglin;
    }
}
