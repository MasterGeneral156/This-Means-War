package themastergeneral.thismeanswar.items.upgrade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import mastergeneral156.chasethedragon.radial.RadialClientEvents;
import mastergeneral156.chasethedragon.radial.RadialMenuOption;
import mastergeneral156.chasethedragon.radial.RadialMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
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
import themastergeneral.thismeanswar.network.packet.GunBayonetUpdatePacket;
import themastergeneral.thismeanswar.network.packet.GunItemMagPacket;
import themastergeneral.thismeanswar.registry.TMWNetworkManager;

public class UpgradeGunBayonetItem extends BasicItem {

	public double increaseMusketLevel;
	public UpgradeGunBayonetItem(double increaseMusketLevel) 
	{
		super();
		this.increaseMusketLevel = increaseMusketLevel;
	}
	
	public double returnBayonetLevel()
	{
		return increaseMusketLevel;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_directions"));
		if (Screen.hasShiftDown())
			tooltip.add(ModUtils.displayString("§2" + this.increaseMusketLevel + " Melee Damage"));
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		List<RadialMenuOption> optionList = new ArrayList<>();
		if (entityIn instanceof Player player) {
			ItemStack offhand = player.getOffhandItem();
			if (offhand.getItem() instanceof NuGunItem gun)
			{
				if (gun.returnBayonetStack(stack) == ItemStack.EMPTY) {
					optionList.add(new RadialMenuOption(
							() -> {
								// Send a packet to the server to remove ammo
								TMWNetworkManager.INSTANCE.sendToServer(new GunBayonetUpdatePacket(player.getMainHandItem()));
							},
							Constants.removeBayonetIcon,
							ModUtils.displayTranslation("radial.thismeanswar.add_bayonet")
					));
				}
			}
			if (!player.getCooldowns().isOnCooldown(this)) {
				if (worldIn.isClientSide && isSelected) {
					handleClientRadialMenu(optionList);
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void handleClientRadialMenu(List<RadialMenuOption> optionList) {
		if (RadialClientEvents.openRadial.isDown()) {
			Minecraft.getInstance().setScreen(new RadialMenuScreen(optionList));
		}
	}
	
	public void applyBayonetToGun(ItemStack offHandStack, Player player)
	{
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		if ((!tagManager.getTag(TMWTags.disableAllUpgrade).contains(player.getOffhandItem().getItem())) && 
				(!tagManager.getTag(TMWTags.disableBayonetUpgrade).contains(player.getOffhandItem().getItem())))
		{
			if (offHandStack.getItem() instanceof NuGunItem gun)
			{
				if (gun.returnBayonetStack(offHandStack).isEmpty())
				{
					gun.setBayonetUpgrade(offHandStack, player.getMainHandItem());
					player.getMainHandItem().shrink(1);
					player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet"), true);
					player.getCooldowns().addCooldown(asItem(), 10);
				}
				else
				{
					player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_fail_already_done"), true);
					player.getCooldowns().addCooldown(asItem(), 100);
				}
			}
			else
			{
				player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bayonet_fail_nocompat"), true);
				player.getCooldowns().addCooldown(asItem(), 100);
			}
		}
		else
		{
			player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			player.getCooldowns().addCooldown(asItem(), 100);
		}
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack offHandStack = playerIn.getOffhandItem();
		ItemStack activeHandStack = playerIn.getItemInHand(handIn);
		if (offHandStack.getItem() instanceof NuGunItem gun)
		{
			applyBayonetToGun(offHandStack, playerIn);
			return InteractionResultHolder.pass(activeHandStack);
		}
		else
		{
			return InteractionResultHolder.fail(activeHandStack);
		}
	}
}
