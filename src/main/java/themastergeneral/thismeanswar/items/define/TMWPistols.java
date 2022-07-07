package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWPistols 
{
	public static BaseGunItem beretta_92_fs = new BaseGunItem(BalanceConfig.BERETTA_92_FS_FIRE_RATE.get(), 
													BalanceConfig.BERETTA_92_FS_RLD_TIME.get(), TMWItems.magazine_9mm, 
													TMWItems.round_9mm, BalanceConfig.BERETTA_92_FS_DMG.get().floatValue(),
													BalanceConfig.BERETTA_92_FS_SPD.get().floatValue(),
													BalanceConfig.BERETTA_92_FS_SPRD.get().floatValue());
	
	public static BaseGunItem glock_26 = new BaseGunItem(BalanceConfig.GLOCK_26_FIRE_RATE.get(), 
												BalanceConfig.GLOCK_26_RLD_TIME.get(), TMWItems.magazine_9mm, 
												TMWItems.round_9mm, BalanceConfig.GLOCK_26_DMG.get().floatValue(), 
												BalanceConfig.GLOCK_26_SPD.get().floatValue(),
												BalanceConfig.GLOCK_26_SPRD.get().floatValue());
	
	public static BaseGunItem m1911 = new BaseGunItem(BalanceConfig.M1911_FIRE_RATE.get(), 
			BalanceConfig.M1911_RLD_TIME.get(), TMWItems.magazine_m1911, 
			TMWItems.round_45, BalanceConfig.M1911_DMG.get().floatValue(), 
			BalanceConfig.M1911_SPD.get().floatValue(),
			BalanceConfig.M1911_SPRD.get().floatValue());
}
