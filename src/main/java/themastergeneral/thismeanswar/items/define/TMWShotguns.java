package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWShotguns {

	public static NuGunItem remmington_m870 = new NuGunItem(
		Constants.m870FireRate, TMWItems.round_12g, 
		Constants.m870Damage, 8, 
		Constants.m870Speed, Constants.m870Spread);
	
	public static NuGunItem sawn_off_double_barrel_12g = new NuGunItem(
		Constants.doubleBarrelFireRate, TMWItems.round_12g, 
		Constants.doubleBarrelDamage, 2, 
		Constants.doubleBarrelSpeed, Constants.doubleBarrelSpread);
	
	public static NuGunItem double_barrel_12g = new NuGunItem(
		Constants.doubleBarrelFireRate,TMWItems.round_12g, 
		Constants.doubleBarrelDamage + Constants.sawnOffBonusDamage, 2, 
		Constants.doubleBarrelSpeed, Constants.doubleBarrelSpread * Constants.sawnOffBonusSpread);

	public static NuGunItem sawn_off_remmington_m870 = new NuGunItem(
		Constants.m870FireRate, TMWItems.round_12g, 
		Constants.m870Damage + Constants.sawnOffBonusDamage, 8, 
		Constants.m870Speed, Constants.m870Spread * Constants.sawnOffBonusSpread);
	
	public static NuGunItem winchester = new NuGunItem(
		Constants.winchesterFireRate, Constants.winchesterReloadTime, 
		TMWItems.magazine_12g_clip, Constants.winchesterDamage,
		Constants.winchesterSpeed, Constants.winchesterSpread);
}
