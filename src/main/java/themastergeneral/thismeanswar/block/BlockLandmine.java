package themastergeneral.thismeanswar.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockLandmine extends AbstractTMWBlock {

	protected float explosionDamage;
	protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);
	
	public BlockLandmine(float explosionDamage) 
	{
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.forceSolidOn()
				.noCollission()
				.requiresCorrectToolForDrops()
				.strength(4.0F)
				.pushReaction(PushReaction.DESTROY));
		this.explosionDamage = explosionDamage;
	}
	
	public BlockLandmine() 
	{
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.forceSolidOn()
				.noCollission()
				.requiresCorrectToolForDrops()
				.strength(4.0F)
				.pushReaction(PushReaction.DESTROY));
		this.explosionDamage = 8.0F;
	}
	
	@Override
	public void entityInside(BlockState blockstate, Level level, BlockPos blockpos, Entity entity) 
	{
		entity.makeStuckInBlock(blockstate, new Vec3(0.25D, (double)0.05F, 0.25D));
		level.removeBlock(blockpos, false);
		level.explode(entity, blockpos.getX(), blockpos.getY(), blockpos.getZ(), explosionDamage, ExplosionInteraction.TNT);
		super.entityInside(blockstate, level, blockpos, entity);
	}
	
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) 
	{
		level.removeBlock(pos, false);
		level.explode(entity, pos.getX(), pos.getY(), pos.getZ(), explosionDamage, ExplosionInteraction.TNT);
		super.stepOn(level, pos, state, entity);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) 
	{
		return SHAPE;
	}
}
