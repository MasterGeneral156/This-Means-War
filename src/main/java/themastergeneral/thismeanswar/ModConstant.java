package themastergeneral.thismeanswar;

public class ModConstant {
	
	//Fire Speed
	public static int SINGLE_FIRE_SPEED = 10;
	public static int AUTO_FIRE_SPEED = 2;
	public static int GRENADE_THROW_COOLDOWN = 15;
	
	//Misc
	public static int BULLET_KILL_TICKS = 2;
	public static int CONTACT_EXPLOSIVE_KILL_TICKS = 200;
	
	//Base Bullet Damage
	public static float NINE_MM_BASE_DMG = 3.4612F;
	public static float TWELVE_GAUGE_BASE_DMG = 5.167F;
	
	
	//Damage balance
	
	//Pistol
	public static float BERETTA_FS_DMG = 2.7145F + NINE_MM_BASE_DMG;
	public static float GLOCK_26_DMG = 2.31567F + NINE_MM_BASE_DMG;
	
	//Shotgun
	public static float REMMINGTON_M870_DMG = 5.1561F + TWELVE_GAUGE_BASE_DMG;
	public static float SAWN_OFF_REMMINGTON_M870_DMG = REMMINGTON_M870_DMG + 1.55F;
	public static float DBL_BRL_DMG = 6.312F + TWELVE_GAUGE_BASE_DMG;
	public static float SAWN_OFF_DBL_BRL_DMG = DBL_BRL_DMG + 1.864F;
	
	//Carbine
	public static float TMG_CARBINE_DMG = 1.784F + NINE_MM_BASE_DMG;
	
	//Throwables
	public static float DYNAMITE_STICK_DMG = 0.44F;
	
}
