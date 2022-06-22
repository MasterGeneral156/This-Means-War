package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWShotguns {

	public static BaseGunItem remmington_m870 = new BaseGunItem(BalanceConfig.M870_FIRE_RATE.get(), TMWItems.round_12g, 
														BalanceConfig.M870_DMG.get().floatValue(), BalanceConfig.M870_RLD_TIME.get(), 
														BalanceConfig.M870_SPD.get().floatValue(), 
														BalanceConfig.M870_SPRD.get().floatValue());
	
	public static BaseGunItem sawn_off_double_barrel_12g = new BaseGunItem(BalanceConfig.SO_DOUBLE_BARREL_FIRE_RATE.get(), TMWItems.round_12g, 
																	BalanceConfig.SO_DOUBLE_BARREL_DMG.get().floatValue(), 2, 
																	BalanceConfig.SO_DOUBLE_BARREL_SPD.get().floatValue(), 
																	BalanceConfig.SO_DOUBLE_BARREL_SPRD.get().floatValue());
	
	public static BaseGunItem double_barrel_12g = new BaseGunItem(BalanceConfig.DOUBLE_BARREL_FIRE_RATE.get(), TMWItems.round_12g, 
															BalanceConfig.DOUBLE_BARREL_DMG.get().floatValue(), 2, 
															BalanceConfig.DOUBLE_BARREL_SPD.get().floatValue(), 
															BalanceConfig.DOUBLE_BARREL_SPRD.get().floatValue());
	
	public static BaseGunItem sawn_off_remmington_m870 = new BaseGunItem(BalanceConfig.SO_M870_FIRE_RATE.get(), 
																TMWItems.round_12g, BalanceConfig.SO_M870_DMG.get().floatValue(), 
																BalanceConfig.SO_M870_RLD_TIME.get(), 
																BalanceConfig.SO_M870_SPD.get().floatValue(), 
																BalanceConfig.SO_M870_SPRD.get().floatValue());
}
