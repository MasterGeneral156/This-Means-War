package themastergeneral.thismeanswar.items;

import java.text.NumberFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nullable;

import org.joml.Random;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
import themastergeneral.thismeanswar.TMWSounds;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.entity.BulletBaseEntity;
import themastergeneral.thismeanswar.items.define.TMWCarbines;
import themastergeneral.thismeanswar.items.define.TMWPistols;
import themastergeneral.thismeanswar.items.define.TMWRifles;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;
import themastergeneral.thismeanswar.items.interfaces.WeaponSniper;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunBayonetItem;

public class NuGunItem extends AbstractModItem {

    public int shotTime;
    protected int reloadTime;
    public NuMagazineItem magazine;
    public AbstractBulletItem bullet;
    protected float damage;
    protected int baseAmmoSize;
    protected int maxAmmo;
    protected int magType;
    protected float bulletSpeed;
    protected float bulletSpread;
    
    private int rofUpgradeScale = 0;
    private int bulletUpgrade = 0;
    
    public static int SLOT_MAG = 0;
    public int SLOT_ROUNDS = 0;
    public int SLOT_BAYONET = 1;
    public int SLOT_ROUND_UPGRADE = 2;
    public int SLOT_ROF_UPGRADE = 3;
    public int SLOT_OVERFLOW = 4;
    
    protected double bayonetUpgradeLvl = 0.0;
    
    protected int counter = 0;
    
    /**
     * Use to create a firearm that's magazine fed.
     * @param Integer shotTime            Ticks between shots
     * @param Integer reloadTime         Ticks to reload magazine.
     * @param ItemStack magazine        Magazine ItemStack
     * @param AbstractBulletItem bullet            Bullet item, for the bullet sprite.
     * @param Float damage                Gun damage
     * @param Float bulletSpeed            Bullet speed
     * @param Float bulletSpread        Bullet spread
     */
    public NuGunItem(int shotTime, int reloadTime, NuMagazineItem magazine, float damage, float bulletSpeed, float bulletSpread) 
    {
        super(new Properties().stacksTo(1));
        this.shotTime=shotTime;
        this.reloadTime=reloadTime;
        this.magazine=magazine;
        this.bullet=magazine.returnBulletItem();
        this.damage=damage;
        this.maxAmmo=0;
        this.magType=Constants.external_mag;
        this.bulletSpread = bulletSpread;
        this.bulletSpeed = bulletSpeed;
    }
    
    /**
     * Use to create a firearm that must have bullet fed directly inside.
     * @param Integer shotTime            Ticks between shots
     * @param AbstractBulletItem bullet            Bullet item, for the bullet sprite.
     * @param Float damage                Gun damage
     * @param Integer maxAmmo            Maximum bullets in gun.
     * @param Float bulletSpeed            Bullet speed
     * @param Float bulletSpread        Bullet spread
     */
    public NuGunItem(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed, float bulletSpread) 
    {
        super(new Properties().stacksTo(1));
        this.shotTime=shotTime;
        this.bullet=bullet;
        this.damage=damage;
        this.maxAmmo=maxAmmo;
        this.magType=Constants.internal_mag;
        this.bulletSpread = bulletSpread;
        this.bulletSpeed = bulletSpeed;
        this.reloadTime=5;
    }
    
