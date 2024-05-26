package themastergeneral.thismeanswar.block;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import themastergeneral.thismeanswar.block.entity.BlockEntityTipRecycler;

public class BlockTipRecycler extends Block implements EntityBlock {
	
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public BlockTipRecycler() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.5F).mapColor(MapColor.STONE).lightLevel((p_50763_) -> {
	         return p_50763_.getValue(BlockStateProperties.LIT) ? 13 : 0;
	      }).requiresCorrectToolForDrops());
		this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.LIT, false).setValue(FACING, Direction.NORTH));
	}
	
	@Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT).add(FACING);
    }
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate)
	{
		return new BlockEntityTipRecycler(blockpos, blockstate);
	}
	
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((BlockEntityTipRecycler) blockEntity).tick(level0, pos0, state0,  ((BlockEntityTipRecycler) blockEntity));
    }
	
	@Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof BlockEntityTipRecycler)) 
		{
			return InteractionResult.PASS;
		}
		
		if (level.isClientSide())
			return InteractionResult.SUCCESS;
		
		if (player instanceof ServerPlayer splayer)
			NetworkHooks.openScreen(splayer, (BlockEntityTipRecycler) blockEntity, pos);
        return InteractionResult.CONSUME;
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
