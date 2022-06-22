package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.block.BlockAmmoStorage;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWItems {
	
	//Casings
	public static BaseTMWItem casing_9mm = new BasicItem();
	public static BaseTMWItem casing_556 = new BasicItem();
	public static BaseTMWItem casing_223 = new BasicItem();
	public static BaseTMWItem casing_12g = new BasicItem();
	
	//Primers
	public static BaseTMWItem primer_pistol = new BasicItem();
	public static BaseTMWItem primer_rifle = new BasicItem();
	public static BaseTMWItem primer_shotgun = new BasicItem();
	
	//Bullet tips
	public static BaseTMWItem bullet_tip_9mm = new BasicItem();
	public static BaseTMWItem bullet_tip_556 = new BasicItem();
	public static BaseTMWItem bullet_tip_223 = new BasicItem();
	public static BaseTMWItem bullet_tip_buckshot = new BasicItem();
	
	//Bullets & Shells
	public static BulletItem round_9mm = new BulletItem(casing_9mm, bullet_tip_9mm);
	public static BulletItem round_12g = new BulletItem(casing_12g, bullet_tip_buckshot);
	public static BulletItem rocket_bazooka = new BulletItem();
	public static BulletItem round_556 = new BulletItem(casing_556, bullet_tip_556);
	public static BulletItem round_223 = new BulletItem(casing_223, bullet_tip_223);
	
	//Magazines
	public static MagazineItem magazine_9mm = new MagazineItem(round_9mm, 15);
	public static MagazineItem magazine_9mm_large = new MagazineItem(round_9mm, 30);
	public static MagazineItem magazine_556 = new MagazineItem(round_556, 20);
	public static MagazineItem magazine_223 = new MagazineItem(round_223, 20);
	
	//Crafting items
	public static BaseTMWItem ingot_lead = new BasicItem();
	public static BaseTMWItem nugget_lead = new BasicItem();
	public static BaseTMWItem dust_lead = new BasicItem();
	
	public static BaseTMWItem ingot_brass = new BasicItem();
	public static BaseTMWItem nugget_brass = new BasicItem();
	public static BaseTMWItem dust_brass = new BasicItem();
	
	public static BaseTMWItem ingot_steel = new BasicItem();
	public static BaseTMWItem nugget_steel = new BasicItem();
	public static BaseTMWItem dust_steel = new BasicItem();
	
	public static BaseTMWItem dust_iron = new BasicItem();
	public static BaseTMWItem dust_gold = new BasicItem();
	public static BaseTMWItem dust_copper = new BasicItem();
	
	public static BaseTMWItem plate_lead = new BasicItem();
	
	//Parts for gun craftings
	public static BaseTMWItem kevlar_raw = new BasicItem();
	
	//Hammers for pressing
	public static DurabilityItem hammer_iron = new DurabilityItem(128);
	public static DurabilityItem hammer_steel = new DurabilityItem(213);
	public static DurabilityItem hammer_diamond = new DurabilityItem(512);
	public static DurabilityItem hammer_creative = new DurabilityItem(Short.MAX_VALUE - 1);
	
	//Bullet Casts
	public static DurabilityItem bullet_cast_9mm = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_556 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_223 = new DurabilityItem(256);
	
	//Health items
	public static BaseHealingItem bandage = new BaseHealingItem(2.5F, 3);
	public static BaseHealingItem gauze = new BaseHealingItem(4.75F, 5);
	
	//Blocks
	public static TMWBlockItem ore_brass = new TMWBlockItem(TMWBlocks.ore_brass);
	public static TMWBlockItem block_brass = new TMWBlockItem(TMWBlocks.block_brass);
	
	public static TMWBlockItem block_lead = new TMWBlockItem(TMWBlocks.block_lead);
	public static TMWBlockItem ore_lead = new TMWBlockItem(TMWBlocks.ore_lead);
	
	public static TMWBlockItem block_steel = new TMWBlockItem(TMWBlocks.block_steel);
	
	public static TMWBlockItem ammo_box = new TMWBlockItem(TMWBlocks.ammo_box);
	public static TMWBlockItem ammo_box_medium = new TMWBlockItem(TMWBlocks.ammo_box_medium);
	public static TMWBlockItem ammo_box_large = new TMWBlockItem(TMWBlocks.ammo_box_large);
	public static TMWBlockItem crusher = new TMWBlockItem(TMWBlocks.crusher);
}
