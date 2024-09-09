package themastergeneral.thismeanswar.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mastergeneral156.chasethedragon.radial.CTDRadial;
import mastergeneral156.chasethedragon.radial.RadialClientEvents;
import mastergeneral156.chasethedragon.radial.RadialMenuOption;
import mastergeneral156.chasethedragon.radial.RadialMenuScreen;
import mastergeneral156.chasethedragon.radial.api.CTDRadialAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.block.entity.BlockEntityAlloySmelter;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeMagCapacityItem;
import themastergeneral.thismeanswar.network.packet.MagAmmoChangePacket;
import themastergeneral.thismeanswar.registry.TMWNetworkManager;

public class NuMagazineItem extends AbstractModItem {

    private AbstractBulletItem bulletRequired;
    private TagKey<Item> compatMags;
    private int capacityUpgrades;
    private int maxAmmo;
    private int baseAmmoSize;
    
    public static int SLOT_AMMO = 0;
    public static int SLOT_CAP_UPGRADES = 1;
    public static int SLOT_OVERFLOW = 2;


    public NuMagazineItem(AbstractBulletItem bulletRequired, int maxAmmoSize, TagKey<Item> compatMag) {
        super(new Properties().stacksTo(1));
        this.bulletRequired = bulletRequired;
        this.maxAmmo = maxAmmoSize;
        this.baseAmmoSize = maxAmmoSize;
        this.compatMags = compatMag;
    }
    
    public NuMagazineItem(AbstractBulletItem Ammo, int maxAmmoSize) 
	{
    	super(new Properties().stacksTo(1));
        this.bulletRequired = Ammo;
        this.maxAmmo = maxAmmoSize;
        this.baseAmmoSize = maxAmmoSize;
        this.compatMags = null;
	}

    @Override
    public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
        setupMag(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        setupMag(stack);
        List<RadialMenuOption> optionList = new ArrayList<>();
        if (entityIn instanceof Player player) {
            if (getCurrentAmmo(stack) > 0) {
                optionList.add(new RadialMenuOption(
                        () -> {
                            // Send a packet to the server to remove ammo
                            TMWNetworkManager.INSTANCE.sendToServer(new MagAmmoChangePacket(itemSlot, -1));
                        },
                        Constants.removeAmmoIcon,
                        ModUtils.displayTranslation("radial.thismeanswar.remove_round")
                ));
            }
            if (getCurrentAmmo(stack) < getMaxAmmo(stack)) {

                optionList.add(new RadialMenuOption(
                        () -> {
                            // Send a packet to the server to remove ammo
                            TMWNetworkManager.INSTANCE.sendToServer(new MagAmmoChangePacket(itemSlot, 1));
                        },
                        Constants.addAmmoIcon,
                        ModUtils.displayTranslation("radial.thismeanswar.add_round")
                ));
            }
            if (!player.getCooldowns().isOnCooldown(this))
            {
                if (worldIn.isClientSide && isSelected) {
                    if (RadialClientEvents.openRadial.isDown())
                        Minecraft.getInstance().setScreen(new RadialMenuScreen(optionList));
                }
            }
        }
    }

