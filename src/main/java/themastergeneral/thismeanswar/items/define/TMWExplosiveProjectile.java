package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.RocketGunItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;

public class TMWExplosiveProjectile {

	public static RocketGunItem bazooka = new RocketGunItem(
			Constants.bazookaFireRate, TMWItems.rocket_bazooka,
			Constants.bazookaDamage, 1, Constants.bazookaSpeed,
			Constants.baseSpread);

	public static RocketGunItem volcanic_thunder = new RocketGunItem(
			Constants.fireRateAuto, TMWItems.round_40mm,
			Constants.grenadeDamage, 6, Constants.bazookaSpeed + Constants.grenadeDamage,
			Constants.baseSpread * 1.12F);
}
