package themastergeneral.thismeanswar.items.upgrade;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.items.AbstractGunItem;
import themastergeneral.thismeanswar.items.BasicItem;

public class UpgradeROFSemiItem extends BasicItem {

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack offHandStack = playerIn.getOffhandItem();
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		if ((!tagManager.getTag(TMWTags.disableAllUpgrade).contains(offHandStack.getItem())) && 
				(!tagManager.getTag(TMWTags.disableSemiAutoUpgrade).contains(offHandStack.getItem())))
		{
			if (offHandStack.getItem() instanceof AbstractGunItem)
			{
				AbstractGunItem offhand = (AbstractGunItem) playerIn.getOffhandItem().getItem();
				if (offhand.shotTime > Constants.fireRateAuto)
				{
					//fail
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
					playerIn.getCooldowns().addCooldown(this, 10);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
				else
				{
					offhand.setGunROF(playerIn.getItemInHand(InteractionHand.OFF_HAND), Constants.fireRateSemi);
					playerIn.getMainHandItem().shrink(1);
					playerIn.getCooldowns().addCooldown(this, 20);
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_rof_semi"), true);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
			}
			else
			{
				playerIn.getCooldowns().addCooldown(this, 10);
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
			}
		}
		else
		{
			playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			playerIn.getCooldowns().addCooldown(this, 10);
			return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_rof_directions"));
		tooltip.add(ModUtils.displayString("Converts firearm's rate of fire to semi-auto."));
		if (Screen.hasShiftDown())
		{
			tooltip.add(ModUtils.displayString("§4Semi-auto Conversion"));
			tooltip.add(ModUtils.displayString("§2+25% bullet damage"));
		}
	}
}
