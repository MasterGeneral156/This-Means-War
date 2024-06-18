package themastergeneral.thismeanswar.items;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.DurabilityItemConfig;
import themastergeneral.thismeanswar.config.MagazineConfigs;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.items.armors.WarArmorItem;
import themastergeneral.thismeanswar.items.charms.CharmCreative;
import themastergeneral.thismeanswar.items.interfaces.AbstractBlockItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractHealingItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;
//import themastergeneral.thismeanswar.items.charms.CharmStealth;
import themastergeneral.thismeanswar.items.tiers.BasicProtArmor;
import themastergeneral.thismeanswar.items.tiers.KevlarArmor;
import themastergeneral.thismeanswar.items.tiers.WarArmor;
import themastergeneral.thismeanswar.items.upgrade.UpgradeBulletType;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunBayonetItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunROFItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeMagCapacityItem;

import com.themastergeneral.ctdcore.item.CTDConsumableItem;

public class TMWItems {
	
	//Just in case
	protected static Properties baseProp = new Properties().stacksTo(1);
	
	//Casings
	public static AbstractModItem casing_9mm = new BasicItem();
	public static AbstractModItem casing_556 = new BasicItem();
	public static AbstractModItem casing_223 = new BasicItem();
	public static AbstractModItem casing_12g = new BasicItem();
	public static AbstractModItem casing_45 = new BasicItem();
	public static AbstractModItem casing_762 = new BasicItem();
	public static AbstractModItem casing_38spec = new BasicItem();
	
	//Primers
	public static AbstractModItem primer_pistol = new BasicItem();
	public static AbstractModItem primer_rifle = new BasicItem();
	public static AbstractModItem primer_shotgun = new BasicItem();
	
	//Bullet tips
	public static AbstractModItem bullet_tip_9mm = new BasicItem();
	public static AbstractModItem bullet_tip_556 = new BasicItem();
	public static AbstractModItem bullet_tip_223 = new BasicItem();
	public static AbstractModItem bullet_tip_buckshot = new BasicItem();
	public static AbstractModItem bullet_tip_45 = new BasicItem();
	public static AbstractModItem bullet_tip_762 = new BasicItem();
	public static AbstractModItem bullet_tip_38spec = new BasicItem();
	
	//Bullets & Shells
	public static AbstractBulletItem round_9mm = new AbstractBulletItem(casing_9mm, bullet_tip_9mm, TMWTags.rounds9mm);
	public static AbstractBulletItem round_12g = new AbstractBulletItem(casing_12g, bullet_tip_buckshot, TMWTags.rounds12g);
	public static AbstractBulletItem rocket_bazooka = new AbstractBulletItem();
	public static AbstractBulletItem round_556 = new AbstractBulletItem(casing_556, bullet_tip_556, TMWTags.rounds556);
	public static AbstractBulletItem round_223 = new AbstractBulletItem(casing_223, bullet_tip_223, TMWTags.rounds223);
	public static AbstractBulletItem round_45 = new AbstractBulletItem(casing_45, bullet_tip_45, TMWTags.rounds45);
	public static AbstractBulletItem round_38spec = new AbstractBulletItem(casing_38spec, bullet_tip_38spec, TMWTags.rounds38spec);
	public static AbstractBulletItem round_40mm = new AbstractBulletItem(TMWTags.rounds40mm);
	public static AbstractBulletItem round_762 = new AbstractBulletItem(casing_762, bullet_tip_762, TMWTags.rounds762);
	public static AbstractBulletItem energy_bolt = new AbstractBulletItem(TMWTags.roundsenergy);
	
	//Magazines
	public static NuMagazineItem magazine_9mm = new NuMagazineItem(round_9mm, Constants.magSize9mm, TMWTags.mags9mm);
	public static NuMagazineItem magazine_9mm_large = new NuMagazineItem(round_9mm, Constants.magSizeLarge9mm, TMWTags.mags9mm);
	public static NuMagazineItem magazine_9mm_large_double = new NuMagazineItem(round_9mm, Constants.magSizeLarge9mm*2, TMWTags.mags9mm);
	public static NuMagazineItem magazine_9mm_short = new NuMagazineItem(round_9mm, Constants.magSizeShort9mm, TMWTags.mags9mm);
	public static NuMagazineItem magazine_9mm_drum = new NuMagazineItem(round_9mm, Constants.magSizeDrum9mm, TMWTags.mags9mm);
	public static NuMagazineItem magazine_9mm_clip = new NuMagazineItem(round_9mm, Constants.magSizeClips);
	
