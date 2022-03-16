package themastergeneral.thismeanswar.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;
import themastergeneral.thismeanswar.items.BulletItem;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockAmmoStorage extends BaseEntityBlock implements EntityBlock {

	public int maxStorage;
	
	public BlockAmmoStorage(int maxStorage) {
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
				.sound(SoundType.WOOD)
				.strength(3F));
		this.maxStorage = maxStorage;
		
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate) {
		return TMWBlockEntityRegistry.ammo_box.get().create(blockpos, blockstate);
	}
	
	public InteractionResult use(BlockState blockstate, Level world, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult blockhit) {
	    
		if (!world.isClientSide) 
		{
			BlockEntityAmmoStorage ammostorage = (BlockEntityAmmoStorage) world.getBlockEntity(blockpos);
		    CompoundTag tag = ammostorage.getTileData();
		    
		    TMWMain.LOGGER.info("Used ammo box.");
		    //Make sure we have the ammo item as a tag...
		    if (tag.contains("AmmoItem"))
		    {
		    	TMWMain.LOGGER.info("Has ammo item tag.");
		    	//Make sure the ammo item is actually set....
		    	if (ammostorage.getAmmo() != ItemStack.EMPTY)
		    	{
		    		TMWMain.LOGGER.info("Item stack is empty.");
		    		ItemStack stack = player.getItemInHand(hand);
		    		//Player is crouching, so they remove items.
		    		if (stack.getItem() instanceof BulletItem)
					{
		    			TMWMain.LOGGER.info("Correct bullet item.");
			    		if (player.isCrouching())
			    		{
			    			
			    			float f = 0.7F;
			                double d0 = (double)(world.random.nextFloat() * 0.7F) + (double)0.15F;
			                double d1 = (double)(world.random.nextFloat() * 0.7F) + (double)0.060000002F + 0.6D;
			                double d2 = (double)(world.random.nextFloat() * 0.7F) + (double)0.15F;
			                ItemStack ammoDrop = new ItemStack(ammostorage.getAmmoItem(), 1);
			                ItemEntity itementity = new ItemEntity(world, (double)blockpos.getX() + d0, (double)blockpos.getY() + d1, (double)blockpos.getZ() + d2, ammoDrop);
			                itementity.setDefaultPickUpDelay();
			                world.addFreshEntity(itementity);
			                ammostorage.updateAmmo(stack.getItem(), -1);
			                TMWMain.LOGGER.info("Dropped 1 bullet");
			    		}
			    		//player is not crouching, so we add items
			    		else
			    		{
			    			//Check to see if ammo in the storage is the same ammo in hand.
			    			if (ammostorage.getAmmoItem() == stack.getItem())
			    			{
			    				//Current stack CAN fit into the storage, so we fit the whole stack into it.
			    				if (stack.getCount() + ammostorage.getAmmoQuantity() <= ammostorage.getAmmoMaxQuantity())
			    				{
			    					TMWMain.LOGGER.info("Adding " + stack.getCount() + " bullets.");
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
			    						TMWMain.LOGGER.info("Adding " + availablespace + " bullets.");
			    						ammostorage.updateAmmo(stack.getItem(), availablespace);
				    					stack.shrink(availablespace);
				    					return InteractionResult.PASS;
			    					}
			    					else
			    					{
			    						TMWMain.LOGGER.info("Not enough space to add bullet.");
			    						return InteractionResult.FAIL;
			    					}
			    				}
			    			}
			    		}
					}
		    		else
		    		{
		    			//display ammo type and quantity
		    			TMWMain.LOGGER.info("Invalid bullet item");
		    			return InteractionResult.PASS;
		    		}
		    	}
		    }
		}
	    return InteractionResult.FAIL;
	}
}
