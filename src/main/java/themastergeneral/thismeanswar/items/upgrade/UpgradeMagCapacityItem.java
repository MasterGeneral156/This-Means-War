package themastergeneral.thismeanswar.items.upgrade;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.items.BasicItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;

public class UpgradeMagCapacityItem extends BasicItem 
{
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack mainHandStack = playerIn.getMainHandItem();
		ItemStack offHandStack = playerIn.getOffhandItem();
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		
		//thismeanswar:upgrades/disable_mag_capacity
		if ((!tagManager.getTag(TMWTags.disableAllUpgrade).contains(offHandStack.getItem())) && 
				(!tagManager.getTag(TMWTags.disableMagUpgrade).contains(offHandStack.getItem())))
		{
			if (offHandStack.getItem() instanceof NuMagazineItem mag)
			{
				if (mag.getCapacityUpgrades(offHandStack) < Constants.maxMagUpgrades)
				{
					mag.addCapacityUpgrade(offHandStack);
					playerIn.getCooldowns().addCooldown(mainHandStack.getItem(), 20);
					playerIn.getMainHandItem().shrink(1);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
				else
				{
					playerIn.getCooldowns().addCooldown(this, 5);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
			}
			else
			{
				playerIn.getCooldowns().addCooldown(this, 5);
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
			}
		}
		else
		{
			playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			playerIn.getCooldowns().addCooldown(this, 5);
			return InteractionResultHolder.fail(playerIn.getMainHandItem());
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_directions"));
		tooltip.add(ModUtils.displayString("Max Upgrades: " + Constants.maxMagUpgrades));
		if (Screen.hasShiftDown())
		{
			tooltip.add(ModUtils.displayString("§2+" + Constants.magIncreasePerLevel * 100 + "% Magazine capacity"));
		}
	}
}
