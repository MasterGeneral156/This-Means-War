package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.entity.SmokeThrowableEntity;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;

import javax.annotation.Nullable;
import java.util.List;

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
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(ModUtils.displayTranslation("item.thismeanswar.nonfunc.desc"));
	}


}
