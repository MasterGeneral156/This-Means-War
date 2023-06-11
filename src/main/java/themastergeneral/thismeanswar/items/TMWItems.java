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
import themastergeneral.thismeanswar.items.charms.CharmStealth;
import themastergeneral.thismeanswar.items.tiers.BasicProtArmor;
import themastergeneral.thismeanswar.items.tiers.KevlarArmor;
import themastergeneral.thismeanswar.items.tiers.WarArmor;
import themastergeneral.thismeanswar.items.upgrade.UpgradeAP;
import themastergeneral.thismeanswar.items.upgrade.UpgradeBayonetItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeMagCapacityItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeROFAutoItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeROFSemiItem;

public class TMWItems {
	
	//Just in case
	protected static Properties baseProp = new Properties().stacksTo(1);
	
	//Casings
	public static AbstractModItem casing_9mm = new BasicItem();
	public static AbstractModItem casing_556 = new BasicItem();
	public static AbstractModItem casing_223 = new BasicItem();
	public static AbstractModItem casing_12g = new BasicItem();
	public static AbstractModItem casing_45 = new BasicItem();
	
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
	
	//Bullets & Shells
	public static AbstractBulletItem round_9mm = new AbstractBulletItem(casing_9mm, bullet_tip_9mm);
	public static AbstractBulletItem round_12g = new AbstractBulletItem(casing_12g, bullet_tip_buckshot);
	public static AbstractBulletItem rocket_bazooka = new AbstractBulletItem();
	public static AbstractBulletItem round_556 = new AbstractBulletItem(casing_556, bullet_tip_556);
	public static AbstractBulletItem round_223 = new AbstractBulletItem(casing_223, bullet_tip_223);
	public static AbstractBulletItem round_45 = new AbstractBulletItem(casing_45, bullet_tip_45);
	public static AbstractBulletItem round_40mm = new AbstractBulletItem();
	public static AbstractBulletItem energy_bolt = new AbstractBulletItem();
	
	//Magazines
	public static AbstractMagazineItem magazine_9mm = new AbstractMagazineItem(round_9mm, Constants.magSize9mm);
	public static AbstractMagazineItem magazine_9mm_large = new AbstractMagazineItem(round_9mm, Constants.magSizeLarge9mm);
	public static AbstractMagazineItem magazine_9mm_clip = new AbstractMagazineItem(round_9mm, Constants.magSizeClips);
	public static AbstractMagazineItem magazine_556 = new AbstractMagazineItem(round_556, Constants.magSizeAR15);
	public static AbstractMagazineItem magazine_556_clip = new AbstractMagazineItem(round_556, Constants.magSizeClips);
	public static AbstractMagazineItem magazine_223 = new AbstractMagazineItem(round_223, Constants.magSizeAR15);
	public static AbstractMagazineItem magazine_223_clip = new AbstractMagazineItem(round_223, Constants.magSizeClips);
	public static AbstractMagazineItem magazine_m1911 = new AbstractMagazineItem(round_45, Constants.magSize1911);
	public static AbstractMagazineItem magazine_45_clip = new AbstractMagazineItem(round_45, Constants.magSizeClips);
	public static AbstractMagazineItem magazine_12g_clip = new AbstractMagazineItem(round_12g, Constants.magSizeClips);
	public static AbstractMagazineItem magazine_40mm_clip = new AbstractMagazineItem(round_40mm, Constants.magSizeClips);
	public static AbstractMagazineItem energy_cell = new AbstractMagazineItem(energy_bolt, Constants.magSizeEnergyCell);
	
	//Crafting items
	public static AbstractModItem ingot_lead = new BasicItem();
	public static AbstractModItem nugget_lead = new BasicItem();
	public static AbstractModItem dust_lead = new BasicItem();
	public static AbstractModItem ingot_brass = new BasicItem();
	public static AbstractModItem nugget_brass = new BasicItem();
	public static AbstractModItem dust_brass = new BasicItem();
	public static AbstractModItem ingot_steel = new BasicItem();
	public static AbstractModItem nugget_steel = new BasicItem();
	public static AbstractModItem dust_steel = new BasicItem();
	public static AbstractModItem dust_iron = new BasicItem();
	public static AbstractModItem dust_gold = new BasicItem();
	public static AbstractModItem dust_copper = new BasicItem();
	public static AbstractModItem plate_lead = new BasicItem();
	
