package themastergeneral.thismeanswar;

import java.util.Random;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themastergeneral.thismeanswar.entity.SmokeThrowableEntity;
import themastergeneral.thismeanswar.items.TMWItems;

@Mod.EventBusSubscriber(modid = "thismeanswar")
public class TMWEvents {

	@SubscribeEvent
    public void onLevelTick(TickEvent.LevelTickEvent event) 
	{
        for (Entity entity : event.level.getEntitiesOfClass(SmokeThrowableEntity.class, AABB.of(BoundingBox.infinite()))) 
    	{
            if (entity instanceof SmokeThrowableEntity) 
            {
            	SmokeThrowableEntity yourEntity = (SmokeThrowableEntity) entity;
                yourEntity.tick();
            }
        }
    }
	
	@SubscribeEvent
    public static void onVillagerTradesSetup(VillagerTradesEvent event) 
	{
		if (event.getType() != VillagerProfession.NONE)
		{
			TMWMain.debugLogger("Giving trades to villagers...");
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_12g, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_223, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_38spec, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_45, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_556, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_762, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_lead, 16, TMWItems.round_9mm, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_12g, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_223, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_38spec, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_45, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_556, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_762, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_lead, 8, TMWItems.bullet_cast_9mm, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_12g, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_223, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_38spec, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_45, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_556, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_762, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.nugget_brass, 16, TMWItems.casing_9mm, 16, 8, 0, new Random().nextFloat(0.5F, 2.0F)));
			
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_12g, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_223, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_38spec, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_45, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_556, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_762, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
			event.getTrades().get(1).add(new TMWTrades(TMWItems.ingot_brass, 8, TMWItems.casing_cast_9mm, 1, 4, 0, new Random().nextFloat(0.5F, 2.0F)));
		}
	}
}
