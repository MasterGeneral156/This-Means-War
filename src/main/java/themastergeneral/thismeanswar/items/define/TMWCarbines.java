package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines 
{
	public static AbstractGunItem tmg_carbine = new AbstractGunItem(
													BalanceConfig.FIRE_RATE_AUTO.get(), 
													BalanceConfig.TMG_CARBINE_RLD_TIME.get(), 
													TMWItems.magazine_9mm_large, TMWItems.round_9mm, 
													BalanceConfig.TMG_CARBINE_DMG.get().floatValue(),
													BalanceConfig.TMG_CARBINE_SPD.get().floatValue(),
													BalanceConfig.TMG_CARBINE_SPRD.get().floatValue());
}
