package themastergeneral.thismeanswar.items.interfaces;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import net.minecraftforge.api.distmarker.Dist;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;

public class AbstractMagazineItem extends AbstractModItem {

	private int maxAmmo; 
	private int baseAmmoSize;
	protected AbstractBulletItem bulletRequired;
	private TagKey<Item> compatMags;
	private int capacityUpgrades;
	public int maxCapacityUpgrades = Constants.maxMagUpgrades;
	
	public AbstractMagazineItem(AbstractBulletItem Ammo, int maxAmmoSize) 
	{
		super(new Properties().stacksTo(1));
		this.maxAmmo = maxAmmoSize;
		this.bulletRequired = Ammo;
		this.baseAmmoSize = maxAmmoSize;
		this.compatMags = null;
	}
	
	public AbstractMagazineItem(AbstractBulletItem Ammo, int maxAmmoSize, TagKey<Item> compatMag) 
	{
		super(new Properties().stacksTo(1));
		this.maxAmmo = maxAmmoSize;
		this.bulletRequired = Ammo;
		this.baseAmmoSize = maxAmmoSize;
		this.compatMags = compatMag;
	}
	
	@Override
	public boolean isFoil(ItemStack stack)
	{
		if (stack.hasTag())
		{
			if (this.getCapacityUpgrades(stack) > 0)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) 
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", maxAmmo);
		compoundnbt.putInt("capUpgrades", 0);
		stack.setTag(compoundnbt);
	}
	
