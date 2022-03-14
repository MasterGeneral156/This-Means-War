package themastergeneral.thismeanswar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWBlockRegistry;
import themastergeneral.thismeanswar.registry.TMWEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWItemRegistry;

@Mod("thismeanswar")
public class TMWMain
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static String MODID = "thismeanswar";
    public static final CreativeModeTab ITEMGROUP = new TMWItemGroup();

    public TMWMain() {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    	
    	MinecraftForge.EVENT_BUS.register(this);
        TMWItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TMWEntityRegistry.ENTITES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TMWBlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TMWBlockEntityRegistry.TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("This Means War, in active development.");
        //OreGenHandler.registerOres();
    }
}
