package themastergeneral.thismeanswar.block;

import java.text.NumberFormat;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.phys.shapes.VoxelShape;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;
import themastergeneral.thismeanswar.items.AbstractBulletItem;
import themastergeneral.thismeanswar.items.AbstractMagazineItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class BlockAmmoStorage extends GlassBlock implements EntityBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 3.0D, 15.0D, 10.0D, 13.0D);
	protected static final VoxelShape SHAPE_ROT = Block.box(3.0D, 0.0D, 1.0D, 13.0D, 10.0D, 15.0D);
	
	public int maxStorage;
	
	public BlockAmmoStorage(int maxStorage) {
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.strength(2.25F));
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
	    	if (!ammostorage.getAmmo().isEmpty())
	    	{
	    		//Player is crouching, so they remove items.
	    		if (player.isCrouching())
	    		{
	    			removeAmmoFromStorage(ammostorage, world, player, blockpos);
	    		}
	    		else
	    		{
	    			//user holding rounds/ammo
		    		if (stack.getItem() instanceof AbstractBulletItem)
					{
		    			//Check to see if ammo in the storage is the same ammo in hand.
		    			return addAmmoToStorage(ammostorage, world, player, blockpos, stack);
					}
		    		//user holding magazine
		    		if (stack.getItem() instanceof AbstractMagazineItem)
		    		{
		    			return fillHeldMagazine(ammostorage, player, stack);
		    		}
		    		else
		    		{
		    			sendUpdateMsg(player, world, blockpos);
		    	    	return InteractionResult.PASS;
		    		}
	    		}
	    	}
	    	else
	    	{
	    		if (stack.getItem() instanceof AbstractBulletItem)
				{
	    			//add into box
	    			ammostorage.updateAmmo(stack.getItem(), stack.getCount());
	    			stack.shrink(stack.getCount());
	    			return InteractionResult.PASS;
				}
	    		sendUpdateMsg(player, world, blockpos);
    	    	return InteractionResult.PASS;
	    	}
	    	sendUpdateMsg(player, world, blockpos);
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
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) 
	{
	      if ((state.getValue(FACING) == Direction.NORTH) || (state.getValue(FACING) == Direction.SOUTH))
	    	  return SHAPE;
	      else
	    	  return SHAPE_ROT;
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
	
	private void sendUpdateMsg(Player player, Level world, BlockPos blockpos)
	{
		BlockEntityAmmoStorage ammostorage = (BlockEntityAmmoStorage) world.getBlockEntity(blockpos);
		if (ammostorage.getAmmo() == ItemStack.EMPTY)
		{
			MutableComponent message = ModUtils.displayTranslation("thismeanswar.empty_ammo_storage");
			player.displayClientMessage(message, true);
		}
		else
		{
			MutableComponent message = ModUtils.displayString(NumberFormat.getInstance().format(ammostorage.getAmmoQuantity()) + " / " + NumberFormat.getInstance().format(ammostorage.getAmmoMaxQuantity()) + " (");
			message.append(ModUtils.displayTranslation(ammostorage.getAmmoItem().getDescriptionId()));
			message.append(")");
			player.displayClientMessage(message, true);
		}
	}
	
	public void removeAmmoFromStorage(BlockEntityAmmoStorage ammostorage, Level world, Player player, BlockPos blockpos)
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
            if (player.getOffhandItem().getItem() != TMWItems.creative_charm)
            	ammostorage.updateAmmo(ammostorage.getAmmoItem(), -1);
            sendUpdateMsg(player, world, blockpos);
        }
	}
	
	public InteractionResult addAmmoToStorage(BlockEntityAmmoStorage ammostorage, Level world, Player player, BlockPos blockpos, ItemStack stack)
	{
		if (ammostorage.getAmmoItem() == stack.getItem())
		{
			//Current stack CAN fit into the storage, so we fit the whole stack into it.
			if (stack.getCount() + ammostorage.getAmmoQuantity() <= ammostorage.getAmmoMaxQuantity())
			{
				ammostorage.updateAmmo(stack.getItem(), stack.getCount());
				stack.shrink(stack.getCount());
				sendUpdateMsg(player, world, blockpos);
				return InteractionResult.PASS;
			}
			//Entire stack cannot fit, so lets try to fit what we can.
			else
			{
				int availablespace = ammostorage.getAmmoMaxQuantity() - ammostorage.getAmmoQuantity();
				if (availablespace > 0)
				{
					ammostorage.updateAmmo(stack.getItem(), availablespace);
					if (player.getOffhandItem().getItem() != TMWItems.creative_charm)
						stack.shrink(availablespace);
					sendUpdateMsg(player, world, blockpos);
					return InteractionResult.PASS;
				}
				else
				{
					sendUpdateMsg(player, world, blockpos);
	    	    	return InteractionResult.PASS;
				}
			}
		}
		return InteractionResult.FAIL;
	}
	
	private InteractionResult fillHeldMagazine(BlockEntityAmmoStorage ammostorage, Player player, ItemStack stack)
	{
		AbstractMagazineItem magStack = (AbstractMagazineItem) stack.getItem();
		//fill mag
		if (magStack.getCurrentAmmo(stack) < magStack.getMaxAmmo(stack))
		{
			int toFill = (magStack.getMaxAmmo(stack) - magStack.getCurrentAmmo(stack));
			if (ammostorage.getAmmoQuantity() >= toFill)
			{
				magStack.addAmmoToMag(stack, toFill);
				if (player.getOffhandItem().getItem() != TMWItems.creative_charm)
				{
					ammostorage.updateAmmo(stack.getItem(), toFill * -1);
					player.getCooldowns().addCooldown(magStack, 20);
				}
				return InteractionResult.PASS;
			}
			else
				return InteractionResult.FAIL;
		}
		else
			return InteractionResult.FAIL;
	}
}
