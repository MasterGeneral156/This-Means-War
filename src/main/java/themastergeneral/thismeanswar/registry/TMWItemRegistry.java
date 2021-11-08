package themastergeneral.thismeanswar.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWItemRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TMWMain.MODID);
	
	public static final RegistryObject<Item> nine_mm_round = TMWItemRegistry.ITEMS.register("nine_mm_round", () -> TMWItems.nine_mm_round);
	public static final RegistryObject<Item> twelve_gauge_shell = TMWItemRegistry.ITEMS.register("twelve_gauge_shell", () -> TMWItems.twelve_gauge_shell);
	public static final RegistryObject<Item> five_five_six_round = TMWItemRegistry.ITEMS.register("five_five_six_round", () -> TMWItems.five_five_six_round);
	public static final RegistryObject<Item> two_two_three_round = TMWItemRegistry.ITEMS.register("two_two_three_round", () -> TMWItems.two_two_three_round);
	
	public static final RegistryObject<Item> nine_mm_magazine = TMWItemRegistry.ITEMS.register("nine_mm_magazine", () -> TMWItems.nine_mm_magazine);
	public static final RegistryObject<Item> nine_mm_magazine_large = TMWItemRegistry.ITEMS.register("nine_mm_magazine_large", () -> TMWItems.nine_mm_magazine_large);
	public static final RegistryObject<Item> m4ar_mag_556 = TMWItemRegistry.ITEMS.register("m4ar_mag_556", () -> TMWItems.m4ar_mag_556);
	public static final RegistryObject<Item> m4ar_mag_223 = TMWItemRegistry.ITEMS.register("m4ar_mag_223", () -> TMWItems.m4ar_mag_223);
	
	public static final RegistryObject<Item> bazooka_rocket = TMWItemRegistry.ITEMS.register("bazooka_rocket", () -> TMWItems.bazooka_rocket);
	
	//Pistols
	public static final RegistryObject<Item> beretta_92_fs = TMWItemRegistry.ITEMS.register("beretta_92_fs", () -> TMWItems.beretta_92_fs);
	public static final RegistryObject<Item> glock_26 = TMWItemRegistry.ITEMS.register("glock_26", () -> TMWItems.glock_26);
	
	//Carbines
	public static final RegistryObject<Item> tmg_carbine = TMWItemRegistry.ITEMS.register("tmg_carbine", () -> TMWItems.tmg_carbine);
	
	//Rifles
	public static final RegistryObject<Item> springfield_saint_556 = TMWItemRegistry.ITEMS.register("springfield_saint_556", () -> TMWItems.springfield_saint_556);
	public static final RegistryObject<Item> springfield_saint_223 = TMWItemRegistry.ITEMS.register("springfield_saint_223", () -> TMWItems.springfield_saint_223);
	
	//Snipers
	
	//Shotguns
	public static final RegistryObject<Item> remmington_m870 = TMWItemRegistry.ITEMS.register("remmington_m870", () -> TMWItems.remmington_m870);
	public static final RegistryObject<Item> sawn_off_remmington_m870 = TMWItemRegistry.ITEMS.register("sawn_off_remmington_m870", () -> TMWItems.sawn_off_remmington_m870);
	public static final RegistryObject<Item> sawn_off_double_barrel_12g = TMWItemRegistry.ITEMS.register("sawn_off_double_barrel_12g", () -> TMWItems.sawn_off_double_barrel_12g);
	public static final RegistryObject<Item> double_barrel_12g = TMWItemRegistry.ITEMS.register("double_barrel_12g", () -> TMWItems.double_barrel_12g);
	
	//RPGs
	public static final RegistryObject<Item> bazooka = TMWItemRegistry.ITEMS.register("bazooka", () -> TMWItems.bazooka);
	
	//Grenades
	public static final RegistryObject<Item> dynamite_stick = TMWItemRegistry.ITEMS.register("dynamite_stick", () -> TMWItems.dynamite_stick);
	public static final RegistryObject<Item> nuclear_warhead = TMWItemRegistry.ITEMS.register("nuclear_warhead", () -> TMWItems.nuclear_warhead);
	public static final RegistryObject<Item> hand_grenade = TMWItemRegistry.ITEMS.register("hand_grenade", () -> TMWItems.hand_grenade);
}
