package themastergeneral.thismeanswar.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import themastergeneral.thismeanswar.block.entity.BlockEntityFactoryHolder;
import themastergeneral.thismeanswar.registry.TMWMenuRegistry;

public class FactoryHolderMenu extends AbstractContainerMenu {

	private final BlockEntityFactoryHolder blockEntity;
	
	public FactoryHolderMenu(int id, Inventory playerInventory, FriendlyByteBuf extraData) 
	{
		this(id, playerInventory, (BlockEntityFactoryHolder) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }
	
	public FactoryHolderMenu(int id, Inventory playerInventory, BlockEntityFactoryHolder blockEntity) {
        super(TMWMenuRegistry.FACTORY_HOLDER_MENU.get(), id);
        this.blockEntity = blockEntity;
        
        // Add custom block entity slots
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 46, 35));
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
    
    @Override
    public boolean stillValid(Player player) {
        return blockEntity.stillValid(player);
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
