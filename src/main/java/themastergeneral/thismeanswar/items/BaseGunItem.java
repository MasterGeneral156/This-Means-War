package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.entity.BulletBaseEntity;

public class BaseGunItem extends BaseTMWItem {

	protected int shotTime;
	protected int reloadTime;
	protected Item magazine;
	protected BulletItem bullet;
	protected float damage;
	protected int maxAmmo;
	protected int magType;
	protected float bulletSpeed;
	protected float bulletSpread;
	
	public BaseGunItem(int shotTime, int reloadTime, MagazineItem magazine, BulletItem bullet, float damage, float bulletSpeed, float bulletSpread) 
	{
		super(new Properties().stacksTo(1).tab(TMWMain.ITEMGROUP));
		this.shotTime=shotTime;
		this.reloadTime=reloadTime;
		this.magazine=magazine;
		this.bullet=bullet;
		this.damage=damage;
		this.maxAmmo=0;
		this.magType=1;
		this.bulletSpread = bulletSpread;
		this.bulletSpeed = bulletSpeed;
	}
	
	//For guns with internal mags
	public BaseGunItem(int shotTime, BulletItem bullet, float damage, int maxAmmo, float bulletSpeed, float bulletSpread) 
	{
		super(new Properties().stacksTo(1).tab(TMWMain.ITEMGROUP));
		this.shotTime=shotTime;
		this.reloadTime=0;
		this.magazine=null;
		this.bullet=bullet;
		this.damage=damage;
		this.maxAmmo=maxAmmo;
		this.magType=2;
		this.bulletSpread = bulletSpread;
		this.bulletSpeed = bulletSpeed;
	}
	
