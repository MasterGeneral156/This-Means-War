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
	
	public static final RegistryObject<Item> nine_mm_magazine = TMWItemRegistry.ITEMS.register("nine_mm_magazine", () -> TMWItems.nine_mm_magazine);
	public static final RegistryObject<Item> nine_mm_magazine_large = TMWItemRegistry.ITEMS.register("nine_mm_magazine_large", () -> TMWItems.nine_mm_magazine_large);
	
	//Pistols
	public static final RegistryObject<Item> beretta_92_fs = TMWItemRegistry.ITEMS.register("beretta_92_fs", () -> TMWItems.beretta_92_fs);
	public static final RegistryObject<Item> glock_26 = TMWItemRegistry.ITEMS.register("glock_26", () -> TMWItems.glock_26);
	
	//Carbines
	public static final RegistryObject<Item> tmg_carbine = TMWItemRegistry.ITEMS.register("tmg_carbine", () -> TMWItems.tmg_carbine);
	
	//Shotguns
	public static final RegistryObject<Item> remmington_m870 = TMWItemRegistry.ITEMS.register("remmington_m870", () -> TMWItems.remmington_m870);
	public static final RegistryObject<Item> sawn_off_remmington_m870 = TMWItemRegistry.ITEMS.register("sawn_off_remmington_m870", () -> TMWItems.sawn_off_remmington_m870);
	public static final RegistryObject<Item> sawn_off_double_barrel_12g = TMWItemRegistry.ITEMS.register("sawn_off_double_barrel_12g", () -> TMWItems.sawn_off_double_barrel_12g);
	public static final RegistryObject<Item> double_barrel_12g = TMWItemRegistry.ITEMS.register("double_barrel_12g", () -> TMWItems.double_barrel_12g);
	
	//Grenades
	public static final RegistryObject<Item> dynamite_stick = TMWItemRegistry.ITEMS.register("dynamite_stick", () -> TMWItems.dynamite_stick);
}
