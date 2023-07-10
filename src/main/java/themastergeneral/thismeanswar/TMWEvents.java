package themastergeneral.thismeanswar;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import themastergeneral.thismeanswar.entity.SmokeThrowableEntity;

public class TMWEvents {

	@SubscribeEvent
    public void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.START) 
        {
        	for (Entity entity : event.level.getEntitiesOfClass(SmokeThrowableEntity.class, null)) 
        	{
                if (entity instanceof SmokeThrowableEntity) 
                {
                	SmokeThrowableEntity yourEntity = (SmokeThrowableEntity) entity;
                    yourEntity.tick();
                }
            }
        }
    }
}
