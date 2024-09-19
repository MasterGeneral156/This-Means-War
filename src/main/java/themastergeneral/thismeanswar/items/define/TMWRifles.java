package themastergeneral.thismeanswar.items.define;

import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWRifles 
{
	public static NuGunItem springfield_saint_556 = new NuGunItem(
		Constants.ar15FireRate556, Constants.ar15ReloadTime556,
		TMWItems.magazine_556, Constants.ar15Damage556,
		Constants.ar15Speed556, Constants.ar15Spread556);
	
	public static NuGunItem springfield_saint_223 = new NuGunItem(
		Constants.ar15FireRate223, Constants.ar15ReloadTime223, 
		TMWItems.magazine_223, Constants.ar15Damage223,
		Constants.ar15Speed223, Constants.ar15Spread223);
	
	public static NuGunItem m16 = new NuGunItem(
		Constants.fireRateAuto, (int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
		TMWItems.magazine_556, Constants.ar15Damage556 * 1.1F,
		Constants.ar15Speed556 * 0.9F, Constants.ar15Spread556 * 2.75F);
	
	public static NuGunItem thunderclaw = new NuGunItem(
		Constants.thunderclawireRate, Constants.thunderclawReloadTime, 
		TMWItems.magazine_45_clip, Constants.thunderclawDamage,
		Constants.thunderclawSpeed, Constants.thunderclawSpread);
	
	public static NuGunItem m16_223 = new NuGunItem(
		Constants.fireRateAuto, (int) Math.round(Constants.ar15ReloadTime223 * 0.9), 
		TMWItems.magazine_223, Constants.ar15Damage223 * 1.1F,
		Constants.ar15Speed223 * 0.9F, Constants.ar15Spread223 * 2.75F);
	
	public static NuGunItem quantum_disruptor = new NuGunItem(
		Constants.quantumDisruptorRate, Constants.quantumDisruptorReloadTime, 
		TMWItems.energy_cell, Constants.quantumDisruptorDamage,
		Constants.quantumDisruptorSpeed, Constants.quantumDisruptorSpread);
	
	public static NuGunItem mosin_nagant = new NuGunItem(
		Constants.mosinFireRate, Constants.mosinReloadTime, 
		TMWItems.magazine_223_clip, Constants.mosinDamage,
		Constants.mosinSpeed, Constants.mosinSpread);
	
	public static NuGunItem scar = new NuGunItem(
		Constants.scarFireRate, Constants.scarReloadTime, 
		TMWItems.magazine_556, Constants.scarDamage,
		Constants.scarSpeed, Constants.scarSpread);
	
	public static NuGunItem bfg_8001 = new NuGunItem(
		Constants.bfg8001Rate, TMWItems.energy_bolt, 
		Constants.bfg8001Damage, Constants.bfg8001MaxAmmo, 
		Constants.bfg8001Speed, Constants.bfg8001Spread);
	
	public static NuGunItem dragunov = new NuGunItem(
		Constants.dragunovFireRate, Constants.dragunovReloadTime, 
		TMWItems.magazine_dragunov, Constants.dragunovDamage,
		Constants.dragunovSpeed, Constants.dragunovSpread);
	
	public static NuGunItem k98 = new NuGunItem(
		Constants.k98FireRate, TMWItems.round_762, 
		Constants.k98Damage, 5, Constants.k98Speed, 
		Constants.k98Spread);
}
