package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

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
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;

public class NuMagazineItem extends AbstractModItem {

    private AbstractBulletItem bulletRequired;
    private TagKey<Item> compatMags;
    private int capacityUpgrades;
    private int maxAmmo;
    private int baseAmmoSize;
    
    public static int SLOT_AMMO = 0;
    public static int SLOT_CAP_UPGRADES = 1;

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
        return currentAmmo[0];
    }
    
    public Item getCurrentAmmoItem(ItemStack stack) {
    	final Item[] currentAmmo = {Items.AIR};
        getInventory(stack).ifPresent(inv -> currentAmmo[0] = inv.getStackInSlot(SLOT_AMMO).getItem());
        return currentAmmo[0];
    }

    public int getMaxAmmo(ItemStack stack) {
        final int[] maxAmmo = {this.baseAmmoSize};
        getInventory(stack).ifPresent(inv -> {
            int capUpgrades = inv.getStackInSlot(SLOT_CAP_UPGRADES).getCount();
            double capBonus = (maxAmmo[0] * Constants.magIncreasePerLevel) * capUpgrades;
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

    public void removeAmmoFromMag(ItemStack stack, int toRemove) {
        getInventory(stack).ifPresent(inventory -> {
            int currentAmmo = getCurrentAmmo(stack);
            if ((currentAmmo - toRemove) >= 0)
                inventory.extractItem(SLOT_AMMO, toRemove, false);
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
    
    public void addCapacityUpgrade(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
        	int caps = getCapacityUpgrades(stack);
            if ((caps + 1) <= Constants.maxMagUpgrades)
                inventory.insertItem(SLOT_CAP_UPGRADES, new ItemStack(TMWItems.mag_capacity_upgrade, 1), false);
            saveInventory(stack);
        });
    }

    public void removeAmmoFromMag(ItemStack stack) {
        removeAmmoFromMag(stack, 1);
    }

    public void addAmmoToMag(ItemStack stack, int toAdd) {
        getInventory(stack).ifPresent(inventory -> {
            int currentAmmo = getCurrentAmmo(stack);
            if ((currentAmmo + toAdd) <= getMaxAmmo(stack))
                inventory.insertItem(SLOT_AMMO, new ItemStack(bulletRequired, toAdd), false);
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
        float stackMaxDamage = this.getMaxAmmo(stack);
        float f = Math.max(0.0F, (stackMaxDamage - (float) (stackMaxDamage - this.getCurrentAmmo(stack))) / stackMaxDamage);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        int currentAmmo = getCurrentAmmo(stack);
        int maxAmmo = getMaxAmmo(stack);
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(ModUtils.displayTranslation(this.returnBulletItem().getDescriptionId()));
        tooltip.add(ModUtils.displayString("(" + currentAmmo + "/" + maxAmmo + ")"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (worldIn instanceof ServerLevel) {
            ItemStack mag = playerIn.getItemInHand(handIn);
            if (handIn == InteractionHand.MAIN_HAND && playerIn.getOffhandItem().isEmpty()) {
                if (playerIn.isCrouching()) {
                    if (getCurrentAmmo(mag) > 0) {
                        playerRemoveAmmo(mag, playerIn);
                    }
                } else {
                    playerAddAmmo(mag, playerIn);
                }
            }
        }
        return InteractionResultHolder.sidedSuccess(playerIn.getItemInHand(handIn), worldIn.isClientSide());
	}
    
    protected void playerAddAmmo(ItemStack stack, Player player, int added)
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
    
    protected void playerRemoveAmmo(ItemStack stack, Player player, int removed)
    {
    	if (getCurrentAmmo(stack) > 0) 
    	{
            removeAmmoFromMag(stack, removed);
            player.getInventory().add(new ItemStack(returnBulletItem(), 1));
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
			super(2);
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
		if (this.getCapacityUpgrades(stack) > 0)
		{
			//returned = "Extended " + returned;
			returned = ModUtils.displayTranslation("thismeanswar.mag.extended").getString();
			returned = returned.concat(" ");
			returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		}
		return returned;
	   
	}
}

