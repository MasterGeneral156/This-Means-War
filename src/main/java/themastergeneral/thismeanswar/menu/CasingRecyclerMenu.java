package themastergeneral.thismeanswar.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.entity.BlockEntityCasingRecycler;
import themastergeneral.thismeanswar.registry.TMWMenuRegistry;

public class CasingRecyclerMenu extends AbstractContainerMenu {

	private final BlockEntityCasingRecycler blockEntity;
	private final ContainerData data;
	
	public CasingRecyclerMenu(int id, Inventory playerInventory, FriendlyByteBuf extraData) 
	{
		this(id, playerInventory, (BlockEntityCasingRecycler) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
	
	public CasingRecyclerMenu(int id, Inventory playerInventory, BlockEntityCasingRecycler blockEntity, ContainerData data) {
        super(TMWMenuRegistry.CASING_RECYCLER_MENU.get(), id);
        this.blockEntity = blockEntity;
        
        // Add custom block entity slots
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 16, 53));
            this.addSlot(new SlotItemHandler(handler, 1, 16, 17));
            this.addSlot(new SlotItemHandler(handler, 2, 46, 35));
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
        TMWMain.debugLogger(data);
	}
    
    @Override
    public boolean stillValid(Player player) {
        return blockEntity.stillValid(player);
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
    
    public int getBurnTime()
	{
		return this.data.get(0);
	}
	
	/*public int getTotalBurnTime()
	{
		return this.blockEntity.getTotalBurnTime();
	}*/
	
	public int getProcessTime()
	{
		return this.data.get(1);
	}
	
	/*public int getMaxProcessTime()
	{
		return this.blockEntity.getMaxProcessTime();
	}
	
	public int getBurnProgress() 
	{
	      int i = this.getTotalBurnTime();
	      int j = this.getBurnTime();
	      return (j != 0 && i != 0) ? i * 24 / j : 0;
   }

   public int getLitProgress() 
   {
      int i = this.getMaxProcessTime();
      if (i == 0) {
         i = 200;
      }

      return this.getProcessTime() * 13 / i;
   }

	public boolean isLit() {
		return this.blockEntity.isLit();
	}*/
}
