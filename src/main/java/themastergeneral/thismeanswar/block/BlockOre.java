package themastergeneral.thismeanswar.block;

import com.themastergeneral.ctdcore.block.CTDBlock;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockOre extends CTDBlock {

	public BlockOre(Integer harvestLevel) 
	{
		super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
				.sound(SoundType.STONE)
				.strength(3F)
				.requiresCorrectToolForDrops());
	}

}
