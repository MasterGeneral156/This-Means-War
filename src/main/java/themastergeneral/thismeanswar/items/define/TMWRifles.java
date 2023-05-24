package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWRifles 
{
	public static AbstractGunItem springfield_saint_556 = new 
															AbstractGunItem(
																Constants.ar15FireRate556, 
																Constants.ar15ReloadTime556,
																TMWItems.magazine_556, 
																TMWItems.round_556, 
																Constants.ar15Damage556,
																Constants.ar15Speed556, 
																Constants.ar15Spread556);
	
	public static AbstractGunItem springfield_saint_223 = new 
															AbstractGunItem(
																Constants.ar15FireRate223, 
																Constants.ar15ReloadTime223, 
																TMWItems.magazine_223, 
																TMWItems.round_223, 
																Constants.ar15Damage223,
																Constants.ar15Speed223, 
																Constants.ar15Spread223);
	
	public static AbstractGunItem m16 = new 
											AbstractGunItem(
												Constants.fireRateAuto, 
												Constants.ar15ReloadTime556, 
												TMWItems.magazine_556, 
												TMWItems.round_556, 
												Constants.ar15Damage556,
												Constants.ar15Speed556, 
												Constants.ar15Spread556);
	
	public static AbstractGunItem thunderclaw = new 
													AbstractGunItem(
														Constants.thunderclawireRate, 
														Constants.thunderclawReloadTime, 
														TMWItems.magazine_45_clip, 
														TMWItems.round_45, 
														Constants.thunderclawDamage,
														Constants.thunderclawSpeed, 
														Constants.thunderclawSpread);
}
