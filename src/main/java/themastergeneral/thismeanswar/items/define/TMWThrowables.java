package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.items.GrenadeItem;

public class TMWThrowables {
	public static GrenadeItem dynamite_stick = new GrenadeItem(BalanceConfig.DYNA_STICK_DMG.get().floatValue());
	
	public static GrenadeItem nuclear_warhead = new GrenadeItem(BalanceConfig.NUKE_DMG.get().floatValue());
	
	public static GrenadeItem hand_grenade = new GrenadeItem(BalanceConfig.GRENADE_DMG.get().floatValue());
}
