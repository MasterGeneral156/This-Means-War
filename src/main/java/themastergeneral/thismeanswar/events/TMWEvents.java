package themastergeneral.thismeanswar.events;

import java.util.Set;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themastergeneral.thismeanswar.items.TMWItems;

@Mod.EventBusSubscriber(modid = "thismeanswar", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TMWEvents 
{
	@SubscribeEvent
    public static void onInventoryChanged(PlayerContainerEvent event) 
	{
        /*if (event.getEntity() != null && event.getEntity().isAlive()) {
            if (event.getEntity().getInventory().hasAnyOf((Set<Item>) TMWItems.charm_stealth.asItem())) {
                event.getEntity().setInvisible(true);
            } else {
                event.getEntity().setInvisible(false);
            }
        }*/
    }
}
