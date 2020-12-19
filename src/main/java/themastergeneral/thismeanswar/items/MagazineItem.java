package themastergeneral.thismeanswar.items;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MagazineItem extends CTDItem {

	private int maxAmmo; 
	private BulletItem bulletRequired;
	
	public MagazineItem(BulletItem Ammo, int maxAmmoSize) 
	{
		super(new Properties().maxStackSize(1));
		this.maxAmmo = maxAmmoSize;
		this.bulletRequired = Ammo;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) 
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", maxAmmo);
		stack.setTag(compoundnbt);
	}
	
	//Show ammo on the magazine
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		tooltip.add(new TranslationTextComponent("Ammo: " + currentAmmo + " / " + maxAmmo));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack mag = playerIn.getHeldItem(handIn);
		if (playerIn.isSneaking())
		{
			if (removeAmmoFromMag(mag))
			{
				playerIn.inventory.addItemStackToInventory(new ItemStack(bulletRequired, 1));
				playerIn.getCooldownTracker().setCooldown(this, 8);
			}
		}
		return ActionResult.resultPass(mag);
	}
	
	//Remove ammo one at a time... called when we manually unload the mags.
	//Returns true if ammo is removed, false if not.
	//See actual logic below.
	private boolean removeAmmoFromMag(ItemStack mag)
	{
		return removeAmmoFromMag(mag, 1);
	}
	
	//In case you wanna remove a lot more ammo at once...
	//Returns true if ammo is removed, false if not.
	private boolean removeAmmoFromMag(ItemStack mag, int toRemove)
	{
		int currentAmmo = getCurrentAmmo(mag);
		if ((currentAmmo - toRemove) >= 0)
		{
			CompoundNBT compoundnbt = new CompoundNBT();
			int newAmmo = currentAmmo - toRemove;
			compoundnbt.putInt("currentAmmo", newAmmo);
			mag.setTag(compoundnbt);
			return true;
		}
		else
		{
			return false;
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
	
	public static int getCurrentAmmo(ItemStack stackIn)
	{
		CompoundNBT nbt = stackIn.getTag();
		return nbt.getInt("currentAmmo");
	}
	
	public static int getMaxAmmo(ItemStack stackIn)
	{
		CompoundNBT nbt = stackIn.getTag();
		return nbt.getInt("maxAmmo");
	}
}