	//Show ammo on the magazine
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		tooltip.add(ModUtils.displayString("Capacity: " + currentAmmo + " / " + maxAmmo));
		tooltip.add(ModUtils.displayTranslation(bulletRequired.getDescriptionId()));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack mag = playerIn.getItemInHand(handIn);
		//when in main hand we empty the mag when crouching
		if (handIn == InteractionHand.MAIN_HAND && playerIn.getOffhandItem().isEmpty())
		{
			if (playerIn.isCrouching())
			{
				if (getCurrentAmmo(mag) > 0)
				{
					removeAmmoFromMag(mag);
					playerIn.getInventory().add(new ItemStack(returnBulletItem(), 1));
					playerIn.getCooldowns().addCooldown(this, 8);
					playerIn.awardStat(Stats.ITEM_USED.get(this));
					playerIn.playSound(SoundEvents.DISPENSER_FAIL, Constants.modVolume, 0.25F);
				}
			}
			//fill mag when not crouching
			else
			{
				if ((getCurrentAmmo(mag) < getMaxAmmo(mag)) && (getMaxAmmo(mag) > 0))
				{
					int slotID = -1;
					for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) 
					{
						ItemStack itemstack1 = playerIn.getInventory().getItem(i);
						if (compatMags != null)
						{
							ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
							if ((itemstack1.getItem()) == returnBulletItem() || (tagManager.getTag(this.returnBulletItem().getCompatBullet()).contains(itemstack1.getItem())))
							{
								slotID=i;
								break;
							}
						}
						else
						{
							if (itemstack1.getItem() == returnBulletItem())
							{
								slotID=i;
								break;
							}
						}
					}
					
					if (slotID > -1)
					{
						ItemStack ibullet = playerIn.getInventory().getItem(slotID);
						addAmmoToMag(mag);
						ibullet.shrink(1);
						playerIn.getCooldowns().addCooldown(this, 8);
						playerIn.awardStat(Stats.ITEM_USED.get(this));
						playerIn.playSound(SoundEvents.DISPENSER_DISPENSE, Constants.modVolume, 0.75F);
					}
				}
			}
		}
		return InteractionResultHolder.sidedSuccess(mag, worldIn.isClientSide());
	}
	
	//Remove ammo one at a time... called when we manually unload the mags.
	//Returns true if ammo is removed, false if not.
	//See actual logic below.
	private void removeAmmoFromMag(ItemStack mag)
	{
		removeAmmoFromMag(mag, 1);
	}
	
	//In case you wanna remove a lot more ammo at once...
	//Returns true if ammo is removed, false if not.
	public void removeAmmoFromMag(ItemStack mag, int toRemove)
	{
		int currentAmmo = getCurrentAmmo(mag);
		if ((currentAmmo - toRemove) >= 0)
		{
			CompoundTag compoundnbt = new CompoundTag();
			int newAmmo = currentAmmo - toRemove;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			compoundnbt.putInt("capUpgrades", getCapacityUpgrades(mag));
			mag.setTag(compoundnbt);
		}
	}
	
	private void addAmmoToMag(ItemStack mag)
	{
		addAmmoToMag(mag, 1);
	}
	
	public void addAmmoToMag(ItemStack mag, int toAdd)
	{
		int currentAmmo = getCurrentAmmo(mag);
		int maxAmmo = getMaxAmmo(mag);
		int magUpgrd = getCapacityUpgrades(mag);
		if ((currentAmmo + toAdd) <= maxAmmo)
		{
			CompoundTag compoundnbt = new CompoundTag();
			int newAmmo = currentAmmo + toAdd;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", maxAmmo);
			compoundnbt.putInt("capUpgrades", magUpgrd);
			mag.setTag(compoundnbt);
		}
	}
	
	public void upgradeMagCapacity(ItemStack mag)
	{
		int capUpgrades = getCapacityUpgrades(mag);
		if ((capUpgrades + 1) <= maxCapacityUpgrades)
		{
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			compoundnbt.putInt("capUpgrades", capUpgrades + 1);
			mag.setTag(compoundnbt);
		}
	}
	
	@Override
	public boolean isBarVisible(ItemStack stack)
	{
		if (getCurrentAmmo(stack) > 0)
			return true;
		else
			return false;
	}
	
	@Override
    public int getBarWidth(ItemStack stack) 
	{
		return Math.round(13.0F - (getMaxAmmo(stack) - getCurrentAmmo(stack)) * 13.0F / getMaxAmmo(stack));
	}
	
	public int getBarColor(ItemStack stack) 
	{
		float stackMaxDamage = this.getMaxAmmo(stack);
		float f = Math.max(0.0F, (stackMaxDamage - (float) (stackMaxDamage - this.getCurrentAmmo(stack))) / stackMaxDamage);
      	return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
	}
	
	public int getCurrentAmmo(ItemStack stackIn)
	{
		if (stackIn.hasTag())
		{
			return stackIn.getTag().getInt("currentAmmo");
		}
		else
		{
			return 0;
		}
	}
	
	public int getMaxAmmo(ItemStack stackIn)
	{
		
		if (stackIn.hasTag())
		{
			int capUpgrades = getCapacityUpgrades(stackIn);
			int maxAmmo = baseAmmoSize;
			double capBonus = (maxAmmo * Constants.magIncreasePerLevel) * capUpgrades;
			
			//Just in case the mags are too small ;)
			//Yw you pistol users
			if ((capBonus < 1.0D * capUpgrades) && capUpgrades > 0)
				capBonus = 1.0D * capUpgrades;
			
			return (int) (maxAmmo + capBonus);	//10% extra ammo per level?
		}
		else
		{
			return 0;
		}
	}
	
	public int getCapacityUpgrades(ItemStack stackIn)
	{
		if (stackIn.hasTag())
			return stackIn.getTag().getInt("capUpgrades");
		else
			return 0;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
	   if (!stack.hasTag())
	   {
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("currentAmmo", 0);
			compoundnbt.putInt("maxAmmo", baseAmmoSize);
			compoundnbt.putInt("capUpgrades", capacityUpgrades);
			stack.setTag(compoundnbt);
	   }
	}
	
	public AbstractBulletItem returnBulletItem()
	{
		return bulletRequired;
	}
	
	public TagKey<Item> getCompatMag()
	{
		if (compatMags != null)
			return compatMags;
		else
			return null;
	}
}
