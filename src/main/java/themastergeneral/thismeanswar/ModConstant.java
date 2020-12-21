package themastergeneral.thismeanswar;

public class ModConstant {
	public static int SINGLE_FIRE_SPEED = 8;
	public static int AUTO_FIRE_SPEED = 3;
	
	//Base Bullet Damage
	public static float NINE_MM_BASE_DMG = 3.4612F;
	public static float TWELVE_GAUGE_BASE_DMG = 5.167F;
	
	//Damage balance
	public static float BERETTA_FS_DMG = 2.7145F + NINE_MM_BASE_DMG;
	public static float GLOCK_26_DMG = 2.31567F + NINE_MM_BASE_DMG;
	public static float REMMINGTON_M870_DMG = 4.1561F + TWELVE_GAUGE_BASE_DMG;
}
