package themastergeneral.thismeanswar.block;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import themastergeneral.thismeanswar.block.entity.BlockEntityCasingRecycler;

public class BlockCasingRecycler extends Block implements EntityBlock {

	public BlockCasingRecycler() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL));
		this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.LIT, false));
	}
	
	@Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT);
    }
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate)
	{
		return new BlockEntityCasingRecycler(blockpos, blockstate);
	}
	
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((BlockEntityCasingRecycler) blockEntity).tick(level0, pos0, state0,  ((BlockEntityCasingRecycler) blockEntity));
    }
	
	@Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof BlockEntityCasingRecycler)) 
		{
			return InteractionResult.PASS;
		}
		
		if (level.isClientSide())
			return InteractionResult.SUCCESS;
		
		if (player instanceof ServerPlayer splayer)
			NetworkHooks.openScreen(splayer, (BlockEntityCasingRecycler) blockEntity, pos);
        return InteractionResult.CONSUME;
    }
	
	@Override
    public void onRemove(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pNewState, boolean pMovedByPiston) {
		BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
	}

}
