package themastergeneral.thismeanswar.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import themastergeneral.thismeanswar.TMWMain;

public class TMWTags {
	
	//Upgrade tags
	public static TagKey<Item> disableAPUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_armor_piercing"));
	public static TagKey<Item> disableFireUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_flame_rounds"));
	public static TagKey<Item> disableMagUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_mag_capacity"));
	public static TagKey<Item> disableFullAutoUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_full_rof"));
	public static TagKey<Item> disableSemiAutoUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_semi_rof"));
	public static TagKey<Item> disableBayonetUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_bayonet"));
	public static TagKey<Item> disableTracerUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_tracer_rounds"));
	public static TagKey<Item> disableAllUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/disable_all_upgrade"));

	//Rounds tags
	public static TagKey<Item> rounds9mm = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_9mm"));
	public static TagKey<Item> rounds12g = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_12g"));
	public static TagKey<Item> rounds45 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_45"));
	public static TagKey<Item> rounds223 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_223"));
	public static TagKey<Item> rounds556 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_556"));
	public static TagKey<Item> roundsenergy = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_energy"));
	public static TagKey<Item> roundsexplosive = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/explosive"));
}
