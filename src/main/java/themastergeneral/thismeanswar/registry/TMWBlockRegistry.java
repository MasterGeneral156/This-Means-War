package themastergeneral.thismeanswar.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.BlockBarbedWire;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWBlockRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TMWMain.MODID);
	
	public static final RegistryObject<Block> ore_brass = BLOCKS.register("ore_brass", () ->TMWBlocks.ore_brass);
	public static final RegistryObject<Block> ore_lead = BLOCKS.register("ore_lead", () ->TMWBlocks.ore_lead);
	
	public static final RegistryObject<Block> red_stone_bricks = BLOCKS.register("red_stone_bricks", () ->TMWBlocks.red_stone_bricks);
	public static final RegistryObject<Block> blue_stone_bricks = BLOCKS.register("blue_stone_bricks", () ->TMWBlocks.blue_stone_bricks);
	public static final RegistryObject<Block> red_chiseled_brick = BLOCKS.register("red_chiseled_brick", () ->TMWBlocks.red_chiseled_bricks);
	public static final RegistryObject<Block> blue_chiseled_brick = BLOCKS.register("blue_chiseled_brick", () ->TMWBlocks.blue_chiseled_bricks);
	
	public static final RegistryObject<Block> block_lead = BLOCKS.register("block_lead", () ->TMWBlocks.block_lead);
	public static final RegistryObject<Block> block_brass = BLOCKS.register("block_brass", () ->TMWBlocks.block_brass);
	public static final RegistryObject<Block> block_steel = BLOCKS.register("block_steel", () ->TMWBlocks.block_steel);
	
	public static final RegistryObject<Block> ammo_box = BLOCKS.register("ammo_box", () ->TMWBlocks.ammo_box);
	public static final RegistryObject<Block> ammo_box_medium = BLOCKS.register("ammo_box_medium", () ->TMWBlocks.ammo_box_medium);
	public static final RegistryObject<Block> ammo_box_large = BLOCKS.register("ammo_box_large", () ->TMWBlocks.ammo_box_large);
	
	public static final RegistryObject<Block> medic_box = BLOCKS.register("medic_box", () ->TMWBlocks.medic_box);
	public static final RegistryObject<Block> medic_box_medium = BLOCKS.register("medic_box_medium", () ->TMWBlocks.medic_box_medium);
	public static final RegistryObject<Block> medic_box_large = BLOCKS.register("medic_box_large", () ->TMWBlocks.medic_box_large);
	
	public static final RegistryObject<Block> barbed_wire = BLOCKS.register("barbed_wire", () ->TMWBlocks.barbed_wire);
	
	//public static final RegistryObject<Block> crusher = BLOCKS.register("crusher", () ->TMWBlocks.crusher);
	public static final RegistryObject<Block> bullet_foundary = BLOCKS.register("bullet_foundary", () ->TMWBlocks.bullet_foundary);
}
