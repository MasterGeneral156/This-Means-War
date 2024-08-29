package themastergeneral.thismeanswar.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import themastergeneral.thismeanswar.menu.BulletWorkshopMenu;

public class BlockFoundary extends CraftingTableBlock {
   	private static final Component CONTAINER_TITLE = Component.translatable("thismeanswar.container.smithing_table");
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

   	public BlockFoundary() {
  		super(BlockBehaviour.Properties.of()
				.sound(SoundType.WOOD)
				.noOcclusion()
				.mapColor(MapColor.WOOD)
				.strength(2.25F));
   		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

   	public MenuProvider getMenuProvider(BlockState p_56435_, Level p_56436_, BlockPos p_56437_) {
		  return new SimpleMenuProvider((p_277304_, p_277305_, p_277306_) -> {
			 return new BulletWorkshopMenu(p_277304_, p_277305_, ContainerLevelAccess.create(p_56436_, p_56437_));
		  }, CONTAINER_TITLE);
   	}

	   public InteractionResult use(BlockState p_56428_, Level p_56429_, BlockPos p_56430_, Player p_56431_, InteractionHand p_56432_, BlockHitResult p_56433_)
	   {
		  if (p_56429_.isClientSide) {
			 return InteractionResult.SUCCESS;
		  } else {
			 p_56431_.openMenu(p_56428_.getMenuProvider(p_56429_, p_56430_));
			 return InteractionResult.CONSUME;
		  }
	   }

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext place) {
		return this.defaultBlockState().setValue(FACING, place.getHorizontalDirection());
	}

	@Override
	public BlockState rotate(BlockState blockstate, Rotation blockrot) {
		return blockstate.setValue(FACING, blockrot.rotate(blockstate.getValue(FACING)));
	}
}