	@Override
	public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) 
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", maxAmmo);
		compoundnbt.putInt("magLoaded", 0);
		compoundnbt.putInt("magType", magType);
		stack.setTag(compoundnbt);
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
			return stackIn.getTag().getInt("maxAmmo");
		}
		else
		{
			return 0;
		}
	}
	
	public int hasMag(ItemStack stackIn)
	{
		if (stackIn.hasTag())
		{
			return stackIn.getTag().getInt("magLoaded");
		}
		else
		{
			return 0;
		}
	}
	
	public int getMagType(ItemStack stackIn)
	{
		if (stackIn.hasTag())
		{
			return stackIn.getTag().getInt("magType");
		}
		else
		{
			return 0;
		}
	}
	
	public boolean canFire(ItemStack stackIn)
	{
		if (getCurrentAmmo(stackIn) > 0)
			return true;
		else
			return false;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
	   if (!stack.hasTag())
	   {
			CompoundNBT compoundnbt = new CompoundNBT();
			compoundnbt.putInt("currentAmmo", 0);
			compoundnbt.putInt("maxAmmo", maxAmmo);
			compoundnbt.putInt("magLoaded", 0);
			compoundnbt.putInt("magType", magType);
			stack.setTag(compoundnbt);
	   }
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		if (getMagType(stack) == 1)
		{
			if (hasMag(stack) == 1)
				return true;
			else
				return false;
		}
		else if (getMagType(stack) == 2)
		{
			if (this.getCurrentAmmo(stack) > 0)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	@Override
    public double getDurabilityForDisplay(ItemStack stack) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		return MathHelper.clamp(1.0D - ((double) currentAmmo / (double) maxAmmo), 0.0D, 1.0D);
	}
	
	public void shootUpdateMag(ItemStack stack)
	{
		removeAmmoFromGun(stack);
	}
	
	//Remove ammo one at a time... called when we manually unload the mags.
	//Returns true if ammo is removed, false if not.
	//See actual logic below.
	private boolean removeAmmoFromGun(ItemStack mag)
	{
		return removeAmmoFromGun(mag, 1);
	}
	
	//In case you wanna remove a lot more ammo at once...
	//Returns true if ammo is removed, false if not.
	private boolean removeAmmoFromGun(ItemStack mag, int toRemove)
	{
		int currentAmmo = getCurrentAmmo(mag);
		if ((currentAmmo - toRemove) >= 0)
		{
			CompoundNBT compoundnbt = new CompoundNBT();
			int newAmmo = currentAmmo - toRemove;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			compoundnbt.putInt("magLoaded", hasMag(mag));
			compoundnbt.putInt("magType", magType);
			mag.setTag(compoundnbt);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	protected void addAmmoToMag(ItemStack mag)
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
			compoundnbt.putInt("magLoaded", hasMag(mag));
			compoundnbt.putInt("magType", getMagType(mag));
			mag.setTag(compoundnbt);
		}
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	protected void setGunAmmo(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", setTo);
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", getMagType(mag));
		mag.setTag(compoundnbt);
	}
	
	protected void setGunMaxAmmo(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", setTo);
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", getMagType(mag));
		mag.setTag(compoundnbt);
	}
	
	protected void setGunMagLoad(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", setTo);
		compoundnbt.putInt("magType", getMagType(mag));
		mag.setTag(compoundnbt);
	}
	
	protected void setGunMagType(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", setTo);
		mag.setTag(compoundnbt);
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
			if (getMagType(mag) == 1)
			{
				if (hasMag(mag) == 0)
				{
					int slotID = 0;
					for(int i = 0; i < playerIn.inventory.getContainerSize(); ++i) 
					{
			               ItemStack itemstack1 = playerIn.inventory.getItem(i);
			               if (itemstack1.hasTag())
			               {
			            	   if (itemstack1.getItem() instanceof MagazineItem)
			            	   {
				            	   if ((itemstack1.getTag().contains("maxAmmo")) && (itemstack1.getTag().contains("currentAmmo")))
		            			   {
				            		   if (itemstack1.getTag().getInt("currentAmmo") > 0)
				            		   {
					            		   slotID = i;
					            		   break;
				            		   }
		            			   }
			            	   }
			               }
			        }
					if (slotID > 0)
					{
						ItemStack itemstack2 = playerIn.inventory.getItem(slotID);
						CompoundNBT nbt = itemstack2.getTag();
						int magAmmo = nbt.getInt("currentAmmo");
						int magMaxAmmo = nbt.getInt("maxAmmo");
						setGunAmmo(mag, magAmmo);
						setGunMaxAmmo(mag, magMaxAmmo);
						setGunMagLoad(mag, 1);
						playerIn.inventory.removeItem(slotID, 1);
						playerIn.getCooldowns().addCooldown(this, reloadTime);
						return ActionResult.sidedSuccess(mag, worldIn.isClientSide());
					}
				}
				if (hasMag(mag) == 1)
				{
					int gunAmmo = getCurrentAmmo(mag);
					int urmaxAmmo = getMaxAmmo(mag);
					
					ItemStack newmag = new ItemStack(magazine);
					
					
					setGunAmmo(mag, 0);
					setGunMaxAmmo(mag, 0);
					setGunMagLoad(mag, 0);
					
					CompoundNBT compoundnbt = new CompoundNBT();
					compoundnbt.putInt("currentAmmo", gunAmmo);
					compoundnbt.putInt("maxAmmo", urmaxAmmo);
					newmag.setTag(compoundnbt);
					
					playerIn.inventory.add(newmag);
					
					playerIn.getCooldowns().addCooldown(this, reloadTime);
					return ActionResult.sidedSuccess(mag, worldIn.isClientSide());
				}
			}
			if (getMagType(mag) == 2)
			{
				if ((getCurrentAmmo(mag) < getMaxAmmo(mag)) && (getMaxAmmo(mag) > 0))
				{
					int slotID = -1;
					for(int i = 0; i < playerIn.inventory.getContainerSize(); ++i) 
					{
						ItemStack itemstack1 = playerIn.inventory.getItem(i);
						if (itemstack1.getItem() == bullet)
						{
							slotID=i;
							break;
						}
					}
					if (slotID >= 0)
					{
						ItemStack ibullet = playerIn.inventory.getItem(slotID);
						addAmmoToMag(mag);
						ibullet.shrink(1);
						playerIn.getCooldowns().addCooldown(this, 8);
					}
				}
			}
		}
		else
		{
			if (canFire(mag))
			{
				BulletBaseEntity bulletEntity = new BulletBaseEntity(worldIn, playerIn, damage, bullet);
				bulletEntity.setItem(new ItemStack(bullet));
				//Up+Down
				bulletEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0F, 1.5F, 1.0F);	
				worldIn.addFreshEntity(bulletEntity);
				
				shootUpdateMag(mag);
				playerIn.awardStat(Stats.ITEM_USED.get(this));
				playerIn.getCooldowns().addCooldown(this, shotTime);
				return ActionResult.sidedSuccess(mag, worldIn.isClientSide());
			}
		}
		return ActionResult.fail(mag);
	}
}
