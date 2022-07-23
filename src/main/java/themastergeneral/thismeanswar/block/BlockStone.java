package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockStone extends AbstractTMWBlock {

	public BlockStone() {
		super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
				.sound(SoundType.STONE)
				.strength(1.0F, 1.5F));
	}

}
