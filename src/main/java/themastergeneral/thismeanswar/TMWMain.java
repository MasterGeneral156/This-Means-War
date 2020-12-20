package themastergeneral.thismeanswar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themastergeneral.thismeanswar.registry.TMWEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWItemRegistry;

@Mod("thismeanswar")
public class TMWMain
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static String MODID = "thismeanswar";
    public static final ItemGroup ITEMGROUP = new TMWItemGroup();

    public TMWMain() {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    	
    	MinecraftForge.EVENT_BUS.register(this);
        TMWItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TMWEntityRegistry.ENTITES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("THIS MEANS WAR! RAWR! BOOM! PEW PEW!");
    }
}
