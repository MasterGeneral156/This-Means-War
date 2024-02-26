package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.ExternalAmmoGunItem;
import themastergeneral.thismeanswar.items.InternalAmmoGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWPistols 
{
	public static ExternalAmmoGunItem beretta_92_fs = new ExternalAmmoGunItem(
		Constants.beretta92FireRate, Constants.beretta92ReloadTime, 
		TMWItems.magazine_9mm, Constants.beretta92Damage,
		Constants.beretta92Speed, Constants.beretta92Spread);
	
	public static ExternalAmmoGunItem glock_26 = new ExternalAmmoGunItem(
		Constants.glock26FireRate, Constants.glock26ReloadTime, 
		TMWItems.magazine_9mm, Constants.glock26Damage, 
		Constants.glock26Speed,Constants.glock26Spread);
	
	public static ExternalAmmoGunItem m1911 = new ExternalAmmoGunItem(
		Constants.m1911FireRate, Constants.m1911ReloadTime, 
		TMWItems.magazine_m1911, Constants.m1911Damage, 
		Constants.m1911Speed,Constants.m1911Spread);
	
	public static ExternalAmmoGunItem m17_viper = new ExternalAmmoGunItem(
		Constants.m17viperFireRate, Constants.m17viperReloadTime, 
		TMWItems.magazine_9mm_short, Constants.m17viperDamage, 
		Constants.m17viperSpeed, Constants.m17viperSpread);
	
	public static ExternalAmmoGunItem mauser_c98 = new ExternalAmmoGunItem(
		Constants.c98viperFireRate, Constants.c98viperReloadTime, 
		TMWItems.magazine_9mm_clip, Constants.c98viperDamage, 
		Constants.c98viperSpeed, Constants.c98viperSpread);
	
	public static InternalAmmoGunItem vespera = new InternalAmmoGunItem(
		Constants.vesperaFireRate, TMWItems.round_38spec, 
		Constants.vesperaDamage, 5, 
		Constants.vesperaSpeed, Constants.vesperaSpread);
	
	public static InternalAmmoGunItem mcstubby = new InternalAmmoGunItem(
		Constants.mcstubbyFireRate, TMWItems.round_9mm, 
		Constants.mcstubbyDamage, 2, 
		Constants.mcstubbySpeed, Constants.mcstubbySpread);
}
