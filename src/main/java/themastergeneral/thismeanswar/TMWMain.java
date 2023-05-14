package themastergeneral.thismeanswar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.config.TMWConfig;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.define.TMWCarbines;
import themastergeneral.thismeanswar.items.define.TMWExplosiveProjectile;
import themastergeneral.thismeanswar.items.define.TMWPistols;
import themastergeneral.thismeanswar.items.define.TMWRifles;
import themastergeneral.thismeanswar.items.define.TMWShotguns;
import themastergeneral.thismeanswar.items.define.TMWThrowables;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWBlockRegistry;
import themastergeneral.thismeanswar.registry.TMWEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWItemRegistry;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

@Mod("thismeanswar")
public class TMWMain
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static String MODID = "thismeanswar";
    public static CreativeModeTab TMWTab;

    public TMWMain() {
    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modBus.addListener(this::setup);
        modBus.addListener(this::registerTabs);
        modBus.addListener(this::fillTab);
    	
    	DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(this::clientSetup));
    	
    	MinecraftForge.EVENT_BUS.register(this);
        TMWItemRegistry.ITEMS.register(modBus);
        TMWEntityRegistry.ENTITES.register(modBus);
        TMWBlockRegistry.BLOCKS.register(modBus);
        TMWBlockEntityRegistry.TILES.register(modBus);
        TMWRecipeTypeRegistration.RECIPE_SERIALIZER.register(modBus);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("This Means War, in active development.");
    }
    
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(final FMLClientSetupEvent event)
    {
    	LOGGER.info("Loading client-side Block Render layers.");
    	//TODO Fix this depre notice
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box_medium, RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.ammo_box_large, RenderType.translucent());
    }
    
    private void registerTabs(CreativeModeTabEvent.Register event)
    {
		TMWTab = event.registerCreativeModeTab(new ResourceLocation(MODID, "thismeanswar_tab"), builder -> builder
                .icon(() -> new ItemStack(TMWCarbines.tmg_carbine))
                .title(Component.translatable("itemGroup.thismeanswar"))
                .build());
    }
    
    private void fillTab(CreativeModeTabEvent.BuildContents ev)
	{
		if (ev.getTab() == TMWTab)
		{	
			ev.accept(TMWCarbines.tmg_carbine);
			ev.accept(TMWItems.magazine_9mm_large);
			ev.accept(TMWPistols.beretta_92_fs);
			ev.accept(TMWPistols.glock_26);
			ev.accept(TMWItems.magazine_9mm);
			ev.accept(TMWPistols.m1911);
			ev.accept(TMWItems.magazine_m1911);
			ev.accept(TMWRifles.springfield_saint_223);
			ev.accept(TMWItems.magazine_223);
			ev.accept(TMWRifles.springfield_saint_556);
			ev.accept(TMWItems.magazine_556);
			ev.accept(TMWShotguns.double_barrel_12g);
			ev.accept(TMWShotguns.remmington_m870);
			ev.accept(TMWShotguns.sawn_off_double_barrel_12g);
			ev.accept(TMWShotguns.sawn_off_remmington_m870);
			ev.accept(TMWExplosiveProjectile.bazooka);
			
			ev.accept(TMWItems.round_12g);
			ev.accept(TMWItems.round_223);
			ev.accept(TMWItems.round_556);
			ev.accept(TMWItems.round_45);
			ev.accept(TMWItems.round_9mm);
			ev.accept(TMWItems.rocket_bazooka);
			
			ev.accept(TMWThrowables.dynamite_stick);
			ev.accept(TMWThrowables.hand_grenade);
			ev.accept(TMWThrowables.nuclear_warhead);
			
			ev.accept(TMWItems.gun_rof_upgrade);
			ev.accept(TMWItems.gun_rof_downgrade);
			ev.accept(TMWItems.mag_capacity_upgrade);
			
			ev.accept(TMWItems.bandage);
			ev.accept(TMWItems.gauze);
			ev.accept(TMWItems.medic_kit);
			
			ev.accept(TMWItems.hammer_iron);
			ev.accept(TMWItems.hammer_steel);
			ev.accept(TMWItems.hammer_diamond);
			ev.accept(TMWItems.hammer_creative);
			ev.accept(TMWItems.hand_saw);
			
			ev.accept(TMWItems.bullet_cast_223);
			ev.accept(TMWItems.bullet_cast_556);
			ev.accept(TMWItems.bullet_cast_9mm);
			ev.accept(TMWItems.bullet_cast_45);
			
			ev.accept(TMWItems.bullet_tip_223);
			ev.accept(TMWItems.bullet_tip_556);
			ev.accept(TMWItems.bullet_tip_9mm);
			ev.accept(TMWItems.bullet_tip_45);
			ev.accept(TMWItems.bullet_tip_buckshot);
			
			ev.accept(TMWItems.casing_223);
			ev.accept(TMWItems.casing_556);
			ev.accept(TMWItems.casing_9mm);
			ev.accept(TMWItems.casing_45);
			ev.accept(TMWItems.casing_12g);
			
			ev.accept(TMWItems.primer_pistol);
			ev.accept(TMWItems.primer_rifle);
			ev.accept(TMWItems.primer_shotgun);
			
			ev.accept(TMWItems.plate_lead);
			
			ev.accept(TMWItems.kevlar_raw);
			
			ev.accept(TMWItems.ingot_brass);
			ev.accept(TMWItems.ingot_steel);
			ev.accept(TMWItems.ingot_lead);
			
			ev.accept(TMWItems.nugget_brass);
			ev.accept(TMWItems.nugget_steel);
			ev.accept(TMWItems.nugget_lead);
			
			ev.accept(TMWItems.dust_brass);
			ev.accept(TMWItems.dust_steel);
			ev.accept(TMWItems.dust_lead);
			ev.accept(TMWItems.dust_copper);
			ev.accept(TMWItems.dust_iron);
			ev.accept(TMWItems.dust_gold);
			
			ev.accept(TMWItems.ammo_box);
			ev.accept(TMWItems.ammo_box_medium);
			ev.accept(TMWItems.ammo_box_large);
			//ev.accept(TMWItems.crusher);
			
			ev.accept(TMWItems.block_brass);
			ev.accept(TMWItems.ore_brass);
			ev.accept(TMWItems.block_lead);
			ev.accept(TMWItems.ore_lead);
			ev.accept(TMWItems.block_steel);
			
			ev.accept(TMWItems.blue_stone_bricks);
			ev.accept(TMWItems.red_stone_bricks);
			ev.accept(TMWItems.blue_chiseled_bricks);
			ev.accept(TMWItems.red_chiseled_bricks);
			
			ev.accept(TMWItems.green_war_armor_helm);
			ev.accept(TMWItems.blue_war_armor_helm);
			ev.accept(TMWItems.red_war_armor_helm);
			//ev.accept(TMWItems.kevlar_helmet);
			
			ev.accept(TMWItems.green_war_armor_chest);
			ev.accept(TMWItems.blue_war_armor_chest);
			ev.accept(TMWItems.red_war_armor_chest);
			//ev.accept(TMWItems.kevlar_chest);
			
			ev.accept(TMWItems.green_war_armor_legs);
			ev.accept(TMWItems.blue_war_armor_legs);
			ev.accept(TMWItems.red_war_armor_legs);
			//ev.accept(TMWItems.kevlar_legs);
			
			ev.accept(TMWItems.green_war_armor_boots);
			ev.accept(TMWItems.blue_war_armor_boots);
			ev.accept(TMWItems.red_war_armor_boots);
			//ev.accept(TMWItems.kevlar_boots);
		}
	}
}
