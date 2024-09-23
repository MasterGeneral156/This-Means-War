package themastergeneral.thismeanswar.config;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

public class MagazineConfigs {
	public static ForgeConfigSpec.IntValue AR_MAG_SIZE;
	public static ForgeConfigSpec.IntValue SML_9MM_MAG_SIZE;
	public static ForgeConfigSpec.IntValue LRG_9MM_MAG_SIZE;
	public static ForgeConfigSpec.IntValue M1911_MAG_SIZE;
	public static ForgeConfigSpec.IntValue MAX_MAG_CAP_UPGRADES;
	public static ForgeConfigSpec.DoubleValue MAG_CAP_T1;
	public static ForgeConfigSpec.DoubleValue MAG_CAP_T2;
	public static ForgeConfigSpec.DoubleValue MAG_CAP_T3;
	public static ForgeConfigSpec.DoubleValue MAG_CAP_CREATIVE;
	
	public static void registerMagConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
		COMMON_BUILDER
				.push("This Means War Magazine Capacity Upgrade")
				.comment("Options for the magazine capacity upgrade items");

		MAX_MAG_CAP_UPGRADES = COMMON_BUILDER
                .comment("Maximum magazine capacity upgrades per magazine")
                .defineInRange("max_magazine_cap_upgrades", 3, 1, 64);

		MAG_CAP_T1 = COMMON_BUILDER
				.comment("Capacity increase per Tier 1 Capacity upgrade")
				.defineInRange("magazine_cap_upgrade_t1", 0.15, 0.01D, Double.MAX_VALUE);

		MAG_CAP_T2 = COMMON_BUILDER
				.comment("Capacity increase per Tier 2 Capacity upgrade")
				.defineInRange("magazine_cap_upgrade_t2", 0.325, 0.01D, Double.MAX_VALUE);

		MAG_CAP_T3 = COMMON_BUILDER
				.comment("Capacity increase per Tier 3 Capacity upgrade")
				.defineInRange("magazine_cap_upgrade_t3", 0.7, 0.01D, Double.MAX_VALUE);

		MAG_CAP_CREATIVE = COMMON_BUILDER
				.comment("Capacity increase per Creative Tier Capacity upgrade")
				.defineInRange("magazine_cap_upgrade_creative", 1024D, 0.01D, Double.MAX_VALUE);
		COMMON_BUILDER.pop();
	}
}
