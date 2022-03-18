package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockMetal extends BlockBase {

	public BlockMetal() {
		super(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
				.sound(SoundType.METAL)
				.strength(5.0F, 6.0F));
	}

}
