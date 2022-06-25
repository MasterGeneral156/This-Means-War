package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import themastergeneral.thismeanswar.TMWMain;

public class MagazineItem extends BaseTMWItem {

	private int maxAmmo; 
	protected BulletItem bulletRequired;
	
	public MagazineItem(BulletItem Ammo, int maxAmmoSize) 
	{
		super(new Properties().stacksTo(1).tab(TMWMain.ITEMGROUP));
		this.maxAmmo = maxAmmoSize;
		this.bulletRequired = Ammo;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	@Override
	public int getItemEnchantability(ItemStack stack)
	{
		return 10;
	}
	
	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) 
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", maxAmmo);
		stack.setTag(compoundnbt);
	}
	
	//Show ammo on the magazine
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		tooltip.add(new TranslatableComponent("Capacity: " + currentAmmo + " / " + maxAmmo));
		tooltip.add(new TranslatableComponent(bulletRequired.getDescriptionId()));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack mag = playerIn.getItemInHand(handIn);
		if (playerIn.isCrouching())
		{
			if (getCurrentAmmo(mag) > 0)
			{
				removeAmmoFromMag(mag);
				playerIn.getInventory().add(new ItemStack(returnBulletItem(), 1));
				playerIn.getCooldowns().addCooldown(this, 8);
			}
		}
		else
		{
			if ((getCurrentAmmo(mag) < getMaxAmmo(mag)) && (getMaxAmmo(mag) > 0))
			{
				int slotID = -1;
				for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) 
				{
					ItemStack itemstack1 = playerIn.getInventory().getItem(i);
					if (itemstack1.getItem() == returnBulletItem())
					{
						slotID=i;
						break;
					}
				}
				
				if (slotID > -1)
				{
					ItemStack ibullet = playerIn.getInventory().getItem(slotID);
					addAmmoToMag(mag);
					ibullet.shrink(1);
					playerIn.getCooldowns().addCooldown(this, 8);
				}
			}
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
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
	private void removeAmmoFromMag(ItemStack mag, int toRemove)
	{
		int currentAmmo = getCurrentAmmo(mag);
		if ((currentAmmo - toRemove) >= 0)
		{
			CompoundTag compoundnbt = new CompoundTag();
			int newAmmo = currentAmmo - toRemove;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			mag.setTag(compoundnbt);
		}
	}
	
	private void addAmmoToMag(ItemStack mag)
	{
		addAmmoToMag(mag, 1);
	}
	
	private void addAmmoToMag(ItemStack mag, int toAdd)
	{
		int currentAmmo = getCurrentAmmo(mag);
		int maxAmmo = getMaxAmmo(mag);
		if ((currentAmmo + toAdd) <= maxAmmo)
		{
			CompoundTag compoundnbt = new CompoundTag();
			int newAmmo = currentAmmo + toAdd;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			mag.setTag(compoundnbt);
		}
	}
	
	@Override
	public boolean isBarVisible(ItemStack stack)
	{
		return true;
	}
	
	@Override
    public int getBarWidth(ItemStack stack) 
	{
		return Math.round(13.0F - (getMaxAmmo(stack) -getCurrentAmmo(stack)) * 13.0F / getMaxAmmo(stack));
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
			int maxAmmo = stackIn.getTag().getInt("maxAmmo");
			int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, stackIn);
			
			return (int) (maxAmmo + ((maxAmmo * 0.1) * enchant));	//10% extra ammo per level?
		}
		else
		{
			return 0;
		}
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
	   if (!stack.hasTag())
	   {
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("currentAmmo", 0);
			compoundnbt.putInt("maxAmmo", maxAmmo);
			stack.setTag(compoundnbt);
	   }
	}
	
	public BulletItem returnBulletItem()
	{
		return bulletRequired;
	}
}
