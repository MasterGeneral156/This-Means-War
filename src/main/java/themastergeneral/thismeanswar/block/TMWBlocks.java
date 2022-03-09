package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class TMWBlocks {
	public static TMWBlockOre ore_lead = new TMWBlockOre(3);
	public static TMWBlockOre ore_brass = new TMWBlockOre(2);
	
	public static TMWBlockBase block_lead = new TMWBlockBase(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).sound(SoundType.METAL));
	public static TMWBlockBase block_steel = new TMWBlockBase(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).sound(SoundType.METAL));
	public static TMWBlockBase block_brass = new TMWBlockBase(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).sound(SoundType.METAL));
}