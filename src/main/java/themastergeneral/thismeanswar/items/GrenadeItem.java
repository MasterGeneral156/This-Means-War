package themastergeneral.thismeanswar.items;

import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.entity.ContactGrenadeEntity;

public class GrenadeItem extends BaseTMWItem {

	protected float explosionRadius;
	public GrenadeItem(float explosionRadius) 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP));
		this.explosionRadius = explosionRadius;
		
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
		
		ContactGrenadeEntity bulletEntity = new ContactGrenadeEntity(worldIn, playerIn, explosionRadius);
		bulletEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 0.75F, 0.5F);
		bulletEntity.setItem(stackIn);
		worldIn.addFreshEntity(bulletEntity);
		
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(this, BalanceConfig.THROWABLE_COOLDOWN.get());
		
		stackIn.shrink(1);
		return InteractionResultHolder.sidedSuccess(stackIn, worldIn.isClientSide());
	}

}
