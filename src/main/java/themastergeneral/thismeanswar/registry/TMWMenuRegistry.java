package themastergeneral.thismeanswar.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.menu.AlloySmelterMenu;
import themastergeneral.thismeanswar.menu.AutomatedLoaderMenu;
import themastergeneral.thismeanswar.menu.CasingRecyclerMenu;
import themastergeneral.thismeanswar.menu.CrusherMenu;
import themastergeneral.thismeanswar.menu.FactoryHolderMenu;
import themastergeneral.thismeanswar.menu.FirearmInspectorMenu;
import themastergeneral.thismeanswar.menu.FormerMenu;
import themastergeneral.thismeanswar.menu.MolderMenu;
import themastergeneral.thismeanswar.menu.PopperMenu;
import themastergeneral.thismeanswar.menu.PressMenu;
import themastergeneral.thismeanswar.menu.TipRecyclerMenu;

public class TMWMenuRegistry {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<MenuType<CasingRecyclerMenu>> CASING_RECYCLER_MENU = CONTAINERS.register("casing_recycler_menu",
	        () -> IForgeMenuType.create(CasingRecyclerMenu::new));
	
	public static final RegistryObject<MenuType<TipRecyclerMenu>> TIP_RECYCLER_MENU = CONTAINERS.register("tip_recycler_menu",
	        () -> IForgeMenuType.create(TipRecyclerMenu::new));
	
	public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU = CONTAINERS.register("crusher_menu",
	        () -> IForgeMenuType.create(CrusherMenu::new));
	
	public static final RegistryObject<MenuType<FactoryHolderMenu>> FACTORY_HOLDER_MENU = CONTAINERS.register("factory_holder_menu",
	        () -> IForgeMenuType.create(FactoryHolderMenu::new));
	
	public static final RegistryObject<MenuType<PressMenu>> PRESS_MENU = CONTAINERS.register("press_menu",
	        () -> IForgeMenuType.create(PressMenu::new));
	
	public static final RegistryObject<MenuType<PopperMenu>> POPPER_MENU = CONTAINERS.register("popper_menu",
	        () -> IForgeMenuType.create(PopperMenu::new));
	
	public static final RegistryObject<MenuType<AlloySmelterMenu>> ALLOY_SMELTER_MENU = CONTAINERS.register("alloy_smelter_menu",
	        () -> IForgeMenuType.create(AlloySmelterMenu::new));
	
	public static final RegistryObject<MenuType<FormerMenu>> FORMER_MENU = CONTAINERS.register("former_menu",
	        () -> IForgeMenuType.create(FormerMenu::new));
	
	public static final RegistryObject<MenuType<MolderMenu>> MOLDER_MENU = CONTAINERS.register("molder_menu",
	        () -> IForgeMenuType.create(MolderMenu::new));
	
	public static final RegistryObject<MenuType<FirearmInspectorMenu>> GUN_INSPECTOR_MENU = CONTAINERS.register("inspector_menu",
	        () -> IForgeMenuType.create(FirearmInspectorMenu::new));
	
	public static final RegistryObject<MenuType<AutomatedLoaderMenu>> AUTOMATED_LOADER_MENU = CONTAINERS.register("automated_loader_menu",
	        () -> IForgeMenuType.create(AutomatedLoaderMenu::new));
}
