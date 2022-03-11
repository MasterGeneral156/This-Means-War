package themastergeneral.thismeanswar.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityAmmoStorage extends BlockEntity implements Clearable {
	
	private static ItemStack ammoType = ItemStack.EMPTY;
	private static int ammoCount = 0;

	public BlockEntityAmmoStorage(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		if (!getAmmo().isEmpty())
		{
			CompoundTag newTag = new CompoundTag();
			tag.put("AmmoItem", getAmmo().save(newTag));
			tag.putInt("AmmoQty", getAmmoQuantity());
		}
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
	
	//Call to update the ammo
	public void updateAmmo(Item item, int count) {
		
	}
	
	public Item getAmmoItem() {
		return getAmmo().getItem();
	}
}
