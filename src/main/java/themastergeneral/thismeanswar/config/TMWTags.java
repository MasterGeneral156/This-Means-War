package themastergeneral.thismeanswar.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import themastergeneral.thismeanswar.TMWMain;

public class TMWTags {
	
	//Upgrade tags
	public static TagKey<Item> apUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/round/ap"));
	public static TagKey<Item> fireUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/round/flame"));
	public static TagKey<Item> magUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/mag/capacity"));
	public static TagKey<Item> fullAutoUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/gun/rof_full"));
	public static TagKey<Item> semiAutoUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/gun/rof_semi"));
	public static TagKey<Item> bayonetUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/gun/bayonet"));
	public static TagKey<Item> tracerUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/round/tracer"));
	public static TagKey<Item> inertUpgrade = ItemTags.create(new ResourceLocation(TMWMain.MODID, "upgrades/round/inert"));

	//Rounds tags
	public static TagKey<Item> rounds9mm = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_9mm"));
	public static TagKey<Item> rounds12g = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_12g"));
	public static TagKey<Item> rounds45 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_45"));
	public static TagKey<Item> rounds223 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_223"));
	public static TagKey<Item> rounds556 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_556"));
	public static TagKey<Item> rounds38spec = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_38spec"));
	public static TagKey<Item> roundsenergy = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_energy"));
	public static TagKey<Item> rounds762 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_762"));
	public static TagKey<Item> rounds40mm = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_40mm"));
	public static TagKey<Item> roundsexplosive = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/explosive"));
	public static TagKey<Item> rounds_all = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/round_all"));
	public static TagKey<Item> rounds_bazooka = ItemTags.create(new ResourceLocation(TMWMain.MODID, "ammo/rocket_bazooka"));
	
	//Magazine Tags
	public static TagKey<Item> mags9mm = ItemTags.create(new ResourceLocation(TMWMain.MODID, "magazines/9mm_magazines"));
	public static TagKey<Item> mags12g = ItemTags.create(new ResourceLocation(TMWMain.MODID, "magazines/12g_magazines"));
	public static TagKey<Item> mags45 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "magazines/45_magazines"));
	public static TagKey<Item> mags223 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "magazines/223_magazines"));
	public static TagKey<Item> mags556 = ItemTags.create(new ResourceLocation(TMWMain.MODID, "magazines/556_magazines"));
	public static TagKey<Item> magsEnergy = ItemTags.create(new ResourceLocation(TMWMain.MODID, "magazines/energy_magazines"));
	
	//Generic tags
	public static TagKey<Item> casing_all = ItemTags.create(new ResourceLocation(TMWMain.MODID, "casing/all"));
	public static TagKey<Item> bullet_tips = ItemTags.create(new ResourceLocation(TMWMain.MODID, "tip/all"));
	
	//Tools
	public static TagKey<Item> hammer = ItemTags.create(new ResourceLocation(TMWMain.MODID, "tools/hammer"));
	public static TagKey<Item> saw = ItemTags.create(new ResourceLocation(TMWMain.MODID, "tools/saw"));
	public static TagKey<Item> cutter = ItemTags.create(new ResourceLocation(TMWMain.MODID, "tools/cutter"));

	//Gun Type
	public static TagKey<Item> launchers = ItemTags.create(new ResourceLocation(TMWMain.MODID, "gun/launchers"));
	public static TagKey<Item> pistols = ItemTags.create(new ResourceLocation(TMWMain.MODID, "gun/pistols"));
	public static TagKey<Item> rifles = ItemTags.create(new ResourceLocation(TMWMain.MODID, "gun/rifles"));
	public static TagKey<Item> shotguns = ItemTags.create(new ResourceLocation(TMWMain.MODID, "gun/shotguns"));
	public static TagKey<Item> snipers = ItemTags.create(new ResourceLocation(TMWMain.MODID, "gun/snipers"));
	public static TagKey<Item> energy = ItemTags.create(new ResourceLocation(TMWMain.MODID, "gun/energy"));
	public static TagKey<Item> guns = ItemTags.create(new ResourceLocation(TMWMain.MODID, "guns"));
}
