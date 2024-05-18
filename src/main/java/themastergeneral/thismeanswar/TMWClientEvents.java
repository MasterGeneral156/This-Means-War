package themastergeneral.thismeanswar;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent.ComputeFov;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import themastergeneral.thismeanswar.block.TMWBlocks;

@Mod.EventBusSubscriber(modid = "thismeanswar", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TMWClientEvents
{
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event)
    {
    	TMWMain.LOGGER.info("Loading client-side Block Render layers.");
    	//TODO Fix this depre notice
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box_medium, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box_large, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.medic_box, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.medic_box_medium, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.medic_box_large, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.barbed_wire, RenderType.translucent());
    }
    
    @SubscribeEvent
    public void onFOVUpdate(ComputeFov event) 
    {
    	if ((event.getCamera().getEntity() instanceof Player player) && ((event.getCamera().getEntity() != Entity.NULL)))
    	{
    		float fovModifier = (player.getPersistentData().getFloat("fovModifier") > 0.01F) ? player.getPersistentData().getFloat("fovModifier") : 1F;
        	event.setFOV(event.getFOV() * fovModifier);
    	}
    }
}
