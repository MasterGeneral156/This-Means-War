package themastergeneral.thismeanswar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themastergeneral.thismeanswar.block.TMWBlocks;
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
import themastergeneral.thismeanswar.registry.TMWSoundRegistry;

@Mod("thismeanswar")
public class TMWMain
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static String MODID = "thismeanswar";

    public TMWMain() {
    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modBus.addListener(this::setup);
        modBus.addListener(this::fillTab);
        modBus.addListener(this::clientSetup);
    	
    	DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(this::clientSetup));
    	
    	MinecraftForge.EVENT_BUS.register(this);
    	MinecraftForge.EVENT_BUS.register(new TMWEvents());
        TMWItemRegistry.ITEMS.register(modBus);
        TMWEntityRegistry.ENTITES.register(modBus);
        TMWBlockRegistry.BLOCKS.register(modBus);
        TMWBlockEntityRegistry.TILES.register(modBus);
        TMWRecipeTypeRegistration.RECIPE_SERIALIZER.register(modBus);
        TMWRecipeTypeRegistration.RECIPE_TYPES.register(modBus);
        TMWTabs.CREATIVE_MODE_TABS.register(modBus);
        TMWSoundRegistry.SOUNDS.register(modBus);
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
    	ItemBlockRenderTypes.setRenderLayer(TMWBlocks.medic_box, RenderType.translucent());
    }
    
    private void fillTab(BuildCreativeModeTabContentsEvent ev)
	{
    	
    	if (ev.getTab() == TMWTabs.GUN_TAB.get())
    	{
    		ev.accept(TMWPistols.beretta_92_fs);
			ev.accept(TMWPistols.glock_26);
			ev.accept(TMWPistols.m1911);
			ev.accept(TMWPistols.m17_viper);
			ev.accept(TMWPistols.mauser_c98);
			ev.accept(TMWPistols.vespera);
			ev.accept(TMWPistols.mcstubby);
    		
    		ev.accept(TMWCarbines.tmg_carbine);
    		ev.accept(TMWCarbines.uzi);
    		ev.accept(TMWCarbines.ump9);
    		ev.accept(TMWCarbines.g36);
    		ev.accept(TMWCarbines.mp40);
			
			ev.accept(TMWRifles.springfield_saint_223);
			ev.accept(TMWRifles.springfield_saint_556);
			ev.accept(TMWRifles.m16);
			ev.accept(TMWRifles.m16_223);
			ev.accept(TMWRifles.scar);
			
			ev.accept(TMWRifles.thunderclaw);
			ev.accept(TMWRifles.mosin_nagant);
			ev.accept(TMWRifles.dragunov);
			ev.accept(TMWRifles.k98);
			
			ev.accept(TMWRifles.quantum_disruptor);
			ev.accept(TMWRifles.bfg_8001);
			
			ev.accept(TMWShotguns.winchester);
			ev.accept(TMWShotguns.double_barrel_12g);
			ev.accept(TMWShotguns.remmington_m870);
			ev.accept(TMWShotguns.sawn_off_double_barrel_12g);
			ev.accept(TMWShotguns.sawn_off_remmington_m870);
			
			ev.accept(TMWExplosiveProjectile.bazooka);
			ev.accept(TMWExplosiveProjectile.volcanic_thunder);
			
			ev.accept(TMWItems.base_upgrade);
			ev.accept(TMWItems.mag_capacity_upgrade);
			ev.accept(TMWItems.gun_rof_upgrade);
			ev.accept(TMWItems.gun_rof_downgrade);
			ev.accept(TMWItems.bullet_upgrade_ap);
			ev.accept(TMWItems.bullet_upgrade_fire);
			ev.accept(TMWItems.bullet_upgrade_tracer);
			ev.accept(TMWItems.bullet_upgrade_inert);
			ev.accept(TMWItems.bullet_upgrade_normal);
			ev.accept(TMWItems.bayonet_wood);
			ev.accept(TMWItems.bayonet_stone);
			ev.accept(TMWItems.bayonet_iron);
			ev.accept(TMWItems.bayonet_gold);
			ev.accept(TMWItems.bayonet_diamond);
			ev.accept(TMWItems.bayonet_netherrite);
    	}
    	if (ev.getTab() == TMWTabs.AMMO_TAB.get())
    	{
    		ev.accept(TMWItems.round_12g);
			ev.accept(TMWItems.round_223);
			ev.accept(TMWItems.round_556);
			ev.accept(TMWItems.round_762);
			ev.accept(TMWItems.round_45);
			ev.accept(TMWItems.round_9mm);
			ev.accept(TMWItems.round_38spec);
			ev.accept(TMWItems.rocket_bazooka);
			ev.accept(TMWItems.round_40mm);
			ev.accept(TMWItems.energy_bolt);
			
			ev.accept(TMWItems.magazine_9mm);
			ev.accept(TMWItems.magazine_9mm_clip);
			ev.accept(TMWItems.magazine_9mm_large);
			ev.accept(TMWItems.magazine_9mm_short);
			ev.accept(TMWItems.magazine_9mm_drum);
			
			ev.accept(TMWItems.magazine_m1911);
			ev.accept(TMWItems.magazine_g36);
			ev.accept(TMWItems.energy_cell);
			
			ev.accept(TMWItems.magazine_223);
			ev.accept(TMWItems.magazine_223_clip);
			
			ev.accept(TMWItems.magazine_556);
			ev.accept(TMWItems.magazine_556_clip);
			
			ev.accept(TMWItems.magazine_dragunov);
			
			ev.accept(TMWItems.magazine_12g_clip);
			ev.accept(TMWItems.magazine_38spec_clip);
			ev.accept(TMWItems.magazine_45_clip);
			ev.accept(TMWItems.magazine_40mm_clip);
			
			ev.accept(TMWItems.base_upgrade);
			
			ev.accept(TMWItems.mag_capacity_upgrade);
    	}
		if (ev.getTab() == TMWTabs.MAIN_TAB.get())
		{	
			ev.accept(TMWItems.ammo_box);
			ev.accept(TMWItems.ammo_box_medium);
			ev.accept(TMWItems.ammo_box_large);
			ev.accept(TMWItems.medic_box);
			ev.accept(TMWItems.medic_box_medium);
			ev.accept(TMWItems.medic_box_large);
			//ev.accept(TMWItems.crusher);
			ev.accept(TMWItems.bullet_foundary);
			
			ev.accept(TMWThrowables.dynamite_stick);
			ev.accept(TMWThrowables.hand_grenade);
			ev.accept(TMWThrowables.stick_grenade);
			ev.accept(TMWThrowables.nuclear_warhead);
			ev.accept(TMWThrowables.smoke_grenade_green);
			ev.accept(TMWThrowables.smoke_grenade_red);
			ev.accept(TMWThrowables.smoke_grenade_orange);
			
			ev.accept(TMWItems.creative_charm);
			
			ev.accept(TMWItems.bandage);
			ev.accept(TMWItems.gauze);
			ev.accept(TMWItems.medic_kit);
			
			ev.accept(TMWItems.hammer_iron);
			ev.accept(TMWItems.hammer_steel);
			ev.accept(TMWItems.hammer_diamond);
			ev.accept(TMWItems.hand_saw);
			
			ev.accept(TMWItems.bullet_cast_223);
			ev.accept(TMWItems.bullet_cast_556);
			ev.accept(TMWItems.bullet_cast_9mm);
			ev.accept(TMWItems.bullet_cast_45);
			ev.accept(TMWItems.bullet_cast_38spec);
			ev.accept(TMWItems.bullet_cast_762);
			ev.accept(TMWItems.bullet_cast_12g);
			
			ev.accept(TMWItems.bullet_tip_223);
			ev.accept(TMWItems.bullet_tip_556);
			ev.accept(TMWItems.bullet_tip_9mm);
			ev.accept(TMWItems.bullet_tip_45);
			ev.accept(TMWItems.bullet_tip_38spec);
			ev.accept(TMWItems.bullet_tip_762);
			ev.accept(TMWItems.bullet_tip_buckshot);
			
			ev.accept(TMWItems.casing_cast_223);
			ev.accept(TMWItems.casing_cast_556);
			ev.accept(TMWItems.casing_cast_9mm);
			ev.accept(TMWItems.casing_cast_45);
			ev.accept(TMWItems.casing_cast_38spec);
			ev.accept(TMWItems.casing_cast_762);
			ev.accept(TMWItems.casing_cast_12g);
			
			ev.accept(TMWItems.casing_223);
			ev.accept(TMWItems.casing_556);
			ev.accept(TMWItems.casing_9mm);
			ev.accept(TMWItems.casing_45);
			ev.accept(TMWItems.casing_38spec);
			ev.accept(TMWItems.casing_762);
			ev.accept(TMWItems.casing_12g);
			
			ev.accept(TMWItems.primer_pistol);
			ev.accept(TMWItems.primer_rifle);
			ev.accept(TMWItems.primer_shotgun);
			
			ev.accept(TMWItems.plate_lead);
			ev.accept(TMWItems.plate_steel);
			
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
			
			ev.accept(TMWItems.block_brass);
			ev.accept(TMWItems.ore_brass);
			ev.accept(TMWItems.block_lead);
			ev.accept(TMWItems.ore_lead);
			ev.accept(TMWItems.block_steel);
			
			ev.accept(TMWItems.blue_stone_bricks);
			ev.accept(TMWItems.red_stone_bricks);
			ev.accept(TMWItems.blue_chiseled_bricks);
			ev.accept(TMWItems.red_chiseled_bricks);
		}
		
		if (ev.getTab() == TMWTabs.ARMOR_TAB.get())
		{
			ev.accept(TMWItems.basic_prot_goggles);
			ev.accept(TMWItems.basic_prot_chest);
			
			ev.accept(TMWItems.green_war_armor_helm);
			ev.accept(TMWItems.green_war_armor_chest);
			ev.accept(TMWItems.green_war_armor_legs);
			ev.accept(TMWItems.green_war_armor_boots);
			
			ev.accept(TMWItems.blue_war_armor_helm);
			ev.accept(TMWItems.blue_war_armor_chest);
			ev.accept(TMWItems.blue_war_armor_legs);
			ev.accept(TMWItems.blue_war_armor_boots);
			
			ev.accept(TMWItems.red_war_armor_helm);
			ev.accept(TMWItems.red_war_armor_chest);
			ev.accept(TMWItems.red_war_armor_legs);
			ev.accept(TMWItems.red_war_armor_boots);
			
			ev.accept(TMWItems.kevlar_helmet);
			ev.accept(TMWItems.kevlar_chest);
			ev.accept(TMWItems.kevlar_boots);
			ev.accept(TMWItems.kevlar_legs);
			
			ev.accept(TMWItems.ww2_russian_helm);
			ev.accept(TMWItems.ww2_russian_chest);
			ev.accept(TMWItems.ww2_russian_legs);
			ev.accept(TMWItems.ww2_russian_boots);
			
			ev.accept(TMWItems.swat_helm);
			ev.accept(TMWItems.swat_chest);
			ev.accept(TMWItems.swat_legs);
			ev.accept(TMWItems.swat_boots);
			
			ev.accept(TMWItems.ww2_british_helm);
			ev.accept(TMWItems.ww2_british_chest);
			ev.accept(TMWItems.ww2_british_legs);
			ev.accept(TMWItems.ww2_british_boots);
		}
	}
}
