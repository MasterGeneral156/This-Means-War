package themastergeneral.thismeanswar.items;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nullable;

import mastergeneral156.chasethedragon.radial.RadialClientEvents;
import mastergeneral156.chasethedragon.radial.RadialMenuOption;
import mastergeneral156.chasethedragon.radial.api.CTDRadialAPI;
import net.minecraft.world.item.Items;
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
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.entity.bullet.*;
import themastergeneral.thismeanswar.items.define.TMWCarbines;
import themastergeneral.thismeanswar.items.define.TMWPistols;
import themastergeneral.thismeanswar.items.define.TMWRifles;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunBayonetItem;
import themastergeneral.thismeanswar.network.packet.GunAddBulletUpgradePacket;
import themastergeneral.thismeanswar.network.packet.GunAmmoChangePacket;
import themastergeneral.thismeanswar.network.packet.GunBayonetUpdatePacket;
import themastergeneral.thismeanswar.network.packet.GunItemMagPacket;
import themastergeneral.thismeanswar.registry.TMWNetworkManager;

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
        List<RadialMenuOption> optionList = new ArrayList<>();
        if (counter == 20)
        {
            doSetupGun(stack);
            Player player = (Player) entityIn;
            doZoom(player.getItemInHand(InteractionHand.MAIN_HAND), player);
            counter = 0;
        }
        if (entityIn instanceof Player player) {
            //External ammo
            if (this.magType == Constants.external_mag) {
                if (this.getMaxAmmo(stack) == 0) {
                    optionList.add(new RadialMenuOption(
                            () -> {
                                // Send a packet to the server to remove ammo
                                TMWNetworkManager.INSTANCE.sendToServer(new GunItemMagPacket(itemSlot));
                            },
                            Constants.addMagIcon,
                            ModUtils.displayTranslation("radial.thismeanswar.add_mag")
                    ));
                } else {
                    optionList.add(new RadialMenuOption(
                            () -> {
                                // Send a packet to the server to remove ammo
                                TMWNetworkManager.INSTANCE.sendToServer(new GunItemMagPacket(itemSlot));
                            },
                            Constants.removeMagIcon,
                            ModUtils.displayTranslation("radial.thismeanswar.remove_mag")
                    ));
                }
            }
            //Internal ammo
            if (this.magType == Constants.internal_mag) {
                if (this.getCurrentAmmo(stack) < this.getMaxAmmo(stack))
                {
                    optionList.add(new RadialMenuOption(
                            () -> {
                                // Send a packet to the server to remove ammo
                                TMWNetworkManager.INSTANCE.sendToServer(new GunAmmoChangePacket(itemSlot, 1));
                            },
                            Constants.addGunAmmoIcon,
                            ModUtils.displayTranslation("radial.thismeanswar.add_gun_round")
                    ));
                }
                if (this.getCurrentAmmo(stack) > 0)
                {
                    optionList.add(new RadialMenuOption(
                            () -> {
                                // Send a packet to the server to remove ammo
                                TMWNetworkManager.INSTANCE.sendToServer(new GunAmmoChangePacket(itemSlot, -1));
                            },
                            Constants.removeGunAmmoIcon,
                            ModUtils.displayTranslation("radial.thismeanswar.remove_gun_round")
                    ));
                }
            }
            if (!returnBayonetStack(stack).isEmpty())
            {
                optionList.add(new RadialMenuOption(
                        () -> {
                            // Send a packet to the server to remove ammo
                            TMWNetworkManager.INSTANCE.sendToServer(new GunBayonetUpdatePacket(stack));
                        },
                        TMWUtils.getIconByStack(returnBayonetStack(stack)),
                        ModUtils.displayTranslation("radial.thismeanswar.remove_bayonet")
                ));
            }

            if (!getRoundUpgrade(stack).isEmpty())
            {
                optionList.add(new RadialMenuOption(
                        () -> {
                            // Send a packet to the server to remove ammo
                            TMWNetworkManager.INSTANCE.sendToServer(new GunAddBulletUpgradePacket(itemSlot));
                        },
                        TMWUtils.getIconByStack(getRoundUpgrade(stack)),
                        ModUtils.displayTranslation("radial.thismeanswar.remove_round_upgrade")
                ));
            }
            // Client-side: Open the radial menu screen
            if (!player.getCooldowns().isOnCooldown(this)) {
                if (worldIn.isClientSide && isSelected) {
                    handleClientRadialMenu(optionList);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void handleClientRadialMenu(List<RadialMenuOption> optionList) {
        if (RadialClientEvents.openRadial.isDown()) {
            //respect the API
            CTDRadialAPI.openRadialMenu(optionList);
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
                    stack.setTag(tag);
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
        if (returnBayonetStack(stack).getItem() instanceof UpgradeGunBayonetItem bayonet) {
            returned[0] = bayonet.returnBayonetLevel();
        }
    	return returned[0];
    }
    
    public ItemStack getRoundUpgrade(ItemStack stack) {
        final ItemStack[] currentAmmo = {ItemStack.EMPTY};
        getInventory(stack).ifPresent(inv -> currentAmmo[0] = inv.getStackInSlot(SLOT_ROUND_UPGRADE));
        return currentAmmo[0];
    }

    public void setRoundUpgrade(ItemStack stack, ItemStack toAdd) {
        getInventory(stack).ifPresent(inventory -> {
            TMWMain.debugLogger(toAdd + " was applied to " + stack);
            inventory.insertItem(SLOT_ROUND_UPGRADE, toAdd.copyWithCount(1), false);
            saveInventory(stack); // Save state after change
        });
    }
    
    public int returnInternalAmmo(ItemStack stack)
    {
    	final AtomicInteger rounds = new AtomicInteger(0);
        getInventory(stack).ifPresent(inv -> rounds.set(inv.getStackInSlot(SLOT_ROUNDS).getCount()));
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
                if (canFire(gun, player))
                {
                    if (!player.isCreative())
                        fireRoundLogic(gun);
                    Entity bentity = this.getRoundEntity(gun, player);
                    if (bentity instanceof BulletBaseEntity bulletEntity) {
                        TMWMain.debugLogger(bulletEntity);
                        bulletEntity.setItem(new ItemStack(bullet));
                        //Up+Down
                        bulletEntity.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
                        bulletEntity.shootFromRotation(player, player.getXRot(), player.getYHeadRot(), 0.0F, getBulletSpeed(gun), getBulletSpread(gun));
                        world.addFreshEntity(bulletEntity);
                        player.awardStat(Stats.ITEM_USED.get(asItem()));
                        player.getCooldowns().addCooldown(asItem(), getRateOfFire(gun));
                        giveBulletCasing(player);
                        float minPitch = 0F;
                        float maxPitch = 1F;
                        float randPitch = minPitch + new Random().nextFloat() * (maxPitch - minPitch);
                        player.playSound(getGunFireSound(), Constants.modVolume, randPitch);
                    }
                    return InteractionResultHolder.sidedSuccess(gun, world.isClientSide());
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
    
    public void handleFillInternalMag(ItemStack gun, Player playerIn)
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

    public void removeInternalAmmo(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.extractItem(this.SLOT_ROUNDS, 1, false);
            saveInventory(stack);
        });
    }

    public void removeBayonet(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.extractItem(this.SLOT_BAYONET, 1, false);
            saveInventory(stack);
        });
    }

    public void removeRoundUpgade(ItemStack stack) {
        getInventory(stack).ifPresent(inventory -> {
            inventory.extractItem(this.SLOT_ROUND_UPGRADE, 1, false);
            saveInventory(stack);
        });
    }

    public void playerRemoveBayonet(ItemStack stack, Player player)
    {
        if (this.returnBayonetStack(stack) != ItemStack.EMPTY)
        {
            ItemStack returned = this.returnBayonetStack(stack).copy();
            this.removeBayonet(stack);
            player.getInventory().add(returned);
        }
    }

    public void playerRemoveRoundUpgrade( Player player, ItemStack stack)
    {
        if (getRoundUpgrade(stack) != ItemStack.EMPTY)
        {
            ItemStack returned = getRoundUpgrade(stack).copy();
            removeRoundUpgade(stack);
            player.getInventory().add(returned);
        }
    }

    public void handleRemoveInternalMag(ItemStack gun, Player player)
    {
        if (getCurrentAmmo(gun) > 0)
        {
            removeInternalAmmo(gun);
            player.getInventory().add(new ItemStack(bullet, 1));
            player.getCooldowns().addCooldown(this, 8);
            player.awardStat(Stats.ITEM_USED.get(this));
            player.playSound(SoundEvents.DISPENSER_FAIL, Constants.modVolume, 0.25F);
        }
    }
    
    public void handleMagazineInsertion(ItemStack gun, Player playerIn) {
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
        else if (asItem() == TMWCarbines.sten)
            return TMWSounds.shot_sten;
		else
			return SoundEvents.GENERIC_EXPLODE;
	}
    
    @Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
    {
        if (getBayonetDamage(stack) > 0D)
        {
        	entity.hurt(player.damageSources().playerAttack(player), (float) getBayonetDamage(stack));
        	return true;
        }
        else
        	return false;
    }
    
    protected boolean canFire(ItemStack stackIn, Player player)
	{
        if (player.isCreative())
            return true;
        else {
            return getCurrentAmmo(stackIn) >= 1;
        }
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
			if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_ap)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_ap"));
            else if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_fire)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_fire"));
            else if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_tracer)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_tracer"));
            else if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_inert)
				tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_inert"));
            else if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_medical)
                tooltip.add(ModUtils.displayTranslation("thismeanswar.firearm_upgrade_medical"));
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
        Item upgradeItem = getRoundUpgrade(gun).getItem();
        if (upgradeItem == TMWItems.bullet_upgrade_ap)
            returned *= 1.21;
    	return returned;
    }
    
    public float getBulletSpread(ItemStack gun)
    {
    	float returned = this.bulletSpread;
    	//ROF modifiers
        Item upgradeItem = getRoundUpgrade(gun).getItem();
    	if (!returnROFUpgrade(gun).isEmpty())
    	{
    		if (returnROFUpgrade(gun).getItem() == TMWItems.gun_rof_upgrade)
    			returned *= 1.1F;
    		else if (returnROFUpgrade(gun).getItem() == TMWItems.gun_rof_downgrade)
    			returned *= 0.8F;
    	}
        if (upgradeItem == TMWItems.bullet_upgrade_ap)
            returned *= 1.11;
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
        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_ap)
            returned *= 0.82F;
        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_inert)
            returned *= 0.05F;
        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_fire)
            returned *= 0.65F;
        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_medical)
            returned *= 0.5F;
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

        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_ap)
        {
            returned = ModUtils.displayTranslation("thismeanswar.gun.ap").getString();
            returned = returned.concat(" ");
        }

        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_inert)
        {
            returned = ModUtils.displayTranslation("thismeanswar.gun.inert").getString();
            returned = returned.concat(" ");
        }

        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_fire)
        {
            returned = ModUtils.displayTranslation("thismeanswar.gun.fire").getString();
            returned = returned.concat(" ");
        }

        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_tracer)
        {
            returned = ModUtils.displayTranslation("thismeanswar.gun.tracer").getString();
            returned = returned.concat(" ");
        }

        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_medical)
        {
            returned = ModUtils.displayTranslation("thismeanswar.gun.medical").getString();
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
        if (getRoundUpgrade(stack).getItem() == TMWItems.bullet_upgrade_fire)
            returned *= 1.65;
    	return returned;
    }

    public Entity getRoundEntity(ItemStack stack, Player player)
    {
        Item roundUpgrade = getRoundUpgrade(stack).getItem();
        if (roundUpgrade == TMWItems.bullet_upgrade_ap)
            return new BulletAPEntity(player.getCommandSenderWorld(), player, getBulletDamage(stack), bullet);
        else if (roundUpgrade == TMWItems.bullet_upgrade_fire)
            return new BulletFireEntity(player.getCommandSenderWorld(), player, getBulletDamage(stack), bullet);
        else if (roundUpgrade == TMWItems.bullet_upgrade_tracer)
            return new BulletTracerEntity(player.getCommandSenderWorld(), player, getBulletDamage(stack), bullet);
        else if (roundUpgrade == TMWItems.bullet_upgrade_medical)
            return new BulletMedicalEntity(player.getCommandSenderWorld(), player, getBulletDamage(stack), bullet);
        else
            return new BulletBaseEntity(player.getCommandSenderWorld(), player, getBulletDamage(stack), bullet);
    }
}
