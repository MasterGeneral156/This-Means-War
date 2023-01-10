package themastergeneral.thismeanswar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.config.ModConfigs;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWBlockRegistry;
import themastergeneral.thismeanswar.registry.TMWEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWItemRegistry;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;
import themastergeneral.thismeanswar.world.OreGen;

@Mod("thismeanswar")
public class TMWMain
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static String MODID = "thismeanswar";
    public static final CreativeModeTab ITEMGROUP = new TMWItemGroup();

    public TMWMain() {
    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modBus.addListener(this::setup);
    	
    	DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(this::clientSetup));
    	
    	MinecraftForge.EVENT_BUS.register(this);
        TMWItemRegistry.ITEMS.register(modBus);
        TMWEntityRegistry.ENTITES.register(modBus);
        TMWBlockRegistry.BLOCKS.register(modBus);
        TMWBlockEntityRegistry.TILES.register(modBus);
        TMWRecipeTypeRegistration.RECIPE_SERIALIZER.register(modBus);
        ModConfigs.register();
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("This Means War, in active development.");
        event.enqueueWork(() -> {
        	OreGen.registerOreFeatures();
        });
    }
    
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(final FMLClientSetupEvent event)
    {
    	LOGGER.info("Loading client-side Block Render layers.");
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box_medium, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box_large, RenderType.translucent());
    }
}
