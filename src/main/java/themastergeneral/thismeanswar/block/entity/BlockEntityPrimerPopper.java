package themastergeneral.thismeanswar.block.entity;

import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.util.RandomSource;
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
import themastergeneral.thismeanswar.config.TMWTags;
import themastergeneral.thismeanswar.menu.PopperMenu;
import themastergeneral.thismeanswar.recipe.PopperRecipe;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

public class BlockEntityPrimerPopper extends BlockEntity implements MenuProvider, BlockEntityTicker<BlockEntityPrimerPopper>
{
    
	private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int EXTRA_SLOT = 3;
    private static final int OUTPUT_SLOT = 2;
    
    private int burnTime;
    private int burnTimeTotal;
    private int processTime;
    private int maxProcessTime;
    private int errorCode;
    
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> BlockEntityPrimerPopper.this.burnTime;
                case 1 -> BlockEntityPrimerPopper.this.processTime;
                case 2 -> BlockEntityPrimerPopper.this.maxProcessTime;
                case 3 -> BlockEntityPrimerPopper.this.errorCode;
                default -> throw new UnsupportedOperationException("Unexpected value: " + pIndex);
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> BlockEntityPrimerPopper.this.burnTime = pValue;
                case 1 -> BlockEntityPrimerPopper.this.processTime = pValue;
                case 2 -> BlockEntityPrimerPopper.this.maxProcessTime = pValue;
                case 3 -> BlockEntityPrimerPopper.this.errorCode = pValue;
            }
        }

        @Override
        public int getCount() {
            return 4;
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
    
	public BlockEntityPrimerPopper(BlockPos pos, BlockState state) {
		super(TMWBlockEntityRegistry.popper.get(), pos, state);
		this.maxProcessTime = 200;
	}
	
	public BlockEntityPrimerPopper(BlockPos pos, BlockState state, int processTime) {
		super(TMWBlockEntityRegistry.popper.get(), pos, state);
		this.maxProcessTime = processTime;
	}
	
	public boolean canPlaceItem(int slot, ItemStack stack) 
	{
		return itemHandler.isItemValid(slot, stack);
	}
	
	@Override
	public void tick(Level level, BlockPos pos, BlockState state, BlockEntityPrimerPopper blockEntity) {
		boolean isBurning = blockEntity.burnTime > 0;
        if (isBurning)
            blockEntity.burnTime--;
        
        ItemStack fuelStack = blockEntity.itemHandler.getStackInSlot(FUEL_SLOT);
        ItemStack inputStack = blockEntity.itemHandler.getStackInSlot(INPUT_SLOT);
        ItemStack inputStack2 = blockEntity.itemHandler.getStackInSlot(EXTRA_SLOT);
        ItemStack outputStack = blockEntity.itemHandler.getStackInSlot(OUTPUT_SLOT);
        Optional<PopperRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(TMWRecipeTypeRegistration.POPPER_TYPE.get(), new SimpleContainer(inputStack, inputStack2), level);
        if (this.checkForFactoryHolder())
        {
        	if (this.checkForRequiredTool())
        	{
		        if (outputStack.getCount() < itemHandler.getSlotLimit(OUTPUT_SLOT))
		        {
		        	if (recipe.isPresent())
		        	{
		        		errorCode = 0;
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
		            			 damageFactoryItem();
								 this.damageFirstInputSlot(inputStack);
								 this.damageSecondInputSlot(inputStack2);
								 processTime = 0;
		                	}
		    	        }
		        	}
		        }
        	}
        	else	//no hammer detected
        		errorCode = 2;
        }
        else
        	errorCode = 1;	//no factory holder detected
        
        if ((inputStack.isEmpty() || !recipe.isPresent()) && processTime > 0 || errorCode > 1)
        	processTime = 0;
        
        boolean wasLit = state.getValue(BlockStateProperties.LIT);
        boolean shouldBeLit = blockEntity.burnTime > 0;
        if (wasLit != shouldBeLit) 
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, shouldBeLit), 3);
        this.level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
        setChanged();
	}

	public void damageFirstInputSlot(ItemStack stack)
	{
		ItemStack newStack = stack.copy();
		if (newStack.isDamageableItem())
		{
			if(newStack.hurt(1, RandomSource.createNewThreadLocalInstance(), null))
				newStack = ItemStack.EMPTY;
		}
		else
			newStack.shrink(1);
		this.itemHandler.setStackInSlot(INPUT_SLOT, newStack);

	}

	public void damageSecondInputSlot(ItemStack stack)
	{
		ItemStack newStack = stack.copy();
		if (newStack.isDamageableItem())
		{
			if(newStack.hurt(1, RandomSource.createNewThreadLocalInstance(), null))
				newStack = ItemStack.EMPTY;
		}
		else
			newStack.shrink(1);
		this.itemHandler.setStackInSlot(EXTRA_SLOT, newStack);

	}
	
	protected boolean checkForFactoryHolder()
	{
		BlockPos pos = this.getBlockPos();
		if (this.getLevel().getBlockEntity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) instanceof BlockEntityFactoryHolder)
			return true;
		else
			return false;
	}
	
	protected void damageFactoryItem()
	{
		BlockPos pos = this.getBlockPos();
		if (this.getLevel().getBlockEntity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) instanceof BlockEntityFactoryHolder holder)
			holder.damageHolderStack();
	}
	
	protected boolean checkForRequiredTool()
	{
		BlockPos pos = this.getBlockPos();
		if (this.getLevel().getBlockEntity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) instanceof BlockEntityFactoryHolder holder)
		{
			ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
			return tagManager.getTag(TMWTags.hammer).contains(holder.getHolderStack().getItem());
		}
		else
			return false;
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
		return new PopperMenu(number, inv, this, this.containerData);
	}

	@Override
	public Component getDisplayName() {
		return ModUtils.displayTranslation("thismeanswar.container.popper");
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
