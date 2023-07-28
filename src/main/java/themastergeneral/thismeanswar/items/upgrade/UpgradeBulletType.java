package themastergeneral.thismeanswar.items.upgrade;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.items.BasicItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;

public class UpgradeBulletType extends BasicItem {

	public int bulletUpgradeLvl;
	protected TagKey<Item> disableUpgrade;
	public UpgradeBulletType(int bulletUpgrade, TagKey<Item> blockItemTag) 
	{
		super();
		this.bulletUpgradeLvl = bulletUpgrade;
		this.disableUpgrade = blockItemTag;
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		if ((!tagManager.getTag(TMWTags.disableAllUpgrade).contains(playerIn.getOffhandItem().getItem())) && 
				(!tagManager.getTag(disableUpgrade).contains(playerIn.getOffhandItem().getItem())))
		{
			if (playerIn.getOffhandItem().getItem() instanceof AbstractGunItem)
			{
				AbstractGunItem offhand = (AbstractGunItem) playerIn.getOffhandItem().getItem();
				int upgradeLevel = offhand.getBulletUpgrade(playerIn.getOffhandItem());
				if (upgradeLevel == bulletUpgradeLvl)
				{
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bullet_fail_same"), true);
					playerIn.getCooldowns().addCooldown(this, 10);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
				else if (upgradeLevel == 0 && bulletUpgradeLvl > 0)
				{
					offhand.upgradeBullet(playerIn.getOffhandItem(), bulletUpgradeLvl);
					playerIn.getCooldowns().addCooldown(this, 20);
					playerIn.getMainHandItem().shrink(1);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
				else if (upgradeLevel > 0 && bulletUpgradeLvl == 0)
				{
					offhand.upgradeBullet(playerIn.getOffhandItem(), bulletUpgradeLvl);
					playerIn.getCooldowns().addCooldown(this, 20);
					playerIn.getMainHandItem().shrink(1);
					return InteractionResultHolder.pass(playerIn.getMainHandItem());
				}
				else
				{
					playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bullet_fail_already_done"), true);
					playerIn.getCooldowns().addCooldown(this, 10);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
			}
			else
			{
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_fail_nocompat"), true);
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
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_directions"));
		if (Screen.hasShiftDown())
		{
			if (stack.getItem() == TMWItems.bullet_upgrade_ap)
			{
					tooltip.add(ModUtils.displayString("§2Armor Piercing Conversion"));
					tooltip.add(ModUtils.displayString("§4-18% bullet damage"));
			}
			if (stack.getItem() == TMWItems.bullet_upgrade_fire)
				tooltip.add(ModUtils.displayString("§2Flamable Rounds Conversion"));
			if (stack.getItem() == TMWItems.bullet_upgrade_tracer)
				tooltip.add(ModUtils.displayString("§2Tracer Rounds Conversion"));
			if (stack.getItem() == TMWItems.bullet_upgrade_inert)
			{
				tooltip.add(ModUtils.displayString("§2Inert Rounds Conversion"));
				tooltip.add(ModUtils.displayString("§4-95% bullet damage"));
			}
		}
	}
}
