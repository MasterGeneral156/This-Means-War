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
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.ForgeRegistries;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWFeatures {

		public static final ConfiguredFeature<?, ?> ORE_BRASS = Feature.ORE
				.configuredCodec(new OreConfiguration(
						OreConfiguration.target(RuleTest),
						TMWBlocks.ore_brass.defaultBlockState(),
						9));
	} 

}
