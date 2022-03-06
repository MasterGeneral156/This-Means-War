package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class TMWBlockOre extends OreBlock {

	public TMWBlockOre(Integer harvestLevel) 
	{
		super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
				.sound(SoundType.STONE)
				.strength(3F)
				.requiresCorrectToolForDrops());
	}

}
