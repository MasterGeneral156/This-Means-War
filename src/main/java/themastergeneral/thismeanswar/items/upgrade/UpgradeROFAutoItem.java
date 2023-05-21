package themastergeneral.thismeanswar.items.upgrade;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.BasicItem;

public class UpgradeROFAutoItem extends BasicItem {

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack offHandStack = playerIn.getOffhandItem();
		if (offHandStack.getItem() instanceof AbstractGunItem)
		{
			AbstractGunItem offhand = (AbstractGunItem) playerIn.getOffhandItem().getItem();
			if (offhand.shotTime == Constants.fireRateAuto)
			{
				//fail
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
			}
			else
			{
				offhand.setGunROF(playerIn.getItemInHand(InteractionHand.OFF_HAND), Constants.fireRateAuto);
				playerIn.getMainHandItem().shrink(1);
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_rof_full"), true);
				return InteractionResultHolder.pass(playerIn.getMainHandItem());
			}
		}
		else
		{
			return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_rof_directions"));
		tooltip.add(ModUtils.displayString("Converts firearm's rate of fire to full-auto."));
		if (Screen.hasShiftDown())
		{
			tooltip.add(ModUtils.displayString("§2Full-auto Conversion"));
			tooltip.add(ModUtils.displayString("§4-50% bullet damage"));
		}
	}
}
