package themastergeneral.thismeanswar.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.define.TMWCarbines;
import themastergeneral.thismeanswar.items.define.TMWExplosiveProjectile;
import themastergeneral.thismeanswar.items.define.TMWPistols;
import themastergeneral.thismeanswar.items.define.TMWRifles;
import themastergeneral.thismeanswar.items.define.TMWShotguns;
import themastergeneral.thismeanswar.items.define.TMWThrowables;

public class TMWItemRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TMWMain.MODID);
	
	public static final RegistryObject<Item> round_9mm = TMWItemRegistry.ITEMS.register("round_9mm", () -> TMWItems.round_9mm);
	public static final RegistryObject<Item> round_12g = TMWItemRegistry.ITEMS.register("round_12g", () -> TMWItems.round_12g);
	public static final RegistryObject<Item> round_556 = TMWItemRegistry.ITEMS.register("round_556", () -> TMWItems.round_556);
	public static final RegistryObject<Item> round_223 = TMWItemRegistry.ITEMS.register("round_223", () -> TMWItems.round_223);
	
	public static final RegistryObject<Item> magazine_9mm = TMWItemRegistry.ITEMS.register("magazine_9mm", () -> TMWItems.magazine_9mm);
	public static final RegistryObject<Item> magazine_9mm_large = TMWItemRegistry.ITEMS.register("magazine_9mm_large", () -> TMWItems.magazine_9mm_large);
	public static final RegistryObject<Item> magazine_556 = TMWItemRegistry.ITEMS.register("magazine_556", () -> TMWItems.magazine_556);
	public static final RegistryObject<Item> magazine_223 = TMWItemRegistry.ITEMS.register("magazine_223", () -> TMWItems.magazine_223);
	
	public static final RegistryObject<Item> rocket_bazooka = TMWItemRegistry.ITEMS.register("rocket_bazooka", () -> TMWItems.rocket_bazooka);
	
	//Pistols
	public static final RegistryObject<Item> beretta_92_fs = TMWItemRegistry.ITEMS.register("beretta_92_fs", () -> TMWPistols.beretta_92_fs);
	public static final RegistryObject<Item> glock_26 = TMWItemRegistry.ITEMS.register("glock_26", () -> TMWPistols.glock_26);
	
	//Carbines
	public static final RegistryObject<Item> tmg_carbine = TMWItemRegistry.ITEMS.register("tmg_carbine", () -> TMWCarbines.tmg_carbine);
	
	//Rifles
	public static final RegistryObject<Item> springfield_saint_556 = TMWItemRegistry.ITEMS.register("springfield_saint_556", () -> TMWRifles.springfield_saint_556);
	public static final RegistryObject<Item> springfield_saint_223 = TMWItemRegistry.ITEMS.register("springfield_saint_223", () -> TMWRifles.springfield_saint_223);
	
	//Snipers
	
	//Shotguns
	public static final RegistryObject<Item> remmington_m870 = TMWItemRegistry.ITEMS.register("remmington_m870", () -> TMWShotguns.remmington_m870);
	public static final RegistryObject<Item> sawn_off_remmington_m870 = TMWItemRegistry.ITEMS.register("sawn_off_remmington_m870", () -> TMWShotguns.sawn_off_remmington_m870);
	public static final RegistryObject<Item> sawn_off_double_barrel_12g = TMWItemRegistry.ITEMS.register("sawn_off_double_barrel_12g", () -> TMWShotguns.sawn_off_double_barrel_12g);
	public static final RegistryObject<Item> double_barrel_12g = TMWItemRegistry.ITEMS.register("double_barrel_12g", () -> TMWShotguns.double_barrel_12g);
	
	//RPGs
	public static final RegistryObject<Item> bazooka = TMWItemRegistry.ITEMS.register("bazooka", () -> TMWExplosiveProjectile.bazooka);
	
	//Grenades
	public static final RegistryObject<Item> dynamite_stick = TMWItemRegistry.ITEMS.register("dynamite_stick", () -> TMWThrowables.dynamite_stick);
	public static final RegistryObject<Item> nuclear_warhead = TMWItemRegistry.ITEMS.register("nuclear_warhead", () -> TMWThrowables.nuclear_warhead);
	public static final RegistryObject<Item> hand_grenade = TMWItemRegistry.ITEMS.register("hand_grenade", () -> TMWThrowables.hand_grenade);
	
	//Crafting items
	public static final RegistryObject<Item> ingot_lead = TMWItemRegistry.ITEMS.register("ingot_lead", () -> TMWItems.ingot_lead);
	public static final RegistryObject<Item> nugget_lead = TMWItemRegistry.ITEMS.register("nugget_lead", () -> TMWItems.nugget_lead);
	public static final RegistryObject<Item> ingot_brass = TMWItemRegistry.ITEMS.register("ingot_brass", () -> TMWItems.ingot_brass);
	public static final RegistryObject<Item> plate_lead = TMWItemRegistry.ITEMS.register("plate_lead", () -> TMWItems.plate_lead);
	
	//Hammers
	public static final RegistryObject<Item> hammer_iron = TMWItemRegistry.ITEMS.register("hammer_iron", () -> TMWItems.hammer_iron);
	public static final RegistryObject<Item> hammer_diamond = TMWItemRegistry.ITEMS.register("hammer_diamond", () -> TMWItems.hammer_diamond);
	public static final RegistryObject<Item> hammer_creative = TMWItemRegistry.ITEMS.register("hammer_creative", () -> TMWItems.hammer_creative);
	
	//Bullet casts
	public static final RegistryObject<Item> bullet_cast_9mm = TMWItemRegistry.ITEMS.register("bullet_cast_9mm", () -> TMWItems.bullet_cast_9mm);
	public static final RegistryObject<Item> bullet_cast_556 = TMWItemRegistry.ITEMS.register("bullet_cast_556", () -> TMWItems.bullet_cast_556);
	public static final RegistryObject<Item> bullet_cast_223 = TMWItemRegistry.ITEMS.register("bullet_cast_223", () -> TMWItems.bullet_cast_223);
	
	//Bullet Casings
	public static final RegistryObject<Item> casing_9mm = TMWItemRegistry.ITEMS.register("casing_9mm", () -> TMWItems.casing_9mm);
	public static final RegistryObject<Item> casing_223 = TMWItemRegistry.ITEMS.register("casing_223", () -> TMWItems.casing_223);
	public static final RegistryObject<Item> casing_556 = TMWItemRegistry.ITEMS.register("casing_556", () -> TMWItems.casing_556);
	public static final RegistryObject<Item> casing_12g = TMWItemRegistry.ITEMS.register("casing_12g", () -> TMWItems.casing_12g);
	
	//Primers
	public static final RegistryObject<Item> primer_pistol = TMWItemRegistry.ITEMS.register("primer_pistol", () -> TMWItems.primer_pistol);
	public static final RegistryObject<Item> primer_rifle = TMWItemRegistry.ITEMS.register("primer_rifle", () -> TMWItems.primer_rifle);
	public static final RegistryObject<Item> primer_shotgun = TMWItemRegistry.ITEMS.register("primer_shotgun", () -> TMWItems.primer_shotgun);
	
	//Bullet tips
	public static final RegistryObject<Item> bullet_tip_9mm = TMWItemRegistry.ITEMS.register("bullet_tip_9mm", () -> TMWItems.bullet_tip_9mm);
	public static final RegistryObject<Item> bullet_tip_223 = TMWItemRegistry.ITEMS.register("bullet_tip_223", () -> TMWItems.bullet_tip_223);
	public static final RegistryObject<Item> bullet_tip_556 = TMWItemRegistry.ITEMS.register("bullet_tip_556", () -> TMWItems.bullet_tip_556);
	public static final RegistryObject<Item> bullet_tip_buckshot = TMWItemRegistry.ITEMS.register("bullet_tip_buckshot", () -> TMWItems.bullet_tip_buckshot);
	
	//Ores
	public static final RegistryObject<Item> ore_lead = TMWItemRegistry.ITEMS.register("ore_lead", () -> TMWItems.ore_lead);
	public static final RegistryObject<Item> ore_brass = TMWItemRegistry.ITEMS.register("ore_brass", () -> TMWItems.ore_brass);
	
}
