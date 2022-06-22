package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWRifles 
{
	public static BaseGunItem springfield_saint_556 = new BaseGunItem(BalanceConfig.SAINT_AR_556_FIRE_RATE.get(), 
															BalanceConfig.SAINT_AR_556_RLD_TIME.get(), TMWItems.magazine_556, 
															TMWItems.round_556, BalanceConfig.SAINT_AR_556_DMG.get().floatValue(),
															BalanceConfig.SAINT_AR_556_SPD.get().floatValue(), 
															BalanceConfig.SAINT_AR_556_SPRD.get().floatValue());
	
	public static BaseGunItem springfield_saint_223 = new BaseGunItem(BalanceConfig.SAINT_AR_223_FIRE_RATE.get(), 
															BalanceConfig.SAINT_AR_223_RLD_TIME.get(), TMWItems.magazine_223, 
															TMWItems.round_223, BalanceConfig.SAINT_AR_223_DMG.get().floatValue(), 
															BalanceConfig.SAINT_AR_223_SPD.get().floatValue(), 
															BalanceConfig.SAINT_AR_223_SPRD.get().floatValue());
}
