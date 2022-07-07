package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWPistols 
{
	public static AbstractGunItem beretta_92_fs = new AbstractGunItem(BalanceConfig.BERETTA_92_FS_FIRE_RATE.get(), 
													BalanceConfig.BERETTA_92_FS_RLD_TIME.get(), TMWItems.magazine_9mm, 
													TMWItems.round_9mm, BalanceConfig.BERETTA_92_FS_DMG.get().floatValue(),
													BalanceConfig.BERETTA_92_FS_SPD.get().floatValue(),
													BalanceConfig.BERETTA_92_FS_SPRD.get().floatValue());
	
	public static AbstractGunItem glock_26 = new AbstractGunItem(BalanceConfig.GLOCK_26_FIRE_RATE.get(), 
												BalanceConfig.GLOCK_26_RLD_TIME.get(), TMWItems.magazine_9mm, 
												TMWItems.round_9mm, BalanceConfig.GLOCK_26_DMG.get().floatValue(), 
												BalanceConfig.GLOCK_26_SPD.get().floatValue(),
												BalanceConfig.GLOCK_26_SPRD.get().floatValue());
	
	public static AbstractGunItem m1911 = new AbstractGunItem(BalanceConfig.M1911_FIRE_RATE.get(), 
			BalanceConfig.M1911_RLD_TIME.get(), TMWItems.magazine_m1911, 
			TMWItems.round_45, BalanceConfig.M1911_DMG.get().floatValue(), 
			BalanceConfig.M1911_SPD.get().floatValue(),
			BalanceConfig.M1911_SPRD.get().floatValue());
}
