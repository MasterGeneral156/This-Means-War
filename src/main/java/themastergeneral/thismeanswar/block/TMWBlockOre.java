package themastergeneral.thismeanswar.block;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class TMWBlockOre extends OreBlock {

	public TMWBlockOre(Integer harvestLevel) 
	{
		super(Block.Properties.of(Material.STONE, MaterialColor.STONE)
				.sound(SoundType.STONE)
				.strength(3F)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(harvestLevel));
	}

}
