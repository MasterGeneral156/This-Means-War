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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.items.BasicItem;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.network.packet.GunAddBulletUpgradePacket;
import themastergeneral.thismeanswar.registry.TMWNetworkManager;

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
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (isSelected) {
			List<RadialMenuOption> optionList = new ArrayList<>();
			if (entityIn instanceof Player player) {
				if (player.getOffhandItem().getItem() instanceof NuGunItem gun) {
					if (gun.getRoundUpgrade(stack) == ItemStack.EMPTY) {
						optionList.add(new RadialMenuOption(
								() -> {
									// Send a packet to the server to remove ammo
									TMWNetworkManager.INSTANCE.sendToServer(new GunAddBulletUpgradePacket(itemSlot));
								},
								//Constants.addGunRoundUpgradeIcon,
								TMWUtils.getIconByStack(stack),
								ModUtils.displayTranslation("radial.thismeanswar.add_round_upgrade")
						));
					}
				}

				if (!player.getCooldowns().isOnCooldown(this)) {
					if (worldIn.isClientSide) {
						handleClientRadialMenu(optionList);
					}
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void handleClientRadialMenu(List<RadialMenuOption> optionList) {
		if (RadialClientEvents.openRadial.isDown()) {
			CTDRadialAPI.openRadialMenu(optionList);
		}
	}

	public void playerApplyUpgrade(Player player, ItemStack stack)
	{
		ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		ItemStack offHand = player.getOffhandItem();
		if (tagManager.getTag(disableUpgrade).contains(offHand.getItem())) {
			if (offHand.getItem() instanceof NuGunItem gun) {
				if (gun.getRoundUpgrade(offHand).isEmpty()) {
					gun.setRoundUpgrade(offHand, stack);
					stack.shrink(1);
					player.getCooldowns().addCooldown(this, 10);
					player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_success"), true);
				} else {
					player.getCooldowns().addCooldown(this, 10);
					player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_bullet_fail_already_done"), true);
				}
			} else {
				player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
				player.getCooldowns().addCooldown(this, 10);
			}
		}
		else
		{
			player.displayClientMessage(ModUtils.displayTranslation("thismeanswar.upgrade_fail_disabled"), true);
			player.getCooldowns().addCooldown(this, 10);
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
					tooltip.add(ModUtils.displayString("§2+21% bullet speed"));
					tooltip.add(ModUtils.displayString("§4+11% recoil"));
			}
			if (stack.getItem() == TMWItems.bullet_upgrade_fire)
			{
				tooltip.add(ModUtils.displayString("§2Flammable Rounds Conversion"));
				tooltip.add(ModUtils.displayString("§4-45% bullet damage"));
				tooltip.add(ModUtils.displayString("§4+65% reloading time"));
			}
			if (stack.getItem() == TMWItems.bullet_upgrade_medical)
			{
				tooltip.add(ModUtils.displayString("§2Medical Rounds Conversion"));
				tooltip.add(ModUtils.displayString("§2Health Healed = Damage/2"));
			}
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
