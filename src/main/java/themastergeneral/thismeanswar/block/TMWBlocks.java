package themastergeneral.thismeanswar.block;

public class TMWBlocks {
	public static BlockOre ore_lead = new BlockOre(3);
	public static BlockOre ore_brass = new BlockOre(2);
	
	public static AbstractTMWBlock block_lead = new BlockMetal();
	public static AbstractTMWBlock block_steel = new BlockMetal();
	public static AbstractTMWBlock block_brass = new BlockMetal();
	
	public static BlockAmmoStorage ammo_box = new BlockAmmoStorage(512);
	public static BlockAmmoStorage ammo_box_medium = new BlockAmmoStorage(1024);
	public static BlockAmmoStorage ammo_box_large = new BlockAmmoStorage(2048);
	
	public static BlockCrusher crusher = new BlockCrusher();
}