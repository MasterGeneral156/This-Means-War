package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockExplosiveResistive extends AbstractTMWBlock {

	public BlockExplosiveResistive(float multi) {
		super(BlockBehaviour.Properties.of().strength(100.0F * multi, 2400.0F * multi).sound(SoundType.GILDED_BLACKSTONE).requiresCorrectToolForDrops());
	}
	
	public BlockExplosiveResistive() {
		super(BlockBehaviour.Properties.of().strength(100.0F, 2400.0F).sound(SoundType.GILDED_BLACKSTONE).requiresCorrectToolForDrops());
	}

}
