package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines 
{
	public static NuGunItem tmg_carbine = new NuGunItem(
		Constants.tmgCarbineFireRate, Constants.tmgCarbineReloadTime,
		TMWItems.magazine_9mm_large,  Constants.tmgCarbineDamage,
		Constants.tmgCarbineSpeed, Constants.tmgCarbineSpread);
	
	public static NuGunItem mp40 = new NuGunItem(
		Constants.mp40FireRate, Constants.mp40ReloadTime,
		TMWItems.magazine_9mm_large,  Constants.mp40Damage,
		Constants.mp40Speed, Constants.mp40Spread);

	public static NuGunItem sten = new NuGunItem(
			Constants.mp40FireRate, Math.round(Constants.mp40ReloadTime*1.1F),
			TMWItems.magazine_9mm_large,  Constants.mp40Damage*1.2F,
			Constants.mp40Speed*0.86F, Constants.mp40Spread*1.15F);
	
	public static NuGunItem ump9 = new NuGunItem(
		Constants.ump9FireRate, Constants.ump9ReloadTime,
		TMWItems.magazine_9mm_large, Constants.ump9Damage,
		Constants.ump9Speed, Constants.ump9Spread);
	
	public static NuGunItem uzi = new NuGunItem(
		Constants.uziFireRate, Constants.uziReloadTime,
		TMWItems.magazine_9mm_large, Constants.uziDamage,
		Constants.uziSpeed, Constants.uziSpread);
	
	public static NuGunItem g36 = new NuGunItem(
		Constants.g36FireRate, Constants.g36ReloadTime,
		TMWItems.magazine_g36, Constants.g36Damage,
		Constants.g36Speed, Constants.g36Spread);
}
