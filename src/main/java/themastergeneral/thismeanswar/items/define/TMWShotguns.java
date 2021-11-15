package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.items.BaseGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWShotguns {

	public static BaseGunItem remmington_m870 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, TMWItems.tweleve_gauge_shell, 
			ModConstant.weaponBalance.remmington_m870.DMG, 8, 
			ModConstant.weaponBalance.remmington_m870.SPD, 
			ModConstant.weaponBalance.remmington_m870.SPRD);
	
	public static BaseGunItem sawn_off_double_barrel_12g = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, TMWItems.tweleve_gauge_shell, 
			ModConstant.weaponBalance.double_barrel_shotgun_sawn_off.DMG, 2, 
			ModConstant.weaponBalance.double_barrel_shotgun_sawn_off.SPD, 
			ModConstant.weaponBalance.double_barrel_shotgun_sawn_off.SPRD);
	
	public static BaseGunItem double_barrel_12g = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, TMWItems.tweleve_gauge_shell, 
			ModConstant.weaponBalance.double_barrel_shotgun.DMG, 2, 
			ModConstant.weaponBalance.double_barrel_shotgun.SPD, 
			ModConstant.weaponBalance.double_barrel_shotgun.SPRD);
	
	public static BaseGunItem sawn_off_remmington_m870 = new BaseGunItem(ModConstant.fireRate.SINGLE_FIRE, TMWItems.tweleve_gauge_shell, 
			ModConstant.weaponBalance.remmington_m870_sawn_off.DMG, 8,
			ModConstant.weaponBalance.remmington_m870_sawn_off.SPD, 
			ModConstant.weaponBalance.remmington_m870_sawn_off.SPRD);
}
