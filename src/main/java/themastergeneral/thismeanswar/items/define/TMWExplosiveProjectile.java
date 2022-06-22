package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.RocketGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWExplosiveProjectile {
	
	public static BaseGunItem bazooka = new RocketGunItem(BalanceConfig.BAZOOKA_FIRE_RATE.get(), TMWItems.rocket_bazooka, 
												BalanceConfig.BAZOOKA_DMG.get().floatValue(), 1, 
												BalanceConfig.BAZOOKA_SPD.get().floatValue());
}
