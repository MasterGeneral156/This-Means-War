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
import net.minecraft.world.item.Item;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;
import themastergeneral.thismeanswar.menu.AutomatedLoaderMenu;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockEntityAutomatedLoader extends BlockEntity implements MenuProvider, BlockEntityTicker<BlockEntityAutomatedLoader>
{
    
	private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int EXTRA_SLOT = 3;
    private static final int OUTPUT_SLOT = 2;
    
    private int burnTime;
    private int burnTimeTotal;
    private int processTime;
    private int maxProcessTime;
    
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> BlockEntityAutomatedLoader.this.burnTime;
                case 1 -> BlockEntityAutomatedLoader.this.processTime;
                case 2 -> BlockEntityAutomatedLoader.this.maxProcessTime;
                default -> throw new UnsupportedOperationException("Unexpected value: " + pIndex);
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> BlockEntityAutomatedLoader.this.burnTime = pValue;
                case 1 -> BlockEntityAutomatedLoader.this.processTime = pValue;
                case 2 -> BlockEntityAutomatedLoader.this.maxProcessTime = pValue;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
    
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
        
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack)
        {
        	if (slot == OUTPUT_SLOT)
        		return false;
        	else if (slot == INPUT_SLOT || slot == EXTRA_SLOT)
        		return true;
        	else 
        	{
        		ItemStack itemstack = this.getStackInSlot(FUEL_SLOT);
        		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        	}
        }
    };
    
    private final LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> itemHandler);
    
	public BlockEntityAutomatedLoader(BlockPos pos, BlockState state) {
		super(TMWBlockEntityRegistry.automated_loader.get(), pos, state);
		this.maxProcessTime = 200;
	}
	
	public BlockEntityAutomatedLoader(BlockPos pos, BlockState state, int processTime) {
		super(TMWBlockEntityRegistry.automated_loader.get(), pos, state);
		this.maxProcessTime = processTime;
	}
	
	public boolean canPlaceItem(int slot, ItemStack stack) 
	{
		return itemHandler.isItemValid(slot, stack);
	}
	
	@Override
	public void tick(Level level, BlockPos pos, BlockState state, BlockEntityAutomatedLoader blockEntity) {
		boolean isBurning = blockEntity.burnTime > 0;
        if (isBurning)
            blockEntity.burnTime--;
        
        ItemStack fuelStack = blockEntity.itemHandler.getStackInSlot(FUEL_SLOT);
        ItemStack inputStack = blockEntity.itemHandler.getStackInSlot(INPUT_SLOT);
        ItemStack inputStack2 = blockEntity.itemHandler.getStackInSlot(EXTRA_SLOT);
        ItemStack outputStack = blockEntity.itemHandler.getStackInSlot(OUTPUT_SLOT);
        
        if (outputStack.isEmpty())
        {
        	ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
	        if (inputStack.getItem() instanceof NuMagazineItem mag)
	        {
	        	//Ammo in mag is less than its max
	        	if (mag.getCurrentAmmo(inputStack) < mag.getMaxAmmo(inputStack))
	        	{
	        		//Second slot is valid bullet for mag
	        		if (mag.returnBulletItem() == inputStack2.getItem())
	        		{
		        		if (!isBurning && !fuelStack.isEmpty())
		        		{
		        			blockEntity.burnTime = ForgeHooks.getBurnTime(fuelStack, null);
		    	            blockEntity.burnTimeTotal = blockEntity.burnTime;
		    	            if (blockEntity.burnTime > 0) 
		    	                fuelStack.shrink(1);
		        		}
		        		blockEntity.processTime++;
		        		if (blockEntity.processTime >= returnProcessTime(blockEntity))
		        		{
		        			mag.addAmmoToMag(inputStack, 1);
		        			if (mag.getCurrentAmmo(inputStack) == mag.getMaxAmmo(inputStack))
		        			{
		        				blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, inputStack.copy());
		        				inputStack.shrink(1);
		        			}
		        			else
		        				blockEntity.itemHandler.setStackInSlot(INPUT_SLOT, inputStack);
		        			inputStack2.shrink(1);
		        			processTime = 0;
		        		}
	        		}
	        	}
	        	else
	        	{
	        		blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, inputStack.copy());
    				inputStack.shrink(1);
	        	}
	        }
	        else if (inputStack.getItem() instanceof NuGunItem gun)
	        {
	        	if (gun.returnMagType() == Constants.internal_mag)
	        	{
	        		if (gun.getCurrentAmmo(inputStack) < gun.getMaxAmmo(inputStack))
		        	{
		        		//Second slot is valid bullet for mag
	        			if (tagManager.getTag(gun.bullet.getCompatBullet()).contains(inputStack2.getItem()))
		        		{
			        		if (!isBurning && !fuelStack.isEmpty())
			        		{
			        			blockEntity.burnTime = ForgeHooks.getBurnTime(fuelStack, null);
			    	            blockEntity.burnTimeTotal = blockEntity.burnTime;
			    	            if (blockEntity.burnTime > 0) 
			    	                fuelStack.shrink(1);
			        		}
			        		blockEntity.processTime++;
			        		if (blockEntity.processTime >= returnProcessTime(blockEntity))
			        		{
			        			gun.addInternalAmmo(inputStack, inputStack2, 1);
			        			if (gun.getCurrentAmmo(inputStack) == gun.getMaxAmmo(inputStack))
			        			{
			        				blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, inputStack.copy());
			        				inputStack.shrink(1);
			        			}
			        			else
			        				blockEntity.itemHandler.setStackInSlot(INPUT_SLOT, inputStack);
			        			inputStack2.shrink(1);
			        			processTime = 0;
			        		}
		        		}
		        	}
	        		else
	        		{
        				blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, inputStack.copy());
        				inputStack.shrink(1);
	        		}
	        	}
	        	else if (gun.returnMagType() == Constants.external_mag)
	        	{
	        		if (gun.getMagazineStack(inputStack).isEmpty())
	        		{
	        			if (tagManager.getTag(gun.magazine.getCompatMag()).contains(inputStack2.getItem()))
		        		{
	        				if (!isBurning && !fuelStack.isEmpty())
			        		{
			        			blockEntity.burnTime = ForgeHooks.getBurnTime(fuelStack, null);
			    	            blockEntity.burnTimeTotal = blockEntity.burnTime;
			    	            if (blockEntity.burnTime > 0) 
			    	                fuelStack.shrink(1);
			        		}
			        		blockEntity.processTime++;
			        		if (blockEntity.processTime >= returnProcessTime(blockEntity))
			        		{
			        			gun.getInventory(inputStack).ifPresent(inventory -> {
			        				inventory.insertItem(gun.SLOT_MAG, inputStack2.copy(), false);
			        				gun.saveInventory(inputStack);
			        			});
			        			blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, inputStack.copy());
			        			inputStack.shrink(1);
			        			inputStack2.shrink(1);
			        			processTime = 0;
			        		}
		        		}
	        		}
	        		else
	        		{
        				blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, inputStack.copy());
        				inputStack.shrink(1);
	        		}
	        	}
	        }
        }
        
        if (inputStack.isEmpty() && processTime > 0)
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
		return new AutomatedLoaderMenu(number, inv, this, this.containerData);
	}

	@Override
	public Component getDisplayName() {
		return ModUtils.displayTranslation("thismeanswar.container.automated_loader");
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
    
    protected int returnProcessTime(BlockEntityAutomatedLoader loader)
    {
    	int returned = this.maxProcessTime;
    	ItemStack loaderStack = loader.itemHandler.getStackInSlot(INPUT_SLOT);
    	if (loaderStack.getItem() instanceof NuGunItem gun)
    	{
    		if (gun.returnMagType() == Constants.external_mag)
    			returned = (int) Math.round(gun.getReloadTime(loaderStack) * 2.4);
    	}
    	if (this.burnTime > 0)
    		returned = (int) Math.round(returned * 0.25);
    	maxProcessTime = returned;
    	return (int) returned;
    }
}
