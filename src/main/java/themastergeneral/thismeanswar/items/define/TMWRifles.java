package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWRifles {

	public static BaseGunItem springfield_saint_556 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 33, TMWItems.magazine_556, 
			TMWItems.round_556, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
	
	public static BaseGunItem springfield_saint_223 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 34, TMWItems.magazine_223, 
			TMWItems.round_556, ModConstant.weaponBalance.tmg_carbine.DMG, 
			ModConstant.weaponBalance.tmg_carbine.SPD, 
			ModConstant.weaponBalance.tmg_carbine.SPRD);
}
