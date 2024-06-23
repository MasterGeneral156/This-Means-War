package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class SupplyBlock extends AbstractTMWBlock {

	public SupplyBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.WOOD)
				.mapColor(MapColor.WOOD)
				.strength(5.0F, 6.0F));
	}

}
