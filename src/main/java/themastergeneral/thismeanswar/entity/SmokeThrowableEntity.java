package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import org.joml.Vector3f;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.items.define.TMWThrowables;

public class SmokeThrowableEntity extends ThrowableItemProjectile {
	protected Vector3f color;
	protected int ticksAlive;
	protected int bounces;
	protected int intColors;
	
	public SmokeThrowableEntity(EntityType<? extends SmokeThrowableEntity> p_i50159_1_, Level p_i50159_2_) {
      super(p_i50159_1_, p_i50159_2_);
      this.color = Vec3.fromRGB24(2551600).toVector3f();
      this.setInvulnerable(true);
      this.canBeCollidedWith();
      setItem(new ItemStack(getDefaultItem()));
	  this.ticksAlive = 0;
	  this.bounces = 0;
	  this.intColors = 2551600;
   }

   public SmokeThrowableEntity(Level worldIn, LivingEntity throwerIn, int Colors) {
      super(EntityType.SNOWBALL, throwerIn, worldIn);
      this.color = Vec3.fromRGB24(Colors).toVector3f();
      this.setInvulnerable(true);
      this.canBeCollidedWith();
	  this.ticksAlive = 0;
	  this.bounces = 0;
	  this.intColors = Colors;
      setItem(new ItemStack(getDefaultItem()));
   }

   public SmokeThrowableEntity(Level worldIn, double x, double y, double z, int Colors) {
      super(EntityType.SNOWBALL, x, y, z, worldIn);
      this.color = Vec3.fromRGB24(Colors).toVector3f();
      this.setInvulnerable(true);
      this.canBeCollidedWith();
	  this.ticksAlive = 0;
	  this.bounces = 0;
	  this.intColors = Colors;
      setItem(new ItemStack(getDefaultItem()));
   }

   @Override
   protected Item getDefaultItem() {
      return TMWThrowables.smoke_grenade_red;
   }
   
   
   
   @Override
   protected void onHitBlock(BlockHitResult result) {
	   BlockPos pos = result.getBlockPos();
	   if (this.getBounces() + 1 <= 5)
	   {
		   this.moveTowardsClosestSpace(pos.getX(), pos.getY(), pos.getZ());
		   setBounces();
	   }
	   else
	   {
		   this.setPos(this.getX(), pos.getY()+1, this.getZ());
		   this.setDeltaMovement(Vec3.ZERO);
		   this.setNoGravity(true);
	   }
	   super.onHitBlock(result);
   }
   
   @Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() 
	{
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.getId());
	}
   
	@Override
	public void tick() 
	{
		super.tick();
		if (!level().isClientSide()) {
			Vector3f vecColor = Vec3.fromRGB24(getColor()).toVector3f();
			int failCon = 0;
			if (this.isInWater()) failCon++;
			if (this.isOnFire()) failCon++;
			if (this.isInLava()) failCon++;
			if (this.isInWall()) failCon++;
			int aliveTick = getTicksAlive();
			if ((aliveTick > 100) && (failCon == 0)) {

				((ServerLevel) level()).sendParticles(new DustParticleOptions(vecColor, 1F), getX(), getEyeY(), getZ(), 5, 0.1, 0.1, 0.1, 0.02);
				if (aliveTick >= (60 * 60 * 20) + 100)    //72100 ticks we die
					kill();
			}
			//kill earlier if its wet or on fire...
			if ((failCon > 0) || (aliveTick >= 700)) {
				((ServerLevel) level()).sendParticles(ParticleTypes.SMOKE, getX(), getEyeY(), getZ(), 5, 0.1, 0.1, 0.1, 0.02);
				kill();
			}
			this.setAliveTick();
		}
	}

	// Save entity data to NBT
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("TicksAlive", this.ticksAlive);
		compound.putInt("Bounces", this.bounces);
		compound.putInt("Color", this.intColors);
	}

	// Load entity data from NBT
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.ticksAlive = compound.getInt("TicksAlive");
		this.bounces = compound.getInt("Bounces");
		this.intColors = compound.getInt("Color");
		this.color = Vec3.fromRGB24(this.intColors).toVector3f();  // Ensure color is restored
	}
	
	protected int getTicksAlive()
	{
		return this.ticksAlive;
	}
	
	protected void setAliveTick()
	{
		ticksAlive++;
	}
	
	protected void setBounces()
	{
		bounces++;
	}
	
	protected int getBounces()
	{
		return this.bounces;
	}
	
	protected void setColor(int color)
	{
		intColors = color;
	}
	
	protected int getColor()
	{
		return this.intColors;
	}
}