    private void setupMag(ItemStack stack) {
        if (!stack.hasTag()) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("MaxAmmo", this.maxAmmo);
            stack.setTag(tag);
            saveInventory(stack);
        }
    }

    public int getCurrentAmmo(ItemStack stack) {
        final int[] currentAmmo = {0};
        getInventory(stack).ifPresent(inv -> currentAmmo[0] = inv.getStackInSlot(SLOT_AMMO).getCount());
        return currentAmmo[0] + (getOverflow(stack) * 126);
    }
    
    public Item getCurrentAmmoItem(ItemStack stack) {
    	final Item[] currentAmmo = {Items.AIR};
        getInventory(stack).ifPresent(inv -> currentAmmo[0] = inv.getStackInSlot(SLOT_AMMO).getItem());
        return currentAmmo[0];
    }
    
    public ItemStack getMagazineCapacityStack(ItemStack stack) {
    	final ItemStack[] currentAmmo = {ItemStack.EMPTY};
        getInventory(stack).ifPresent(inv -> currentAmmo[0] = inv.getStackInSlot(SLOT_CAP_UPGRADES));
        return currentAmmo[0];
    }

    public int getMaxAmmo(ItemStack stack) {
        final int[] maxAmmo = {this.baseAmmoSize};
        getInventory(stack).ifPresent(inv -> 
        {
        	double capBonus = 0;
        	int capUpgrades = inv.getStackInSlot(SLOT_CAP_UPGRADES).getCount();
        	if (inv.getStackInSlot(SLOT_CAP_UPGRADES).getItem() instanceof UpgradeMagCapacityItem magUpgrade)
        	{
                capBonus = (maxAmmo[0] * magUpgrade.returnMagIncrease()) * capUpgrades;
        	}
        	if ((capBonus < 1.0D * capUpgrades) && capUpgrades > 0)
                capBonus = 1.0D * capUpgrades;
            maxAmmo[0] += capBonus;
        });
        return maxAmmo[0];
    }

    public int getCapacityUpgrades(ItemStack stack) {
        final int[] capacityUpgrades = {0};
        getInventory(stack).ifPresent(inv -> capacityUpgrades[0] = inv.getStackInSlot(SLOT_CAP_UPGRADES).getCount());
        return capacityUpgrades[0];
    }

    protected void removeAmmoFromMag(ItemStack stack, int toRemove) {
        getInventory(stack).ifPresent(inventory -> {
            int currentAmmo = getCurrentAmmo(stack);

            if (currentAmmo <= toRemove) {
                // Case when ammo will drop to 0 or below
                inventory.extractItem(SLOT_AMMO, toRemove, false);
                if (getOverflow(stack) > 0) {
                    removeOverflow(stack);
                    inventory.setStackInSlot(SLOT_AMMO, ItemStack.EMPTY);
                }
            } else {
                // Regular case, just reduce the ammo count
                if ((currentAmmo - toRemove) > 127 * (getOverflow(stack))) {
                    inventory.extractItem(SLOT_AMMO, toRemove, false);
                } else {
                    if (getOverflow(stack) > 0) {
                        removeOverflow(stack);
                        int remainingAmmo = currentAmmo - toRemove;
                        int ammoInMainSlot = remainingAmmo % 127;
                        inventory.setStackInSlot(SLOT_AMMO, inventory.getStackInSlot(SLOT_AMMO).copyWithCount(ammoInMainSlot));
                    } else {
                        inventory.extractItem(SLOT_AMMO, toRemove, false);
                    }
                }
            }

            saveInventory(stack);
        });
    }
    
    public void removeCapacityUpgrade(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            int caps = getCapacityUpgrades(stack);
            if ((caps - 1) >= 0)
                inventory.extractItem(SLOT_CAP_UPGRADES, 1, false);
            saveInventory(stack);
        });
    }
    
    public void addCapacityUpgrade(ItemStack stack, ItemStack toAdd) {
        getInventory(stack).ifPresent(inventory -> {
        	int caps = getCapacityUpgrades(stack);
            if ((caps + 1) <= Constants.maxMagUpgrades)
                inventory.insertItem(SLOT_CAP_UPGRADES, toAdd.copyWithCount(1), false);
            saveInventory(stack);
        });
    }
    
    public void addOverflow(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.insertItem(SLOT_OVERFLOW, new ItemStack(TMWItems.creative_charm, 1), false);
            saveInventory(stack);
        });
    }
    
    public int getOverflow(ItemStack stack) {
        final int[] capacityUpgrades = {0};
        getInventory(stack).ifPresent(inv -> capacityUpgrades[0] = inv.getStackInSlot(SLOT_OVERFLOW).getCount());
        return capacityUpgrades[0];
    }
    
    public void removeOverflow(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.extractItem(SLOT_OVERFLOW, 1, false);
            saveInventory(stack);
        });
    }

    public void removeAmmoFromMag(ItemStack stack) {
        removeAmmoFromMag(stack, 1);
    }

    public void addAmmoToMag(ItemStack stack, int toAdd) {
        getInventory(stack).ifPresent(inventory -> {
            int currentAmmo = getCurrentAmmo(stack);

            if ((currentAmmo + toAdd) <= getMaxAmmo(stack)) {
                if ((currentAmmo + toAdd) < 127 * (getOverflow(stack) + 1)) {
                    inventory.insertItem(SLOT_AMMO, new ItemStack(bulletRequired, toAdd), false);
                } else {
                    int ammoToAddToOverflow = (currentAmmo + toAdd) - (127 * (getOverflow(stack) + 1));
                    inventory.setStackInSlot(SLOT_AMMO, inventory.getStackInSlot(SLOT_AMMO).copyWithCount(127 * (getOverflow(stack) + 1)));
                    addOverflow(stack);
                }
            }
            saveInventory(stack);
        });
    }

    public void addAmmoToMag(ItemStack stack) {
        addAmmoToMag(stack, 1);
    }

    private void saveInventory(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt("MaxAmmo", getMaxAmmo(stack));
            tag.put("Inventory", inventory.serializeNBT());
        });
    }

    private static LazyOptional<ItemStackHandler> getInventory(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).cast();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new CustomItemHandlerProvider(stack);
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.hasTag() && getCapacityUpgrades(stack) > 0;
    }
    
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 0.0F;
    }
    
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCurrentAmmo(stack) > 0 || getMaxAmmo(stack) > 0;
    }
    
    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F - (getMaxAmmo(stack) - getCurrentAmmo(stack)) * 13.0F / getMaxAmmo(stack));
    }
    
    public int getBarColor(ItemStack stack) {
        float stackMaxDamage = getMaxAmmo(stack);
        float f = Math.max(0.0F, (stackMaxDamage - (float) (stackMaxDamage - getCurrentAmmo(stack))) / stackMaxDamage);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        int currentAmmo = getCurrentAmmo(stack);
        int maxAmmo = getMaxAmmo(stack);
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(ModUtils.displayTranslation(this.returnBulletItem().getDescriptionId()));
        if (!Screen.hasShiftDown())
            tooltip.add(ModUtils.displayString("(" + ModUtils.returnShortenedNumber(currentAmmo) + "/" + ModUtils.returnShortenedNumber(maxAmmo) + ")"));
        else
            tooltip.add(ModUtils.displayString(TMWUtils.ammoFillBar(currentAmmo, maxAmmo)));
    }
    
    public void playerAddAmmo(ItemStack stack, Player player, int added)
    {
    	if ((getCurrentAmmo(stack) < getMaxAmmo(stack)) && (getMaxAmmo(stack) > 0)) 
    	{
            int slotID = findBulletInInventory(player);
            if (slotID > -1) {
                ItemStack ibullet = player.getInventory().getItem(slotID);
                addAmmoToMag(stack, added);
                ibullet.shrink(added);
                player.getCooldowns().addCooldown(stack.getItem(), 8);
                player.awardStat(Stats.ITEM_USED.get(this.asItem()));
                player.playSound(SoundEvents.DISPENSER_DISPENSE, Constants.modVolume, 0.75F);
            }
        }
    }

    public void playerRemoveAmmo(ItemStack stack, Player player, int removed) {
        if (getCurrentAmmo(stack) > 0) {
            removeAmmoFromMag(stack, removed);
            player.getInventory().add(new ItemStack(returnBulletItem(), removed));
            player.getCooldowns().addCooldown(this, 8);
            player.awardStat(Stats.ITEM_USED.get(this));
            player.playSound(SoundEvents.DISPENSER_FAIL, Constants.modVolume, 0.25F);
        }
    }
    
    protected void playerAddAmmo(ItemStack stack, Player player)
    {
    	playerAddAmmo(stack, player, 1);
    }
    
    protected void playerRemoveAmmo(ItemStack stack, Player player)
    {
    	playerRemoveAmmo(stack, player, 1);
    }
	
	private int findBulletInInventory(Player playerIn) {
	int slotID = -1;
	for (int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) {
		ItemStack itemstack1 = playerIn.getInventory().getItem(i);
		if (compatMags != null) {
		    ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
		    if (itemstack1.getItem() == returnBulletItem()
		            || (tagManager.getTag(this.returnBulletItem().getCompatBullet())
		                    .contains(itemstack1.getItem()))) {
		        slotID = i;
		        break;
		    }
		} else {
		    if (itemstack1.getItem() == returnBulletItem()) {
		        slotID = i;
		        break;
		    }
		}
		}
		return slotID;
	}
	
	public AbstractBulletItem returnBulletItem() {
		return bulletRequired;
	}
	
	public static class CustomItemHandler extends ItemStackHandler 
	{
		private final ItemStack stack;
	
		public CustomItemHandler(ItemStack stack) 
		{
			super(3);
			this.stack = stack;
			if (stack.hasTag() && stack.getTag().contains("Inventory"))
				this.deserializeNBT(stack.getTag().getCompound("Inventory"));
		}
	
		@Override
		protected int getStackLimit(int slot, @NotNull ItemStack stack) 
		{
			if (slot == SLOT_AMMO)
			{
				if (this.stack.getItem() instanceof NuMagazineItem magItem)
					return magItem.getMaxAmmo(this.stack);
				else
					return 1;
			}
			else if (slot == SLOT_CAP_UPGRADES)
				return Constants.maxMagUpgrades;
			else
				return 64;
		}
		
		@Override
		protected void onContentsChanged(int slot) 
		{
			super.onContentsChanged(slot);
			if (!stack.isEmpty()) 
			{
			    CompoundTag tag = stack.getOrCreateTag();
			    tag.put("Inventory", this.serializeNBT());
			}
		}
	}
	
	public static class CustomItemHandlerProvider implements ICapabilityProvider 
	{
		private final ItemStack stack;
		private final CustomItemHandler itemHandler;
		
		public CustomItemHandlerProvider(ItemStack stack) 
		{
			this.stack = stack;
			this.itemHandler = new CustomItemHandler(stack);
		}
		
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable net.minecraft.core.Direction side) 
		{
			return cap == ForgeCapabilities.ITEM_HANDLER ? LazyOptional.of(() -> (T) itemHandler) : LazyOptional.empty();
		}
	}
	
	@Override
	public String getDescriptionId(ItemStack stack) 
	{
		String returned = this.getDescriptionId();
		if (this.getMagazineCapacityStack(stack).getItem() == TMWItems.mag_capacity_upgrade)
		{
			returned = ModUtils.displayTranslation("thismeanswar.mag.extended").getString();
			returned = returned.concat(" ");
			returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		}
		else if (this.getMagazineCapacityStack(stack).getItem() == TMWItems.mag_capacity_upgrade_t2)
		{
			returned = ModUtils.displayTranslation("thismeanswar.mag.extended_t2").getString();
			returned = returned.concat(" ");
			returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		}
		else if (this.getMagazineCapacityStack(stack).getItem() == TMWItems.mag_capacity_upgrade_t3)
		{
			returned = ModUtils.displayTranslation("thismeanswar.mag.extended_t3").getString();
			returned = returned.concat(" ");
			returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		}
		else if (this.getMagazineCapacityStack(stack).getItem() == TMWItems.creative_mag_capacity_upgrade)
		{
			returned = ModUtils.displayTranslation("thismeanswar.mag.extended_creative").getString();
			returned = returned.concat(" ");
			returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		}
		else if (this.getCapacityUpgrades(stack) > 0)
		{
			returned = ModUtils.displayTranslation("thismeanswar.mag.extended").getString();
			returned = returned.concat(" ");
			returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		}
		return returned;
	   
	}
	
	public TagKey<Item> getCompatMag()
	{
		if (compatMags != null)
			return compatMags;
		else
			return null;
	}
}

