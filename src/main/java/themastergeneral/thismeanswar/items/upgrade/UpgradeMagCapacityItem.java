package themastergeneral.thismeanswar.items.upgrade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import mastergeneral156.chasethedragon.radial.RadialClientEvents;
import mastergeneral156.chasethedragon.radial.RadialMenuOption;
import mastergeneral156.chasethedragon.radial.RadialMenuScreen;
import mastergeneral156.chasethedragon.radial.api.CTDRadialAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.items.BasicItem;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;
import themastergeneral.thismeanswar.network.packet.GunBayonetUpdatePacket;
import themastergeneral.thismeanswar.registry.TMWNetworkManager;

public class UpgradeMagCapacityItem extends BasicItem 
{
	protected double multiplier;
	public UpgradeMagCapacityItem(double increased) 
	{
		super();
		this.multiplier = increased;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof Player player)) {
			return;  // Exit early if the entity is not a player
		}

		List<RadialMenuOption> optionList = new ArrayList<>();
		ItemStack offhand = player.getOffhandItem();
		if (offhand.getItem() instanceof NuMagazineItem mag)
		{
			if (mag.getMagazineCapacityStack(offhand) == ItemStack.EMPTY) {
				optionList.add(new RadialMenuOption(
						() -> {
							// Send a packet to the server to remove ammo
							//TMWNetworkManager.INSTANCE.sendToServer(new GunBayonetUpdatePacket(player.getMainHandItem()));
						},
						TMWUtils.getIconByStack(stack),
						ModUtils.displayTranslation("radial.thismeanswar.add_mag_cap_upgrade")
				));

			}
		}
		// Client-side: Open the radial menu screen
		if (!player.getCooldowns().isOnCooldown(this)) {
			if (worldIn.isClientSide && isSelected) {
				handleClientRadialMenu(optionList);
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void handleClientRadialMenu(List<RadialMenuOption> optionList) {
		if (RadialClientEvents.openRadial.isDown()) {
			CTDRadialAPI.openRadialMenu(optionList);
		}
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack mainHandStack = playerIn.getMainHandItem();
		ItemStack offHandStack = playerIn.getOffhandItem();
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		if (tagManager.getTag(TMWTags.magUpgrade).contains(offHandStack.getItem())) {
			if (offHandStack.getItem() instanceof NuMagazineItem mag)
			{
				if ((mag.getMagazineCapacityStack(offHandStack).getItem() == mainHandStack.getItem()) || (mag.getMagazineCapacityStack(offHandStack) == ItemStack.EMPTY))
				{
					if (mag.getCapacityUpgrades(offHandStack) < Constants.maxMagUpgrades)
					{
						mag.addCapacityUpgrade(offHandStack, mainHandStack);
						playerIn.getCooldowns().addCooldown(mainHandStack.getItem(), 5);
						playerIn.getMainHandItem().shrink(1);
						playerIn.displayClientMessage(ModUtils.displayTranslation("item.thismeanswar.mag_capacity_upgrade.success"), true);
						return InteractionResultHolder.pass(playerIn.getMainHandItem());
					}
					else
					{
						playerIn.displayClientMessage(ModUtils.displayTranslation("item.thismeanswar.mag_capacity_upgrade.max"), true);
						playerIn.getCooldowns().addCooldown(mainHandStack.getItem(), 100);
						return InteractionResultHolder.fail(playerIn.getMainHandItem());
					}
				}
				else
				{
					playerIn.displayClientMessage(ModUtils.displayTranslation("item.thismeanswar.mag_capacity_upgrade.different"), true);
					playerIn.getCooldowns().addCooldown(mainHandStack.getItem(), 100);
					return InteractionResultHolder.fail(playerIn.getMainHandItem());
				}
			}
			else
			{
				playerIn.displayClientMessage(ModUtils.displayTranslation("item.thismeanswar.mag_capacity_upgrade.invalid"), true);
				playerIn.getCooldowns().addCooldown(mainHandStack.getItem(), 100);
				return InteractionResultHolder.fail(playerIn.getMainHandItem());
			}
		}
		else
		{
			playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			playerIn.getCooldowns().addCooldown(mainHandStack.getItem(), 100);
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
			tooltip.add(ModUtils.displayString("§2+" + multiplier * 100 + "% Magazine capacity"));
	}
	
	public double returnMagIncrease()
	{
		return multiplier;
	}
}
