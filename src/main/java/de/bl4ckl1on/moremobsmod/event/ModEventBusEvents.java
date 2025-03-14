package de.bl4ckl1on.moremobsmod.event;

import de.bl4ckl1on.moremobsmod.MoreMobsMod;
import de.bl4ckl1on.moremobsmod.entity.ModEntities;
import de.bl4ckl1on.moremobsmod.entity.client.MonkeyModel;
import de.bl4ckl1on.moremobsmod.entity.custom.MonkeyEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = MoreMobsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonkeyModel.LAYER_LOCATION, MonkeyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MONKEY.get(), MonkeyEntity.createAttributes().build());
    }
}