	//Upgrades
	public static UpgradeMagCapacityItem mag_capacity_upgrade = new UpgradeMagCapacityItem();
	public static UpgradeROFAutoItem gun_rof_upgrade = new UpgradeROFAutoItem();
	public static UpgradeROFSemiItem gun_rof_downgrade = new UpgradeROFSemiItem();
	public static AbstractModItem base_upgrade = new BasicItem();
	public static UpgradeAP bullet_upgrade_ap = new UpgradeAP(1, TMWTags.disableAPUpgrade);
	public static UpgradeAP bullet_upgrade_normal = new UpgradeAP(0, TMWTags.disableAPUpgrade);
	public static UpgradeAP bullet_upgrade_fire = new UpgradeAP(2, TMWTags.disableFireUpgrade);
	
	//Charms
	public static CharmStealth charm_stealth = new CharmStealth();
	public static CharmCreative creative_charm = new CharmCreative();
	
	//Parts for gun craftings
	public static AbstractModItem kevlar_raw = new BasicItem();
	
	//Durability crafting items
	public static DurabilityItem hammer_iron = new DurabilityItem(128);
	public static DurabilityItem hammer_steel = new DurabilityItem(224);
	public static DurabilityItem hammer_diamond = new DurabilityItem(469);
	public static DurabilityItem hand_saw = new DurabilityItem(175);
	
	//Bullet Casts
	public static DurabilityItem bullet_cast_9mm = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_556 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_223 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_45 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_12g = new DurabilityItem(256);
	
	//Musket Upgrade
	public static UpgradeBayonetItem bayonet_wood = new UpgradeBayonetItem(2);
	public static UpgradeBayonetItem bayonet_stone = new UpgradeBayonetItem(2.5);
	public static UpgradeBayonetItem bayonet_iron = new UpgradeBayonetItem(3);
	public static UpgradeBayonetItem bayonet_gold = new UpgradeBayonetItem(2.75);
	public static UpgradeBayonetItem bayonet_diamond = new UpgradeBayonetItem(3.5);
	public static UpgradeBayonetItem bayonet_netherrite = new UpgradeBayonetItem(4);
	
	//Health items
	public static AbstractHealingItem bandage = new AbstractHealingItem(2.5F, 3);
	public static AbstractHealingItem gauze = new AbstractHealingItem(4.75F, 5);
	public static AbstractHealingItem medic_kit = new AbstractHealingItem(12F, 10);
	
	//Armor Material
	public static ArmorMaterial kevlar_material = new KevlarArmor("thismeanswar:kevlar_armor");
	public static ArmorMaterial basic_prot_material = new BasicProtArmor("thismeanswar:basic_prot");
	public static ArmorMaterial blue_war_armor_material = new WarArmor("thismeanswar:blue_war_armor");
	public static ArmorMaterial red_war_armor_material = new WarArmor("thismeanswar:red_war_armor");
	public static ArmorMaterial green_war_armor_material = new WarArmor("thismeanswar:green_war_armor");
	
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
	
	public static AbstractBlockItem ammo_box = new AbstractBlockItem(TMWBlocks.ammo_box);
	public static AbstractBlockItem ammo_box_medium = new AbstractBlockItem(TMWBlocks.ammo_box_medium);
	public static AbstractBlockItem ammo_box_large = new AbstractBlockItem(TMWBlocks.ammo_box_large);
	public static AbstractBlockItem medic_box = new AbstractBlockItem(TMWBlocks.medic_box);
	//public static AbstractBlockItem crusher = new AbstractBlockItem(TMWBlocks.crusher);
}
