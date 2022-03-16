package themastergeneral.thismeanswar.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockEntityAmmoStorage extends BlockEntity implements Clearable {
	
	int maxAmmo;
	ItemStackHandler ammoType = new ItemStackHandler(1);
	int ammoCount = 0;

	public BlockEntityAmmoStorage(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
		maxAmmo = 512;
	}
	
	public BlockEntityAmmoStorage(BlockPos p_155229_, BlockState p_155230_) {
		super(TMWBlockEntityRegistry.ammo_box.get(), p_155229_, p_155230_);
		maxAmmo = 512;
	}
	
	public BlockEntityAmmoStorage(BlockPos p_155229_, BlockState p_155230_, int ammoMax) {
		super(TMWBlockEntityRegistry.ammo_box.get(), p_155229_, p_155230_);
		maxAmmo = ammoMax;
	}
	
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		ammoType.deserializeNBT(tag.getCompound("AmmoItem"));
		ammoCount = tag.getInt("AmmoQty");
		maxAmmo = tag.getInt("AmmoMax");
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag) 
	{

		super.saveAdditional(tag);
		tag.put("AmmoItem", getAmmo().serializeNBT());
		tag.putInt("AmmoQty", getAmmoQuantity());
		tag.putInt("AmmoMax", getAmmoMaxQuantity());
	}
	
	@Override
	public CompoundTag getUpdateTag() 
	{
		CompoundTag compound = new CompoundTag();
        saveAdditional(compound);
        return compound;
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() 
	{
	  return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void clearContent() {
		setContainerAmmo(ItemStack.EMPTY);
		setContainerAmmoQty(0);
	}
	
	@Override
	public void handleUpdateTag(CompoundTag tag)
	{
		tag.put("AmmoItem", getAmmo().serializeNBT());
		tag.putInt("AmmoQty", getAmmoQuantity());
		tag.putInt("AmmoMax", getAmmoMaxQuantity());
	}
	
	//Set ammo type
	public void setContainerAmmo(ItemStack stack) {
		ammoType.setStackInSlot(0, stack);
		this.setChanged();
	}
	
	public void setContainerAmmoQty(int quantity)
	{
		ammoCount = quantity;
		this.setChanged();
	}

	public ItemStack getAmmo() {
		return ammoType.getStackInSlot(0);
	}
	
	public int getAmmoQuantity() {
		if (ammoCount < 0)
		{
			ammoCount = 0;
			this.setChanged();
		}
		return ammoCount;
	}
	
	public int getAmmoMaxQuantity() {
		return maxAmmo;
	}
	
	//Call to update the ammo
	public void updateAmmo(Item item, int count) {
		if ((item != getAmmoItem()) && (getAmmo() == ItemStack.EMPTY))
			setContainerAmmo(new ItemStack(item));
		setContainerAmmoQty(getAmmoQuantity() + (count));
	}
	
	public void setAmmo(Item item, int count) {
		setContainerAmmo(new ItemStack(item));
		setContainerAmmoQty(count);
	}
	
	public void updateAmmoQty(int count)
	{
		ammoCount = ammoCount + count;
		this.setChanged();
	}
	
	public Item getAmmoItem() {
		return getAmmo().getItem();
	}
	
	@Override
	public AABB getRenderBoundingBox()
    {
		return new AABB(getBlockPos(), getBlockPos().offset(2, 5, 2));
    }
}
