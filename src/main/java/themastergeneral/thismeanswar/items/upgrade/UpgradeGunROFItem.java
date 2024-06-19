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
import themastergeneral.thismeanswar.items.BasicItem;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;

public class UpgradeGunROFItem extends BasicItem {
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_directions"));
		Item item = stack.getItem();
		if (Screen.hasShiftDown())
		{
			if (item == TMWItems.gun_rof_upgrade)
			{
				tooltip.add(ModUtils.displayString("§2Full-auto Conversion"));
				tooltip.add(ModUtils.displayString("§4-50% bullet damage"));
				tooltip.add(ModUtils.displayString("§4+10% recoil"));
			}
			if (item == TMWItems.gun_rof_downgrade)
			{
				tooltip.add(ModUtils.displayString("§4Semi-auto Conversion"));
				tooltip.add(ModUtils.displayString("§2+25% bullet damage"));
				tooltip.add(ModUtils.displayString("§2-20% recoil"));
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack offHandStack = playerIn.getOffhandItem();
		ItemStack activeHandStack = playerIn.getItemInHand(handIn);
		
		if (activeHandStack.getItem() == TMWItems.gun_rof_upgrade)
		{
			if (offHandStack.getItem() instanceof NuGunItem gun)
			{
				if (gun.getRateOfFire(offHandStack) == Constants.fireRateAuto)
				{
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
					playerIn.getCooldowns().addCooldown(this, 10);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
				else
				{
					convertToFullAuto(offHandStack, playerIn);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
			}
			else
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
		else if (activeHandStack.getItem() == TMWItems.gun_rof_downgrade)
		{
			if (offHandStack.getItem() instanceof NuGunItem gun)
			{
				if (gun.getRateOfFire(offHandStack) != Constants.fireRateAuto)
				{
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
					playerIn.getCooldowns().addCooldown(this, 10);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
				else
				{
					convertToSemiAuto(offHandStack, playerIn);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
			}
			else
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
		else
			return InteractionResultHolder.fail(playerIn.getMainHandItem());
		
	}
	
	protected void convertToFullAuto(ItemStack offHandStack, Player player)
	{
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		if ((!tagManager.getTag(TMWTags.disableAllUpgrade).contains(offHandStack.getItem())) && 
				(!tagManager.getTag(TMWTags.disableFullAutoUpgrade).contains(offHandStack.getItem())))
		{
			if (offHandStack.getItem() instanceof NuGunItem gun)
			{
				if (gun.returnROFUpgrade(offHandStack).isEmpty())
				{
					if (gun.getRateOfFire(offHandStack) != Constants.fireRateAuto)
					{
						gun.setROFUpgrade(offHandStack, player.getMainHandItem());
						player.getMainHandItem().shrink(1);
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_rof_full"), true);
						player.getCooldowns().addCooldown(this, 20);
					}
					else
					{
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
						player.getCooldowns().addCooldown(this, 10);
					}
				}
				else
				{
					player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
					player.getCooldowns().addCooldown(this, 10);
				}
			}
			else
			{
				player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
				player.getCooldowns().addCooldown(this, 10);
			}
		}
		else
		{
			player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			player.getCooldowns().addCooldown(this, 10);
		}
	}
	
	protected void convertToSemiAuto(ItemStack offHandStack, Player player)
	{
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		if ((!tagManager.getTag(TMWTags.disableAllUpgrade).contains(offHandStack.getItem())) && 
				(!tagManager.getTag(TMWTags.disableSemiAutoUpgrade).contains(offHandStack.getItem())))
		{
			if (offHandStack.getItem() instanceof NuGunItem gun)
			{
				if (gun.returnROFUpgrade(offHandStack).isEmpty())
				{
					if (gun.getRateOfFire(offHandStack) == Constants.fireRateAuto)
					{
						gun.setROFUpgrade(offHandStack, player.getMainHandItem());
						player.getMainHandItem().shrink(1);
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_rof_semi"), true);
						player.getCooldowns().addCooldown(this, 20);
					}
					else
					{
						player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
						player.getCooldowns().addCooldown(this, 10);
					}
				}
				else
				{
					player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
					player.getCooldowns().addCooldown(this, 10);
				}
			}
			else
			{
				player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_nocompat"), true);
				player.getCooldowns().addCooldown(this, 10);
			}
		}
		else
		{
			player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			player.getCooldowns().addCooldown(this, 10);
		}
	}
}
