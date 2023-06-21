package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.MultiAmmoCostGunItem;
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
												(int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
												TMWItems.magazine_556, 
												TMWItems.round_556, 
												Constants.ar15Damage556 * 1.1F,
												Constants.ar15Speed556 * 0.9F, 
												Constants.ar15Spread556 * 0.9F);
	
	public static AbstractGunItem thunderclaw = new 
													AbstractGunItem(
														Constants.thunderclawireRate, 
														Constants.thunderclawReloadTime, 
														TMWItems.magazine_45_clip, 
														TMWItems.round_45, 
														Constants.thunderclawDamage,
														Constants.thunderclawSpeed, 
														Constants.thunderclawSpread);
	public static AbstractGunItem m16_223 = new 
											AbstractGunItem(
												Constants.fireRateAuto, 
												(int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
												TMWItems.magazine_223, 
												TMWItems.round_223, 
												Constants.ar15Damage223 * 1.1F,
												Constants.ar15Speed223 * 0.9F, 
												Constants.ar15Spread223 * 0.9F);
	
	public static AbstractGunItem quantum_disruptor = new 
				AbstractGunItem(
					Constants.quantumDisruptorRate, 
					Constants.quantumDisruptorReloadTime, 
					TMWItems.energy_cell, 
					TMWItems.energy_bolt, 
					Constants.quantumDisruptorDamage,
					Constants.quantumDisruptorSpeed, 
					Constants.quantumDisruptorSpread);
	
	public static AbstractGunItem mosin_nagant = new 
			AbstractGunItem(
				Constants.mosinFireRate, 
				Constants.mosinReloadTime, 
				TMWItems.magazine_223_clip, 
				TMWItems.round_223, 
				Constants.mosinDamage,
				Constants.mosinSpeed, 
				Constants.mosinSpread);
	
	public static AbstractGunItem scar = new 
			AbstractGunItem(
				Constants.scarFireRate, 
				Constants.scarReloadTime, 
				TMWItems.magazine_556, 
				TMWItems.round_556, 
				Constants.scarDamage,
				Constants.scarSpeed, 
				Constants.scarSpread);
	
	public static MultiAmmoCostGunItem bfg_8001 = new 
				MultiAmmoCostGunItem(
						Constants.bfg8001Rate, 
						TMWItems.energy_bolt, 
						Constants.bfg8001Damage, 
						Constants.bfg8001MaxAmmo, 
						Constants.bfg8001Speed, 
						Constants.bfg8001Spread, 
						Constants.bfg8001Cost);
}
