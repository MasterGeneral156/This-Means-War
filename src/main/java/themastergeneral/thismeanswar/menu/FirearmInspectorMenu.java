package themastergeneral.thismeanswar.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import themastergeneral.thismeanswar.block.entity.BlockEntityGunInspector;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;
import themastergeneral.thismeanswar.registry.TMWMenuRegistry;

public class FirearmInspectorMenu extends AbstractContainerMenu {

	private final BlockEntityGunInspector blockEntity;
	
	public FirearmInspectorMenu(int id, Inventory playerInventory, FriendlyByteBuf extraData) 
	{
		this(id, playerInventory, (BlockEntityGunInspector) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }
	
	public FirearmInspectorMenu(int id, Inventory playerInventory, BlockEntityGunInspector blockEntity) {
        super(TMWMenuRegistry.GUN_INSPECTOR_MENU.get(), id);
        this.blockEntity = blockEntity;
        
        // Add custom block entity slots
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 8, 17));
        });
        
        // Add player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
	}
	
	public ItemStack getSlotStack()
	{
		return blockEntity.itemHandler.getStackInSlot(0);
	}
    
    @Override
    public boolean stillValid(Player player) {
        return blockEntity.stillValid(player);
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
    	ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) 
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int containerSlotCount = 4; // Number of slots in the block entity

            if (index < containerSlotCount) {
                // If the slot is in the block entity, move item to the player inventory
                if (!this.moveItemStackTo(itemstack1, containerSlotCount, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // If the slot is in the player inventory, move item to the block entity
                if (!this.moveItemStackTo(itemstack1, 0, containerSlotCount, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
    
    public boolean isGunItem(ItemStack stack)
    {
    	return stack.getItem() instanceof AbstractGunItem;
    }
    
    public boolean isMagItem(ItemStack stack)
    {
    	return stack.getItem() instanceof AbstractMagazineItem;
    }
    
    public int getMagAmmo(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractMagazineItem mag)
    		return mag.getCurrentAmmo(stack);
    	else
    		return -1;
    }
    
    public Item getMagRoundItem(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractMagazineItem mag)
    		return mag.returnBulletItem().asItem();
    	else
    		return Items.AIR;
    }
    
    public int getMagMaxAmmo(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractMagazineItem mag)
    		return mag.getMaxAmmo(stack);
    	else
    		return -1;
    }
    
    public int getMagCapUpgrades(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractMagazineItem mag)
    		return mag.getCapacityUpgrades(stack);
    	else
    		return -1;
    }
    
    public float getGunDamage(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.returnBulletDamage(stack);
    	else
    		return -1F;
    }
    
    public float getGunSpread(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.returnBulletSpread(stack);
    	else
    		return -1F;
    }
    
    public float getGunSpeed(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.returnBulletSpeed(stack);
    	else
    		return -1F;
    }
    
    public int getGunBulletType(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.getBulletUpgrade(stack);
    	else
    		return -1;
    }
    
    public int getGunMagType(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.getMagType(stack);
    	else
    		return -1;
    }
    
    public double getGunBayonetLevel(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.getBayonetLevel(stack);
    	else
    		return -1D;
    }
    
    public int getGunMaxAmmo(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.getMaxAmmo(stack);
    	else
    		return -1;
    }
    
    public int getGunROF(ItemStack stack)
    {
    	if (stack.getItem() instanceof AbstractGunItem gun)
    		return gun.getRateOfFire(stack);
    	else
    		return -1;
    }
}
