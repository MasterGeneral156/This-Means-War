package themastergeneral.thismeanswar.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;

public class BlockBarbedWire extends AbstractTMWBlock {

	public BlockBarbedWire() 
	{
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.WOOD)
				.mapColor(MapColor.METAL)
				.forceSolidOn()
				.noCollission()
				.requiresCorrectToolForDrops()
				.strength(4.0F)
				.pushReaction(PushReaction.DESTROY));
	}
	
	@Override
	public void entityInside(BlockState blockstate, Level level, BlockPos blockpos, Entity entity) 
	{
		entity.makeStuckInBlock(blockstate, new Vec3(0.25D, (double)0.05F, 0.25D));
		this.damageEntityInside(entity);
		super.entityInside(blockstate, level, blockpos, entity);
	}
	
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) 
	{
		this.damageEntityInside(entity);
		super.stepOn(level, pos, state, entity);
	}
	   
	protected void damageEntityInside(Entity entity)
	{
		entity.hurt(entity.damageSources().cactus(), 2.85F);
	}
}
