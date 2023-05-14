package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.RocketGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWExplosiveProjectile {
	
	public static AbstractGunItem bazooka = new RocketGunItem(Constants.bazookaFireRate, TMWItems.rocket_bazooka, 
												Constants.bazookaDamage, 1, 
												Constants.bazookaSpeed);
}
