package themastergeneral.thismeanswar.block;

public class TMWBlocks {
	public static BlockOre ore_lead = new BlockOre();
	public static BlockOre ore_brass = new BlockOre();
	
	public static BlockMetal block_lead = new BlockMetal();
	public static BlockMetal block_steel = new BlockMetal();
	public static BlockMetal block_brass = new BlockMetal();
	
	public static RedTeamBlock red_stone_bricks = new RedTeamBlock();
	public static BlueTeamBlock blue_stone_bricks = new BlueTeamBlock();
	public static RedTeamBlock red_chiseled_bricks = new RedTeamBlock();
	public static BlueTeamBlock blue_chiseled_bricks = new BlueTeamBlock();
	
	public static BlockAmmoStorage ammo_box = new BlockAmmoStorage(512);
	public static BlockAmmoStorage ammo_box_medium = new BlockAmmoStorage(1024);
	public static BlockAmmoStorage ammo_box_large = new BlockAmmoStorage(2048);
	
	public static BlockMedicBox medic_box = new BlockMedicBox(1024F);
	public static BlockMedicBox medic_box_medium = new BlockMedicBox(2048F);
	public static BlockMedicBox medic_box_large = new BlockMedicBox(4096F);
	
	public static BlockBarbedWire barbed_wire = new BlockBarbedWire();
	
	//public static BlockCrusher crusher = new BlockCrusher();
	public static BlockFoundary bullet_foundary = new BlockFoundary();
}