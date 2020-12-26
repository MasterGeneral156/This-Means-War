package themastergeneral.thismeanswar.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.entity.RocketBaseEntity;

public class RocketGunItem extends BaseGunItem {

	public RocketGunItem(int shotTime, int reloadTime, MagazineItem magazine, BulletItem bullet, float damage, float bulletSpeed) 
	{
		super(shotTime, reloadTime, magazine, bullet, damage, bulletSpeed, 0.0F);
	}
	
	//For guns with internal mags
	public RocketGunItem(int shotTime, BulletItem bullet, float damage, int maxAmmo, float bulletSpeed) 
	{
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, 0.0F);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack mag = playerIn.getHeldItem(handIn);
		if (playerIn.isSneaking())
		{
			if (getMagType(mag) == 1)
			{
				if (hasMag(mag) == 0)
				{
					int slotID = 0;
					for(int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) 
					{
			               ItemStack itemstack1 = playerIn.inventory.getStackInSlot(i);
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
						ItemStack itemstack2 = playerIn.inventory.getStackInSlot(slotID);
						CompoundNBT nbt = itemstack2.getTag();
						int magAmmo = nbt.getInt("currentAmmo");
						int magMaxAmmo = nbt.getInt("maxAmmo");
						setGunAmmo(mag, magAmmo);
						setGunMaxAmmo(mag, magMaxAmmo);
						setGunMagLoad(mag, 1);
						playerIn.inventory.decrStackSize(slotID, 1);
						playerIn.getCooldownTracker().setCooldown(this, reloadTime);
						return ActionResult.resultPass(mag);
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
					
					playerIn.inventory.addItemStackToInventory(newmag);
					
					playerIn.getCooldownTracker().setCooldown(this, reloadTime);
					return ActionResult.resultPass(mag);
				}
			}
			if (getMagType(mag) == 2)
			{
				if ((getCurrentAmmo(mag) < getMaxAmmo(mag)) && (getMaxAmmo(mag) > 0))
				{
					int slotID = -1;
					for(int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) 
					{
						ItemStack itemstack1 = playerIn.inventory.getStackInSlot(i);
						if (itemstack1.getItem() == bullet)
						{
							slotID=i;
							break;
						}
					}
					if (slotID >= 0)
					{
						ItemStack ibullet = playerIn.inventory.getStackInSlot(slotID);
						addAmmoToMag(mag);
						ibullet.shrink(1);
						playerIn.getCooldownTracker().setCooldown(this, 8);
					}
				}
			}
		}
		else
		{
			if (canFire(mag))
			{
				RocketBaseEntity bulletEntity = new RocketBaseEntity(worldIn, playerIn, damage, bullet, bulletSpeed);
				bulletEntity.setItem(new ItemStack(bullet));
				//Up+Down
				bulletEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0F, 1.5F, 1.0F);
				worldIn.addEntity(bulletEntity);
				
				shootUpdateMag(mag);
				playerIn.addStat(Stats.ITEM_USED.get(this));
				playerIn.getCooldownTracker().setCooldown(this, shotTime);
				return ActionResult.resultPass(mag);
			}
		}
		return ActionResult.resultFail(mag);
	}

}
