package themastergeneral.thismeanswar.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWBlockRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TMWMain.MODID);
	
	public static final RegistryObject<Block> brass_ore = BLOCKS.register("brass_ore", () ->TMWBlocks.brass_ore);
	public static final RegistryObject<Block> lead_ore = BLOCKS.register("lead_ore", () ->TMWBlocks.lead_ore);
}
