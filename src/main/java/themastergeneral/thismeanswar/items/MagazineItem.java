package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import themastergeneral.thismeanswar.TMWMain;

public class MagazineItem extends BaseTMWItem {

	private int maxAmmo; 
	private BulletItem bulletRequired;
	
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
	public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) 
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", maxAmmo);
		stack.setTag(compoundnbt);
	}
	
	//Show ammo on the magazine
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		tooltip.add(new TranslationTextComponent("Ammo: " + currentAmmo + " / " + maxAmmo));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack mag = playerIn.getItemInHand(handIn);
		if (playerIn.isCrouching())
		{
			if (getCurrentAmmo(mag) > 0)
			{
				removeAmmoFromMag(mag);
				playerIn.inventory.add(new ItemStack(bulletRequired, 1));
				playerIn.getCooldowns().addCooldown(this, 8);
			}
		}
		else
		{
			if ((getCurrentAmmo(mag) < getMaxAmmo(mag)) && (getMaxAmmo(mag) > 0))
			{
				int slotID = -1;
				for(int i = 0; i < playerIn.inventory.getContainerSize(); ++i) 
				{
					ItemStack itemstack1 = playerIn.inventory.getItem(i);
					if (itemstack1.getItem() == bulletRequired)
					{
						slotID=i;
						break;
					}
				}
				
				if (slotID > -1)
				{
					ItemStack ibullet = playerIn.inventory.getItem(slotID);
					addAmmoToMag(mag);
					ibullet.shrink(1);
					playerIn.getCooldowns().addCooldown(this, 8);
				}
			}
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		return ActionResult.sidedSuccess(mag, worldIn.isClientSide());
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
			CompoundNBT compoundnbt = new CompoundNBT();
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
			CompoundNBT compoundnbt = new CompoundNBT();
			int newAmmo = currentAmmo + toAdd;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			mag.setTag(compoundnbt);
		}
	}
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return true;
	}
	
	@Override
    public double getDurabilityForDisplay(ItemStack stack) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		return MathHelper.clamp(1.0D - ((double) currentAmmo / (double) maxAmmo), 0.0D, 1.0D);
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
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
	   if (!stack.hasTag())
	   {
			CompoundNBT compoundnbt = new CompoundNBT();
			compoundnbt.putInt("currentAmmo", 0);
			compoundnbt.putInt("maxAmmo", maxAmmo);
			stack.setTag(compoundnbt);
	   }
	}
}
