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
	
	public static final RegistryObject<Item> round_9mm = ITEMS.register("round_9mm", () -> TMWItems.round_9mm);
	public static final RegistryObject<Item> round_12g = ITEMS.register("round_12g", () -> TMWItems.round_12g);
	public static final RegistryObject<Item> round_556 = ITEMS.register("round_556", () -> TMWItems.round_556);
	public static final RegistryObject<Item> round_223 = ITEMS.register("round_223", () -> TMWItems.round_223);
	
	public static final RegistryObject<Item> magazine_9mm = ITEMS.register("magazine_9mm", () -> TMWItems.magazine_9mm);
	public static final RegistryObject<Item> magazine_9mm_large = ITEMS.register("magazine_9mm_large", () -> TMWItems.magazine_9mm_large);
	public static final RegistryObject<Item> magazine_556 = ITEMS.register("magazine_556", () -> TMWItems.magazine_556);
	public static final RegistryObject<Item> magazine_223 = ITEMS.register("magazine_223", () -> TMWItems.magazine_223);
	
	public static final RegistryObject<Item> rocket_bazooka = ITEMS.register("rocket_bazooka", () -> TMWItems.rocket_bazooka);
	
	//Pistols
	public static final RegistryObject<Item> beretta_92_fs = ITEMS.register("beretta_92_fs", () -> TMWPistols.beretta_92_fs);
	public static final RegistryObject<Item> glock_26 = ITEMS.register("glock_26", () -> TMWPistols.glock_26);
	
	//Carbines
	public static final RegistryObject<Item> tmg_carbine = ITEMS.register("tmg_carbine", () -> TMWCarbines.tmg_carbine);
	
	//Rifles
	public static final RegistryObject<Item> springfield_saint_556 = ITEMS.register("springfield_saint_556", () -> TMWRifles.springfield_saint_556);
	public static final RegistryObject<Item> springfield_saint_223 = ITEMS.register("springfield_saint_223", () -> TMWRifles.springfield_saint_223);
	
	//Snipers
	
	//Shotguns
	public static final RegistryObject<Item> remmington_m870 = ITEMS.register("remmington_m870", () -> TMWShotguns.remmington_m870);
	public static final RegistryObject<Item> sawn_off_remmington_m870 = ITEMS.register("sawn_off_remmington_m870", () -> TMWShotguns.sawn_off_remmington_m870);
	public static final RegistryObject<Item> sawn_off_double_barrel_12g = ITEMS.register("sawn_off_double_barrel_12g", () -> TMWShotguns.sawn_off_double_barrel_12g);
	public static final RegistryObject<Item> double_barrel_12g = ITEMS.register("double_barrel_12g", () -> TMWShotguns.double_barrel_12g);
	
	//RPGs
	public static final RegistryObject<Item> bazooka = ITEMS.register("bazooka", () -> TMWExplosiveProjectile.bazooka);
	
	//Grenades
	public static final RegistryObject<Item> dynamite_stick = ITEMS.register("dynamite_stick", () -> TMWThrowables.dynamite_stick);
	public static final RegistryObject<Item> nuclear_warhead = ITEMS.register("nuclear_warhead", () -> TMWThrowables.nuclear_warhead);
	public static final RegistryObject<Item> hand_grenade = ITEMS.register("hand_grenade", () -> TMWThrowables.hand_grenade);
	
	//Crafting items
	public static final RegistryObject<Item> plate_lead = ITEMS.register("plate_lead", () -> TMWItems.plate_lead);
	public static final RegistryObject<Item> ingot_lead = ITEMS.register("ingot_lead", () -> TMWItems.ingot_lead);
	public static final RegistryObject<Item> nugget_lead = ITEMS.register("nugget_lead", () -> TMWItems.nugget_lead);
	public static final RegistryObject<Item> dust_lead = ITEMS.register("dust_lead", () -> TMWItems.dust_lead);
	
	public static final RegistryObject<Item> ingot_brass = ITEMS.register("ingot_brass", () -> TMWItems.ingot_brass);
	public static final RegistryObject<Item> nugget_brass = ITEMS.register("nugget_brass", () -> TMWItems.nugget_brass);
	public static final RegistryObject<Item> dust_brass = ITEMS.register("dust_brass", () -> TMWItems.dust_brass);
	
	public static final RegistryObject<Item> ingot_steel = ITEMS.register("ingot_steel", () -> TMWItems.ingot_steel);
	public static final RegistryObject<Item> nugget_steel = ITEMS.register("nugget_steel", () -> TMWItems.nugget_steel);
	public static final RegistryObject<Item> dust_steel = ITEMS.register("dust_steel", () -> TMWItems.dust_steel);
	
	public static final RegistryObject<Item> dust_copper = ITEMS.register("dust_copper", () -> TMWItems.dust_copper);
	public static final RegistryObject<Item> dust_iron = ITEMS.register("dust_iron", () -> TMWItems.dust_iron);
	public static final RegistryObject<Item> dust_gold = ITEMS.register("dust_gold", () -> TMWItems.dust_gold);
	
	public static final RegistryObject<Item> kevlar_raw = ITEMS.register("kevlar_raw", () -> TMWItems.kevlar_raw);
	
	//Hammers
	public static final RegistryObject<Item> hammer_iron = ITEMS.register("hammer_iron", () -> TMWItems.hammer_iron);
	public static final RegistryObject<Item> hammer_diamond = ITEMS.register("hammer_diamond", () -> TMWItems.hammer_diamond);
	public static final RegistryObject<Item> hammer_steel = ITEMS.register("hammer_steel", () -> TMWItems.hammer_steel);
	public static final RegistryObject<Item> hammer_creative = ITEMS.register("hammer_creative", () -> TMWItems.hammer_creative);
	
	//Bullet casts
	public static final RegistryObject<Item> bullet_cast_9mm = ITEMS.register("bullet_cast_9mm", () -> TMWItems.bullet_cast_9mm);
	public static final RegistryObject<Item> bullet_cast_556 = ITEMS.register("bullet_cast_556", () -> TMWItems.bullet_cast_556);
	public static final RegistryObject<Item> bullet_cast_223 = ITEMS.register("bullet_cast_223", () -> TMWItems.bullet_cast_223);
	
	//Bullet Casings
	public static final RegistryObject<Item> casing_9mm = ITEMS.register("casing_9mm", () -> TMWItems.casing_9mm);
	public static final RegistryObject<Item> casing_223 = ITEMS.register("casing_223", () -> TMWItems.casing_223);
	public static final RegistryObject<Item> casing_556 = ITEMS.register("casing_556", () -> TMWItems.casing_556);
	public static final RegistryObject<Item> casing_12g = ITEMS.register("casing_12g", () -> TMWItems.casing_12g);
	
	//Health
	public static final RegistryObject<Item> bandage = ITEMS.register("bandage", () -> TMWItems.bandage);
	
	//Primers
	public static final RegistryObject<Item> primer_pistol = ITEMS.register("primer_pistol", () -> TMWItems.primer_pistol);
	public static final RegistryObject<Item> primer_rifle = ITEMS.register("primer_rifle", () -> TMWItems.primer_rifle);
	public static final RegistryObject<Item> primer_shotgun = ITEMS.register("primer_shotgun", () -> TMWItems.primer_shotgun);
	
	//Bullet tips
	public static final RegistryObject<Item> bullet_tip_9mm = ITEMS.register("bullet_tip_9mm", () -> TMWItems.bullet_tip_9mm);
	public static final RegistryObject<Item> bullet_tip_223 = ITEMS.register("bullet_tip_223", () -> TMWItems.bullet_tip_223);
	public static final RegistryObject<Item> bullet_tip_556 = ITEMS.register("bullet_tip_556", () -> TMWItems.bullet_tip_556);
	public static final RegistryObject<Item> bullet_tip_buckshot = ITEMS.register("bullet_tip_buckshot", () -> TMWItems.bullet_tip_buckshot);
	
	//Ores
	public static final RegistryObject<Item> ore_lead = ITEMS.register("ore_lead", () -> TMWItems.ore_lead);
	public static final RegistryObject<Item> ore_brass = ITEMS.register("ore_brass", () -> TMWItems.ore_brass);
	
	//Metal Blocks
	public static final RegistryObject<Item> block_lead = ITEMS.register("block_lead", () -> TMWItems.block_lead);
	public static final RegistryObject<Item> block_brass = ITEMS.register("block_brass", () -> TMWItems.block_brass);
	public static final RegistryObject<Item> block_steel = ITEMS.register("block_steel", () -> TMWItems.block_steel);
	
	//Ammo Box
	public static final RegistryObject<Item> ammo_box = ITEMS.register("ammo_box", () -> TMWItems.ammo_box);
	public static final RegistryObject<Item> ammo_box_medium = ITEMS.register("ammo_box_medium", () -> TMWItems.ammo_box_medium);
	public static final RegistryObject<Item> ammo_box_large = ITEMS.register("ammo_box_large", () -> TMWItems.ammo_box_large);
	
	//Processing
	public static final RegistryObject<Item> crusher = ITEMS.register("crusher", () -> TMWItems.crusher);
}
