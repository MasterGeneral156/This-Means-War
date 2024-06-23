package themastergeneral.thismeanswar.block;

import javax.annotation.Nullable;

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
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import themastergeneral.thismeanswar.block.entity.BlockEntityAutomatedLoader;

public class BlockAutomatedLoader extends TMWRotatableBlock implements EntityBlock {
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate)
	{
		return new BlockEntityAutomatedLoader(blockpos, blockstate);
	}
	
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((BlockEntityAutomatedLoader) blockEntity).tick(level0, pos0, state0,  ((BlockEntityAutomatedLoader) blockEntity));
    }
	
	@Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof BlockEntityAutomatedLoader)) 
		{
			return InteractionResult.PASS;
		}
		
		if (level.isClientSide())
			return InteractionResult.SUCCESS;
		
		if (player instanceof ServerPlayer splayer)
			NetworkHooks.openScreen(splayer, (BlockEntityAutomatedLoader) blockEntity, pos);
        return InteractionResult.CONSUME;
    }
}
