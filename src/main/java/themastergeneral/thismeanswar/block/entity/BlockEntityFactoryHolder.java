package themastergeneral.thismeanswar.block.entity;

import java.util.Optional;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import themastergeneral.thismeanswar.menu.FactoryHolderMenu;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockEntityFactoryHolder extends BlockEntity implements MenuProvider
{
    
	private static final int INPUT_SLOT = 0;
    
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
        
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack)
        {
        	return true;
        }
    };
    
    private final LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> itemHandler);
	
	public BlockEntityFactoryHolder(BlockPos pos, BlockState state) {
		super(TMWBlockEntityRegistry.factory_holder.get(), pos, state);
	}
	

	@Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("Inventory"));
    }
	
	@Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", itemHandler.serializeNBT());
    }
	
	@Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction direction) 
	{
        if (capability == ForgeCapabilities.ITEM_HANDLER)
            return handler.cast();
        return super.getCapability(capability, direction);
    }
	
	@Override
    public void setRemoved() 
	{
        super.setRemoved();
        handler.invalidate();
    }

	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public AbstractContainerMenu createMenu(int number, Inventory inv, Player player) {
		return new FactoryHolderMenu(number, inv, this);
	}

	@Override
	public Component getDisplayName() {
		return ModUtils.displayTranslation("thismeanswar.container.factoryholder");
	}
    
    @Override
    public @NotNull CompoundTag getUpdateTag() 
    {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }
    
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
    	return ClientboundBlockEntityDataPacket.create(this);
    }
    
    public ItemStack getHolderStack()
    {
    	return this.itemHandler.getStackInSlot(INPUT_SLOT);
    }
}
