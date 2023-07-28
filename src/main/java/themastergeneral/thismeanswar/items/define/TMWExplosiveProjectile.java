package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.RocketGunItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;

public class TMWExplosiveProjectile {
	
	public static AbstractGunItem bazooka = new RocketGunItem(Constants.bazookaFireRate, TMWItems.rocket_bazooka, 
												Constants.bazookaDamage, 1, 
												Constants.bazookaSpeed);
	
	public static AbstractGunItem volcanic_thunder = new 
														RocketGunItem(
															Constants.fireRateAuto, 
															TMWItems.round_40mm, 
															Constants.grenadeDamage, 
															6, 
															Constants.bazookaSpeed + Constants.grenadeDamage);
}
