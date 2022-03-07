package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.RocketGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWExplosiveProjectile {
	
	public static BaseGunItem bazooka = new RocketGunItem(ModConstant.fireRate.SINGLE_FIRE, TMWItems.rocket_bazooka, 
			ModConstant.weaponBalance.bazooka.EXPLOSION_RADIUS, 1, ModConstant.weaponBalance.bazooka.SPEED);
}
