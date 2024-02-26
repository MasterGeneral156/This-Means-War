package themastergeneral.thismeanswar.block;

import com.themastergeneral.ctdcore.block.CTDBlock;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockOre extends CTDBlock {

	public BlockOre() 
	{
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.STONE)
				.strength(3F)
				.mapColor(MapColor.STONE)
				.requiresCorrectToolForDrops());
	}

}
