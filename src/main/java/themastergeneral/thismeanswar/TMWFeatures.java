package themastergeneral.thismeanswar;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.ForgeRegistries;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWFeatures {
	//public static final ResourceKey<ConfiguredFeature<?, ?>> CUSTOM_ORE_KEY = ResourceKey.create((ResourceKey<? extends Registry<T>>) ForgeRegistries.FEATURES, new ResourceLocation(TMWMain.MODID, "custom_ore"));
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
	
		List<OreConfiguration.TargetBlockState> OVERWORLD_CUSTOM_ORES = List.of(
	            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), TMWBlocks.ore_brass.defaultBlockState())
	    );
		
		ConfiguredFeature<?, ?> customOreFeature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_CUSTOM_ORES, 9));
		//context.register(CUSTOM_ORE_KEY, customOreFeature);
	} 

}
