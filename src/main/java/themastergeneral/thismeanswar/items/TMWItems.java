package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.ModConstant;

public class TMWItems {
	
	public static BulletItem nine_mm_round = new BulletItem();
	public static BulletItem twelve_gauge_shell= new BulletItem();
	
	public static MagazineItem nine_mm_magazine = new MagazineItem(nine_mm_round, 15);
	public static MagazineItem nine_mm_magazine_large = new MagazineItem(nine_mm_round, 30);

	public static GunItem beretta_92_fs = new GunItem(ModConstant.SINGLE_FIRE_SPEED, 35, nine_mm_magazine, nine_mm_round, ModConstant.BERETTA_FS_DMG);
	public static GunItem glock_26 = new GunItem(ModConstant.SINGLE_FIRE_SPEED, 20, nine_mm_magazine, nine_mm_round, ModConstant.GLOCK_26_DMG);
	public static GunItem tmg_carbine = new GunItem(ModConstant.AUTO_FIRE_SPEED, 38, nine_mm_magazine_large, nine_mm_round, ModConstant.TMG_CARBINE_DMG);
	
	public static GunItem remmington_m870 = new GunItem(ModConstant.SINGLE_FIRE_SPEED, twelve_gauge_shell, ModConstant.REMMINGTON_M870_DMG, 8);
	public static GunItem sawn_off_double_barrel_12g = new GunItem(ModConstant.SINGLE_FIRE_SPEED, twelve_gauge_shell, ModConstant.SAWN_OFF_DBL_BRL_DMG, 2);
	public static GunItem double_barrel_12g = new GunItem(ModConstant.SINGLE_FIRE_SPEED, twelve_gauge_shell, ModConstant.DBL_BRL_DMG, 2);
	public static GunItem sawn_off_remmington_m870 = new GunItem(ModConstant.SINGLE_FIRE_SPEED, twelve_gauge_shell, ModConstant.REMMINGTON_M870_DMG, 8);
}
