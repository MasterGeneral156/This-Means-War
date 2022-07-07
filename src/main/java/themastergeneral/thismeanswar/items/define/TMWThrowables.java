package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.AbstractContactThrowable;

public class TMWThrowables {
	public static AbstractContactThrowable dynamite_stick = new AbstractContactThrowable(BalanceConfig.DYNA_STICK_DMG.get().floatValue());
	
	public static AbstractContactThrowable nuclear_warhead = new AbstractContactThrowable(BalanceConfig.NUKE_DMG.get().floatValue());
	
	public static AbstractContactThrowable hand_grenade = new AbstractContactThrowable(BalanceConfig.GRENADE_DMG.get().floatValue());
}
