package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DurabilityItemConfig {
	public static ForgeConfigSpec.IntValue IRON_HAMMER;
	public static ForgeConfigSpec.IntValue STEEL_HAMMER;
	public static ForgeConfigSpec.IntValue DIAMOND_HAMMER;
	public static ForgeConfigSpec.IntValue HAND_SAW;
	
	public static void registerHammerConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
		COMMON_BUILDER.comment("Durability configuration for in-game items.").push("Hammers");
		IRON_HAMMER = COMMON_BUILDER
                .comment("Max durability for Iron Hammer")
                .defineInRange("durability", 128, 16, Integer.MAX_VALUE);
		
		STEEL_HAMMER = COMMON_BUILDER
                .comment("Max Durability for Steel Hammer")
                .defineInRange("durability", 224, 16, Integer.MAX_VALUE);
		
		DIAMOND_HAMMER = COMMON_BUILDER
                .comment("Max durability for Diamond Hammer")
                .defineInRange("durability", 469, 16, Integer.MAX_VALUE);
		COMMON_BUILDER.push("Hand Saw");
		
		HAND_SAW = COMMON_BUILDER
                .comment("Max durability for Hand Saw")
                .defineInRange("durability", 174, 16, Integer.MAX_VALUE);
	}
}
