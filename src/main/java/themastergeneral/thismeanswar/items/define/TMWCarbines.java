package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines 
{
	public static AbstractGunItem tmg_carbine = new AbstractGunItem(
													Constants.tmgCarbineFireRate, 
													Constants.tmgCarbineReloadTime,
													TMWItems.magazine_9mm_large, TMWItems.round_9mm, 
													Constants.tmgCarbineDamage,
													Constants.tmgCarbineSpeed,
													Constants.tmgCarbineSpread);
	
	public static AbstractGunItem ump9 = new AbstractGunItem(
			Constants.ump9FireRate, 
			Constants.ump9ReloadTime,
			TMWItems.magazine_9mm_large, TMWItems.round_9mm, 
			Constants.ump9Damage,
			Constants.ump9Speed,
			Constants.ump9Spread);
	
	public static AbstractGunItem uzi = new AbstractGunItem(
			Constants.uziFireRate, 
			Constants.uziReloadTime,
			TMWItems.magazine_9mm_large, TMWItems.round_9mm, 
			Constants.uziDamage,
			Constants.uziSpeed,
			Constants.uziSpread);
	
	public static AbstractGunItem g36 = new AbstractGunItem(
			Constants.g36FireRate, 
			Constants.g36ReloadTime,
			TMWItems.magazine_556, TMWItems.round_556, 
			Constants.g36Damage,
			Constants.g36Speed,
			Constants.g36Spread);
}
