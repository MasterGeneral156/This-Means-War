package themastergeneral.thismeanswar.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;

public class BlockEntityMedicBox extends BlockEntity 
{
	float healthStored = 0;
	
	public BlockEntityMedicBox(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
	}
	
	public BlockEntityMedicBox(BlockPos p_155229_, BlockState p_155230_) {
		super(TMWBlockEntityRegistry.medic_box.get(), p_155229_, p_155230_);
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		healthStored = tag.getFloat("healthStored");
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag) 
	{
		super.saveAdditional(tag);
		tag.putFloat("healthStored", this.getHealthStored());
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
	public void handleUpdateTag(CompoundTag tag)
	{
		tag.putFloat("healthStored", this.getHealthStored());
	}
	
	public float getHealthStored() 
	{
		if (healthStored < 0)
		{
			healthStored = 0;
			this.setChanged();
		}
		return healthStored;
	}
	
	public void updateHealthStored(float change)
	{
		healthStored = healthStored + change;
		this.setChanged();
	}
	
	@Override
	public AABB getRenderBoundingBox()
    {
		return new AABB(getBlockPos(), getBlockPos().offset(2, 5, 2));
    }
	
	public void applyCreativeCharm()
	{
		healthStored = Short.MAX_VALUE;
		this.setChanged();
	}
}
