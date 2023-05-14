package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWShotguns {

	public static AbstractGunItem remmington_m870 = new AbstractGunItem(Constants.m870FireRate, TMWItems.round_12g, 
														Constants.m870Damage, 8, 
														Constants.m870Speed, 
														Constants.m870Spread);
	
	public static AbstractGunItem sawn_off_double_barrel_12g = new AbstractGunItem(Constants.doubleBarrelFireRate, TMWItems.round_12g, 
																	Constants.doubleBarrelDamage, 2, 
																	Constants.doubleBarrelSpeed, 
																	Constants.doubleBarrelSpread);
	
	public static AbstractGunItem double_barrel_12g = new AbstractGunItem(Constants.doubleBarrelFireRate, TMWItems.round_12g, 
																	Constants.doubleBarrelDamage + Constants.sawnOffBonusDamage, 2, 
																	Constants.doubleBarrelSpeed, 
																	Constants.doubleBarrelSpread + Constants.sawnOffBonusSpread);
	
	public static AbstractGunItem sawn_off_remmington_m870 = new AbstractGunItem(Constants.m870FireRate, TMWItems.round_12g, 
			Constants.m870Damage + Constants.sawnOffBonusDamage, 8, 
			Constants.m870Speed, 
			Constants.m870Spread + Constants.sawnOffBonusSpread);
}