	public static NuMagazineItem magazine_556 = new NuMagazineItem(round_556, Constants.magSizeAR15, TMWTags.mags556);
	public static NuMagazineItem magazine_556_large = new NuMagazineItem(round_556, Constants.magSizeAR15*2, TMWTags.mags556);
	public static NuMagazineItem magazine_556_clip = new NuMagazineItem(round_556, Constants.magSizeClips);
	
	public static NuMagazineItem magazine_223 = new NuMagazineItem(round_223, Constants.magSizeAR15, TMWTags.mags223);
	public static NuMagazineItem magazine_223_large = new NuMagazineItem(round_223, Constants.magSizeAR15*2, TMWTags.mags223);
	public static NuMagazineItem magazine_223_clip = new NuMagazineItem(round_223, Constants.magSizeClips);
	
	public static NuMagazineItem magazine_dragunov = new NuMagazineItem(round_762, Constants.magSizeDragunov);
	public static NuMagazineItem magazine_m1911 = new NuMagazineItem(round_45, Constants.magSize1911);
	public static NuMagazineItem magazine_g36 = new NuMagazineItem(round_556, Constants.magSizeG36);
	public static NuMagazineItem energy_cell = new NuMagazineItem(energy_bolt, Constants.magSizeEnergyCell, TMWTags.magsEnergy);
	
	public static NuMagazineItem magazine_45_clip = new NuMagazineItem(round_45, Constants.magSizeClips);
	public static NuMagazineItem magazine_12g_clip = new NuMagazineItem(round_12g, Constants.magSizeClips);
	public static NuMagazineItem magazine_40mm_clip = new NuMagazineItem(round_40mm, Constants.magSizeClips);
	public static NuMagazineItem magazine_38spec_clip = new NuMagazineItem(round_38spec, Constants.magSizeClips);
	
	//Ingots
	public static AbstractModItem ingot_lead = new BasicItem();
	public static AbstractModItem ingot_brass = new BasicItem();
	public static AbstractModItem ingot_steel = new BasicItem();
	public static AbstractModItem ingot_halominium = new BasicItem();
	
	//Nuggies
	public static AbstractModItem nugget_steel = new BasicItem();
	public static AbstractModItem nugget_brass = new BasicItem();
	public static AbstractModItem nugget_lead = new BasicItem();
	
	//Dusts
	public static AbstractModItem dust_steel = new BasicItem();
	public static AbstractModItem dust_iron = new BasicItem();
	public static AbstractModItem dust_gold = new BasicItem();
	public static AbstractModItem dust_copper = new BasicItem();
	public static AbstractModItem dust_brass = new BasicItem();
	public static AbstractModItem dust_lead = new BasicItem();
	
	//Plates
	public static AbstractModItem plate_lead = new BasicItem();
	public static AbstractModItem plate_steel = new BasicItem();
	public static AbstractModItem plate_iron = new BasicItem();
	public static AbstractModItem plate_diamond = new BasicItem();
	public static AbstractModItem plate_brass = new BasicItem();
	public static AbstractModItem plate_gold = new BasicItem();
	
	public static AbstractModItem ancient_fabric = new BasicItem();
	public static AbstractModItem ancient_fabric_russia = new BasicItem();
	public static AbstractModItem ancient_fabric_british = new BasicItem();
	public static AbstractModItem ancient_fabric_us = new BasicItem();
	
	//Raw Ore
	public static AbstractModItem raw_brass = new BasicItem();
	public static AbstractModItem raw_lead = new BasicItem();
	
	//Upgrades
	public static UpgradeMagCapacityItem mag_capacity_upgrade = new UpgradeMagCapacityItem(Constants.magIncreasePerLevel);
	public static UpgradeMagCapacityItem mag_capacity_upgrade_t2 = new UpgradeMagCapacityItem(Constants.magIncreaseT2PerLevel);
	public static UpgradeMagCapacityItem mag_capacity_upgrade_t3 = new UpgradeMagCapacityItem(Constants.magIncreaseT3PerLevel);
	public static UpgradeMagCapacityItem creative_mag_capacity_upgrade = new UpgradeMagCapacityItem(1000D);
	public static UpgradeGunROFItem gun_rof_upgrade = new UpgradeGunROFItem();
	public static UpgradeGunROFItem gun_rof_downgrade = new UpgradeGunROFItem();
	public static AbstractModItem base_upgrade = new BasicItem();
	public static UpgradeBulletType bullet_upgrade_ap = new UpgradeBulletType(Constants.bulletUpgradeAP, TMWTags.disableAPUpgrade);
	public static UpgradeBulletType bullet_upgrade_normal = new UpgradeBulletType(Constants.bulletUpgradeNull, TMWTags.disableAPUpgrade);
	public static UpgradeBulletType bullet_upgrade_fire = new UpgradeBulletType(Constants.bulletUpgradeFire, TMWTags.disableFireUpgrade);
	public static UpgradeBulletType bullet_upgrade_tracer = new UpgradeBulletType(Constants.bulletUpgradeTracer, TMWTags.disableTracerUpgrade);
	public static UpgradeBulletType bullet_upgrade_inert = new UpgradeBulletType(Constants.bulletUpgradeInert, TMWTags.disableInertUpgrade);
	
