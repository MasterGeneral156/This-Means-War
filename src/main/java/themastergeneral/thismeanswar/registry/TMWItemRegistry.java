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
	public static final RegistryObject<Item> nine_mm_magazine = TMWItemRegistry.ITEMS.register("nine_mm_magazine", () -> TMWItems.nine_mm_magazine);
}
