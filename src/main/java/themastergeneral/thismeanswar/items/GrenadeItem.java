package themastergeneral.thismeanswar.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.TMWMain;
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
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack stackIn = playerIn.getItemInHand(handIn);
		
		ContactGrenadeEntity bulletEntity = new ContactGrenadeEntity(worldIn, playerIn, explosionRadius);
		bulletEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 0.75F, 0.5F);
		bulletEntity.setItem(stackIn);
		worldIn.addFreshEntity(bulletEntity);
		
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(this, ModConstant.misc.GRENADE_THROW_COOLDOWN);
		
		stackIn.shrink(1);
		return ActionResult.sidedSuccess(stackIn, worldIn.isClientSide());
	}

}