	//Charms
	//public static CharmStealth charm_stealth = new CharmStealth();
	public static CharmCreative creative_charm = new CharmCreative();
	
	//Parts for gun craftings
	public static AbstractModItem kevlar_raw = new BasicItem();
	
	//Durability crafting items
	public static DurabilityItem hammer_wood = new DurabilityItem(59);
	public static DurabilityItem hammer_stone = new DurabilityItem(131);
	public static DurabilityItem hammer_gold = new DurabilityItem(488);
	public static DurabilityItem hammer_iron = new DurabilityItem(250);
	public static DurabilityItem hammer_steel = new DurabilityItem(993);
	public static DurabilityItem hammer_diamond = new DurabilityItem(1561);
	public static DurabilityItem hammer_emerald = new DurabilityItem(1745);
	public static DurabilityItem hammer_netherite = new DurabilityItem(2031);
	public static DurabilityItem hammer_creative = new TMWCreativeItem();
	
	public static DurabilityItem hand_saw = new DurabilityItem(993);
	public static DurabilityItem creative_hand_saw = new TMWCreativeItem();
	
	public static DurabilityItem cutter_iron = new WireCutters(250);
	public static DurabilityItem cutter_steel = new WireCutters(993);
	public static DurabilityItem cutter_diamond = new WireCutters(1561);
	public static DurabilityItem cutter_creative = new WireCutters(Short.MAX_VALUE);
	
	//Bullet Casts
	public static DurabilityItem bullet_cast_9mm = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_556 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_223 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_45 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_38spec = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_12g = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_762 = new DurabilityItem(256);
	
	public static DurabilityItem creative_bullet_cast_9mm = new TMWCreativeItem();
	public static DurabilityItem creative_bullet_cast_556 = new TMWCreativeItem();
	public static DurabilityItem creative_bullet_cast_223 = new TMWCreativeItem();
	public static DurabilityItem creative_bullet_cast_45 = new TMWCreativeItem();
	public static DurabilityItem creative_bullet_cast_38spec = new TMWCreativeItem();
	public static DurabilityItem creative_bullet_cast_12g = new TMWCreativeItem();
	public static DurabilityItem creative_bullet_cast_762 = new TMWCreativeItem();
	
	//Casing Casts
	public static DurabilityItem casing_cast_9mm = new DurabilityItem(256);
	public static DurabilityItem casing_cast_556 = new DurabilityItem(256);
	public static DurabilityItem casing_cast_223 = new DurabilityItem(256);
	public static DurabilityItem casing_cast_45 = new DurabilityItem(256);
	public static DurabilityItem casing_cast_38spec = new DurabilityItem(256);
	public static DurabilityItem casing_cast_12g = new DurabilityItem(256);
	public static DurabilityItem casing_cast_762 = new DurabilityItem(256);
	
	public static DurabilityItem creative_casing_cast_9mm = new TMWCreativeItem();
	public static DurabilityItem creative_casing_cast_556 = new TMWCreativeItem();
	public static DurabilityItem creative_casing_cast_223 = new TMWCreativeItem();
	public static DurabilityItem creative_casing_cast_45 = new TMWCreativeItem();
	public static DurabilityItem creative_casing_cast_38spec = new TMWCreativeItem();
	public static DurabilityItem creative_casing_cast_12g = new TMWCreativeItem();
	public static DurabilityItem creative_casing_cast_762 = new TMWCreativeItem();
	
	//Musket Upgrade
	public static UpgradeGunBayonetItem bayonet_wood = new UpgradeGunBayonetItem(2.5);
	public static UpgradeGunBayonetItem bayonet_stone = new UpgradeGunBayonetItem(4.5);
	public static UpgradeGunBayonetItem bayonet_iron = new UpgradeGunBayonetItem(6.5);
	public static UpgradeGunBayonetItem bayonet_gold = new UpgradeGunBayonetItem(5.5);
	public static UpgradeGunBayonetItem bayonet_diamond = new UpgradeGunBayonetItem(8.5);
	public static UpgradeGunBayonetItem bayonet_netherrite = new UpgradeGunBayonetItem(10.5);
	
