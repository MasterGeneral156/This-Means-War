package themastergeneral.thismeanswar.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import themastergeneral.thismeanswar.entity.RocketBaseEntity;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;

public class RocketGunItem extends AbstractGunItem {

	public RocketGunItem(int shotTime, int reloadTime, AbstractMagazineItem magazine, AbstractBulletItem bullet, float damage, float bulletSpeed) 
	{
		super(shotTime, reloadTime, magazine, bullet, damage, bulletSpeed, 0.0F);
	}
	
	//For guns with internal mags
	public RocketGunItem(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed) 
	{
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, 0.0F);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
	{
		ItemStack mag = playerIn.getItemInHand(handIn);
		if (playerIn.isCrouching())
		{
			if (getMagType(mag) == 1)
			{
				if (hasMag(mag) == 0)
				{
					int slotID = 0;
					for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) 
					{
			               ItemStack itemstack1 = playerIn.getInventory().getItem(i);
			               if (itemstack1.hasTag())
			               {
			            	   if (itemstack1.getItem() instanceof AbstractMagazineItem)
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
						ItemStack itemstack2 = playerIn.getInventory().getItem(slotID);
						CompoundTag nbt = itemstack2.getTag();
						int magAmmo = nbt.getInt("currentAmmo");
						int magMaxAmmo = nbt.getInt("maxAmmo");
						setGunAmmo(mag, magAmmo);
						setGunMaxAmmo(mag, magMaxAmmo);
						setGunMagLoad(mag, 1);
						playerIn.getInventory().removeItem(slotID, 1);
						playerIn.getCooldowns().addCooldown(this, reloadTime);
						return InteractionResultHolder.sidedSuccess(mag, worldIn.isClientSide());
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
					
					CompoundTag compoundnbt = new CompoundTag();
					compoundnbt.putInt("currentAmmo", gunAmmo);
					compoundnbt.putInt("maxAmmo", urmaxAmmo);
					newmag.setTag(compoundnbt);
					
					playerIn.getInventory().add(newmag);
					
					playerIn.getCooldowns().addCooldown(this, reloadTime);
					return InteractionResultHolder.sidedSuccess(mag, worldIn.isClientSide());
				}
			}
			if (getMagType(mag) == 2)
			{
				if ((getCurrentAmmo(mag) < getMaxAmmo(mag)) && (getMaxAmmo(mag) > 0))
				{
					int slotID = -1;
					for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) 
					{
						ItemStack itemstack1 = playerIn.getInventory().getItem(i);
						if (itemstack1.getItem() == bullet)
						{
							slotID=i;
							break;
						}
					}
					if (slotID >= 0)
					{
						ItemStack ibullet = playerIn.getInventory().getItem(slotID);
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
				RocketBaseEntity bulletEntity = new RocketBaseEntity(worldIn, playerIn, this.returnBulletDamage(mag), bullet, bulletSpeed);
				bulletEntity.setItem(new ItemStack(bullet));
				//Up+Down
				bulletEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0F, 1.5F, 1.0F);
				worldIn.addFreshEntity(bulletEntity);
				
				shootUpdateMag(mag);
				playerIn.awardStat(Stats.ITEM_USED.get(this));
				playerIn.getCooldowns().addCooldown(this, getRateOfFire(mag));
				return InteractionResultHolder.sidedSuccess(mag, worldIn.isClientSide());
			}
		}
		return InteractionResultHolder.fail(mag);
	}

}
