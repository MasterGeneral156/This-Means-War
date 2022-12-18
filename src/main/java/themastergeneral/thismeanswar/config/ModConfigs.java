package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ModConfigs {

	public static void register() 
	{
        registerCommonConfigs();
        registerMagConfig();
        registerGunConfigs();
        registerDurabilityConfig();
    }
	
	private static void registerCommonConfigs() 
	{
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		BalanceConfig.registerCommonConfig(SERVER_BUILDER);
		BalanceConfig.register9mmConfig(SERVER_BUILDER);
		BalanceConfig.register12gConfig(SERVER_BUILDER);
		BalanceConfig.register223Config(SERVER_BUILDER);
		BalanceConfig.register556Config(SERVER_BUILDER);
		BalanceConfig.register45Config(SERVER_BUILDER);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SERVER_BUILDER.build(), "ctd/tmw/reference.toml");
	}
	
	private static void registerDurabilityConfig()
	{
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		DurabilityItemConfig.registerHammerConfig(SERVER_BUILDER);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SERVER_BUILDER.build(), "ctd/tmw/durability.toml");
	}
	
	private static void registerGunConfigs()
	{
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		BalanceConfig.registerTMGCarbineConfig(SERVER_BUILDER);
		BalanceConfig.registerSaintAR223Config(SERVER_BUILDER);
		BalanceConfig.registerSaintAR556Config(SERVER_BUILDER);
		BalanceConfig.registerBazookaConfig(SERVER_BUILDER);
		BalanceConfig.registerBeretta92fsConfig(SERVER_BUILDER);
		BalanceConfig.registerGlock26Config(SERVER_BUILDER);
		BalanceConfig.registerM870Config(SERVER_BUILDER);
		BalanceConfig.registerM1911Config(SERVER_BUILDER);
		BalanceConfig.registerSOM870Config(SERVER_BUILDER);
		BalanceConfig.registerDoubleBarrelConfig(SERVER_BUILDER);
		BalanceConfig.registerSODoubleBarrelConfig(SERVER_BUILDER);
		BalanceConfig.registerGrenadeConfig(SERVER_BUILDER);
		BalanceConfig.registerDynamiteStickConfig(SERVER_BUILDER);
		BalanceConfig.registerNukeConfig(SERVER_BUILDER);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SERVER_BUILDER.build(), "ctd/tmw/guns.toml");
	}
	
	private static void registerMagConfig()
	{
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		MagazineConfigs.registerMagConfig(SERVER_BUILDER);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SERVER_BUILDER.build(), "ctd/tmw/mags.toml");
	}
}
