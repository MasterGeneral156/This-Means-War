package themastergeneral.thismeanswar.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWBlockRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TMWMain.MODID);
	
	public static final RegistryObject<Block> ore_brass = BLOCKS.register("ore_brass", () ->TMWBlocks.ore_brass);
	public static final RegistryObject<Block> block_brass = BLOCKS.register("block_brass", () ->TMWBlocks.block_brass);
	
	public static final RegistryObject<Block> block_lead = BLOCKS.register("block_lead", () ->TMWBlocks.block_lead);
	public static final RegistryObject<Block> ore_lead = BLOCKS.register("ore_lead", () ->TMWBlocks.ore_lead);
	
	public static final RegistryObject<Block> block_steel = BLOCKS.register("block_steel", () ->TMWBlocks.block_steel);
	
	public static final RegistryObject<Block> ammo_box = BLOCKS.register("ammo_box", () ->TMWBlocks.ammo_box);
	public static final RegistryObject<Block> ammo_box_medium = BLOCKS.register("ammo_box_medium", () ->TMWBlocks.ammo_box_medium);
	public static final RegistryObject<Block> ammo_box_large = BLOCKS.register("ammo_box_large", () ->TMWBlocks.ammo_box_large);
}
