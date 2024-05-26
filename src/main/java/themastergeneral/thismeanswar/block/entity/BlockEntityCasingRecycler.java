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
import themastergeneral.thismeanswar.menu.CasingRecyclerMenu;
import themastergeneral.thismeanswar.recipe.CrusherRecipe;
import themastergeneral.thismeanswar.recipe.RecyclerRecipe;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

public class BlockEntityCasingRecycler extends BlockEntity implements MenuProvider, BlockEntityTicker<BlockEntityCasingRecycler>
{
    
	private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    
    private int burnTime;
    private int burnTimeTotal;
    private int processTime;
    private int maxProcessTime;
    
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> BlockEntityCasingRecycler.this.burnTime;
                case 1 -> BlockEntityCasingRecycler.this.processTime;
                case 2 -> BlockEntityCasingRecycler.this.maxProcessTime;
                default -> throw new UnsupportedOperationException("Unexpected value: " + pIndex);
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> BlockEntityCasingRecycler.this.burnTime = pValue;
                case 1 -> BlockEntityCasingRecycler.this.processTime = pValue;
                case 2 -> BlockEntityCasingRecycler.this.maxProcessTime = pValue;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
    
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
        
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack)
        {
        	if (slot == OUTPUT_SLOT)
        		return false;
        	else if (slot == INPUT_SLOT)
        		return true;
        	else 
        	{
        		ItemStack itemstack = this.getStackInSlot(FUEL_SLOT);
        		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        	}
        }
    };
    
    private final LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> itemHandler);
    
	public BlockEntityCasingRecycler(BlockPos pos, BlockState state) {
		super(TMWBlockEntityRegistry.casing_recycler.get(), pos, state);
		this.maxProcessTime = 200;
	}
	
	public BlockEntityCasingRecycler(BlockPos pos, BlockState state, int processTime) {
		super(TMWBlockEntityRegistry.casing_recycler.get(), pos, state);
		this.maxProcessTime = processTime;
	}
	
	public boolean canPlaceItem(int slot, ItemStack stack) 
	{
		if (slot == OUTPUT_SLOT)
	         return false;
        else if (slot == INPUT_SLOT)
	         return true;
        else {
	         ItemStack itemstack = this.itemHandler.getStackInSlot(FUEL_SLOT);
	         return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
         }
	}
	
	
	@Override
	public void tick(Level level, BlockPos pos, BlockState state, BlockEntityCasingRecycler blockEntity) {
		boolean isBurning = blockEntity.burnTime > 0;
        if (isBurning)
            blockEntity.burnTime--;
        
        ItemStack fuelStack = blockEntity.itemHandler.getStackInSlot(FUEL_SLOT);
        ItemStack inputStack = blockEntity.itemHandler.getStackInSlot(INPUT_SLOT);
        ItemStack outputStack = blockEntity.itemHandler.getStackInSlot(OUTPUT_SLOT);
        Optional<RecyclerRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(TMWRecipeTypeRegistration.RECYCLER_TYPE.get(), new SimpleContainer(inputStack), level);
        if (outputStack.getCount() < itemHandler.getSlotLimit(OUTPUT_SLOT))
        {
        	if (recipe.isPresent())
        	{
        		ItemStack resultStack = recipe.get().getResultItem(level.registryAccess()).copy();
        		if (!isBurning && !fuelStack.isEmpty() && ((outputStack.getItem() == resultStack.getItem() || (outputStack.getItem() == ItemStack.EMPTY.getItem())))) 
    	        {
        			blockEntity.burnTime = ForgeHooks.getBurnTime(fuelStack, null);
    	            blockEntity.burnTimeTotal = blockEntity.burnTime;
    	            if (blockEntity.burnTime > 0) 
    	                fuelStack.shrink(1);
    	        }
        		if (isBurning && ((outputStack.getItem() == resultStack.getItem() || (outputStack.getItem() == Items.AIR))))
    	        {
        			blockEntity.processTime++;
            		if (blockEntity.processTime == blockEntity.maxProcessTime)
                	{
            			 if (outputStack.isEmpty()) 
            			 {
            				 blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, resultStack);  // Example output item
            			 } 
            			 else if (outputStack.getItem() == resultStack.getItem()) 
            			 {
            				 outputStack.grow(resultStack.getCount());
            			 }
            			 inputStack.shrink(1);
            			 processTime = 0;
                	}
    	        }
        	}
        }
        
        if ((inputStack.isEmpty() || !recipe.isPresent()) && processTime > 0)
        	processTime = 0;
        
        boolean wasLit = state.getValue(BlockStateProperties.LIT);
        boolean shouldBeLit = blockEntity.burnTime > 0;
        if (wasLit != shouldBeLit) 
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, shouldBeLit), 3);
        this.level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
        setChanged();
	}

	@Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.burnTime = tag.getInt("BurnTime");
        this.burnTimeTotal = tag.getInt("BurnTimeTotal");
        this.processTime = tag.getInt("ProcessTime");
        itemHandler.deserializeNBT(tag.getCompound("Inventory"));
    }
	
	@Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("BurnTime", this.burnTime);
        tag.putInt("BurnTimeTotal", this.burnTimeTotal);
        tag.putInt("ProcessTime", this.processTime);
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
		return new CasingRecyclerMenu(number, inv, this, this.containerData);
	}

	@Override
	public Component getDisplayName() {
		return ModUtils.displayTranslation("thismeanswar.container.casing_recycler");
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
}