    @Override
    public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) 
    {
        this.doSetupGun(stack);
    }
    
    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
    {
        counter++;
        if (counter == 20)
        {
            doSetupGun(stack);
            Player player = (Player) entityIn;
            doZoom(player.getItemInHand(InteractionHand.MAIN_HAND), player);
            counter = 0;
        }
    }
    
    protected void doSetupGun(ItemStack stack)
    {
        if (!stack.hasTag()) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("MagType", this.magType);
            stack.setTag(tag);
            saveInventory(stack);
        }
    }
    
    public void saveInventory(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            CompoundTag tag = stack.getOrCreateTag();
            tag.put("Inventory", inventory.serializeNBT());
        });
    }

    public LazyOptional<ItemStackHandler> getInventory(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).cast();
    }
    
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new CustomItemHandlerProvider(stack);
    }
    
    public static class CustomItemHandler extends ItemStackHandler {

        private final ItemStack stack;

        public CustomItemHandler(ItemStack stack) 
		{
			super(6);
			this.stack = stack;
			if (stack.hasTag() && stack.getTag().contains("Inventory"))
				this.deserializeNBT(stack.getTag().getCompound("Inventory"));
		}
        
        /*@Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack)
        {
            int returned = 1;
            if (slot == 0)    //Mag/bullet slot
            {
                if (returnMagType() == Constants.internal_mag)
                    returned = getMaxAmmo(stack);
            }
            return returned;
            
        }*/

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (!stack.isEmpty()) {
            	if (stack.getItem() instanceof NuGunItem gun)
            	{
	                CompoundTag tag = stack.getOrCreateTag();
	                tag.putInt("MagType", gun.returnMagType());
	                tag.put("Inventory", this.serializeNBT());
            	}
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
    
    public int returnMagType()
    {
        return magType;
    }
    
    public ItemStack getAmmoStack(ItemStack stack) {

        return getMagazineStack(stack);
    }
    
    public ItemStack getMagazineStack(ItemStack stack) 
    {
    	final ItemStack[] capacityUpgrades = {ItemStack.EMPTY};
        getInventory(stack).ifPresent(inv -> capacityUpgrades[0] = inv.getStackInSlot(SLOT_MAG));
        return capacityUpgrades[0];
    }
    
    public NuMagazineItem getMagazine(ItemStack stack) {

        return (NuMagazineItem) getMagazineStack(stack).getItem();
    }
    
    public int getCurrentAmmo(ItemStack stack) {
    	AtomicInteger returned = new AtomicInteger(0);
        if (returnMagType() == Constants.external_mag) {
            // Fetch the inventory from the ItemStack's capabilities
            getInventory(stack).ifPresent(inventory -> {
                ItemStack magStack = inventory.getStackInSlot(SLOT_MAG);
                if (magStack.getItem() instanceof NuMagazineItem mag) {
                    returned.set(mag.getCurrentAmmo(magStack));
                }
            });
        } else if (returnMagType() == Constants.internal_mag) 
        {
            getInventory(stack).ifPresent(inventory -> {
            	returned.set(inventory.getStackInSlot(SLOT_MAG).getCount() + (getOverflow(stack) * 126));
            });
        }
        return returned.get();
    }
    
    public int getOverflow(ItemStack stack) {
        final int[] capacityUpgrades = {0};
        getInventory(stack).ifPresent(inv -> capacityUpgrades[0] = inv.getStackInSlot(SLOT_OVERFLOW).getCount());
        return capacityUpgrades[0];
    }
    
    public void addOverflow(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.insertItem(SLOT_OVERFLOW, new ItemStack(TMWItems.creative_charm, 1), false);
            saveInventory(stack);
        });
    }
    
    public void removeOverflow(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.extractItem(SLOT_OVERFLOW, 1, false);
            saveInventory(stack);
        });
    }
    
    public int getMaxAmmo(ItemStack stack) {
    	AtomicInteger returned = new AtomicInteger(0);
        if (returnMagType() == Constants.external_mag) {
            // Fetch the inventory from the ItemStack's capabilities
            getInventory(stack).ifPresent(inventory -> {
                ItemStack magStack = inventory.getStackInSlot(SLOT_MAG);
                if (magStack.getItem() instanceof NuMagazineItem mag) {
                    returned.set(mag.getMaxAmmo(magStack));
                }
            });
        } else if (returnMagType() == Constants.internal_mag) {
            returned.set(maxAmmo);
        }
        return returned.get();
    }
    
    public ItemStack returnBayonetStack(ItemStack stack)
    {
    	final ItemStack[] bayonet = {ItemStack.EMPTY};
        getInventory(stack).ifPresent(inv -> bayonet[0] = inv.getStackInSlot(SLOT_BAYONET));
        return bayonet[0];
    }
    
    public double getBayonetDamage(ItemStack stack) {
    	final double[] returned = {Double.NaN};
    	getInventory(stack).ifPresent(inventory -> {
            ItemStack magStack = inventory.getStackInSlot(SLOT_MAG);
            if (magStack.getItem() instanceof UpgradeGunBayonetItem bayonet) {
                returned[0] = bayonet.returnBayonetLevel();
            }
        });
    	return returned[0];
    }
    
    public ItemStack getRoundUpgrade(ItemStack stack) {
    	final ItemStack[] roundType = {ItemStack.EMPTY};
        getInventory(stack).ifPresent(inv -> roundType[0] = inv.getStackInSlot(SLOT_ROUND_UPGRADE));
        return roundType[0];
    }
    
    public int returnInternalAmmo(ItemStack stack)
    {
    	final AtomicInteger rounds = new AtomicInteger(0);
        getInventory(stack).ifPresent(inv -> rounds.set(inv.getStackInSlot(SLOT_ROUND_UPGRADE).getCount()));
        return rounds.get();
    }
    
    public void addInternalAmmo(ItemStack stack, ItemStack toAdd, int qtyToAdd)
    {
    	getInventory(stack).ifPresent(inventory -> {
        	int currentAmmo = getAmmoStack(stack).getCount();
    		
            if ((currentAmmo + qtyToAdd) <= getMaxAmmo(stack))
            {
            	if ((currentAmmo + qtyToAdd) < 126 * (getOverflow(stack) + 1))	//hack because max stack is 127
            		inventory.insertItem(SLOT_ROUNDS, toAdd.copyWithCount(qtyToAdd), false);
            	else
            	{
            		addOverflow(stack);
            		inventory.setStackInSlot(SLOT_ROUNDS, inventory.getStackInSlot(SLOT_ROUNDS).copyWithCount(0));
            	}
            }
            saveInventory(stack); // Save state after change
        });
    }
    
    public void setROFUpgrade(ItemStack stack, ItemStack toAdd)
    {
    	getInventory(stack).ifPresent(inventory -> {
    		inventory.insertItem(SLOT_ROF_UPGRADE, toAdd.copyWithCount(1), false);
            saveInventory(stack); // Save state after change
        });
    }
    
    public void setBayonetUpgrade(ItemStack stack, ItemStack toAdd)
    {
    	getInventory(stack).ifPresent(inventory -> {
    		inventory.insertItem(SLOT_BAYONET, toAdd.copyWithCount(1), false);
            saveInventory(stack); // Save state after change
        });
    }
    
    public ItemStack returnROFUpgrade(ItemStack stack)
    {
    	final ItemStack[] rofUpgrade = {ItemStack.EMPTY};
        getInventory(stack).ifPresent(inv -> rofUpgrade[0] = inv.getStackInSlot(SLOT_ROF_UPGRADE));
        return rofUpgrade[0];
    }
    
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
    	if (world instanceof ServerLevel)
    	{
    		if (hand == InteractionHand.MAIN_HAND)
    		{
		        ItemStack gun = player.getItemInHand(hand);
		        if (player.isCrouching())
		        {
		        	//attempt reload
		        	if (returnMagType() == Constants.internal_mag)
		        		handleFillInternalMag(gun, player);
		        	else if (returnMagType() == Constants.external_mag)
		        		handleMagazineInsertion(gun, player);
		        	return InteractionResultHolder.sidedSuccess(gun, world.isClientSide());
		        }
		        else
		        {
		        	if (canFire(gun, player))
		        	{
                        if (!player.isCreative())
		        		    fireRoundLogic(gun);
		        		BulletBaseEntity bulletEntity = new BulletBaseEntity(world, player, getBulletDamage(gun), bullet);
						bulletEntity.setItem(new ItemStack(bullet));
						//Up+Down
						//bulletEntity.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
						//bulletEntity.shootFromRotation(player, player.getXRot(), player.getYHeadRot(), 0F, getBulletSpeed(gun), 1.0F);
						bulletEntity.shootFromRotation(player, player.getXRot(), player.getYHeadRot(), 0.0F, getBulletSpeed(gun), getBulletSpread(gun));
						//bulletEntity.applyRandomSpread(returnBulletSpread(mag));
						world.addFreshEntity(bulletEntity);
						player.awardStat(Stats.ITEM_USED.get(asItem()));
						player.getCooldowns().addCooldown(asItem(), getRateOfFire(gun));
						giveBulletCasing(player);
						float minPitch = 0F;
					    float maxPitch = 1F;
					    float randPitch = minPitch + new Random().nextFloat() * (maxPitch - minPitch);
						player.playSound(getGunFireSound(), 0.1F, randPitch);
						return InteractionResultHolder.sidedSuccess(gun, world.isClientSide());
		        	}
		        }
    		}
    	}
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }
    
    public void giveBulletCasing(Player player)
	{
		Item casing = bullet.returnCasingItem();
		if (casing != null)
			player.getInventory().add(new ItemStack(casing));
	}
    
    protected void handleFillInternalMag(ItemStack gun, Player playerIn)
	{
		//Add ammo into internal fed firearms
		if ((getCurrentAmmo(gun) < getMaxAmmo(gun)) && (getMaxAmmo(gun) > 0))
		{
			int slotID = -1;
			ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
			for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) 
			{
				ItemStack itemstack1 = playerIn.getInventory().getItem(i);
				if ((itemstack1.getItem() == bullet) || (tagManager.getTag(bullet.getCompatBullet()).contains(itemstack1.getItem())))
				{
					slotID=i;
					break;
				}
			}
			if (slotID >= 0)
			{
				ItemStack ibullet = playerIn.getInventory().getItem(slotID);
				addInternalAmmo(gun, ibullet, 1);
				saveInventory(gun);
				ibullet.shrink(1);
				playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.bullet_loaded"), true);
				playerIn.getCooldowns().addCooldown(gun.getItem(), 8);
				playerIn.playSound(SoundEvents.DISPENSER_DISPENSE, Constants.modVolume, 0.75F);
			}
		}
	}
    
    protected void handleMagazineInsertion(ItemStack gun, Player playerIn) {
        getInventory(gun).ifPresent(inventory -> {
            if (inventory.getStackInSlot(SLOT_MAG).isEmpty()) {
                int slotID = -1;
                for (int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) {
                    ItemStack itemstack1 = playerIn.getInventory().getItem(i);
                    if ((itemstack1.getItem() instanceof NuMagazineItem mag) && (!(itemstack1.getItem() instanceof NuBulletBoxItem))) {
                        if (mag.getCurrentAmmoItem(itemstack1) == bullet && mag.getCurrentAmmo(itemstack1) > 0) {
                            slotID = i;
                            break;
                        }
                    }
                }
                if (slotID >= 0) {
                    ItemStack itemstack2 = playerIn.getInventory().getItem(slotID);
                    inventory.insertItem(SLOT_MAG, itemstack2.copy(), false);
                    playerIn.getInventory().removeItem(slotID, 1);
                    saveInventory(gun); // Save inventory state here
                    playerIn.getCooldowns().addCooldown(this.asItem(), getReloadTime(gun));
                    playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.mag_loaded"), true);
                }
            } else {
                playerIn.getInventory().add(inventory.getStackInSlot(SLOT_MAG));
                inventory.extractItem(SLOT_MAG, 1, false);
                saveInventory(gun); // Save inventory state here
                playerIn.getCooldowns().addCooldown(this.asItem(), getReloadTime(gun));
                playerIn.displayClientMessage(ModUtils.displayTranslation("thismeanswar.mag_unloaded"), true);
            }
        });
    }
    
    private SoundEvent getGunFireSound()
	{
		if (asItem() == TMWPistols.m1911)
			return TMWSounds.shot_1911;
		else if (asItem() == TMWCarbines.tmg_carbine)
			return TMWSounds.shot_tmg_carbine;
		else if (asItem() == TMWRifles.thunderclaw)
			return TMWSounds.shot_thunderclaw;
		else if (asItem() == TMWCarbines.mp40)
			return TMWSounds.shot_mp40;
		else if (asItem() == TMWRifles.k98)
			return TMWSounds.shot_k98;
		else
			return SoundEvents.GENERIC_EXPLODE;
	}
    
    @Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
    {
        if (getBayonetDamage(stack) > 0)
        {
        	entity.hurt(player.damageSources().playerAttack(player), (float) getBayonetDamage(stack));
        	return true;
        }
        else
        	return false;
    }
    
    protected boolean canFire(ItemStack stackIn, Player player)
	{
        if (!player.isCreative())
		    return getCurrentAmmo(stackIn) >= 1;
        else
            return true;
	}
    
    protected void fireRoundLogic(ItemStack stackin) {
        if (returnMagType() == Constants.internal_mag) {
            // Internal magazine logic
            getInventory(stackin).ifPresent(inventory -> {
                inventory.extractItem(SLOT_ROUNDS, 1, false);
                saveInventory(stackin);
            });
        } else if (returnMagType() == Constants.external_mag) {
            // External magazine logic
            getInventory(stackin).ifPresent(inventory -> {
                ItemStack magStack = inventory.getStackInSlot(SLOT_MAG);
                if (magStack.getItem() instanceof NuMagazineItem mag) {
                    mag.removeAmmoFromMag(magStack);
                    saveInventory(stackin);
                }
            });
        }
    }
    
    @Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		int currentAmmo = getCurrentAmmo(stack);
		String rofString = (getRateOfFire(stack) == Constants.fireRateAuto) ? "thismeanswar.firearm_rof_full" : "thismeanswar.firearm_rof_semi";
		int maxAmmo = getMaxAmmo(stack);
		//int bulletUpgrade = getBulletUpgrade(stack);
		//Show the current firearm capacity if its max ammo is greater than 0
		if (maxAmmo > 0)
			tooltip.add(ModUtils.displayString("Capacity: " + ModUtils.returnShortenedNumber(currentAmmo) + " / " + ModUtils.returnShortenedNumber(maxAmmo)));
		else if (maxAmmo == 0)
		{
			//Missing magazine, no ammo.
			if (returnMagType() == Constants.external_mag)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_no_mag_warning"));
			//Missing rounds, no ammo.
			if (returnMagType() == Constants.internal_mag)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_no_rounds_warning"));
		}
		//display magazine AND round on one line. useful for the externally fed guns.
		if (magazine != null)
		{
			MutableComponent magComp = ModUtils.displayTranslation(magazine.getDescriptionId());
			MutableComponent bulletComp = ModUtils.displayTranslation(bullet.getDescriptionId());
			if (!getMagazineStack(stack).isEmpty())
			{
				magComp = ModUtils.displayTranslation(getMagazineStack(stack).getDescriptionId());
				bulletComp = ModUtils.displayTranslation(getMagazine(stack).returnBulletItem().getDescriptionId());
			}
			
			MutableComponent merged = magComp.append(" (");
			merged = merged.append(bulletComp);
			merged = merged.append(")");
			tooltip.add(merged);
		}
		//Display just the round for internally fed guns
		else
			tooltip.add(ModUtils.displayTranslation(bullet.getDescriptionId()));
		if (!Screen.hasShiftDown())
			tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_more_info"));
		if (Screen.hasShiftDown())
		{
			tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_more_info_display"));
			String colorFormat = "";
			if (getBulletDamage(stack) < this.damage)
				colorFormat = "§4";
			else if (getBulletDamage(stack) > this.damage)
				colorFormat = "§2";
			tooltip.add(ModUtils.displayTranslation(rofString));
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setMinimumFractionDigits(1);
			formatter.setMaximumFractionDigits(2);
			MutableComponent bulletDmgString = ModUtils.displayTranslation("thismeanswar.firearm_bullet_dmg");
			bulletDmgString = bulletDmgString.append(colorFormat + formatter.format(getBulletDamage(stack)));
			tooltip.add(bulletDmgString);
			if (Screen.hasControlDown())
			{
				String colorFormat4 = "";
				if (getReloadTime(stack) > this.reloadTime)
					colorFormat4 = "§4";
				else if (getReloadTime(stack) < this.reloadTime)
					colorFormat4 = "§2";
				if (this.reloadTime > 0)
				{
					MutableComponent bulletSprdString = ModUtils.displayTranslation("thismeanswar.firearm_reload_time");
					bulletSprdString = bulletSprdString.append(colorFormat4 + formatter.format(getReloadTime(stack)));
					tooltip.add(bulletSprdString);
				}
				
				String colorFormat2 = "";
				if (getBulletSpread(stack) > this.bulletSpread)
					colorFormat2 = "§4";
				else if (getBulletSpread(stack) < this.bulletSpread)
					colorFormat2 = "§2";
				if (this.bulletSpread > 0)
				{
					MutableComponent bulletSprdString = ModUtils.displayTranslation("thismeanswar.firearm_bullet_sprd");
					bulletSprdString = bulletSprdString.append(colorFormat2 + formatter.format(getBulletSpread(stack)));
					tooltip.add(bulletSprdString);
				}
				
				String colorFormat3 = "";
				if (getBulletSpeed(stack) < this.bulletSpeed)
					colorFormat3 = "§4";
				else if (getBulletSpeed(stack) > this.bulletSpeed)
					colorFormat3 = "§2";
				MutableComponent bulletSpdString = ModUtils.displayTranslation("thismeanswar.firearm_bullet_spd");
				bulletSpdString = bulletSpdString.append(colorFormat3 + formatter.format(getBulletSpeed(stack)));
				tooltip.add(bulletSpdString);
			}
			//display bayonet damage
			if (this.getBayonetDamage(stack) > 0)
			{
				MutableComponent bayonetString = ModUtils.displayTranslation("thismeanswar.firearm_melee_dmg");
				bayonetString = bayonetString.append(Double.toString(getBayonetDamage(stack)));
				tooltip.add(bayonetString);
			}
			//display bullet damage upgrade type
			if (bulletUpgrade == Constants.bulletUpgradeAP)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_ap"));
			if (bulletUpgrade == Constants.bulletUpgradeFire)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_fire"));
			if (bulletUpgrade == Constants.bulletUpgradeTracer)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_tracer"));
			if (bulletUpgrade == Constants.bulletUpgradeInert)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_inert"));
		}
			
	}
    
    public int getBarColor(ItemStack stack) 
    {
       float stackMaxDamage = getMaxAmmo(stack);
       float f = Math.max(0.0F, (stackMaxDamage - (float) (stackMaxDamage - getCurrentAmmo(stack))) / stackMaxDamage);
       return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }
    
    @Override
    public int getBarWidth(ItemStack stack) 
	{
		return Math.round(13.0F - (getMaxAmmo(stack) - getCurrentAmmo(stack)) * 13.0F / getMaxAmmo(stack));
	}
    
    @Override
	public boolean isBarVisible(ItemStack stack)
	{
    	if (getMaxAmmo(stack) > 0)
    		return true;
    	else
    		return false;
	}
    
    public float getBulletSpeed(ItemStack gun)
    {
    	float returned = this.bulletSpeed;
    	return returned;
    }
    
    public float getBulletSpread(ItemStack gun)
    {
    	float returned = this.bulletSpread;
    	//ROF modifiers
    	if (!returnROFUpgrade(gun).isEmpty())
    	{
    		if (returnROFUpgrade(gun).getItem() == TMWItems.gun_rof_upgrade)
    			returned *= 1.1F;
    		else if (returnROFUpgrade(gun).getItem() == TMWItems.gun_rof_downgrade)
    			returned *= 0.8F;
    	}
    	return returned;
    }
    public float getBulletDamage(ItemStack stack)
	{
    	float returned = this.damage;
    	//ROF modifiers
    	if (!returnROFUpgrade(stack).isEmpty())
    	{
    		if (returnROFUpgrade(stack).getItem() == TMWItems.gun_rof_upgrade)
    			returned *= 0.5F;
    		else if (returnROFUpgrade(stack).getItem() == TMWItems.gun_rof_downgrade)
    			returned *= 1.25F;
    	}
    	return returned;
	}
    
    public int getRateOfFire(ItemStack stack)
    {
    	int returned = this.shotTime;
    	if (!returnROFUpgrade(stack).isEmpty())
    	{
    		if (returnROFUpgrade(stack).getItem() == TMWItems.gun_rof_upgrade)
    			returned = Constants.fireRateAuto;
    		else if (returnROFUpgrade(stack).getItem() == TMWItems.gun_rof_downgrade)
    			returned = Constants.fireRateSemi;
    	}
    	return returned;
    }
    
    @Override
	public String getDescriptionId(ItemStack stack) 
	{
		String returned = "";
		if (returnROFUpgrade(stack).getItem() == TMWItems.gun_rof_upgrade)
		{
			returned = ModUtils.displayTranslation("thismeanswar.gun.fullauto").getString();
			returned = returned.concat(" ");
		}
		if (returnROFUpgrade(stack).getItem() == TMWItems.gun_rof_downgrade)
		{
			returned = ModUtils.displayTranslation("thismeanswar.gun.semiauto").getString();
			returned = returned.concat(" ");
		}
		returned = returned.concat(ModUtils.displayTranslation(this.getDescriptionId()).getString());
		if (!returnBayonetStack(stack).isEmpty())
		{
			returned = returned.concat(" w/ ");
			returned = returned.concat(ModUtils.displayTranslation(returnBayonetStack(stack).getDescriptionId()).getString());
		}
		
		return returned;
	   
	}
    
    protected void doZoom(ItemStack stack, Player player)
    {
    	ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
    	if (tagManager.getTag(TMWTags.snipers).contains(stack.getItem()))
    	{
			if (Screen.hasAltDown())
				player.getPersistentData().putFloat("fovModifier", 0.75F);
			else
				player.getPersistentData().remove("fovModifier");
    	}
    }
    
    public int getReloadTime(ItemStack stack)
    {
    	int returned = reloadTime;
    	if (returnMagType() == Constants.external_mag)
    	{
    		if (getMagazineStack(stack) != ItemStack.EMPTY)
    			if (getMagazineStack(stack).getItem() != magazine.asItem())
    				returned *= 1.3;
    	}
    	return returned;
    }
}
