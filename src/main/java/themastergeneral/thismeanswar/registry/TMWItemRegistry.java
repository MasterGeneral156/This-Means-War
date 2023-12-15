package themastergeneral.thismeanswar.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.define.TMWCarbines;
import themastergeneral.thismeanswar.items.define.TMWExplosiveProjectile;
import themastergeneral.thismeanswar.items.define.TMWPistols;
import themastergeneral.thismeanswar.items.define.TMWRifles;
import themastergeneral.thismeanswar.items.define.TMWShotguns;
import themastergeneral.thismeanswar.items.define.TMWThrowables;

public class TMWItemRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TMWMain.MODID);
	
	//Rounds
	public static final RegistryObject<Item> round_9mm = ITEMS.register("round_9mm", () -> TMWItems.round_9mm);
	public static final RegistryObject<Item> round_12g = ITEMS.register("round_12g", () -> TMWItems.round_12g);
	public static final RegistryObject<Item> round_556 = ITEMS.register("round_556", () -> TMWItems.round_556);
	public static final RegistryObject<Item> round_223 = ITEMS.register("round_223", () -> TMWItems.round_223);
	public static final RegistryObject<Item> round_45 = ITEMS.register("round_45", () -> TMWItems.round_45);
	public static final RegistryObject<Item> round_38spec = ITEMS.register("round_38spec", () -> TMWItems.round_38spec);
	public static final RegistryObject<Item> rocket_bazooka = ITEMS.register("rocket_bazooka", () -> TMWItems.rocket_bazooka);
	public static final RegistryObject<Item> round_40mm = ITEMS.register("round_40mm", () -> TMWItems.round_40mm);
	public static final RegistryObject<Item> energy_bolt = ITEMS.register("energy_bolt", () -> TMWItems.energy_bolt);
	
	//Mags
	public static final RegistryObject<Item> magazine_9mm = ITEMS.register("magazine_9mm", () -> TMWItems.magazine_9mm);
	public static final RegistryObject<Item> magazine_9mm_large = ITEMS.register("magazine_9mm_large", () -> TMWItems.magazine_9mm_large);
	public static final RegistryObject<Item> magazine_9mm_short = ITEMS.register("magazine_9mm_short", () -> TMWItems.magazine_9mm_short);
	public static final RegistryObject<Item> magazine_9mm_drum = ITEMS.register("magazine_9mm_drum", () -> TMWItems.magazine_9mm_drum);
	public static final RegistryObject<Item> magazine_9mm_clip = ITEMS.register("magazine_9mm_clip", () -> TMWItems.magazine_9mm_clip);
	
	public static final RegistryObject<Item> magazine_556 = ITEMS.register("magazine_556", () -> TMWItems.magazine_556);
	public static final RegistryObject<Item> magazine_223 = ITEMS.register("magazine_223", () -> TMWItems.magazine_223);
	public static final RegistryObject<Item> magazine_m1911 = ITEMS.register("magazine_m1911", () -> TMWItems.magazine_m1911);
	public static final RegistryObject<Item> magazine_556_clip = ITEMS.register("magazine_556_clip", () -> TMWItems.magazine_556_clip);
	public static final RegistryObject<Item> magazine_223_clip = ITEMS.register("magazine_223_clip", () -> TMWItems.magazine_223_clip);
	public static final RegistryObject<Item> magazine_45_clip = ITEMS.register("magazine_45_clip", () -> TMWItems.magazine_45_clip);
	public static final RegistryObject<Item> magazine_12g_clip = ITEMS.register("magazine_12g_clip", () -> TMWItems.magazine_12g_clip);
	public static final RegistryObject<Item> magazine_40mm_clip = ITEMS.register("magazine_40mm_clip", () -> TMWItems.magazine_40mm_clip);
	public static final RegistryObject<Item> magazine_g36 = ITEMS.register("magazine_g36", () -> TMWItems.magazine_g36);
	public static final RegistryObject<Item> energy_cell = ITEMS.register("energy_cell", () -> TMWItems.energy_cell);
	
	/**
	 * Firearms
	 */
	//Pistols
	public static final RegistryObject<Item> beretta_92_fs = ITEMS.register("beretta_92_fs", () -> TMWPistols.beretta_92_fs);
	public static final RegistryObject<Item> glock_26 = ITEMS.register("glock_26", () -> TMWPistols.glock_26);
	public static final RegistryObject<Item> m1911 = ITEMS.register("m1911", () -> TMWPistols.m1911);
	public static final RegistryObject<Item> m17_viper = ITEMS.register("m17_viper", () -> TMWPistols.m17_viper);
	public static final RegistryObject<Item> mauser_c98 = ITEMS.register("mauser_c98", () -> TMWPistols.mauser_c98);
	public static final RegistryObject<Item> vespera = ITEMS.register("vespera", () -> TMWPistols.vespera);
	
	//Carbines
	public static final RegistryObject<Item> tmg_carbine = ITEMS.register("tmg_carbine", () -> TMWCarbines.tmg_carbine);
	public static final RegistryObject<Item> ump9 = ITEMS.register("ump9", () -> TMWCarbines.ump9);
	public static final RegistryObject<Item> uzi = ITEMS.register("uzi", () -> TMWCarbines.uzi);
	public static final RegistryObject<Item> g36 = ITEMS.register("g36", () -> TMWCarbines.g36);
	
	//Rifles
	public static final RegistryObject<Item> springfield_saint_556 = ITEMS.register("springfield_saint_556", () -> TMWRifles.springfield_saint_556);
	public static final RegistryObject<Item> springfield_saint_223 = ITEMS.register("springfield_saint_223", () -> TMWRifles.springfield_saint_223);
	public static final RegistryObject<Item> m16 = ITEMS.register("m16", () -> TMWRifles.m16);
	public static final RegistryObject<Item> m16_223 = ITEMS.register("m16_223", () -> TMWRifles.m16_223);
	public static final RegistryObject<Item> quantum_disruptor = ITEMS.register("quantum_disruptor", () -> TMWRifles.quantum_disruptor);
	public static final RegistryObject<Item> bfg_8001 = ITEMS.register("bfg_8001", () -> TMWRifles.bfg_8001);
	public static final RegistryObject<Item> scar = ITEMS.register("scar", () -> TMWRifles.scar);
	
	//Snipers
	public static final RegistryObject<Item> thunderclaw = ITEMS.register("thunderclaw", () -> TMWRifles.thunderclaw);
	public static final RegistryObject<Item> mosin_nagant = ITEMS.register("mosin_nagant", () -> TMWRifles.mosin_nagant);
	
	//Shotguns
	public static final RegistryObject<Item> remmington_m870 = ITEMS.register("remmington_m870", () -> TMWShotguns.remmington_m870);
	public static final RegistryObject<Item> sawn_off_remmington_m870 = ITEMS.register("sawn_off_remmington_m870", () -> TMWShotguns.sawn_off_remmington_m870);
	public static final RegistryObject<Item> sawn_off_double_barrel_12g = ITEMS.register("sawn_off_double_barrel_12g", () -> TMWShotguns.sawn_off_double_barrel_12g);
	public static final RegistryObject<Item> double_barrel_12g = ITEMS.register("double_barrel_12g", () -> TMWShotguns.double_barrel_12g);
	
	//RPGs
	public static final RegistryObject<Item> bazooka = ITEMS.register("bazooka", () -> TMWExplosiveProjectile.bazooka);
	public static final RegistryObject<Item> volcanic_thunder = ITEMS.register("volcanic_thunder", () -> TMWExplosiveProjectile.volcanic_thunder);
	
	/**
	 * Armor
	 */
	//Kevlar
	public static final RegistryObject<Item> kevlar_helmet = ITEMS.register("kevlar_helmet", () -> TMWItems.kevlar_helmet);
	public static final RegistryObject<Item> kevlar_chest = ITEMS.register("kevlar_chest", () -> TMWItems.kevlar_chest);
	public static final RegistryObject<Item> kevlar_legs = ITEMS.register("kevlar_legs", () -> TMWItems.kevlar_legs);
	public static final RegistryObject<Item> kevlar_boots = ITEMS.register("kevlar_boots", () -> TMWItems.kevlar_boots);
	
	//SWAT Armors
	public static final RegistryObject<Item> swat_helmet = ITEMS.register("swat_helm", () -> TMWItems.swat_helm);
	public static final RegistryObject<Item> swat_chest = ITEMS.register("swat_chest", () -> TMWItems.swat_chest);
	public static final RegistryObject<Item> swat_legs = ITEMS.register("swat_legs", () -> TMWItems.swat_legs);
	public static final RegistryObject<Item> swat_boots = ITEMS.register("swat_boots", () -> TMWItems.swat_boots);
	
	//WW2 ARmors
	//Russian WW2 Armor
	public static final RegistryObject<Item> ww2_russian_helmet = ITEMS.register("ww2_russian_helm", () -> TMWItems.ww2_russian_helm);
	public static final RegistryObject<Item> ww2_russian_chest = ITEMS.register("ww2_russian_chest", () -> TMWItems.ww2_russian_chest);
	public static final RegistryObject<Item> ww2_russian_legs = ITEMS.register("ww2_russian_legs", () -> TMWItems.ww2_russian_legs);
	public static final RegistryObject<Item> ww2_russian_boots = ITEMS.register("ww2_russian_boots", () -> TMWItems.ww2_russian_boots);
	
	//British WW2 Armor
	public static final RegistryObject<Item> ww2_british_helmet = ITEMS.register("ww2_british_helm", () -> TMWItems.ww2_british_helm);
	public static final RegistryObject<Item> ww2_british_chest = ITEMS.register("ww2_british_chest", () -> TMWItems.ww2_british_chest);
	public static final RegistryObject<Item> ww2_british_legs = ITEMS.register("ww2_british_legs", () -> TMWItems.ww2_british_legs);
	public static final RegistryObject<Item> ww2_british_boots = ITEMS.register("ww2_british_boots", () -> TMWItems.ww2_british_boots);
	
	//green war armor
	public static final RegistryObject<Item> green_war_armor_helm = ITEMS.register("green_war_armor_helm", () -> TMWItems.green_war_armor_helm);
	public static final RegistryObject<Item> green_war_armor_chest = ITEMS.register("green_war_armor_chest", () -> TMWItems.green_war_armor_chest);
	public static final RegistryObject<Item> green_war_armor_legs = ITEMS.register("green_war_armor_legs", () -> TMWItems.green_war_armor_legs);
	public static final RegistryObject<Item> green_war_armor_boots = ITEMS.register("green_war_armor_boots", () -> TMWItems.green_war_armor_boots);
	
	//red war armor
	public static final RegistryObject<Item> red_war_armor_helm = ITEMS.register("red_war_armor_helm", () -> TMWItems.red_war_armor_helm);
	public static final RegistryObject<Item> red_war_armor_chest = ITEMS.register("red_war_armor_chest", () -> TMWItems.red_war_armor_chest);
	public static final RegistryObject<Item> red_war_armor_legs = ITEMS.register("red_war_armor_legs", () -> TMWItems.red_war_armor_legs);
	public static final RegistryObject<Item> red_war_armor_boots = ITEMS.register("red_war_armor_boots", () -> TMWItems.red_war_armor_boots);
		
	//blue war armor
	public static final RegistryObject<Item> blue_war_armor_helm = ITEMS.register("blue_war_armor_helm", () -> TMWItems.blue_war_armor_helm);
	public static final RegistryObject<Item> blue_war_armor_chest = ITEMS.register("blue_war_armor_chest", () -> TMWItems.blue_war_armor_chest);
	public static final RegistryObject<Item> blue_war_armor_legs = ITEMS.register("blue_war_armor_legs", () -> TMWItems.blue_war_armor_legs);
	public static final RegistryObject<Item> blue_war_armor_boots = ITEMS.register("blue_war_armor_boots", () -> TMWItems.blue_war_armor_boots);
	
	//Misc Armor
	public static final RegistryObject<Item> basic_prot_goggles = ITEMS.register("basic_prot_goggles", () -> TMWItems.basic_prot_goggles);
	public static final RegistryObject<Item> basic_prot_chest = ITEMS.register("basic_prot_chest", () -> TMWItems.basic_prot_chest);
	
	/**
	 * Throwables 
	 */
	//Grenades
	public static final RegistryObject<Item> dynamite_stick = ITEMS.register("dynamite_stick", () -> TMWThrowables.dynamite_stick);
	public static final RegistryObject<Item> nuclear_warhead = ITEMS.register("nuclear_warhead", () -> TMWThrowables.nuclear_warhead);
	public static final RegistryObject<Item> hand_grenade = ITEMS.register("hand_grenade", () -> TMWThrowables.hand_grenade);
	public static final RegistryObject<Item> smoke_grenade_red = ITEMS.register("smoke_grenade_red", () -> TMWThrowables.smoke_grenade_red);
	public static final RegistryObject<Item> smoke_grenade_green = ITEMS.register("smoke_grenade_green", () -> TMWThrowables.smoke_grenade_green);
	public static final RegistryObject<Item> smoke_grenade_orange = ITEMS.register("smoke_grenade_orange", () -> TMWThrowables.smoke_grenade_orange);
	
	/**
	 * Other Usable Items
	 */
	
	//Medical
	public static final RegistryObject<Item> bandage = ITEMS.register("bandage", () -> TMWItems.bandage);
	public static final RegistryObject<Item> gauze = ITEMS.register("gauze", () -> TMWItems.gauze);
	public static final RegistryObject<Item> medic_kit = ITEMS.register("medic_kit", () -> TMWItems.medic_kit);
	
	//Upgrades
	public static final RegistryObject<Item> mag_capacity_upgrade = ITEMS.register("mag_capacity_upgrade", () -> TMWItems.mag_capacity_upgrade);
	public static final RegistryObject<Item> gun_rof_upgrade = ITEMS.register("gun_rof_upgrade", () -> TMWItems.gun_rof_upgrade);
	public static final RegistryObject<Item> gun_rof_downgrade = ITEMS.register("gun_rof_downgrade", () -> TMWItems.gun_rof_downgrade);
	public static final RegistryObject<Item> creative_charm = ITEMS.register("creative_charm", () -> TMWItems.creative_charm);
	public static final RegistryObject<Item> base_upgrade = ITEMS.register("base_upgrade", () -> TMWItems.base_upgrade);
	public static final RegistryObject<Item> bullet_upgrade_ap = ITEMS.register("bullet_upgrade_ap", () -> TMWItems.bullet_upgrade_ap);
	public static final RegistryObject<Item> bullet_upgrade_normal = ITEMS.register("bullet_upgrade_normal", () -> TMWItems.bullet_upgrade_normal);
	public static final RegistryObject<Item> bullet_upgrade_fire = ITEMS.register("bullet_upgrade_fire", () -> TMWItems.bullet_upgrade_fire);
	public static final RegistryObject<Item> bullet_upgrade_tracer = ITEMS.register("bullet_upgrade_tracer", () -> TMWItems.bullet_upgrade_tracer);
	public static final RegistryObject<Item> bullet_upgrade_inert = ITEMS.register("bullet_upgrade_inert", () -> TMWItems.bullet_upgrade_inert);
	
	//Charms
	//public static final RegistryObject<Item> charm_stealth = ITEMS.register("charm_stealth", () -> TMWItems.charm_stealth);
	
	/**
	 * Crafting Items
	 */
	
	//Lead
	public static final RegistryObject<Item> plate_lead = ITEMS.register("plate_lead", () -> TMWItems.plate_lead);
	public static final RegistryObject<Item> ingot_lead = ITEMS.register("ingot_lead", () -> TMWItems.ingot_lead);
	public static final RegistryObject<Item> nugget_lead = ITEMS.register("nugget_lead", () -> TMWItems.nugget_lead);
	public static final RegistryObject<Item> dust_lead = ITEMS.register("dust_lead", () -> TMWItems.dust_lead);
	
	//Brass
	public static final RegistryObject<Item> ingot_brass = ITEMS.register("ingot_brass", () -> TMWItems.ingot_brass);
	public static final RegistryObject<Item> nugget_brass = ITEMS.register("nugget_brass", () -> TMWItems.nugget_brass);
	public static final RegistryObject<Item> dust_brass = ITEMS.register("dust_brass", () -> TMWItems.dust_brass);
	
	//Steel
	public static final RegistryObject<Item> ingot_steel = ITEMS.register("ingot_steel", () -> TMWItems.ingot_steel);
	public static final RegistryObject<Item> nugget_steel = ITEMS.register("nugget_steel", () -> TMWItems.nugget_steel);
	public static final RegistryObject<Item> dust_steel = ITEMS.register("dust_steel", () -> TMWItems.dust_steel);
	public static final RegistryObject<Item> plate_steel = ITEMS.register("plate_steel", () -> TMWItems.plate_steel);
	
	//Vanilla dusts
	public static final RegistryObject<Item> dust_copper = ITEMS.register("dust_copper", () -> TMWItems.dust_copper);
	public static final RegistryObject<Item> dust_iron = ITEMS.register("dust_iron", () -> TMWItems.dust_iron);
	public static final RegistryObject<Item> dust_gold = ITEMS.register("dust_gold", () -> TMWItems.dust_gold);
	
	//Misc
	public static final RegistryObject<Item> kevlar_raw = ITEMS.register("kevlar_raw", () -> TMWItems.kevlar_raw);
	
	//Durability crafting items
	public static final RegistryObject<Item> hammer_iron = ITEMS.register("hammer_iron", () -> TMWItems.hammer_iron);
	public static final RegistryObject<Item> hammer_diamond = ITEMS.register("hammer_diamond", () -> TMWItems.hammer_diamond);
	public static final RegistryObject<Item> hammer_steel = ITEMS.register("hammer_steel", () -> TMWItems.hammer_steel);
	public static final RegistryObject<Item> hand_saw = ITEMS.register("hand_saw", () -> TMWItems.hand_saw);
	
	//Bullet casts
	public static final RegistryObject<Item> bullet_cast_9mm = ITEMS.register("bullet_cast_9mm", () -> TMWItems.bullet_cast_9mm);
	public static final RegistryObject<Item> bullet_cast_556 = ITEMS.register("bullet_cast_556", () -> TMWItems.bullet_cast_556);
	public static final RegistryObject<Item> bullet_cast_223 = ITEMS.register("bullet_cast_223", () -> TMWItems.bullet_cast_223);
	public static final RegistryObject<Item> bullet_cast_45 = ITEMS.register("bullet_cast_45", () -> TMWItems.bullet_cast_45);
	public static final RegistryObject<Item> bullet_cast_38spec = ITEMS.register("bullet_cast_38spec", () -> TMWItems.bullet_cast_38spec);
	public static final RegistryObject<Item> bullet_cast_12g = ITEMS.register("bullet_cast_12g", () -> TMWItems.bullet_cast_12g);
	
	//Casing casts
	public static final RegistryObject<Item> casing_cast_9mm = ITEMS.register("casing_cast_9mm", () -> TMWItems.casing_cast_9mm);
	public static final RegistryObject<Item> casing_cast_556 = ITEMS.register("casing_cast_556", () -> TMWItems.casing_cast_556);
	public static final RegistryObject<Item> casing_cast_223 = ITEMS.register("casing_cast_223", () -> TMWItems.casing_cast_223);
	public static final RegistryObject<Item> casing_cast_45 = ITEMS.register("casing_cast_45", () -> TMWItems.casing_cast_45);
	public static final RegistryObject<Item> casing_cast_38spec = ITEMS.register("casing_cast_38spec", () -> TMWItems.casing_cast_38spec);
	public static final RegistryObject<Item> casing_cast_12g = ITEMS.register("casing_cast_12g", () -> TMWItems.casing_cast_12g);
	
	//Bullet Casings
	public static final RegistryObject<Item> casing_9mm = ITEMS.register("casing_9mm", () -> TMWItems.casing_9mm);
	public static final RegistryObject<Item> casing_223 = ITEMS.register("casing_223", () -> TMWItems.casing_223);
	public static final RegistryObject<Item> casing_556 = ITEMS.register("casing_556", () -> TMWItems.casing_556);
	public static final RegistryObject<Item> casing_12g = ITEMS.register("casing_12g", () -> TMWItems.casing_12g);
	public static final RegistryObject<Item> casing_38spec = ITEMS.register("casing_38spec", () -> TMWItems.casing_38spec);
	public static final RegistryObject<Item> casing_45 = ITEMS.register("casing_45", () -> TMWItems.casing_45);
	
	//Bayonet Upgrade
	public static final RegistryObject<Item> bayonet_wood = ITEMS.register("bayonet_wood", () -> TMWItems.bayonet_wood);
	public static final RegistryObject<Item> bayonet_stone = ITEMS.register("bayonet_stone", () -> TMWItems.bayonet_stone);
	public static final RegistryObject<Item> bayonet_iron = ITEMS.register("bayonet_iron", () -> TMWItems.bayonet_iron);
	public static final RegistryObject<Item> bayonet_gold = ITEMS.register("bayonet_gold", () -> TMWItems.bayonet_gold);
	public static final RegistryObject<Item> bayonet_diamond = ITEMS.register("bayonet_diamond", () -> TMWItems.bayonet_diamond);
	public static final RegistryObject<Item> bayonet_netherrite = ITEMS.register("bayonet_netherrite", () -> TMWItems.bayonet_netherrite);
	
	//Primers
	public static final RegistryObject<Item> primer_pistol = ITEMS.register("primer_pistol", () -> TMWItems.primer_pistol);
	public static final RegistryObject<Item> primer_rifle = ITEMS.register("primer_rifle", () -> TMWItems.primer_rifle);
	public static final RegistryObject<Item> primer_shotgun = ITEMS.register("primer_shotgun", () -> TMWItems.primer_shotgun);
	
	//Bullet tips
	public static final RegistryObject<Item> bullet_tip_9mm = ITEMS.register("bullet_tip_9mm", () -> TMWItems.bullet_tip_9mm);
	public static final RegistryObject<Item> bullet_tip_223 = ITEMS.register("bullet_tip_223", () -> TMWItems.bullet_tip_223);
	public static final RegistryObject<Item> bullet_tip_556 = ITEMS.register("bullet_tip_556", () -> TMWItems.bullet_tip_556);
	public static final RegistryObject<Item> bullet_tip_38spec = ITEMS.register("bullet_tip_38spec", () -> TMWItems.bullet_tip_38spec);
	public static final RegistryObject<Item> bullet_tip_buckshot = ITEMS.register("bullet_tip_buckshot", () -> TMWItems.bullet_tip_buckshot);
	public static final RegistryObject<Item> bullet_tip_45 = ITEMS.register("bullet_tip_45", () -> TMWItems.bullet_tip_45);
	
	/**
	 * Blocks
	 */
	
	//Ores
	public static final RegistryObject<Item> ore_lead = ITEMS.register("ore_lead", () -> TMWItems.ore_lead);
	public static final RegistryObject<Item> ore_brass = ITEMS.register("ore_brass", () -> TMWItems.ore_brass);
	
	//Metal Blocks
	public static final RegistryObject<Item> block_lead = ITEMS.register("block_lead", () -> TMWItems.block_lead);
	public static final RegistryObject<Item> block_brass = ITEMS.register("block_brass", () -> TMWItems.block_brass);
	public static final RegistryObject<Item> block_steel = ITEMS.register("block_steel", () -> TMWItems.block_steel);
	
	//Misc Blocks
	public static final RegistryObject<Item> red_stone_bricks = ITEMS.register("red_stone_bricks", () -> TMWItems.red_stone_bricks);
	public static final RegistryObject<Item> blue_stone_bricks = ITEMS.register("blue_stone_bricks", () -> TMWItems.blue_stone_bricks);
	public static final RegistryObject<Item> red_chiseled_bricks = ITEMS.register("red_chiseled_bricks", () -> TMWItems.red_chiseled_bricks);
	public static final RegistryObject<Item> blue_chiseled_bricks = ITEMS.register("blue_chiseled_bricks", () -> TMWItems.blue_chiseled_bricks);
	
	//Ammo Box
	public static final RegistryObject<Item> ammo_box = ITEMS.register("ammo_box", () -> TMWItems.ammo_box);
	public static final RegistryObject<Item> ammo_box_medium = ITEMS.register("ammo_box_medium", () -> TMWItems.ammo_box_medium);
	public static final RegistryObject<Item> ammo_box_large = ITEMS.register("ammo_box_large", () -> TMWItems.ammo_box_large);
	
	public static final RegistryObject<Item> medic_box = ITEMS.register("medic_box", () -> TMWItems.medic_box);
	
	//Processing
	//public static final RegistryObject<Item> crusher = ITEMS.register("crusher", () -> TMWItems.crusher);
	public static final RegistryObject<Item> bullet_foundary = ITEMS.register("bullet_foundary", () -> TMWItems.bullet_foundary);
}
