package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.ModConstant;

public class TMWItems {
	
	//Bullets & Shells
	public static BulletItem nine_mm_round = new BulletItem();
	public static BulletItem twelve_gauge_shell = new BulletItem();
	public static BulletItem bazooka_rocket = new BulletItem();
	public static BulletItem five_five_six_round = new BulletItem();
	public static BulletItem two_two_three_round = new BulletItem();
	
	//Magazines
	public static MagazineItem nine_mm_magazine = new MagazineItem(nine_mm_round, 15);
	public static MagazineItem nine_mm_magazine_large = new MagazineItem(nine_mm_round, 30);
	public static MagazineItem m4ar_mag_556 = new MagazineItem(five_five_six_round, 20);
	public static MagazineItem m4ar_mag_223 = new MagazineItem(two_two_three_round, 20);
	
	//Rifles
	public static BaseGunItem springfield_saint_556 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 33, m4ar_mag_556, 
			five_five_six_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	public static BaseGunItem springfield_saint_223 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 34, m4ar_mag_223, 
			two_two_three_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	//RPGs
	
	public static BaseGunItem bazooka = new RocketGunItem(ModConstant.fireRate.SINGLE_FIRE, bazooka_rocket, 
			ModConstant.weaponBalance.bazooka.EXPLOSION_RADIUS, 1, ModConstant.weaponBalance.bazooka.SPEED);
}
