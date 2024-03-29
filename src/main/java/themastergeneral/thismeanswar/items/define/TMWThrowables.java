package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.SmokeThrowableItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractContactThrowable;

public class TMWThrowables {
	public static AbstractContactThrowable dynamite_stick = new AbstractContactThrowable(Constants.dynamiteStickDamage);
	
	public static AbstractContactThrowable nuclear_warhead = new AbstractContactThrowable(Constants.nukeDamage);
	
	public static AbstractContactThrowable hand_grenade = new AbstractContactThrowable(Constants.grenadeDamage);
	public static AbstractContactThrowable stick_grenade = new AbstractContactThrowable(Constants.grenadeDamage);
	
	public static SmokeThrowableItem smoke_grenade_red = new SmokeThrowableItem(14423100);
	public static SmokeThrowableItem smoke_grenade_green = new SmokeThrowableItem(8388352);
	public static SmokeThrowableItem smoke_grenade_orange = new SmokeThrowableItem(13789470);
}
