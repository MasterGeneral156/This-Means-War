package themastergeneral.thismeanswar.items;

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
	public static BulletItem nine_mm_round = new BulletItem(casing_9mm, bullet_tip_9mm);
	public static BulletItem tweleve_gauge_shell = new BulletItem(casing_12g, bullet_tip_buckshot);
	public static BulletItem bazooka_rocket = new BulletItem();
	public static BulletItem five_five_six_round = new BulletItem(casing_556, bullet_tip_556);
	public static BulletItem two_two_three_round = new BulletItem(casing_223, bullet_tip_223);
	
	//Magazines
	public static MagazineItem nine_mm_magazine = new MagazineItem(nine_mm_round, 15);
	public static MagazineItem nine_mm_magazine_large = new MagazineItem(nine_mm_round, 30);
	public static MagazineItem m4ar_mag_556 = new MagazineItem(five_five_six_round, 20);
	public static MagazineItem m4ar_mag_223 = new MagazineItem(two_two_three_round, 20);
	
	//Crafting items
	public static BaseTMWItem lead_ingot = new BasicItem();
	public static BaseTMWItem lead_nugget = new BasicItem();
	public static BaseTMWItem brass_ingot = new BasicItem();
	
	public static BaseTMWItem plate_lead = new BasicItem();
	
	//Hammers for pressing
	public static DurabilityItem hammer_iron = new DurabilityItem(128);
	public static DurabilityItem hammer_diamond = new DurabilityItem(512);
	public static DurabilityItem hammer_creative = new DurabilityItem(Short.MAX_VALUE - 1);
	
	//Bullet Casts
	public static DurabilityItem bullet_cast_9mm = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_556 = new DurabilityItem(256);
	public static DurabilityItem bullet_cast_223 = new DurabilityItem(256);
	
	//Blocks
	public static TMWBlockItem brass_ore = new TMWBlockItem(TMWBlocks.brass_ore);
	public static TMWBlockItem lead_ore = new TMWBlockItem(TMWBlocks.lead_ore);
}
