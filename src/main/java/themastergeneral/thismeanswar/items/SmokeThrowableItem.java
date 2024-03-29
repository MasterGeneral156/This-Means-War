package themastergeneral.thismeanswar.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.entity.ContactGrenadeEntity;
import themastergeneral.thismeanswar.entity.SmokeThrowableEntity;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;

public class SmokeThrowableItem extends AbstractModItem {

	protected int color;
	public SmokeThrowableItem(int Color) 
	{
		super(new Properties());
		this.color = Color;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack stackIn = playerIn.getItemInHand(handIn);
		
		SmokeThrowableEntity bulletEntity = new SmokeThrowableEntity(worldIn, playerIn, color);
		bulletEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 0.75F, 0.5F);
		bulletEntity.setItem(stackIn);
		worldIn.addFreshEntity(bulletEntity);
		
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(this, Constants.cooldownThrowable);
		
		stackIn.shrink(1);
		return InteractionResultHolder.sidedSuccess(stackIn, worldIn.isClientSide());
	}

}
