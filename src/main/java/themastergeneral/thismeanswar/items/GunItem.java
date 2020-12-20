package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.entity.BulletBaseEntity;

public class GunItem extends CTDItem 
{
	protected int shotTime;
	protected int reloadTime;
	protected Item magazine;
	protected Item bullet;
	protected float damage;
	protected int maxAmmo;
	protected int magType;
	
	public GunItem(int shotTime, int reloadTime, MagazineItem magazine, Item bullet, float damage) 
	{
		super(new Properties().maxStackSize(1));
		this.shotTime=shotTime;
		this.reloadTime=reloadTime;
		this.magazine=magazine;
		this.bullet=bullet;
		this.damage=damage;
		this.maxAmmo=0;
		this.magType=1;
	}
	
	//For guns with internal mags
	public GunItem(int shotTime, Item bullet, float damage, int maxAmmo) 
	{
		super(new Properties().maxStackSize(1));
		this.shotTime=shotTime;
		this.reloadTime=0;
		this.magazine=null;
		this.bullet=bullet;
		this.damage=damage;
		this.maxAmmo=maxAmmo;
		this.magType=2;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack mag = playerIn.getHeldItem(handIn);
		ItemStack lookfor = new ItemStack(magazine);
		if (playerIn.isOnGround())
		{
			TMWMain.LOGGER.info("On ground");
			if (playerIn.isSneaking())
			{
				TMWMain.LOGGER.info("Sneak");
				if (getMagType(mag) == 1)
				{
					TMWMain.LOGGER.info("Mag type = magazine");
					if (hasMag(mag) == 0)
					{
						TMWMain.LOGGER.info("Gun is empty");
						if (playerIn.inventory.count(lookfor.getItem().asItem()) > 1)
						{
							TMWMain.LOGGER.info("Have valid magazine.");
							int slotId = playerIn.inventory.getSlotFor(lookfor);
							TMWMain.LOGGER.info(slotId);
							ItemStack magazine = playerIn.inventory.getStackInSlot(slotId);
							if (magazine.hasTag())
							{
								TMWMain.LOGGER.info("Updating gun, removing mag.");
								CompoundNBT nbt = magazine.getTag();
								int magAmmo = nbt.getInt("currentAmmo");
								int magMaxAmmo = nbt.getInt("maxAmmo");
								setGunAmmo(mag, magAmmo);
								setGunMaxAmmo(mag, magMaxAmmo);
								setGunMagLoad(mag, 1);
								playerIn.inventory.decrStackSize(slotId, 1);
								playerIn.getCooldownTracker().setCooldown(this, (int) (reloadTime * 0.75));
							}
						}
					}
				}
			}
			else
			{
				TMWMain.LOGGER.info("No sneak");
				if (canFire(mag))
				{
					TMWMain.LOGGER.info("Firing");
					if (!worldIn.isRemote) 
					{
						BulletBaseEntity bulletEntity = new BulletBaseEntity(worldIn, playerIn, damage);
						bulletEntity.setItem(new ItemStack(bullet));
						bulletEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
						worldIn.addEntity(bulletEntity);
					}
					shootUpdateMag(mag);
					playerIn.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
		return ActionResult.resultPass(mag);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) 
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", 0);
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
		if (getCurrentAmmo(stackIn) == 0)
			return false;
		else if (getMaxAmmo(stackIn) == 0)
			return false;
		else if (hasMag(stackIn) == 0)
			return false;
		else
			return true;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
	   if (!stack.hasTag())
	   {
			CompoundNBT compoundnbt = new CompoundNBT();
			compoundnbt.putInt("currentAmmo", 0);
			compoundnbt.putInt("maxAmmo", 0);
			compoundnbt.putInt("magLoaded", 0);
			compoundnbt.putInt("magType", magType);
			stack.setTag(compoundnbt);
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
	
	private void setGunAmmo(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", setTo);
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", magType);
		mag.setTag(compoundnbt);
	}
	
	private void setGunMaxAmmo(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", magType);
		mag.setTag(compoundnbt);
	}
	
	private void setGunMagLoad(ItemStack mag, int setTo)
	{
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", setTo);
		compoundnbt.putInt("magType", magType);
		mag.setTag(compoundnbt);
	}
}
