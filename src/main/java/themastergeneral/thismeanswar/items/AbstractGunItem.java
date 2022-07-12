package themastergeneral.thismeanswar.items;

import java.text.NumberFormat;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.property.Properties;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.config.BalanceConfig;
import themastergeneral.thismeanswar.entity.BulletBaseEntity;

public class AbstractGunItem extends AbstractModItem {

	protected int shotTime;
	protected int reloadTime;
	protected Item magazine;
	protected AbstractBulletItem bullet;
	protected float damage;
	protected int maxAmmo;
	protected int magType;
	protected float bulletSpeed;
	protected float bulletSpread;
	
	protected int external_mag = 1;
	protected int internal_mag = 2;
	
	private int rofUpgradeScale = 0;
	
	/**
	 * Use to create a firearm that's magazine fed.
	 * @param Integer shotTime 			Ticks between shots
	 * @param Integer reloadTime 		Ticks to reload magazine.
	 * @param ItemStack magazine		Magazine ItemStack
	 * @param AbstractBulletItem bullet			Bullet item, for the bullet sprite.
	 * @param Float damage				Gun damage
	 * @param Float bulletSpeed			Bullet speed
	 * @param Float bulletSpread		Bullet spread
	 */
	public AbstractGunItem(int shotTime, int reloadTime, AbstractMagazineItem magazine, AbstractBulletItem bullet, float damage, float bulletSpeed, float bulletSpread) 
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
	
	/**
	 * Use to create a firearm that must have bullet fed directly inside.
	 * @param Integer shotTime 			Ticks between shots
	 * @param AbstractBulletItem bullet			Bullet item, for the bullet sprite.
	 * @param Float damage				Gun damage
	 * @param Integer maxAmmo			Maximum bullets in gun.
	 * @param Float bulletSpeed			Bullet speed
	 * @param Float bulletSpread		Bullet spread
	 */
	public AbstractGunItem(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed, float bulletSpread) 
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
	
