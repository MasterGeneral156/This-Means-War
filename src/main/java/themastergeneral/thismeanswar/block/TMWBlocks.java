package themastergeneral.thismeanswar.block;

public class TMWBlocks {
	public static BlockOre ore_lead = new BlockOre();
	public static BlockOre ore_brass = new BlockOre();
	
	public static BlockMetal block_lead = new BlockMetal();
	public static BlockMetal block_steel = new BlockMetal();
	public static BlockMetal block_brass = new BlockMetal();
	
	public static SupplyBlock supplies_ammo = new SupplyBlock();
	public static SupplyBlock supplies_guns = new SupplyBlock();
	public static SupplyBlock supplies_mags = new SupplyBlock();
	
	public static RedTeamBlock red_stone_bricks = new RedTeamBlock();
	public static BlueTeamBlock blue_stone_bricks = new BlueTeamBlock();
	public static RedTeamBlock red_chiseled_bricks = new RedTeamBlock();
	public static BlueTeamBlock blue_chiseled_bricks = new BlueTeamBlock();
	
	public static BlockAmmoStorage ammo_box = new BlockAmmoStorage(1024);
	public static BlockAmmoStorage ammo_box_medium = new BlockAmmoStorage(2048);
	public static BlockAmmoStorage ammo_box_large = new BlockAmmoStorage(4096);
	
	public static BlockMedicBox medic_box = new BlockMedicBox(1024F);
	public static BlockMedicBox medic_box_medium = new BlockMedicBox(2048F);
	public static BlockMedicBox medic_box_large = new BlockMedicBox(4096F);
	
	public static BlockBarbedWire barbed_wire = new BlockBarbedWire();
	
	public static BlockCrusher crusher = new BlockCrusher();
	public static BlockFoundary bullet_foundary = new BlockFoundary();
	public static BlockCasingRecycler casing_recycler = new BlockCasingRecycler();
	public static BlockTipRecycler tip_recycler = new BlockTipRecycler();
	public static BlockFactoryHolder factory_holder = new BlockFactoryHolder();
	public static BlockPress press = new BlockPress();
	public static BlockPrimerPopper primer_popper = new BlockPrimerPopper();
	public static BlockAlloySmelter alloy_smelter = new BlockAlloySmelter();
	public static BlockCasingFormer casing_former = new BlockCasingFormer();
	public static BlockTipMolder tip_molder = new BlockTipMolder();
	public static BlockAutomatedLoader automated_loader = new BlockAutomatedLoader();
	
	public static BlockFirearmInspector firearm_inspector = new BlockFirearmInspector();
	
	public static BlockExplosiveResistive shelter_weak = new BlockExplosiveResistive();
	public static BlockExplosiveResistive shelter_medium = new BlockExplosiveResistive(2);
	public static BlockExplosiveResistive shelter_strong = new BlockExplosiveResistive(4);
	public static BlockExplosiveResistive shelter_op = new BlockExplosiveResistive(8);
	public static BlockExplosiveResistive shelter_creative = new BlockExplosiveResistive(64);
	
	public static BlockLandmine land_mine = new BlockLandmine(20F);
}