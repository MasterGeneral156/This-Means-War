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

public class UpgradeBayonetItem extends BasicItem 
{
	public int increaseMusketLevel;
	public UpgradeBayonetItem(int increaseMusketLevel) 
	{
		super();
		this.increaseMusketLevel = increaseMusketLevel;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		if (playerIn.getOffhandItem().getItem() instanceof AbstractGunItem)
		{
			AbstractGunItem offhand = (AbstractGunItem) playerIn.getOffhandItem().getItem();
			int musketLevel = offhand.getBayonetLevel(playerIn.getOffhandItem());
			if (offhand.hasMag(playerIn.getOffhandItem()) == 0)
			{
				if (musketLevel == 0)
				{
					offhand.upgradeMusketLevel(playerIn.getOffhandItem(), increaseMusketLevel);
					playerIn.getCooldowns().addCooldown(this, 20);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
				else
				{
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_fail_already_done"), true);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
			}
			else
			{
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_fail_mag_inserted"), true);
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
			}
		}
		else
		{
			playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_fail_nocompat"), true);
			return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_directions"));
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_warning"));
		if (Screen.hasShiftDown())
		{
			tooltip.add(ModUtils.displayString("§2" + this.increaseMusketLevel + " Melee Damage"));
		}
	}
}