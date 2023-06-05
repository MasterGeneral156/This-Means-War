package themastergeneral.thismeanswar.block;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;
import themastergeneral.thismeanswar.block.entity.BlockEntityMedicBox;
import themastergeneral.thismeanswar.items.AbstractHealingItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class BlockMedicBox extends GlassBlock implements EntityBlock 
{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 3.0D, 15.0D, 10.0D, 13.0D);
	protected static final VoxelShape SHAPE_ROT = Block.box(3.0D, 0.0D, 1.0D, 13.0D, 10.0D, 15.0D);

	public BlockMedicBox() 
	{
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.strength(2.25F));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate)
	{
		return new BlockEntityMedicBox(blockpos, blockstate);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext place) 
	{
	      return this.defaultBlockState().setValue(FACING, place.getHorizontalDirection().getOpposite());
   	}
	
	@Override
	public BlockState rotate(BlockState blockstate, Rotation blockrot) 
	{
		return blockstate.setValue(FACING, blockrot.rotate(blockstate.getValue(FACING)));
   	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) 
	{
	   p_48725_.add(FACING);
   	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) 
	{
	      if ((state.getValue(FACING) == Direction.NORTH) || (state.getValue(FACING) == Direction.SOUTH))
	    	  return SHAPE;
	      else
	    	  return SHAPE_ROT;
	}
	
	public InteractionResult use(BlockState blockstate, Level world, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult blockhit) 
	{
		if (!world.isClientSide) 
		{
			ItemStack stack = player.getItemInHand(hand);
			BlockEntityMedicBox medicBox = (BlockEntityMedicBox) world.getBlockEntity(blockpos);
			if (stack.getItem() instanceof AbstractHealingItem)
			{
				AbstractHealingItem healthItem = (AbstractHealingItem) stack.getItem();
				if (healthItem.getRegeneratedHealth() > 0.0F)
				{
					if ((medicBox.getHealthStored() + healthItem.getRegeneratedHealth()) < 1024)
					{
					
						medicBox.updateHealthStored(healthItem.getRegeneratedHealth());
						stack.shrink(1);
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.medic_box.success"), true);
						return InteractionResult.CONSUME;
					}
					else
					{
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.medic_box.fail"), true);
						return InteractionResult.FAIL;
					}
				}
			}
			else if (medicBox.getHealthStored() > 0)
			{
				if (player.getHealth() < player.getMaxHealth())
				{
					float healthHealed = player.getMaxHealth() - player.getHealth();
					if (medicBox.getHealthStored() < healthHealed)
					{
						player.setHealth(player.getHealth() + medicBox.getHealthStored());
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.medic_box.regenerated"), true);
						return InteractionResult.SUCCESS;
					}
					else
					{
						player.setHealth(player.getHealth() + healthHealed);
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.medic_box.regenerated"), true);
						return InteractionResult.PASS;
					}
				}
				else
				{
					player.displayClientMessage(ModUtils.displayString(medicBox.getHealthStored() + " / 1024 health currently stored."), true);
					return InteractionResult.PASS;
				}
			}
			else
			{
				player.displayClientMessage(ModUtils.displayString("You currently have full health."), true);
				return InteractionResult.FAIL;
			}
		}
		return InteractionResult.FAIL;
	}

}
