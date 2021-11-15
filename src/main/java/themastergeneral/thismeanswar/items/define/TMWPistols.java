package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWPistols {

		public static BaseGunItem beretta_92_fs = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 35, TMWItems.nine_mm_magazine, 
				TMWItems.nine_mm_round, ModConstant.weaponBalance.beretta_fs.DMG,
					ModConstant.weaponBalance.beretta_fs.SPD,
					ModConstant.weaponBalance.beretta_fs.SPRD);
		
		public static BaseGunItem glock_26 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, 20, TMWItems.nine_mm_magazine, 
				TMWItems.nine_mm_round, ModConstant.weaponBalance.glock_26.DMG, 
					ModConstant.weaponBalance.glock_26.SPD,
					ModConstant.weaponBalance.glock_26.SPRD);
}