	//Health items
	public static AbstractHealingItem bandage = new AbstractHealingItem(2.5F, 3);
	public static AbstractHealingItem gauze = new AbstractHealingItem(4.75F, 5);
	public static AbstractHealingItem medic_kit = new AbstractHealingItem(10.25F, 10);
	
	//Food
	public static CTDConsumableItem mre = new CTDConsumableItem(2, 5.25F);
	public static CTDConsumableItem instant_coffee = new CTDConsumableItem(0, 0.65F);
	
	
	//Armor Material
	public static ArmorMaterial kevlar_material = new KevlarArmor("kevlar_armor");
	public static ArmorMaterial ww2_russian_mat = new KevlarArmor("ww2_russian_armor");
	public static ArmorMaterial ww2_brit_mat = new KevlarArmor("ww2_british_armor");
	public static ArmorMaterial swat_mat = new KevlarArmor("swat_armor");
	public static ArmorMaterial basic_prot_material = new BasicProtArmor("basic_prot");
	public static ArmorMaterial blue_war_armor_material = new WarArmor("blue_war_armor");
	public static ArmorMaterial red_war_armor_material = new WarArmor("red_war_armor");
	public static ArmorMaterial green_war_armor_material = new WarArmor("green_war_armor");
	
	public static ArmorItem ww2_russian_helm = new ArmorItem(ww2_russian_mat, Type.HELMET, baseProp);
	public static ArmorItem ww2_russian_chest = new ArmorItem(ww2_russian_mat, Type.CHESTPLATE, baseProp);
	public static ArmorItem ww2_russian_legs = new ArmorItem(ww2_russian_mat, Type.LEGGINGS, baseProp);
	public static ArmorItem ww2_russian_boots = new ArmorItem(ww2_russian_mat, Type.BOOTS, baseProp);
	
	public static ArmorItem ww2_british_helm = new ArmorItem(ww2_brit_mat, Type.HELMET, baseProp);
	public static ArmorItem ww2_british_chest = new ArmorItem(ww2_brit_mat, Type.CHESTPLATE, baseProp);
	public static ArmorItem ww2_british_legs = new ArmorItem(ww2_brit_mat, Type.LEGGINGS, baseProp);
	public static ArmorItem ww2_british_boots = new ArmorItem(ww2_brit_mat, Type.BOOTS, baseProp);
	
	public static ArmorItem           swat_helm = new ArmorItem(swat_mat, Type.HELMET, baseProp);
	public static ArmorItem swat_chest = new ArmorItem(swat_mat, Type.CHESTPLATE, baseProp);
	public static ArmorItem swat_legs = new ArmorItem(swat_mat, Type.LEGGINGS, baseProp);
	public static ArmorItem swat_boots = new ArmorItem(swat_mat, Type.BOOTS, baseProp);
	
	public static ArmorItem kevlar_helmet = new ArmorItem(kevlar_material, Type.HELMET, baseProp);
	public static ArmorItem kevlar_chest = new ArmorItem(kevlar_material, Type.CHESTPLATE, baseProp);
	public static ArmorItem kevlar_legs = new ArmorItem(kevlar_material, Type.LEGGINGS, baseProp);
	public static ArmorItem kevlar_boots = new ArmorItem(kevlar_material, Type.BOOTS, baseProp);
	
	//Green War Armor
	public static ArmorItem green_war_armor_helm = new WarArmorItem(green_war_armor_material, Type.HELMET);
	public static ArmorItem green_war_armor_chest = new WarArmorItem(green_war_armor_material, Type.CHESTPLATE);
	public static ArmorItem green_war_armor_legs = new WarArmorItem(green_war_armor_material, Type.LEGGINGS);
	public static ArmorItem green_war_armor_boots = new WarArmorItem(green_war_armor_material, Type.BOOTS);
	
	//Red War Armor
	public static ArmorItem red_war_armor_helm = new WarArmorItem(red_war_armor_material, Type.HELMET);
	public static ArmorItem red_war_armor_chest = new WarArmorItem(red_war_armor_material, Type.CHESTPLATE);
	public static ArmorItem red_war_armor_legs = new WarArmorItem(red_war_armor_material, Type.LEGGINGS);
	public static ArmorItem red_war_armor_boots = new WarArmorItem(red_war_armor_material, Type.BOOTS);
	
