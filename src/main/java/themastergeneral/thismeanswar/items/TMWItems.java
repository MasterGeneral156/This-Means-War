package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.ModConstant;

public class TMWItems {
	
	public static BulletItem nine_mm_round = new BulletItem();
	
	public static MagazineItem nine_mm_magazine = new MagazineItem(nine_mm_round, 15);

	public static GunItem beretta_92_fs = new GunItem(ModConstant.SINGLE_FIRE_SPEED, 35, nine_mm_magazine, nine_mm_round, ModConstant.BERETTA_FS_DMG);
}
