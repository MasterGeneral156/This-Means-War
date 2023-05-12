package themastergeneral.thismeanswar.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.config.DurabilityItemConfig;
import themastergeneral.thismeanswar.config.MagazineConfigs;
import themastergeneral.thismeanswar.items.tiers.KevlarArmor;
import themastergeneral.thismeanswar.items.tiers.WarArmor;

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
	
	//Magazines
	public static AbstractMagazineItem magazine_9mm = new AbstractMagazineItem(round_9mm, MagazineConfigs.SML_9MM_MAG_SIZE.getDefault());
	public static AbstractMagazineItem magazine_9mm_large = new AbstractMagazineItem(round_9mm, MagazineConfigs.LRG_9MM_MAG_SIZE.get());
	public static AbstractMagazineItem magazine_556 = new AbstractMagazineItem(round_556, MagazineConfigs.AR_MAG_SIZE.get());
	public static AbstractMagazineItem magazine_223 = new AbstractMagazineItem(round_223, MagazineConfigs.AR_MAG_SIZE.get());
	public static AbstractMagazineItem magazine_m1911 = new AbstractMagazineItem(round_45, MagazineConfigs.M1911_MAG_SIZE.get());
	
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
	
	public static AbstractModItem mag_capacity_upgrade = new BasicItem(MagazineConfigs.MAX_MAG_CAP_UPGRADES.get());
	public static AbstractModItem gun_rof_upgrade= new BasicItem(1);
	public static AbstractModItem gun_rof_downgrade= new BasicItem(1);
	
	//Parts for gun craftings
	public static AbstractModItem kevlar_raw = new BasicItem();
	
	//Durability crafting items
	public static DurabilityItem hammer_iron = new DurabilityItem(DurabilityItemConfig.IRON_HAMMER.get());
	public static DurabilityItem hammer_steel = new DurabilityItem(DurabilityItemConfig.STEEL_HAMMER.get());
	public static DurabilityItem hammer_diamond = new DurabilityItem(DurabilityItemConfig.DIAMOND_HAMMER.get());
	public static DurabilityItem hammer_creative = new DurabilityItem(Short.MAX_VALUE - 1);
	public static DurabilityItem hand_saw = new DurabilityItem(DurabilityItemConfig.HAND_SAW.get());
	
	//Bullet Casts
	public static DurabilityItem bullet_cast_9mm = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_556 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_223 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_45 = new DurabilityItem(256);
	
	//Health items
	public static AbstractHealingItem bandage = new AbstractHealingItem(2.5F, 3);
	public static AbstractHealingItem gauze = new AbstractHealingItem(4.75F, 5);
	public static AbstractHealingItem medic_kit = new AbstractHealingItem(12F, 10);
	
	//Armor Material
	public static ArmorMaterial kevlar_material = new KevlarArmor("thismeanswar:kevlar_armor", 128, new int[]{4, 7, 9, 4}, 2, 0, 0, TMWItems.kevlar_raw);
	public static ArmorMaterial blue_war_armor_material = new WarArmor("thismeanswar:blue_war_armor");
	public static ArmorMaterial red_war_armor_material = new WarArmor("thismeanswar:red_war_armor");
	public static ArmorMaterial green_war_armor_material = new WarArmor("thismeanswar:green_war_armor");
	
	public static ArmorItem kevlar_helmet = new ArmorItem(kevlar_material, Type.HELMET, baseProp);
	public static ArmorItem kevlar_chest = new ArmorItem(kevlar_material, Type.CHESTPLATE, baseProp);
	public static ArmorItem kevlar_legs = new ArmorItem(kevlar_material, Type.LEGGINGS, baseProp);
	public static ArmorItem kevlar_boots = new ArmorItem(kevlar_material, Type.BOOTS, baseProp);
	
	//Green War Armor
	public static ArmorItem green_war_armor_helm = new ArmorItem(green_war_armor_material, Type.HELMET, baseProp);
	public static ArmorItem green_war_armor_chest = new ArmorItem(green_war_armor_material, Type.CHESTPLATE, baseProp);
	public static ArmorItem green_war_armor_legs = new ArmorItem(green_war_armor_material, Type.LEGGINGS, baseProp);
	public static ArmorItem green_war_armor_boots = new ArmorItem(green_war_armor_material, Type.BOOTS, baseProp);
	
	//Red War Armor
	public static ArmorItem red_war_armor_helm = new ArmorItem(red_war_armor_material, Type.HELMET, baseProp);
	public static ArmorItem red_war_armor_chest = new ArmorItem(red_war_armor_material, Type.CHESTPLATE, baseProp);
	public static ArmorItem red_war_armor_legs = new ArmorItem(red_war_armor_material, Type.LEGGINGS, baseProp);
	public static ArmorItem red_war_armor_boots = new ArmorItem(red_war_armor_material, Type.BOOTS, baseProp);
	
	//Blue War Armor
	public static ArmorItem blue_war_armor_helm = new ArmorItem(blue_war_armor_material, Type.HELMET, baseProp);
	public static ArmorItem blue_war_armor_chest = new ArmorItem(blue_war_armor_material, Type.CHESTPLATE, baseProp);
	public static ArmorItem blue_war_armor_legs = new ArmorItem(blue_war_armor_material, Type.LEGGINGS, baseProp);
	public static ArmorItem blue_war_armor_boots = new ArmorItem(blue_war_armor_material, Type.BOOTS, baseProp);
	
	//Blocks
	public static AbstractBlockItem ore_brass = new AbstractBlockItem(TMWBlocks.ore_brass);
	public static AbstractBlockItem ore_lead = new AbstractBlockItem(TMWBlocks.ore_lead);
	
	public static AbstractBlockItem block_brass = new AbstractBlockItem(TMWBlocks.block_brass);
	public static AbstractBlockItem block_lead = new AbstractBlockItem(TMWBlocks.block_lead);
	public static AbstractBlockItem block_steel = new AbstractBlockItem(TMWBlocks.block_steel);
	
	public static AbstractBlockItem blue_stone_bricks = new AbstractBlockItem(TMWBlocks.blue_stone_bricks);
	public static AbstractBlockItem red_stone_bricks = new AbstractBlockItem(TMWBlocks.red_stone_bricks);
	
	public static AbstractBlockItem ammo_box = new AbstractBlockItem(TMWBlocks.ammo_box);
	public static AbstractBlockItem ammo_box_medium = new AbstractBlockItem(TMWBlocks.ammo_box_medium);
	public static AbstractBlockItem ammo_box_large = new AbstractBlockItem(TMWBlocks.ammo_box_large);
	//public static AbstractBlockItem crusher = new AbstractBlockItem(TMWBlocks.crusher);
}
