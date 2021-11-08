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

	//Pistols
	public static BaseGunItem beretta_92_fs = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 35, nine_mm_magazine, 
				nine_mm_round, ModConstant.weaponBalance.beretta_fs.DMG,
				ModConstant.weaponBalance.beretta_fs.SPD,
				ModConstant.weaponBalance.beretta_fs.SPRD);
	
	public static BaseGunItem glock_26 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 20, nine_mm_magazine, 
				nine_mm_round, ModConstant.weaponBalance.glock_26.DMG, 
				ModConstant.weaponBalance.glock_26.SPD,
				ModConstant.weaponBalance.glock_26.SPRD);
	
	//Carbines
	public static BaseGunItem tmg_carbine = new BaseGunItem(ModConstant.fireRate.AUTO_FIRE, 38, nine_mm_magazine_large, 
			nine_mm_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	//Rifles
	public static BaseGunItem springfield_saint_556 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 33, m4ar_mag_556, 
			five_five_six_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	public static BaseGunItem springfield_saint_223 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 34, m4ar_mag_223, 
			two_two_three_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	//Shotguns
	public static BaseGunItem remmington_m870 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, twelve_gauge_shell, 
			ModConstant.weaponBalance.remmington_m870.DMG, 8, 
			ModConstant.weaponBalance.remmington_m870.SPD, 
			ModConstant.weaponBalance.remmington_m870.SPRD);
	
	public static BaseGunItem sawn_off_double_barrel_12g = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, twelve_gauge_shell, 
			ModConstant.weaponBalance.double_barrel_shotgun_sawn_off.DMG, 2, 
			ModConstant.weaponBalance.double_barrel_shotgun_sawn_off.SPD, 
			ModConstant.weaponBalance.double_barrel_shotgun_sawn_off.SPRD);
	
	public static BaseGunItem double_barrel_12g = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, twelve_gauge_shell, 
			ModConstant.weaponBalance.double_barrel_shotgun.DMG, 2, 
			ModConstant.weaponBalance.double_barrel_shotgun.SPD, 
			ModConstant.weaponBalance.double_barrel_shotgun.SPRD);
	
	public static BaseGunItem sawn_off_remmington_m870 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, twelve_gauge_shell, 
			ModConstant.weaponBalance.remmington_m870_sawn_off.DMG, 8,
			ModConstant.weaponBalance.remmington_m870_sawn_off.SPD, 
			ModConstant.weaponBalance.remmington_m870_sawn_off.SPRD);
	
	//RPGs
	
	public static BaseGunItem bazooka = new RocketGunItem(ModConstant.fireRate.SINGLE_FIRE, bazooka_rocket, 
			ModConstant.weaponBalance.bazooka.EXPLOSION_RADIUS, 1, ModConstant.weaponBalance.bazooka.SPEED);

	//Grenades
	public static GrenadeItem dynamite_stick = new GrenadeItem(ModConstant.weaponBalance.dynamite_stick.EXPLOSION_RADIUS);
	public static GrenadeItem nuclear_warhead = new GrenadeItem(ModConstant.weaponBalance.nuclear_warhead.EXPLOSION_RADIUS);
	public static GrenadeItem hand_grenade = new GrenadeItem(ModConstant.weaponBalance.hand_grenade.EXPLOSION_RADIUS);
}
