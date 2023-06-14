package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlockMetal extends AbstractTMWBlock {

	public BlockMetal() {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.strength(5.0F, 6.0F));
	}

}
