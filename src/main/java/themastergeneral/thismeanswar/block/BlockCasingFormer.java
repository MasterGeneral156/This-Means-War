package themastergeneral.thismeanswar.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import themastergeneral.thismeanswar.block.entity.BlockEntityCasingFormer;

public class BlockCasingFormer extends TMWRotatableBlock implements EntityBlock {
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate)
	{
		return new BlockEntityCasingFormer(blockpos, blockstate);
	}
	
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((BlockEntityCasingFormer) blockEntity).tick(level0, pos0, state0,  ((BlockEntityCasingFormer) blockEntity));
    }
	
	@Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof BlockEntityCasingFormer)) 
		{
			return InteractionResult.PASS;
		}
		
		if (level.isClientSide())
			return InteractionResult.SUCCESS;
		
		if (player instanceof ServerPlayer splayer)
			NetworkHooks.openScreen(splayer, (BlockEntityCasingFormer) blockEntity, pos);
        return InteractionResult.CONSUME;
    }
}
