package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.ExternalAmmoGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines 
{
	public static ExternalAmmoGunItem tmg_carbine = new ExternalAmmoGunItem(
														Constants.tmgCarbineFireRate, Constants.tmgCarbineReloadTime,
														TMWItems.magazine_9mm_large,  Constants.tmgCarbineDamage,
														Constants.tmgCarbineSpeed, Constants.tmgCarbineSpread);
	
	public static ExternalAmmoGunItem ump9 = new ExternalAmmoGunItem(
			Constants.ump9FireRate, 
			Constants.ump9ReloadTime,
			TMWItems.magazine_9mm_large, 
			Constants.ump9Damage,
			Constants.ump9Speed,
			Constants.ump9Spread);
	
	public static ExternalAmmoGunItem uzi = new ExternalAmmoGunItem(
			Constants.uziFireRate, 
			Constants.uziReloadTime,
			TMWItems.magazine_9mm_large, 
			Constants.uziDamage,
			Constants.uziSpeed,
			Constants.uziSpread);
	
	public static ExternalAmmoGunItem g36 = new ExternalAmmoGunItem(
			Constants.g36FireRate, 
			Constants.g36ReloadTime,
			TMWItems.magazine_g36, 
			Constants.g36Damage,
			Constants.g36Speed,
			Constants.g36Spread);
}
