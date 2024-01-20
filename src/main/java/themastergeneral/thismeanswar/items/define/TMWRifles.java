package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.ExternalAmmoGunItem;
import themastergeneral.thismeanswar.items.InternalAmmoGunItem;
import themastergeneral.thismeanswar.items.MultiAmmoCostGunItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;

public class TMWRifles 
{
	public static ExternalAmmoGunItem springfield_saint_556 = new 
			ExternalAmmoGunItem(
					Constants.ar15FireRate556, 
					Constants.ar15ReloadTime556,
					TMWItems.magazine_556, 
					Constants.ar15Damage556,
					Constants.ar15Speed556, 
					Constants.ar15Spread556);
	
	public static ExternalAmmoGunItem springfield_saint_223 = new 
			ExternalAmmoGunItem(
					Constants.ar15FireRate223, 
					Constants.ar15ReloadTime223, 
					TMWItems.magazine_223, 
					Constants.ar15Damage223,
					Constants.ar15Speed223, 
					Constants.ar15Spread223);
	
	public static ExternalAmmoGunItem m16 = new 
			ExternalAmmoGunItem(
				Constants.fireRateAuto, 
				(int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
				TMWItems.magazine_556, 
				Constants.ar15Damage556 * 1.1F,
				Constants.ar15Speed556 * 0.9F, 
				Constants.ar15Spread556 * 0.9F);
	
	public static ExternalAmmoGunItem thunderclaw = new 
			ExternalAmmoGunItem(
				Constants.thunderclawireRate, 
				Constants.thunderclawReloadTime, 
				TMWItems.magazine_45_clip, 
				Constants.thunderclawDamage,
				Constants.thunderclawSpeed, 
				Constants.thunderclawSpread);
	public static ExternalAmmoGunItem m16_223 = new 
			ExternalAmmoGunItem(
				Constants.fireRateAuto, 
				(int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
				TMWItems.magazine_223, 
				Constants.ar15Damage223 * 1.1F,
				Constants.ar15Speed223 * 0.9F, 
				Constants.ar15Spread223 * 0.9F);
	
	public static ExternalAmmoGunItem quantum_disruptor = new 
			ExternalAmmoGunItem(
				Constants.quantumDisruptorRate, 
				Constants.quantumDisruptorReloadTime, 
				TMWItems.energy_cell, 
				Constants.quantumDisruptorDamage,
				Constants.quantumDisruptorSpeed, 
				Constants.quantumDisruptorSpread);
	
	public static ExternalAmmoGunItem mosin_nagant = new 
			ExternalAmmoGunItem(
				Constants.mosinFireRate, 
				Constants.mosinReloadTime, 
				TMWItems.magazine_223_clip, 
				Constants.mosinDamage,
				Constants.mosinSpeed, 
				Constants.mosinSpread);
	
	public static ExternalAmmoGunItem scar = new 
			ExternalAmmoGunItem(
				Constants.scarFireRate, 
				Constants.scarReloadTime, 
				TMWItems.magazine_556, 
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
	
	public static ExternalAmmoGunItem dragunov = new 
			ExternalAmmoGunItem(
				Constants.dragunovFireRate, 
				Constants.dragunovReloadTime, 
				TMWItems.magazine_dragunov, 
				Constants.dragunovDamage,
				Constants.dragunovSpeed, 
				Constants.dragunovSpread);
	
	public static InternalAmmoGunItem k98 = new 
			InternalAmmoGunItem(Constants.k98FireRate, TMWItems.round_762, Constants.k98Damage, 5, Constants.k98Speed, Constants.k98Spread);
}
