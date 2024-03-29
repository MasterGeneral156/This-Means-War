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
	public static float sawnOffBonusSpread = 1.51356F;
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
	public static int bulletUpgradeInert = 4;
	
	//Magazine Sizes
	public static int magSize9mm = 15;
	public static int magSizeLarge9mm = 30;
	public static int magSizeShort9mm = 8;
	public static int magSizeDrum9mm = 64;
	
	public static int magSizeAR15 = 30;
	public static int magSizeLargeAR15 = 60;
	public static int magSizeShortAR15 = 15;
	public static int magSizeDrumAR15 = 100;
	
	public static int magSize1911 = 7;
	public static int magSizeDragunov = 10;
	public static int magSizeClips = 5;
	public static int magSizeEnergyCell = 19;
	public static int magSizeG36 = 25;
	public static int magSizeShortG36 = 10;
	public static int magSizeLargeG36 = 52;
	
	//Mag Upgrade
	public static int maxMagUpgrades = 5;
	public static double magIncreasePerLevel = 0.15;
	
	//Base Round Damage
	public static float baseDamage9mm = 3.8712F;				//updated 12/15/2023
	public static float baseDamage45 = 4.432F;					//updated 12/15/2023
	public static float baseDamage38spec = 4.944F;				//updated 12/15/2023
	public static float baseDamage223 = 6.432F;					//updated 12/15/2023
	public static float baseDamage556 = 8.3441F;				//updated 12/15/2023
	public static float baseDamage762 = 9.1244F;				//updated 12/23/2023
	public static float baseDamage12g = 11.8981F;				//updated 12/23/2023
	public static float baseDamageEnergy = 21.5124F;			//updated 12/23/2013
	
	//Base Round Speed
	public static float baseSpeed9mm = 14.81243F;
	public static float baseSpeed45 = 22.214F;
	public static float baseSpeed38spec = 38.324F;
	public static float baseSpeed223 = 45.241F;
	public static float baseSpeed556 = 66.454F;
	public static float baseSpeed762 = 78.971F;
	public static float baseSpeed12g = 104.81243F;
	public static float baseSpeedEnergy = 160.34F;
	
	//Base Round Spread
	public static float baseSpread9mm = 0.0272133F;				//updated 02/20/2024
	public static float baseSpread45 = 0.0378341F;				//updated 02/20/2024
	public static float baseSpread556 = 0.04415126F;			//updated 02/20/2024
	public static float baseSpread223 = 0.03947547F;			//updated 02/20/2024
	public static float baseSpread12g = 0.0714574579F;			//updated 02/20/2024
	public static float baseSpread762 = 0.0494404F;				//updated 02/20/2024
	public static float baseSpread38spec = 0.02814004F;			//updated 02/20/2024
	public static float baseSpreadEnergy = 0.0926561F;			//updated 02/20/2024

	//TMG Carbine
	public static int tmgCarbineFireRate = fireRateAuto;
	public static int tmgCarbineReloadTime = 36;
	public static float tmgCarbineDamage = 2.0112F + baseDamage9mm;
	public static float tmgCarbineSpeed = 11.49043F + baseSpeed9mm;
	public static float tmgCarbineSpread = baseSpread9mm * 0.981F;
	
	//MP40
	public static int mp40FireRate = fireRateAuto;
	public static int mp40ReloadTime = 55;
	public static float mp40Damage = 1.75355F + baseDamage9mm;
	public static float mp40Speed = 9.8144F + baseSpeed9mm;
	public static float mp40Spread = baseSpread9mm * 0.895F;
	
	//UMP9
	public static int ump9FireRate = fireRateAuto;
	public static int ump9ReloadTime = 48;
	public static float ump9Damage = 2.345F + baseDamage9mm;
	public static float ump9Speed = 9.01521F + baseSpeed9mm;
	public static float ump9Spread = baseSpread9mm * 0.975F;
	
	//Uzi
	public static int uziFireRate = fireRateAuto;
	public static int uziReloadTime = 25;
	public static float uziDamage = 1.3511F + baseDamage9mm;
	public static float uziSpeed = 21.5152F + baseSpeed9mm;
	public static float uziSpread = baseSpread9mm * 3.511F;
	
	//G36
	public static int g36FireRate = fireRateAuto;
	public static int g36ReloadTime = 36;
	public static float g36Damage = 2.5153F + baseDamage556;
	public static float g36Speed = 32.1532F + baseSpeed556;
	public static float g36Spread = baseSpread556 * 1.23525F;
	
	//AR15 (223)
	public static int ar15FireRate223 = cooldownSemiAuto;
	public static int ar15ReloadTime223 = 45;
	public static float ar15Damage223 = 2.526F + baseDamage223;
	public static float ar15Speed223 = 11.919F + baseSpeed223;
	public static float ar15Spread223 = baseSpread223 * 1.384F;
	
	//AR15 (556)
	public static int ar15FireRate556 = cooldownSemiAuto;
	public static int ar15ReloadTime556 = 45;
	public static float ar15Damage556 = 5.7848F + baseDamage556;
	public static float ar15Speed556 = 15.312F + baseSpeed556;
	public static float ar15Spread556 = baseSpread556 * 1.625F;
	
	//SCAR
	public static int scarFireRate = cooldownSemiAuto;
	public static int scarReloadTime = 50;
	public static float scarDamage = 5.7848F + baseDamage556;
	public static float scarSpeed = 16.451F + baseSpeed556;
	public static float scarSpread = baseSpread556 * 1.845F;
	
	//Mosin
	public static int mosinFireRate = cooldownSemiAuto;
	public static int mosinReloadTime = 65;
	public static float mosinDamage = 5.7848F + baseDamage223;
	public static float mosinSpeed = 16.451F + baseSpeed223;
	public static float mosinSpread = baseSpread223 * 1.845F;
	
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
	public static float beretta92Spread = baseSpread9mm * 0.99F;
	
	//BFG8k1
	public static int laserblasterFireRate = cooldownSemiAuto;
	public static int laserblasterReloadTime = 38;
	public static float laserblasterDamage = baseDamageEnergy * 0.7417f;
	public static float laserblasterSpeed = baseSpeedEnergy * 1.1f;
	public static float laserblasterSpread = baseSpreadEnergy * 1.4f;
	
	//M17 'Viper'
	public static int m17viperFireRate = fireRateAuto;
	public static int m17viperReloadTime = 30;
	public static float m17viperDamage = 2.412F + baseDamage9mm;
	public static float m17viperSpeed = 3.41235F + baseSpeed9mm;
	public static float m17viperSpread = baseSpread9mm * 1.45F;
	
	//Mc'Stubby
	public static int mcstubbyFireRate = cooldownSemiAuto;
	public static float mcstubbyDamage = 2.79114F + baseDamage9mm;
	public static float mcstubbySpeed = 2.98571F + baseSpeed9mm;
	public static float mcstubbySpread = baseSpread9mm * 1.65F;
	
	//Mauser C98
	public static int c98viperFireRate = fireRateAuto;
	public static int c98viperReloadTime = 10;
	public static float c98viperDamage = 1.962F + baseDamage9mm;
	public static float c98viperSpeed = 6.124F + baseSpeed9mm;
	public static float c98viperSpread = baseSpread9mm * 2.145F;
	
	//Glock 26
	public static int glock26FireRate = cooldownSemiAuto;
	public static int glock26ReloadTime = 40;
	public static float glock26Damage = 5.77687F + baseDamage9mm;
	public static float glock26Speed = 7.97243F + baseSpeed9mm;
	public static float glock26Spread = baseSpread9mm * 1.028F;
	
	//M1911
	public static int m1911FireRate = cooldownSemiAuto;
	public static int m1911ReloadTime = 54;
	public static float m1911Damage = 7.1245F + baseDamage45;
	public static float m1911Speed = 14.124F + baseSpeed45;
	public static float m1911Spread = baseSpread45 * 1.1F;
	
	//Thuderclaw 45
	public static int thunderclawireRate = cooldownSemiAuto;
	public static int thunderclawReloadTime = 35;
	public static float thunderclawDamage = 4.671F + baseDamage45;
	public static float thunderclawSpeed = 21.4123F + baseSpeed45;
	public static float thunderclawSpread = baseSpread45 * 0.86F;
	
	//Quantum Disruptor
	public static int quantumDisruptorRate = cooldownSemiAuto;
	public static int quantumDisruptorReloadTime = 70;
	public static float quantumDisruptorDamage = 1.454F + baseDamageEnergy;
	public static float quantumDisruptorSpeed = 34.6434124F + baseSpeedEnergy;
	public static float quantumDisruptorSpread = 0.01F + baseSpreadEnergy;
	
	//BFG8k1
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
	public static float m870Spread = baseSpread12g * 1.561F;
	
	//Winchester
	public static int winchesterFireRate = cooldownSemiAuto;
	public static int winchesterReloadTime = 10;
	public static float winchesterDamage = 9.914F + baseDamage12g;
	public static float winchesterSpeed = 65.65243F + baseSpeed12g;
	public static float winchesterSpread = baseSpread12g * 1.966F;

	//Double Barrel
	public static int doubleBarrelFireRate = cooldownSemiAuto;
	public static int doubleBarrelReloadTime = 8;
	public static float doubleBarrelDamage = 11.479F + baseDamage12g;
	public static float doubleBarrelSpeed = 56.36843F + baseSpeed12g;
	public static float doubleBarrelSpread = baseSpread12g * 1.8413F;
	
	//Vespera
	public static int vesperaFireRate = cooldownSemiAuto;
	public static int vesperaReloadTime = 5;
	public static float vesperaDamage = 11.479F + baseDamage38spec;
	public static float vesperaSpeed = 14.36843F + baseSpeed38spec;
	public static float vesperaSpread = baseSpread38spec * 1.25f;
	
	//Dragunov
	public static int dragunovFireRate = cooldownSemiAuto;
	public static int dragunovReloadTime = 35;
	public static float dragunovDamage = 11.479F + baseDamage762;
	public static float dragunovSpeed = 14.36843F + baseSpeed762;
	public static float dragunovSpread = baseSpread762 * 0.9841F;
	
	//K98
	public static int k98FireRate = cooldownSemiAuto;
	public static int k98ReloadTime = 15;
	public static float k98Damage = 12.191F + baseDamage762;
	public static float k98Speed = 11.36843F + baseSpeed762;
	public static float k98Spread = baseSpread762 * 1.0133F;
	
	//Throwables
	public static float grenadeDamage = 1.75F;
	public static float dynamiteStickDamage = 0.44F;
	public static float nukeDamage = 345.67F;
}
