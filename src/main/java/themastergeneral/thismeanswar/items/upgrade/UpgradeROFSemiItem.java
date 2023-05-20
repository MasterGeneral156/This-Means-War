package themastergeneral.thismeanswar.items.upgrade;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.BasicItem;

public class UpgradeROFSemiItem extends BasicItem {

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack offHandStack = playerIn.getOffhandItem();
		if (offHandStack.getItem() instanceof AbstractGunItem)
		{
			AbstractGunItem offhand = (AbstractGunItem) playerIn.getOffhandItem().getItem();
			if (offhand.shotTime > Constants.fireRateAuto)
			{
				//fail
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
			}
			else
			{
				offhand.setGunROF(playerIn.getItemInHand(InteractionHand.OFF_HAND), Constants.fireRateSemi);
				playerIn.getMainHandItem().shrink(1);
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_rof_semi"), true);
				return InteractionResultHolder.pass(playerIn.getMainHandItem());
			}
		}
		else
		{
			return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
	}
}
