package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.entity.ContactExplosiveEntity;

public class GrenadeItem extends CTDItem {

	protected float explosionRadius;
	public GrenadeItem(float explosionRadius) 
	{
		super(new Properties().group(TMWMain.ITEMGROUP));
		this.explosionRadius = explosionRadius;
		
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack stackIn = playerIn.getHeldItem(handIn);
		
		ContactExplosiveEntity bulletEntity = new ContactExplosiveEntity(worldIn, playerIn, this.explosionRadius, stackIn.getItem());
		bulletEntity.setItem(stackIn);
		bulletEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);	
		worldIn.addEntity(bulletEntity);
		playerIn.addStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldownTracker().setCooldown(this, ModConstant.GRENADE_THROW_COOLDOWN);
		stackIn.shrink(1);
		return ActionResult.resultPass(stackIn);
	}

}
