package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockStone extends AbstractTMWBlock {

	public BlockStone() {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.STONE)
				.mapColor(MapColor.STONE)
				.strength(1.0F, 1.5F));
	}

}
