package themastergeneral.thismeanswar;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.define.TMWPistols;

public class TMWTabs {

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TMWMain.MODID);
	
	public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("tmw_main", () -> CreativeModeTab.builder()
			.withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> TMWItems.ammo_box.getDefaultInstance())
            .title(Component.translatable("itemGroup.thismeanswar"))
            .build());
	
	public static final RegistryObject<CreativeModeTab> AMMO_TAB = CREATIVE_MODE_TABS.register("tmw_ammo", () -> CreativeModeTab.builder()
			.icon(() -> TMWItems.round_40mm.getDefaultInstance())
            .title(Component.translatable("itemGroup.thismeanswar.ammo"))
            .build());
	
	public static final RegistryObject<CreativeModeTab> GUN_TAB = CREATIVE_MODE_TABS.register("tmw_guns", () -> CreativeModeTab.builder()
            .withTabsBefore(MAIN_TAB.getKey())
            .withTabsAfter(AMMO_TAB.getKey())
			.icon(() -> TMWPistols.m1911.getDefaultInstance())
            .title(Component.translatable("itemGroup.thismeanswar.guns"))
            .build());
	
	public static final RegistryObject<CreativeModeTab> ARMOR_TAB = CREATIVE_MODE_TABS.register("tmw_armor", () -> CreativeModeTab.builder()
            .withTabsBefore(AMMO_TAB.getKey())
			.icon(() -> TMWItems.basic_prot_chest.getDefaultInstance())
            .title(Component.translatable("itemGroup.thismeanswar.armor"))
            .build());
}
