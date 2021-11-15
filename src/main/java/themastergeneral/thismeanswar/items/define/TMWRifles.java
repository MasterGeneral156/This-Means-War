package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWRifles {

	public static BaseGunItem springfield_saint_556 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 33, TMWItems.m4ar_mag_556, 
			TMWItems.five_five_six_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	public static BaseGunItem springfield_saint_223 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 34, TMWItems.m4ar_mag_223, 
			TMWItems.two_two_three_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
}
