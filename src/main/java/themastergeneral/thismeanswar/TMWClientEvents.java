package themastergeneral.thismeanswar;

import net.minecraft.client.gui.screens.MenuScreens;
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
import themastergeneral.thismeanswar.menu.screen.AlloySmelterScreen;
import themastergeneral.thismeanswar.menu.screen.CasingRecyclerScreen;
import themastergeneral.thismeanswar.menu.screen.CrusherScreen;
import themastergeneral.thismeanswar.menu.screen.FactoryHolderScreen;
import themastergeneral.thismeanswar.menu.screen.FormerScreen;
import themastergeneral.thismeanswar.menu.screen.GunInspectorScreen;
import themastergeneral.thismeanswar.menu.screen.MolderScreen;
import themastergeneral.thismeanswar.menu.screen.PopperScreen;
import themastergeneral.thismeanswar.menu.screen.PressScreen;
import themastergeneral.thismeanswar.menu.screen.TipRecyclerScreen;
import themastergeneral.thismeanswar.registry.TMWMenuRegistry;

@Mod.EventBusSubscriber(modid = "thismeanswar", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TMWClientEvents
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
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
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.land_mine, RenderType.translucent());

    	event.enqueueWork(() -> {
    		MenuScreens.register(TMWMenuRegistry.CASING_RECYCLER_MENU.get(), CasingRecyclerScreen::new);
    		MenuScreens.register(TMWMenuRegistry.TIP_RECYCLER_MENU.get(), TipRecyclerScreen::new);
    		MenuScreens.register(TMWMenuRegistry.CRUSHER_MENU.get(), CrusherScreen::new);
    		MenuScreens.register(TMWMenuRegistry.FACTORY_HOLDER_MENU.get(), FactoryHolderScreen::new);
    		MenuScreens.register(TMWMenuRegistry.PRESS_MENU.get(), PressScreen::new);
    		MenuScreens.register(TMWMenuRegistry.POPPER_MENU.get(), PopperScreen::new);
    		MenuScreens.register(TMWMenuRegistry.ALLOY_SMELTER_MENU.get(), AlloySmelterScreen::new);
    		MenuScreens.register(TMWMenuRegistry.FORMER_MENU.get(), FormerScreen::new);
    		MenuScreens.register(TMWMenuRegistry.MOLDER_MENU.get(), MolderScreen::new);
    		MenuScreens.register(TMWMenuRegistry.GUN_INSPECTOR_MENU.get(), GunInspectorScreen::new);
    	});
    }
    
    @SubscribeEvent
    public void onFOVUpdate(ComputeFov event) 
    {
    	if ((event.getCamera().getEntity() instanceof Player player) && ((event.getCamera().getEntity() != Entity.NULL)))
    	{
    		float fovModifier = (player.getPersistentData().getFloat("fovModifier") > 0.01F) ? player.getPersistentData().getFloat("fovModifier") : 1F;
        	TMWMain.debugLogger(fovModifier);
    		event.setFOV(event.getFOV() * fovModifier);
    	}
    }
}
