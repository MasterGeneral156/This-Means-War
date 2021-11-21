package themastergeneral.thismeanswar.registry.world.gen;

import java.util.ArrayList;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;

@Mod.EventBusSubscriber
public class OreGenHandler {
	
	private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> netherOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> endOres = new ArrayList<ConfiguredFeature<?, ?>>();
 
    public static void registerOres(){
    	
        //Overworld Ore Register
        overworldOres.add(register("lead_ore_gen", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE, TMWBlocks.lead_ore.defaultBlockState(), 4)).range(64).squared().count(20)));
        
        overworldOres.add(register("brass_ore_gen", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE, TMWBlocks.brass_ore.defaultBlockState(), 12)).range(32).squared().count(5)));
 
        /*Nether Ore Register
        netherOres.add(register("example_nether_ore", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NETHERRACK, YOUR_ORE_BLOCK.getDefaultState(), 4)).range(64).squared().count(5);
 
        //The End Ore Register
        endOres.add(register("example_end_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                new BlockMatchRuleTest(Blocks.END_STONE), YOUR_ORE_BLOCK.getDefaultState(), 4)) //Vein Size
                .func_242733_d(128).func_242728_a() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency
        */
    }
 
    @SubscribeEvent
    protected static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if(event.getCategory().equals(Biome.Category.NETHER)){
            for(ConfiguredFeature<?, ?> ore : netherOres){
                if (ore != null) generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        if(event.getCategory().equals(Biome.Category.THEEND)){
            for(ConfiguredFeature<?, ?> ore : endOres){
                if (ore != null) generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        for(ConfiguredFeature<?, ?> ore : overworldOres){
            if (ore != null) generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
    }
 
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, TMWMain.MODID + ":" + name, configuredFeature);
    }
}
