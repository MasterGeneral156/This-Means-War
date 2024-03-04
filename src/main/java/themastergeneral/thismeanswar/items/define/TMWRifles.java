package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.ExternalAmmoGunItem;
import themastergeneral.thismeanswar.items.InternalAmmoGunItem;
import themastergeneral.thismeanswar.items.MultiAmmoCostGunItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.WeaponSniper;

public class TMWRifles 
{
	public static ExternalAmmoGunItem springfield_saint_556 = new ExternalAmmoGunItem(
		Constants.ar15FireRate556, Constants.ar15ReloadTime556,
		TMWItems.magazine_556, Constants.ar15Damage556,
		Constants.ar15Speed556, Constants.ar15Spread556);
	
	public static ExternalAmmoGunItem springfield_saint_223 = new ExternalAmmoGunItem(
		Constants.ar15FireRate223, Constants.ar15ReloadTime223, 
		TMWItems.magazine_223, Constants.ar15Damage223,
		Constants.ar15Speed223, Constants.ar15Spread223);
	
	public static ExternalAmmoGunItem m16 = new ExternalAmmoGunItem(
		Constants.fireRateAuto, (int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
		TMWItems.magazine_556, Constants.ar15Damage556 * 1.1F,
		Constants.ar15Speed556 * 0.9F, Constants.ar15Spread556 * 0.9F);
	
	public static WeaponSniper thunderclaw = new WeaponSniper(
		Constants.thunderclawireRate, Constants.thunderclawReloadTime, 
		TMWItems.magazine_45_clip, Constants.thunderclawDamage,
		Constants.thunderclawSpeed, Constants.thunderclawSpread, 0.75F);
	
	public static ExternalAmmoGunItem m16_223 = new ExternalAmmoGunItem(
		Constants.fireRateAuto, (int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
		TMWItems.magazine_223, Constants.ar15Damage223 * 1.1F,
		Constants.ar15Speed223 * 0.9F, Constants.ar15Spread223 * 0.9F);
	
	public static WeaponSniper quantum_disruptor = new WeaponSniper(
		Constants.quantumDisruptorRate, Constants.quantumDisruptorReloadTime, 
		TMWItems.energy_cell, Constants.quantumDisruptorDamage,
		Constants.quantumDisruptorSpeed, Constants.quantumDisruptorSpread, 1F);
	
	public static WeaponSniper mosin_nagant = new WeaponSniper(
		Constants.mosinFireRate, Constants.mosinReloadTime, 
		TMWItems.magazine_223_clip, Constants.mosinDamage,
		Constants.mosinSpeed, Constants.mosinSpread, 0.65F);
	
	public static ExternalAmmoGunItem scar = new ExternalAmmoGunItem(
		Constants.scarFireRate, Constants.scarReloadTime, 
		TMWItems.magazine_556, Constants.scarDamage,
		Constants.scarSpeed, Constants.scarSpread);
	
	public static MultiAmmoCostGunItem bfg_8001 = new MultiAmmoCostGunItem(
		Constants.bfg8001Rate, TMWItems.energy_bolt, 
		Constants.bfg8001Damage, Constants.bfg8001MaxAmmo, 
		Constants.bfg8001Speed, Constants.bfg8001Spread, 
		Constants.bfg8001Cost);
	
	public static WeaponSniper dragunov = new WeaponSniper(
		Constants.dragunovFireRate, Constants.dragunovReloadTime, 
		TMWItems.magazine_dragunov, Constants.dragunovDamage,
		Constants.dragunovSpeed, Constants.dragunovSpread, 0.4F);
	
	public static WeaponSniper k98 = new WeaponSniper(
		Constants.k98FireRate, TMWItems.round_762, 
		Constants.k98Damage, 5, Constants.k98Speed, 
		Constants.k98Spread, 0.6F);
}
