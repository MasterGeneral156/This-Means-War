package themastergeneral.thismeanswar.config;

public class Constants 
{
	public static int fireRateAuto = 1;
	public static int fireRateSemi = 5;
	public static int cooldownThrowable = 15;
	public static float sawnOffBonusSpread = 5.1356F;
	public static float sawnOffBonusDamage = 2.682F;
	public static float baseSpread = 1.4F;
	
	//Magazine Sizes
	public static int magSize9mm = 15;
	public static int magSizeLarge9mm = 30;
	public static int magSizeAR15 = 30;
	public static int magSize1911 = 7;
	
	//Mag Upgrade
	public static int maxMagUpgrades = 5;
	public static double magIncreasePerLevel = 0.15;
	
	//Base Round Damage
	public static float baseDamage9mm = 3.4612F;
	public static float baseDamage45 = 6.862F;
	public static float baseDamage556 = 7.2348F;
	public static float baseDamage223 = 4.015F;
	public static float baseDamage12g = 5.167F;
	
	//Base Round Speed
	public static float baseSpeed9mm = 7.81243F;
	public static float baseSpeed45 = 11.214F;
	public static float baseSpeed556 = 11.454F;
	public static float baseSpeed223 = 8.241F;
	public static float baseSpeed12g = 52.81243F;
	
	//Base Round Spread
	public static float baseSpread9mm = 1.011F;
	public static float baseSpread45 = 1.023F;
	public static float baseSpread556 = 1.655F;
	public static float baseSpread223 = 1.414F;
	public static float baseSpread12g = 1.07F;

	//TMG Carbine
	public static int tmgCarbineFireRate = fireRateAuto;
	public static int tmgCarbineReloadTime = 36;
	public static float tmgCarbineDamage = 2.0112F + baseDamage9mm;
	public static float tmgCarbineSpeed = 11.49043F + baseSpeed9mm;
	public static float tmgCarbineSpread = 0.981F + baseSpread9mm;
	
	//AR15 (223)
	public static int ar15FireRate223 = fireRateSemi;
	public static int ar15ReloadTime223 = 45;
	public static float ar15Damage223 = 2.526F + baseDamage223;
	public static float ar15Speed223 = 11.919F + baseSpeed223;
	public static float ar15Spread223 = 1.384F + baseSpread223;
	
	//AR15 (556)
	public static int ar15FireRate556 = fireRateSemi;
	public static int ar15ReloadTime556 = 45;
	public static float ar15Damage556 = 5.7848F + baseDamage556;
	public static float ar15Speed556 = 15.312F + baseSpeed556;
	public static float ar15Spread556 = 1.625F + baseSpread556;
	
	//Bazooka
	public static int bazookaFireRate = fireRateSemi;
	public static int bazookaReloadTime = 35;
	public static float bazookaDamage = 5.75F;
	public static float bazookaSpeed = 50.53F;
	
	//Beretta 92
	public static int beretta92FireRate = fireRateSemi;
	public static int beretta92ReloadTime = 50;
	public static float beretta92Damage = 6.1757F + baseDamage9mm;
	public static float beretta92Speed = 7.71243F + baseSpeed9mm;
	public static float beretta92Spread = 0.99F + baseSpread9mm;
	
	//Glock 26
	public static int glock26FireRate = fireRateSemi;
	public static int glock26ReloadTime = 40;
	public static float glock26Damage = 5.77687F + baseDamage9mm;
	public static float glock26Speed = 7.97243F + baseSpeed9mm;
	public static float glock26Spread = 1.028F + baseSpread9mm;
	
	//M1911
	public static int m1911FireRate = fireRateSemi;
	public static int m1911ReloadTime = 54;
	public static float m1911Damage = 7.1245F + baseDamage45;
	public static float m1911Speed = 14.124F + baseSpeed45;
	public static float m1911Spread = 1.1F + baseSpread45;
	
	//M870
	public static int m870FireRate = fireRateSemi;
	public static int m870ReloadTime = 8;
	public static float m870Damage = 10.3231F + baseDamage12g;
	public static float m870Speed = 52.65243F + baseSpeed12g;
	public static float m870Spread = 1.13F + baseSpread12g;
	
	//Double Barrel
	public static int doubleBarrelFireRate = fireRateSemi;
	public static int doubleBarrelReloadTime = 8;
	public static float doubleBarrelDamage = 11.479F + baseDamage12g;
	public static float doubleBarrelSpeed = 56.36843F + baseSpeed12g;
	public static float doubleBarrelSpread = 1.5F + baseSpread12g;
	
	//Throwables
	public static float grenadeDamage = 1.75F;
	public static float dynamiteStickDamage = 0.44F;
	public static float nukeDamage = 345.67F;
}
