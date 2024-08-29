package themastergeneral.thismeanswar.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import themastergeneral.thismeanswar.block.entity.BlockEntityTipMolder;
import themastergeneral.thismeanswar.registry.TMWMenuRegistry;

public class MolderMenu extends AbstractContainerMenu {

	private final BlockEntityTipMolder blockEntity;
	private final ContainerData data;
	
	public MolderMenu(int id, Inventory playerInventory, FriendlyByteBuf extraData) 
	{
		this(id, playerInventory, (BlockEntityTipMolder) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(5));
    }
	
	public MolderMenu(int id, Inventory playerInventory, BlockEntityTipMolder blockEntity, ContainerData data) {
        super(TMWMenuRegistry.MOLDER_MENU.get(), id);
        this.blockEntity = blockEntity;
        
        // Add custom block entity slots
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
        	this.addSlot(new SlotItemHandler(handler, 0, 17, 53));
            this.addSlot(new SlotItemHandler(handler, 1, 8, 17));
            this.addSlot(new SlotItemHandler(handler, 2, 46, 35));
            this.addSlot(new SlotItemHandler(handler, 3, 26, 17));
        });
        
        this.data = data;
        
        // Add player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        
        addDataSlots(data);
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
    
    public int getBurnTime()
	{
		return this.data.get(0);
	}

    public int getTotalBurnTime()
    {
        return this.data.get(4);
    }
	
	public int getProcessTime()
	{
		return this.data.get(1);
	}
	
	public int getMaxProcessTime()
	{
		return this.data.get(2);
	}
	
	public int getErrorCode()
	{
		return this.data.get(3);
	}
	
	public int getProcessPercent()
	{
		int returned = (getProcessTime() / getMaxProcessTime()) * 100;
		return returned;
	}
}
