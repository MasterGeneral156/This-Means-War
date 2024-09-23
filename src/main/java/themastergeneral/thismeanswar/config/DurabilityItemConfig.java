package themastergeneral.thismeanswar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DurabilityItemConfig {
	public static ForgeConfigSpec.IntValue WOODEN_TOOL;
	public static ForgeConfigSpec.IntValue STONE_TOOL;
	public static ForgeConfigSpec.IntValue GOLD_TOOL;
	public static ForgeConfigSpec.IntValue IRON_TOOL;
	public static ForgeConfigSpec.IntValue STEEL_TOOL;
	public static ForgeConfigSpec.IntValue DIAMOND_TOOL;
	public static ForgeConfigSpec.IntValue EMERALD_TOOL;
	public static ForgeConfigSpec.IntValue NETHERITE_TOOL;
	public static ForgeConfigSpec.IntValue HAND_SAW;
	
	public static void registerHammerConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
		COMMON_BUILDER
				.push("This Means War Tool Durability")
				.comment("Durability configuration for in-game items.");
		WOODEN_TOOL =
				COMMON_BUILDER
					.comment("Max durability for wooden tools provided from the mod")
					.defineInRange("wooden_durability", 59, 1, Integer.MAX_VALUE);
		STONE_TOOL =
				COMMON_BUILDER
						.comment("Max durability for stone tools provided from the mod")
						.defineInRange("stone_durability", 131, 1, Integer.MAX_VALUE);
		GOLD_TOOL =
				COMMON_BUILDER
						.comment("Max durability for golden tools provided from the mod")
						.defineInRange("golden_durability", 25, 1, Integer.MAX_VALUE);
		IRON_TOOL =
				COMMON_BUILDER
                	.comment("Max durability for iron tools provided from the mod")
                	.defineInRange("iron_durability", 128, 1, Integer.MAX_VALUE);
		STEEL_TOOL =
				COMMON_BUILDER
					.comment("Max durability for steel tools provided from the mod")
					.defineInRange("steel_durability", 993, 1, Integer.MAX_VALUE);
		DIAMOND_TOOL =
				COMMON_BUILDER
					.comment("Max durability for diamond tools provided from the mod")
					.defineInRange("diamond_durability", 1561, 1, Integer.MAX_VALUE);
		EMERALD_TOOL =
				COMMON_BUILDER
						.comment("Max durability for emerald tools provided from the mod")
						.defineInRange("emerald_durability", 1745, 1, Integer.MAX_VALUE);
		NETHERITE_TOOL =
				COMMON_BUILDER
						.comment("Max durability for netherite tools provided from the mod")
						.defineInRange("netherite_durability", 2031, 1, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}
}
