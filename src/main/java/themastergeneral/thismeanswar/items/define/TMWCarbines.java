package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWCarbines {
	
		public static BaseGunItem tmg_carbine = new BaseGunItem(ModConstant.fireRate.AUTO_FIRE, 38, TMWItems.nine_mm_magazine_large, 
				TMWItems.nine_mm_round, ModConstant.weaponBalance.tmg_carbine.DMG, 
				ModConstant.weaponBalance.tmg_carbine.SPD, 
				ModConstant.weaponBalance.tmg_carbine.SPRD);
}
