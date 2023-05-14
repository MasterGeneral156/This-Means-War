package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWPistols 
{
	public static AbstractGunItem beretta_92_fs = new AbstractGunItem(Constants.beretta92FireRate, 
													Constants.beretta92ReloadTime, TMWItems.magazine_9mm, 
													TMWItems.round_9mm, Constants.beretta92Damage,
													Constants.beretta92Speed,
													Constants.beretta92Spread);
	
	public static AbstractGunItem glock_26 = new AbstractGunItem(Constants.glock26FireRate, 
												Constants.glock26ReloadTime, TMWItems.magazine_9mm, 
												TMWItems.round_9mm, Constants.glock26Damage, 
												Constants.glock26Speed,
												Constants.glock26Spread);
	
	public static AbstractGunItem m1911 = new AbstractGunItem(Constants.m1911FireRate, 
			Constants.m1911ReloadTime, TMWItems.magazine_m1911, 
			TMWItems.round_45, Constants.m1911Damage, 
			Constants.m1911Speed,
			Constants.m1911Spread);
}
