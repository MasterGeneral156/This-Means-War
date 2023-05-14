package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractContactThrowable;

public class TMWThrowables {
	public static AbstractContactThrowable dynamite_stick = new AbstractContactThrowable(Constants.dynamiteStickDamage);
	
	public static AbstractContactThrowable nuclear_warhead = new AbstractContactThrowable(Constants.nukeDamage);
	
	public static AbstractContactThrowable hand_grenade = new AbstractContactThrowable(Constants.grenadeDamage);
}
