package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockExplosiveResistive extends AbstractTMWBlock {

	public BlockExplosiveResistive(float multi) {
		super(BlockBehaviour.Properties.of().strength(33.3F * multi, 333.3F * multi).sound(SoundType.GILDED_BLACKSTONE).requiresCorrectToolForDrops());
	}
	
	public BlockExplosiveResistive() {
		super(BlockBehaviour.Properties.of().strength(33.3F, 333.3F).sound(SoundType.GILDED_BLACKSTONE).requiresCorrectToolForDrops());
	}

}
