package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines 
{
	public static BaseGunItem tmg_carbine = new BaseGunItem(
													BalanceConfig.FIRE_RATE_AUTO.get(), 
													BalanceConfig.TMG_CARBINE_RLD_TIME.get(), 
													TMWItems.magazine_9mm_large, TMWItems.round_9mm, 
													BalanceConfig.TMG_CARBINE_DMG.get().floatValue(),
													BalanceConfig.TMG_CARBINE_SPD.get().floatValue(),
													BalanceConfig.TMG_CARBINE_SPRD.get().floatValue());
}
