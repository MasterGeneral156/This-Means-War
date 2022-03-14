package themastergeneral.thismeanswar.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockEntityAmmoStorage extends BlockEntity implements Clearable {
	
	private static ItemStack ammoType = ItemStack.EMPTY;
	private static int ammoCount = 0;
	private static int ammoMax;

	public BlockEntityAmmoStorage(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
	}
	
	public BlockEntityAmmoStorage(BlockPos p_155229_, BlockState p_155230_) {
		super(TMWBlockEntityRegistry.ammo_box.get(), p_155229_, p_155230_);
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		if (tag.contains("AmmoItem", 8)) 
		{
			this.setAmmo(ItemStack.of(tag.getCompound("AmmoItem")).getItem(), tag.getInt("AmmoQty"));
			this.setMaxAmmo(tag.getInt("AmmoMax"));
		}
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		if (!getAmmo().isEmpty())
		{
			CompoundTag newTag = new CompoundTag();
			tag.put("AmmoItem", getAmmo().save(newTag));
			tag.putInt("AmmoQty", getAmmoQuantity());
			tag.putInt("AmmoMax", getAmmoMaxQuantity());
		}
	}
	
	@Override
	public CompoundTag getUpdateTag() 
	{
		CompoundTag tag = new CompoundTag();
		tag.put("AmmoItem", getAmmo().save(tag));
		tag.putInt("AmmoQty", getAmmoQuantity());
		tag.putInt("AmmoMax", getAmmoMaxQuantity());
		return tag;
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
	
	//Set ammo type
	public void setContainerAmmo(ItemStack stack) {
		ammoType = new ItemStack(stack.getItem());
		this.setChanged();
	}
	
	public void setContainerAmmoQty(int quantity)
	{
		ammoCount = quantity;
		this.setChanged();
	}

	public ItemStack getAmmo() {
		return ammoType;
	}
	
	public int getAmmoQuantity() {
		return ammoCount;
	}
	
	public int getAmmoMaxQuantity() {
		return ammoMax;
	}
	
	//Call to update the ammo
	public void updateAmmo(Item item, int count) {
		if (item != getAmmoItem())
			setContainerAmmo(new ItemStack(item));
		setContainerAmmoQty(getAmmoQuantity() + (count));
	}
	
	public void setAmmo(Item item, int count) {
		setContainerAmmo(new ItemStack(item));
		setContainerAmmoQty(count);
	}
	
	public void setMaxAmmo(int max) {
		ammoMax = max;
		this.setChanged();
	}
	
	public Item getAmmoItem() {
		return getAmmo().getItem();
	}
}
