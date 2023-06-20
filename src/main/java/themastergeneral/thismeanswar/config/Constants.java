package themastergeneral.thismeanswar.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.tags.ITag;
import themastergeneral.thismeanswar.TMWMain;

public class Constants 
{
	public static int fireRateAuto = 1;
	public static int fireRateSemi = 2;
	public static int fireRateBurst = 3;
	
	public static int projectileKillTime = 1200;
	public static int cooldownThrowable = 15;
	public static int cooldownSemiAuto = 25;
	public static float sawnOffBonusSpread = 5.1356F;
	public static float sawnOffBonusDamage = 2.682F;
	public static float baseSpread = 1.4F;
	public static float directHitExplosionMulti = 10F;
	
	public static int external_mag = 1;
	public static int internal_mag = 2;
	
	public static float modVolume = 0.25F;
	
	//Upgrade constants
	public static int bulletUpgradeNull = 0;
	public static int bulletUpgradeAP = 1;
	public static int bulletUpgradeFire = 2;
	public static int bulletUpgradeTracer = 3;
	
	//Magazine Sizes
	public static int magSize9mm = 15;
	public static int magSizeLarge9mm = 30;
	public static int magSizeAR15 = 30;
	public static int magSize1911 = 7;
	public static int magSizeClips = 5;
	public static int magSizeEnergyCell = 19;
	
	//Mag Upgrade
	public static int maxMagUpgrades = 5;
	public static double magIncreasePerLevel = 0.15;
	public static double magDamageDecrease = 0.03;
	
	//Base Round Damage
	public static float baseDamage9mm = 3.4612F;
	public static float baseDamage45 = 6.862F;
	public static float baseDamage556 = 7.2348F;
	public static float baseDamage223 = 4.015F;
	public static float baseDamage12g = 5.167F;
	public static float baseDamageEnergy = 8.9124F;
	
	//Base Round Speed
	public static float baseSpeed9mm = 7.81243F;
	public static float baseSpeed45 = 11.214F;
	public static float baseSpeed556 = 11.454F;
	public static float baseSpeed223 = 8.241F;
	public static float baseSpeed12g = 52.81243F;
	public static float baseSpeedEnergy = 75.34F;
	
	//Base Round Spread
	public static float baseSpread9mm = 1.011F;
	public static float baseSpread45 = 1.023F;
	public static float baseSpread556 = 1.655F;
	public static float baseSpread223 = 1.414F;
	public static float baseSpread12g = 1.07F;
	public static float baseSpreadEnergy = 0.71F;

	//TMG Carbine
	public static int tmgCarbineFireRate = fireRateAuto;
	public static int tmgCarbineReloadTime = 36;
	public static float tmgCarbineDamage = 2.0112F + baseDamage9mm;
	public static float tmgCarbineSpeed = 11.49043F + baseSpeed9mm;
	public static float tmgCarbineSpread = 0.981F + baseSpread9mm;
	
	//UMP9
	public static int ump9FireRate = fireRateAuto;
	public static int ump9ReloadTime = 48;
	public static float ump9Damage = 2.345F + baseDamage9mm;
	public static float ump9Speed = 9.01521F + baseSpeed9mm;
	public static float ump9Spread = 0.975F + baseSpread9mm;
	
	//Uzi
	public static int uziFireRate = fireRateAuto;
	public static int uziReloadTime = 25;
	public static float uziDamage = 1.3511F + baseDamage9mm;
	public static float uziSpeed = 21.5152F + baseSpeed9mm;
	public static float uziSpread = 3.511F + baseSpread9mm;
	
	//AR15 (223)
	public static int ar15FireRate223 = cooldownSemiAuto;
	public static int ar15ReloadTime223 = 45;
	public static float ar15Damage223 = 2.526F + baseDamage223;
	public static float ar15Speed223 = 11.919F + baseSpeed223;
	public static float ar15Spread223 = 1.384F + baseSpread223;
	