	//Blue War Armor
	public static ArmorItem blue_war_armor_helm = new WarArmorItem(blue_war_armor_material, Type.HELMET);
	public static ArmorItem blue_war_armor_chest = new WarArmorItem(blue_war_armor_material, Type.CHESTPLATE);
	public static ArmorItem blue_war_armor_legs = new WarArmorItem(blue_war_armor_material, Type.LEGGINGS);
	public static ArmorItem blue_war_armor_boots = new WarArmorItem(blue_war_armor_material, Type.BOOTS);
	
	//Misc Armor
	public static ArmorItem basic_prot_chest = new ArmorItem(basic_prot_material, Type.CHESTPLATE, baseProp);
	public static ArmorItem basic_prot_goggles = new ArmorItem(basic_prot_material, Type.HELMET, baseProp);
	
	//Blocks
	public static AbstractBlockItem ore_brass = new AbstractBlockItem(TMWBlocks.ore_brass);
	public static AbstractBlockItem ore_lead = new AbstractBlockItem(TMWBlocks.ore_lead);
	
	public static AbstractBlockItem block_brass = new AbstractBlockItem(TMWBlocks.block_brass);
	public static AbstractBlockItem block_lead = new AbstractBlockItem(TMWBlocks.block_lead);
	public static AbstractBlockItem block_steel = new AbstractBlockItem(TMWBlocks.block_steel);
	
	public static AbstractBlockItem blue_stone_bricks = new AbstractBlockItem(TMWBlocks.blue_stone_bricks);
	public static AbstractBlockItem red_stone_bricks = new AbstractBlockItem(TMWBlocks.red_stone_bricks);
	public static AbstractBlockItem blue_chiseled_bricks = new AbstractBlockItem(TMWBlocks.blue_chiseled_bricks);
	public static AbstractBlockItem red_chiseled_bricks = new AbstractBlockItem(TMWBlocks.red_chiseled_bricks);
	
	public static AbstractBlockItem shelter_weak = new AbstractBlockItem(TMWBlocks.shelter_weak);
	public static AbstractBlockItem shelter_medium = new AbstractBlockItem(TMWBlocks.shelter_medium);
	public static AbstractBlockItem shelter_strong = new AbstractBlockItem(TMWBlocks.shelter_strong);
	public static AbstractBlockItem shelter_op = new AbstractBlockItem(TMWBlocks.shelter_op);
	public static AbstractBlockItem shelter_creative = new AbstractBlockItem(TMWBlocks.shelter_creative);
	
	public static AbstractBlockItem ammo_box = new AbstractBlockItem(TMWBlocks.ammo_box);
	public static AbstractBlockItem ammo_box_medium = new AbstractBlockItem(TMWBlocks.ammo_box_medium);
	public static AbstractBlockItem ammo_box_large = new AbstractBlockItem(TMWBlocks.ammo_box_large);
	public static AbstractBlockItem medic_box = new AbstractBlockItem(TMWBlocks.medic_box);
	public static AbstractBlockItem medic_box_medium = new AbstractBlockItem(TMWBlocks.medic_box_medium);
	public static AbstractBlockItem medic_box_large = new AbstractBlockItem(TMWBlocks.medic_box_large);
	public static AbstractBlockItem crusher = new AbstractBlockItem(TMWBlocks.crusher);
	public static AbstractBlockItem barbed_wire = new AbstractBlockItem(TMWBlocks.barbed_wire);
	public static AbstractBlockItem bullet_foundary = new AbstractBlockItem(TMWBlocks.bullet_foundary);
	public static AbstractBlockItem casing_recycler = new AbstractBlockItem(TMWBlocks.casing_recycler);
	public static AbstractBlockItem tip_recycler = new AbstractBlockItem(TMWBlocks.tip_recycler);
	public static AbstractBlockItem factory_holder = new AbstractBlockItem(TMWBlocks.factory_holder);
	public static AbstractBlockItem press = new AbstractBlockItem(TMWBlocks.press);
	public static AbstractBlockItem primer_popper = new AbstractBlockItem(TMWBlocks.primer_popper);
	public static AbstractBlockItem alloy_smelter = new AbstractBlockItem(TMWBlocks.alloy_smelter);
	public static AbstractBlockItem casing_former = new AbstractBlockItem(TMWBlocks.casing_former);
	public static AbstractBlockItem tip_molder = new AbstractBlockItem(TMWBlocks.tip_molder);
	public static AbstractBlockItem firearm_inspector = new AbstractBlockItem(TMWBlocks.firearm_inspector);
	public static AbstractBlockItem land_mine = new AbstractBlockItem(TMWBlocks.land_mine);
}
