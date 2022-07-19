package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MagazineConfigs {
	public static ForgeConfigSpec.IntValue AR_MAG_SIZE;
	public static ForgeConfigSpec.IntValue SML_9MM_MAG_SIZE;
	public static ForgeConfigSpec.IntValue LRG_9MM_MAG_SIZE;
	public static ForgeConfigSpec.IntValue M1911_MAG_SIZE;
	public static ForgeConfigSpec.IntValue MAX_MAG_CAP_UPGRADES;
	
	public static void registerMagConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
		AR_MAG_SIZE = COMMON_BUILDER
                .comment("How large should the AR M4 Magazine be?")
                .defineInRange("AR M4 Mag Size", 20, 1, Integer.MAX_VALUE);
		
		SML_9MM_MAG_SIZE = COMMON_BUILDER
                .comment("How large should the 9mm Magazine be?")
                .defineInRange("9mm Mag Size", 15, 1, Integer.MAX_VALUE);
		
		LRG_9MM_MAG_SIZE = COMMON_BUILDER
                .comment("How large should the Large 9mm Magazine be?")
                .defineInRange("Large 9mm Mag Size", 30, 1, Integer.MAX_VALUE);
		
		M1911_MAG_SIZE = COMMON_BUILDER
                .comment("How large should the M1911 Magazine be?")
                .defineInRange("M1911 Mag Size", 7, 1, Integer.MAX_VALUE);
		
		MAX_MAG_CAP_UPGRADES = COMMON_BUILDER
                .comment("Max amount of Capacity Upgrades to be applied on a magazine")
                .defineInRange("Max Mag Cap Upgrades", 3, 1, 15);
	}
}