	//AR15 (556)
	public static int ar15FireRate556 = cooldownSemiAuto;
	public static int ar15ReloadTime556 = 45;
	public static float ar15Damage556 = 5.7848F + baseDamage556;
	public static float ar15Speed556 = 15.312F + baseSpeed556;
	public static float ar15Spread556 = 1.625F + baseSpread556;
	
	//Bazooka
	public static int bazookaFireRate = cooldownSemiAuto;
	public static int bazookaReloadTime = 35;
	public static float bazookaDamage = 5.75F;
	public static float bazookaSpeed = 50.53F;
	
	//Beretta 92
	public static int beretta92FireRate = cooldownSemiAuto;
	public static int beretta92ReloadTime = 50;
	public static float beretta92Damage = 6.1757F + baseDamage9mm;
	public static float beretta92Speed = 7.71243F + baseSpeed9mm;
	public static float beretta92Spread = 0.99F + baseSpread9mm;
	
	//M17 'Viper'
	public static int m17viperFireRate = fireRateAuto;
	public static int m17viperReloadTime = 30;
	public static float m17viperDamage = 2.412F + baseDamage9mm;
	public static float m17viperSpeed = 3.41235F + baseSpeed9mm;
	public static float m17viperSpread = 1.45F + baseSpread9mm;
	
	//Glock 26
	public static int glock26FireRate = cooldownSemiAuto;
	public static int glock26ReloadTime = 40;
	public static float glock26Damage = 5.77687F + baseDamage9mm;
	public static float glock26Speed = 7.97243F + baseSpeed9mm;
	public static float glock26Spread = 1.028F + baseSpread9mm;
	
	//M1911
	public static int m1911FireRate = cooldownSemiAuto;
	public static int m1911ReloadTime = 54;
	public static float m1911Damage = 7.1245F + baseDamage45;
	public static float m1911Speed = 14.124F + baseSpeed45;
	public static float m1911Spread = 1.1F + baseSpread45;
	
	//Thuderclaw 45
	public static int thunderclawireRate = cooldownSemiAuto;
	public static int thunderclawReloadTime = 35;
	public static float thunderclawDamage = 4.671F + baseDamage45;
	public static float thunderclawSpeed = 21.4123F + baseSpeed45;
	public static float thunderclawSpread = 0.86F + baseSpread45;
	
	//Quantum Disruptor
	public static int quantumDisruptorRate = cooldownSemiAuto;
	public static int quantumDisruptorReloadTime = 70;
	public static float quantumDisruptorDamage = 1.454F + baseDamageEnergy;
	public static float quantumDisruptorSpeed = 34.6434124F + baseSpeedEnergy;
	public static float quantumDisruptorSpread = 0.01F + baseSpreadEnergy;
	
	//Quantum Disruptor
	public static int bfg8001Rate = cooldownSemiAuto;
	public static int bfg8001ReloadTime = 70;
	public static float bfg8001Damage = 11.4124F + baseDamageEnergy;
	public static float bfg8001Speed = 0.124F + baseSpeedEnergy;
	public static float bfg8001Spread = 0F + baseSpreadEnergy;
	public static int bfg8001MaxAmmo = 100;
	public static int bfg8001Cost = 5;
	
	//M870
	public static int m870FireRate = cooldownSemiAuto;
	public static int m870ReloadTime = 8;
	public static float m870Damage = 10.3231F + baseDamage12g;
	public static float m870Speed = 52.65243F + baseSpeed12g;
	public static float m870Spread = 1.13F + baseSpread12g;
	
	//Double Barrel
	public static int doubleBarrelFireRate = cooldownSemiAuto;
	public static int doubleBarrelReloadTime = 8;
	public static float doubleBarrelDamage = 11.479F + baseDamage12g;
	public static float doubleBarrelSpeed = 56.36843F + baseSpeed12g;
	public static float doubleBarrelSpread = 1.5F + baseSpread12g;
	
	//Throwables
	public static float grenadeDamage = 1.75F;
	public static float dynamiteStickDamage = 0.44F;
	public static float nukeDamage = 345.67F;
}
