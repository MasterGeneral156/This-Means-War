package themastergeneral.thismeanswar.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class TMWBlocks {
	public static BlockOre ore_lead = new BlockOre(3);
	public static BlockOre ore_brass = new BlockOre(2);
	
	public static BlockBase block_lead = new BlockBase(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).sound(SoundType.METAL).strength(5.0F, 6.0F));
	public static BlockBase block_steel = new BlockBase(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).sound(SoundType.METAL).strength(5.0F, 6.0F));
	public static BlockBase block_brass = new BlockBase(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).sound(SoundType.METAL).strength(5.0F, 6.0F));
	
	public static BlockAmmoStorage ammo_box = new BlockAmmoStorage(512);
	public static BlockAmmoStorage ammo_box_medium = new BlockAmmoStorage(1024);
	public static BlockAmmoStorage ammo_box_large = new BlockAmmoStorage(2048);
}