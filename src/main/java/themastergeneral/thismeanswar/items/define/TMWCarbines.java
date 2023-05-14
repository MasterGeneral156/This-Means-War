package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines 
{
	public static AbstractGunItem tmg_carbine = new AbstractGunItem(
													Constants.tmgCarbineFireRate, 
													Constants.tmgCarbineReloadTime,
													TMWItems.magazine_9mm_large, TMWItems.round_9mm, 
													Constants.tmgCarbineDamage,
													Constants.tmgCarbineSpeed,
													Constants.tmgCarbineSpread);
}
