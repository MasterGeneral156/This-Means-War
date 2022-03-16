package themastergeneral.thismeanswar.block;

import java.text.NumberFormat;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
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
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;
import themastergeneral.thismeanswar.items.BulletItem;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockAmmoStorage extends GlassBlock implements EntityBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
	
	public int maxStorage;
	
	public BlockAmmoStorage(int maxStorage) {
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
				.sound(SoundType.WOOD)
				.strength(3F));
		this.maxStorage = maxStorage;
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
		
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate) {
		return new BlockEntityAmmoStorage(blockpos, blockstate, maxStorage);
	}
	
	public InteractionResult use(BlockState blockstate, Level world, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult blockhit) 
	{
		if (!world.isClientSide) 
		{
			ItemStack stack = player.getItemInHand(hand);
			BlockEntityAmmoStorage ammostorage = (BlockEntityAmmoStorage) world.getBlockEntity(blockpos);
		    
	    	//Make sure the ammo item is actually set....
	    	if (ammostorage.getAmmo() != ItemStack.EMPTY)
	    	{
	    		//Player is crouching, so they remove items.
	    		if (player.isCrouching())
	    		{
	    			float f = 0.7F;
	                double d0 = (double)(world.random.nextFloat() * f) + (double)0.15F;
	                double d1 = (double)(world.random.nextFloat() * f) + (double)0.060000002F + 0.6D;
	                double d2 = (double)(world.random.nextFloat() * f) + (double)0.15F;
	                if (ammostorage.getAmmoQuantity() > 0)
	                {
		                ItemStack ammoDrop = new ItemStack(ammostorage.getAmmoItem(), 1);
		                ItemEntity itementity = new ItemEntity(world, (double)blockpos.getX() + d0, (double)blockpos.getY() + d1, (double)blockpos.getZ() + d2, ammoDrop);
		                itementity.setDefaultPickUpDelay();
		                world.addFreshEntity(itementity);
		                ammostorage.updateAmmo(ammostorage.getAmmoItem(), -1);
		                TMWMain.LOGGER.info("Dropped 1 bullet");
	                }
	    		}
	    		else
	    		{
		    		if (stack.getItem() instanceof BulletItem)
					{
		    			//Check to see if ammo in the storage is the same ammo in hand.
		    			if (ammostorage.getAmmoItem() == stack.getItem())
		    			{
		    				//Current stack CAN fit into the storage, so we fit the whole stack into it.
		    				if (stack.getCount() + ammostorage.getAmmoQuantity() <= ammostorage.getAmmoMaxQuantity())
		    				{
		    					ammostorage.updateAmmo(stack.getItem(), stack.getCount());
		    					stack.shrink(stack.getCount());
		    					return InteractionResult.PASS;
		    				}
		    				//Entire stack cannot fit, so lets try to fit what we can.
		    				else
		    				{
		    					int availablespace = ammostorage.getAmmoMaxQuantity() - ammostorage.getAmmoQuantity();
		    					if (availablespace > 0)
		    					{
		    						ammostorage.updateAmmo(stack.getItem(), availablespace);
			    					stack.shrink(availablespace);
			    					return InteractionResult.PASS;
		    					}
		    					else
		    					{
		    						return InteractionResult.FAIL;
		    					}
		    				}
		    			}
					}
		    		else
		    		{
		    			return InteractionResult.PASS;
		    		}
	    		}
	    	}
	    	else
	    	{
	    		if (stack.getItem() instanceof BulletItem)
				{
	    			ammostorage.updateAmmo(stack.getItem(), stack.getCount());
	    			stack.shrink(stack.getCount());
	    			return InteractionResult.PASS;
				}
	    	}
	    	TextComponent message = new TextComponent(NumberFormat.getInstance().format(ammostorage.getAmmoQuantity()) + " / " + NumberFormat.getInstance().format(ammostorage.getAmmoMaxQuantity()) + " (");
			message.append(new TranslatableComponent(ammostorage.getAmmo().getItem().getDescriptionId()));
			message.append(")");
			player.displayClientMessage(message, true);
	    	return InteractionResult.PASS;
		}
		return InteractionResult.FAIL;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext place) {
	      return this.defaultBlockState().setValue(FACING, place.getHorizontalDirection().getOpposite());
   	}
	
	@Override
	public BlockState rotate(BlockState blockstate, Rotation blockrot) {
		return blockstate.setValue(FACING, blockrot.rotate(blockstate.getValue(FACING)));
   	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
	   p_48725_.add(FACING);
   	}
	
	public VoxelShape getCollisionShape(BlockState p_56702_, BlockGetter p_56703_, BlockPos p_56704_, CollisionContext p_56705_) {
		return SHAPE;
	}

	public VoxelShape getBlockSupportShape(BlockState p_56707_, BlockGetter p_56708_, BlockPos p_56709_) {
		return Shapes.block();
	}

	public VoxelShape getVisualShape(BlockState p_56684_, BlockGetter p_56685_, BlockPos p_56686_, CollisionContext p_56687_) {
		return Shapes.block();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState blockstate, Level world, BlockPos blockpos, BlockState blockstate2, boolean bool)
	{
		BlockEntityAmmoStorage ammostorage = (BlockEntityAmmoStorage) world.getBlockEntity(blockpos);
		int totalAvailable = ammostorage.getAmmoQuantity();
		while (totalAvailable > 0)
		{
			float f = 0.7F;
            double d0 = (double)(world.random.nextFloat() * f) + (double)0.15F;
            double d1 = (double)(world.random.nextFloat() * f) + (double)0.060000002F + 0.6D;
            double d2 = (double)(world.random.nextFloat() * f) + (double)0.15F;
            ItemStack ammoDrop = new ItemStack(ammostorage.getAmmoItem(), 1);
            ammostorage.updateAmmo(ammostorage.getAmmoItem(), -1);
            totalAvailable--;
            
			if (totalAvailable >= 64)
			{
				totalAvailable = totalAvailable - 63;
                ammoDrop = new ItemStack(ammostorage.getAmmoItem(), 64);
                ammostorage.updateAmmo(ammostorage.getAmmoItem(), -64);
			}
			TMWMain.LOGGER.info(totalAvailable);
			ItemEntity itementity = new ItemEntity(world, (double)blockpos.getX() + d0, (double)blockpos.getY() + d1, (double)blockpos.getZ() + d2, ammoDrop);
            itementity.setDefaultPickUpDelay();
            world.addFreshEntity(itementity);
		}
		super.onRemove(blockstate, world, blockpos, blockstate2, bool);
	}
}
