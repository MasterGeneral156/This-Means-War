package themastergeneral.thismeanswar.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.menu.CasingRecyclerMenu;
import themastergeneral.thismeanswar.menu.CrusherMenu;
import themastergeneral.thismeanswar.menu.FactoryHolderMenu;
import themastergeneral.thismeanswar.menu.PopperMenu;
import themastergeneral.thismeanswar.menu.PressMenu;
import themastergeneral.thismeanswar.menu.TipRecyclerMenu;

public class TMWMenuRegistry {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<MenuType<CasingRecyclerMenu>> CASING_RECYCLER_MENU = CONTAINERS.register("casing_recycler_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new CasingRecyclerMenu(windowId, inv, data)));
	
	public static final RegistryObject<MenuType<TipRecyclerMenu>> TIP_RECYCLER_MENU = CONTAINERS.register("tip_recycler_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new TipRecyclerMenu(windowId, inv, data)));
	
	public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU = CONTAINERS.register("crusher_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new CrusherMenu(windowId, inv, data)));
	
	public static final RegistryObject<MenuType<FactoryHolderMenu>> FACTORY_HOLDER_MENU = CONTAINERS.register("factory_holder_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new FactoryHolderMenu(windowId, inv, data)));
	
	public static final RegistryObject<MenuType<PressMenu>> PRESS_MENU = CONTAINERS.register("press_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new PressMenu(windowId, inv, data)));
	
	public static final RegistryObject<MenuType<PopperMenu>> POPPER_MENU = CONTAINERS.register("popper_menu",
	        () -> IForgeMenuType.create((windowId, inv, data) -> new PopperMenu(windowId, inv, data)));
}
