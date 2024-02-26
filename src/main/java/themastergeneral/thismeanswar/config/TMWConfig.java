package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber
public class TMWConfig {

	public static final ForgeConfigSpec.Builder GENERAL_SPEC = new ForgeConfigSpec.Builder();
	
	public static ForgeConfigSpec COMMON;
    
	static 
	{
	    //BalanceConfig.registerCommonConfig(GENERAL_SPEC);
	    MagazineConfigs.registerMagConfig(GENERAL_SPEC);
	    DurabilityItemConfig.registerHammerConfig(GENERAL_SPEC);
	    //BalanceConfig.register12gConfig(GENERAL_SPEC);
	    //BalanceConfig.register223Config(GENERAL_SPEC);
	    //BalanceConfig.register45Config(GENERAL_SPEC);
	    //BalanceConfig.register556Config(GENERAL_SPEC);
	    //BalanceConfig.register9mmConfig(GENERAL_SPEC);
	    //BalanceConfig.registerBazookaConfig(GENERAL_SPEC);
	    //BalanceConfig.registerBeretta92fsConfig(GENERAL_SPEC);
	    //BalanceConfig.registerDoubleBarrelConfig(GENERAL_SPEC);
	    //BalanceConfig.registerDynamiteStickConfig(GENERAL_SPEC);
	    //BalanceConfig.registerGlock26Config(GENERAL_SPEC);
	    //BalanceConfig.registerGrenadeConfig(GENERAL_SPEC);
	    //BalanceConfig.registerM1911Config(GENERAL_SPEC);
	    //BalanceConfig.registerM870Config(GENERAL_SPEC);
	    //BalanceConfig.registerNukeConfig(GENERAL_SPEC);
	    //BalanceConfig.registerSaintAR223Config(GENERAL_SPEC);
	    //BalanceConfig.registerSaintAR556Config(GENERAL_SPEC);
	    //BalanceConfig.registerSODoubleBarrelConfig(GENERAL_SPEC);
	    //BalanceConfig.registerSOM870Config(GENERAL_SPEC);
	    //BalanceConfig.registerTMGCarbineConfig(GENERAL_SPEC);
	    
	    COMMON = GENERAL_SPEC.build();
	}
}
