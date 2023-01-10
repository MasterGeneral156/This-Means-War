package themastergeneral.thismeanswar.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class OreGen {

	public static Holder<PlacedFeature> BRASS_OREGEN;
	public static Holder<PlacedFeature> LEAD_OREGEN;
	
	public static void registerOreFeatures() {
        OreConfiguration brassConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, TMWBlocks.ore_brass.defaultBlockState(), 20);
        OreConfiguration leadConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, TMWBlocks.ore_lead.defaultBlockState(), 20);
        
        BRASS_OREGEN = registerPlacedOreFeature("overworld_brass_gen", new ConfiguredFeature<>(Feature.ORE, brassConfig),
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75)));
        
        LEAD_OREGEN = registerPlacedOreFeature("overworld_lead_gen", new ConfiguredFeature<>(Feature.ORE, leadConfig),
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75)));
    }
	
	private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedOreFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }
	
	@SubscribeEvent
	public static void onBiomeLoadingEvent(BiomeLoadingEvent event) 
	{
        if (event.getCategory() == Biome.BiomeCategory.THEEND) 
        {
        } 
        else if (event.getCategory() == Biome.BiomeCategory.NETHER) 
        {
        } else 
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BRASS_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, LEAD_OREGEN);
        }
    }
}
