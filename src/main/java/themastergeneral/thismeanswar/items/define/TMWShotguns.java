package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWShotguns {

	public static AbstractGunItem remmington_m870 = new AbstractGunItem(BalanceConfig.M870_FIRE_RATE.get(), TMWItems.round_12g, 
														BalanceConfig.M870_DMG.get().floatValue(), BalanceConfig.M870_RLD_TIME.get(), 
														BalanceConfig.M870_SPD.get().floatValue(), 
														BalanceConfig.M870_SPRD.get().floatValue());
	
	public static AbstractGunItem sawn_off_double_barrel_12g = new AbstractGunItem(BalanceConfig.SO_DOUBLE_BARREL_FIRE_RATE.get(), TMWItems.round_12g, 
																	BalanceConfig.SO_DOUBLE_BARREL_DMG.get().floatValue(), 2, 
																	BalanceConfig.SO_DOUBLE_BARREL_SPD.get().floatValue(), 
																	BalanceConfig.SO_DOUBLE_BARREL_SPRD.get().floatValue());
	
	public static AbstractGunItem double_barrel_12g = new AbstractGunItem(BalanceConfig.DOUBLE_BARREL_FIRE_RATE.get(), TMWItems.round_12g, 
															BalanceConfig.DOUBLE_BARREL_DMG.get().floatValue(), 2, 
															BalanceConfig.DOUBLE_BARREL_SPD.get().floatValue(), 
															BalanceConfig.DOUBLE_BARREL_SPRD.get().floatValue());
	
	public static AbstractGunItem sawn_off_remmington_m870 = new AbstractGunItem(BalanceConfig.SO_M870_FIRE_RATE.get(), 
																TMWItems.round_12g, BalanceConfig.SO_M870_DMG.get().floatValue(), 
																BalanceConfig.SO_M870_RLD_TIME.get(), 
																BalanceConfig.SO_M870_SPD.get().floatValue(), 
																BalanceConfig.SO_M870_SPRD.get().floatValue());
}
