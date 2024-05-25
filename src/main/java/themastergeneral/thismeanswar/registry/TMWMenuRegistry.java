package themastergeneral.thismeanswar.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.menu.CasingRecyclerMenu;
import themastergeneral.thismeanswar.menu.TipRecyclerMenu;

public class TMWMenuRegistry {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<MenuType<CasingRecyclerMenu>> CASING_RECYCLER_MENU = CONTAINERS.register("casing_recycler_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new CasingRecyclerMenu(windowId, inv, data)));
	
	public static final RegistryObject<MenuType<TipRecyclerMenu>> TIP_RECYCLER_MENU = CONTAINERS.register("tip_recycler_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new TipRecyclerMenu(windowId, inv, data)));
}
