package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TMWConfig {

	public static final ForgeConfigSpec GENERAL_SPEC;
    
	static 
	{
	    ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
	    BalanceConfig.registerCommonConfig(configBuilder);
	    MagazineConfigs.registerMagConfig(configBuilder);
	    DurabilityItemConfig.registerHammerConfig(configBuilder);
	    BalanceConfig.register12gConfig(configBuilder);
	    BalanceConfig.register223Config(configBuilder);
	    BalanceConfig.register45Config(configBuilder);
	    BalanceConfig.register556Config(configBuilder);
	    BalanceConfig.register9mmConfig(configBuilder);
	    BalanceConfig.registerBazookaConfig(configBuilder);
	    BalanceConfig.registerBeretta92fsConfig(configBuilder);
	    BalanceConfig.registerDoubleBarrelConfig(configBuilder);
	    BalanceConfig.registerDynamiteStickConfig(configBuilder);
	    BalanceConfig.registerGlock26Config(configBuilder);
	    BalanceConfig.registerGrenadeConfig(configBuilder);
	    BalanceConfig.registerM1911Config(configBuilder);
	    BalanceConfig.registerM870Config(configBuilder);
	    BalanceConfig.registerNukeConfig(configBuilder);
	    BalanceConfig.registerSaintAR223Config(configBuilder);
	    BalanceConfig.registerSaintAR556Config(configBuilder);
	    BalanceConfig.registerSODoubleBarrelConfig(configBuilder);
	    BalanceConfig.registerSOM870Config(configBuilder);
	    BalanceConfig.registerTMGCarbineConfig(configBuilder);
	    GENERAL_SPEC = configBuilder.build();
	}
}