	//TODO: Fix this
	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) 
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", 0);
		compoundnbt.putInt("maxAmmo", maxAmmo);
		compoundnbt.putInt("magLoaded", 0);
		compoundnbt.putInt("magType", magType);
		compoundnbt.putInt("rofUpgrade", rofUpgradeScale);
		
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
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
	   if (!stack.hasTag())
	   {
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("currentAmmo", 0);
			compoundnbt.putInt("maxAmmo", maxAmmo);
			compoundnbt.putInt("magLoaded", 0);
			compoundnbt.putInt("magType", magType);
			compoundnbt.putInt("rofUpgrade", rofUpgradeScale);
			stack.setTag(compoundnbt);
	   }
	}
	
	@Override
	public boolean isBarVisible(ItemStack stack)
	{
		if (getMagType(stack) == external_mag)
		{
			if (hasMag(stack) == 1)
				return true;
			else
				return false;
		}
		else if (getMagType(stack) == internal_mag)
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
    public int getBarWidth(ItemStack stack) 
	{
		return Math.round(13.0F - (getMaxAmmo(stack) -getCurrentAmmo(stack)) * 13.0F / getMaxAmmo(stack));
	}
	
	public void shootUpdateMag(ItemStack stack)
	{
		removeAmmoFromGun(stack);
	}

   public int getBarColor(ItemStack stack) 
   {
      float stackMaxDamage = this.getMaxAmmo(stack);
      float f = Math.max(0.0F, (stackMaxDamage - (float) (stackMaxDamage - this.getCurrentAmmo(stack))) / stackMaxDamage);
      return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
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
			CompoundTag compoundnbt = new CompoundTag();
			int newAmmo = currentAmmo - toRemove;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			compoundnbt.putInt("magLoaded", hasMag(mag));
			compoundnbt.putInt("magType", magType);
			compoundnbt.putInt("rofUpgrade", getRateOfFire(mag));
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
			CompoundTag compoundnbt = new CompoundTag();
			int newAmmo = currentAmmo + toAdd;
			compoundnbt.putInt("currentAmmo", newAmmo);
			compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
			compoundnbt.putInt("magLoaded", hasMag(mag));
			compoundnbt.putInt("magType", getMagType(mag));
			compoundnbt.putInt("rofUpgrade", getRateOfFire(mag));
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
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", setTo);
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", getMagType(mag));
		compoundnbt.putInt("rofUpgrade", getRateOfFire(mag));
		mag.setTag(compoundnbt);
	}
	
	protected void setGunMaxAmmo(ItemStack mag, int setTo)
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", setTo);
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", getMagType(mag));
		compoundnbt.putInt("rofUpgrade", getRateOfFire(mag));
		mag.setTag(compoundnbt);
	}
	
	protected void setGunMagLoad(ItemStack mag, int setTo)
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", setTo);
		compoundnbt.putInt("magType", getMagType(mag));
		compoundnbt.putInt("rofUpgrade", getRateOfFire(mag));
		mag.setTag(compoundnbt);
	}
	
	protected void setGunMagType(ItemStack mag, int setTo)
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", setTo);
		compoundnbt.putInt("rofUpgrade", getRateOfFire(mag));
		mag.setTag(compoundnbt);
	}
	
	//Show ammo on the magazine
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		int maxAmmo = getMaxAmmo(stack);
		tooltip.add(new TranslatableComponent("Capacity: " + currentAmmo + " / " + maxAmmo));
		if (magazine == null)
			tooltip.add(new TranslatableComponent(bullet.getDescriptionId()));
		else
			tooltip.add(new TranslatableComponent(magazine.getDescriptionId()));
			
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack mag = playerIn.getItemInHand(handIn);
		if (handIn == InteractionHand.MAIN_HAND && playerIn.getOffhandItem().isEmpty())
		{
			if (playerIn.isCrouching())
			{
				if (getMagType(mag) == external_mag)
				{
					if (hasMag(mag) == 0)
					{
						int slotID = 0;
						for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) 
						{
				               ItemStack itemstack1 = playerIn.getInventory().getItem(i);
				               if (itemstack1.hasTag())
				               {
				            	   if (itemstack1.getItem() == magazine)
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
							setGunROF(mag, getRateOfFire(mag));
							playerIn.getInventory().removeItem(slotID, 1);
							playerIn.getCooldowns().addCooldown(this, reloadTime);
							playerIn.displayClientMessage(new TextComponent("Magazine has been inserted."), true);
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
						setGunROF(mag, getRateOfFire(mag));
						
						CompoundTag compoundnbt = new CompoundTag();
						compoundnbt.putInt("currentAmmo", gunAmmo);
						compoundnbt.putInt("maxAmmo", urmaxAmmo);
						newmag.setTag(compoundnbt);
						
						playerIn.getInventory().add(newmag);
						
						playerIn.getCooldowns().addCooldown(this, reloadTime);
						playerIn.displayClientMessage(new TextComponent("Magazine has been unloaded."), true);
						return InteractionResultHolder.sidedSuccess(mag, worldIn.isClientSide());
					}
				}
				if (getMagType(mag) == internal_mag)
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
							playerIn.displayClientMessage(new TextComponent("Bullet has been inserted."), true);
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
					bulletEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0F, 1.5F, 1.0F);	
					worldIn.addFreshEntity(bulletEntity);
					
					shootUpdateMag(mag);
					playerIn.awardStat(Stats.ITEM_USED.get(this));
					playerIn.getCooldowns().addCooldown(this, getRateOfFire(mag));
					giveBulletCasing(playerIn);
					return InteractionResultHolder.sidedSuccess(mag, worldIn.isClientSide());
				}
			}
		}
		else if (handIn == InteractionHand.OFF_HAND && (playerIn.getMainHandItem().getItem() == TMWItems.gun_rof_upgrade))
		{
			if (playerIn.isCrouching())
			{
				if (getRateOfFire(mag) == BalanceConfig.FIRE_RATE_AUTO.get())
				{
					//fail
					TextComponent message = new TextComponent("Upgrade is not compatible with current weapon.");
					playerIn.displayClientMessage(message, true);
				}
				else
				{
					TextComponent message = new TextComponent("Rate of Fire is now full-auto.");
					setGunROF(playerIn.getItemInHand(InteractionHand.OFF_HAND), BalanceConfig.FIRE_RATE_AUTO.get());
					playerIn.getMainHandItem().shrink(1);
					playerIn.displayClientMessage(message, true);
				}
			}
		}
		else if (handIn == InteractionHand.OFF_HAND && (playerIn.getMainHandItem().getItem() == TMWItems.gun_rof_downgrade))
		{
			if (playerIn.isCrouching())
			{
				if (getRateOfFire(mag) == BalanceConfig.FIRE_RATE_SINGLE.get())
				{
					//fail
					TextComponent message = new TextComponent("Upgrade is not compatible with current weapon.");
					playerIn.displayClientMessage(message, true);
				}
				else
				{
					TextComponent message = new TextComponent("Rate of Fire is now 'fully semi-automatic'.");
					setGunROF(playerIn.getItemInHand(InteractionHand.OFF_HAND), BalanceConfig.FIRE_RATE_SINGLE.get());
					playerIn.getMainHandItem().shrink(1);
					playerIn.displayClientMessage(message, true);
				}
			}
		}
		return InteractionResultHolder.fail(mag);
	}
	
	public void giveBulletCasing(Player player)
	{
		Item casing = bullet.returnCasingItem();
		if (casing != null)
			player.getInventory().add(new ItemStack(casing));
	}
	
	protected int getRateOfFire(ItemStack stack)
	{
		if (stack.hasTag())
		{
			return stack.getTag().getInt("rofUpgrade");
		}
		else
		{
			return this.shotTime;
		}
	}
	
	protected void setGunROF(ItemStack mag, int setTo)
	{
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putInt("currentAmmo", getCurrentAmmo(mag));
		compoundnbt.putInt("maxAmmo", getMaxAmmo(mag));
		compoundnbt.putInt("magLoaded", hasMag(mag));
		compoundnbt.putInt("magType", getMagType(mag));
		compoundnbt.putInt("rofUpgrade", setTo);
		mag.setTag(compoundnbt);
	}
}
