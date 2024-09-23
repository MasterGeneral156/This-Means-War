package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TMWClientConfig {
	public static ForgeConfigSpec.DoubleValue volume_gun;
	
	public static void registerConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
		COMMON_BUILDER
				.push("This Means War Tool 'Client' Options")
				.comment("Volume and other misc options");
		volume_gun =
				COMMON_BUILDER
					.comment("Volume for gun firing")
					.defineInRange("volume_gun", 0.25D, 0.01, 1.0D);
		COMMON_BUILDER.pop();
	}
}
