package themastergeneral.thismeanswar.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public abstract class BlockCrusher extends AbstractTMWBlock implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	   public static final BooleanProperty LIT = BlockStateProperties.LIT;
	   
	   public BlockCrusher() {
		      super(BlockBehaviour.Properties.of()
		    		  .requiresCorrectToolForDrops()
		    		  .mapColor(MapColor.STONE)
		    		  .strength(3.5F));
		      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
		   }

	/*@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate) {
		return new BlockEntityCrusher(blockpos, blockstate);
	}
	
	public InteractionResult use(BlockState p_48706_, Level p_48707_, BlockPos p_48708_, Player p_48709_, InteractionHand p_48710_, BlockHitResult p_48711_) {
	      if (p_48707_.isClientSide) {
	         return InteractionResult.SUCCESS;
	      } else {
	         this.openContainer(p_48707_, p_48708_, p_48709_);
	         return InteractionResult.CONSUME;
	      }
	   }
	
	protected void openContainer(Level p_48690_, BlockPos p_48691_, Player p_48692_) {
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
      return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
	      p_48725_.add(FACING, LIT);
	   }
	
	@Override
	public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
	      return p_48722_.setValue(FACING, p_48723_.rotate(p_48722_.getValue(FACING)));
	   }
	@Override
	   public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
	      return p_48719_.rotate(p_48720_.getRotation(p_48719_.getValue(FACING)));
	   }
	   
	@Nullable
	   protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends BlockEntityCrusher> p_151990_) {
	      return p_151988_.isClientSide ? null : createTicker(p_151988_, p_151989_, p_151990_);
	   }*/

}
