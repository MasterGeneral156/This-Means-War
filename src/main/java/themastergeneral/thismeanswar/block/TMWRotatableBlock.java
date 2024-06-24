package themastergeneral.thismeanswar.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;

public class TMWRotatableBlock extends Block {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	
	public TMWRotatableBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.5F).mapColor(MapColor.STONE).lightLevel((p_50763_) -> {
	         return p_50763_.getValue(BlockStateProperties.LIT) ? 13 : 0;
	      }).requiresCorrectToolForDrops());
		this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.LIT, false).setValue(FACING, Direction.NORTH));
	}
	
	public TMWRotatableBlock(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.LIT, false).setValue(FACING, Direction.NORTH));
	}
	
	@Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT).add(FACING);
    }
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext place) {
	      return this.defaultBlockState().setValue(FACING, place.getHorizontalDirection().getOpposite());
   	}
	
	@Override
	public BlockState rotate(BlockState blockstate, Rotation blockrot) {
		return blockstate.setValue(FACING, blockrot.rotate(blockstate.getValue(FACING)));
   	}
}
