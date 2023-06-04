package themastergeneral.thismeanswar.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import themastergeneral.thismeanswar.TMWMain;

public class TMWTags {
	public static TagKey<Item> disableAPUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_armor_piercing"));
	public static TagKey<Item> disableMagUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_mag_capacity"));
	public static TagKey<Item> disableFullAutoUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_full_rof"));
	public static TagKey<Item> disableSemiAutoUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_semi_rof"));
	public static TagKey<Item> disableBayonetUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_bayonet"));
	public static TagKey<Item> disableAllUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_all_upgrade"));

}
