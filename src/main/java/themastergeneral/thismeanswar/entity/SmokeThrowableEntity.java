package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import org.joml.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.items.define.TMWThrowables;

public class SmokeThrowableEntity extends ThrowableItemProjectile implements Tickable {
	protected Vector3f color;
	protected int intColors;
	protected int ticksAlive = 0;
	protected int bounces = 0;
	
	protected EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(SmokeThrowableEntity.class, EntityDataSerializers.INT);
	protected EntityDataAccessor<Integer> TICKS_ALIVE = SynchedEntityData.defineId(SmokeThrowableEntity.class, EntityDataSerializers.INT);
	protected EntityDataAccessor<Integer> BOUNCES_DONE = SynchedEntityData.defineId(SmokeThrowableEntity.class, EntityDataSerializers.INT);   
	
	public SmokeThrowableEntity(EntityType<? extends SmokeThrowableEntity> p_i50159_1_, Level p_i50159_2_) {
      super(p_i50159_1_, p_i50159_2_);
      this.color = Vec3.fromRGB24(2551600).toVector3f();
      
      this.setInvulnerable(true);
      this.canBeCollidedWith();
      setItem(new ItemStack(getDefaultItem()));
      this.entityData.define(COLOR, 2551600);
      this.entityData.define(TICKS_ALIVE, 0);
      this.entityData.define(BOUNCES_DONE, 0);
   }

   public SmokeThrowableEntity(Level worldIn, LivingEntity throwerIn, int Colors) {
      super(EntityType.SNOWBALL, throwerIn, worldIn);
      this.color = Vec3.fromRGB24(Colors).toVector3f();
      
      this.setInvulnerable(true);
      this.canBeCollidedWith();
      this.entityData.define(COLOR, 2551600);
      this.entityData.define(TICKS_ALIVE, 0);
      this.entityData.define(BOUNCES_DONE, 0);
      setItem(new ItemStack(getDefaultItem()));
   }

   public SmokeThrowableEntity(Level worldIn, double x, double y, double z, int Colors) {
      super(EntityType.SNOWBALL, x, y, z, worldIn);
      this.color = Vec3.fromRGB24(Colors).toVector3f();
      
      this.setInvulnerable(true);
      this.canBeCollidedWith();
      this.entityData.define(COLOR, 2551600);
      this.entityData.define(TICKS_ALIVE, 0);
      this.entityData.define(BOUNCES_DONE, 0);
      setItem(new ItemStack(getDefaultItem()));
   }

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
		Vector3f vecColor = Vec3.fromRGB24(getColor()).toVector3f();
		int failCon = 0;
		if (this.isInWaterRainOrBubble())
			failCon++;
		if (this.isOnFire())
			failCon++;
		if (this.isInLava())
			failCon++;
		if (this.isInWall())
			failCon++;
		int aliveTick = getTicksAlive();
		if ((aliveTick > 100) && (failCon == 0))
		{
			getCommandSenderWorld().addParticle(new DustParticleOptions(vecColor, 1F), getX(), getEyeY(), getZ(), 2.5D, 100D, 1D);
			if (aliveTick >= (60 * 60 * 20) + 100)	//72100 ticks we die
				kill();
		}
		//kill earlier if its wet or on fire...
		if ((failCon > 0) || (aliveTick >= 700))
			kill();
		TMWMain.debugLogger("Alive: " + getTicksAlive());
		TMWMain.debugLogger("Bounces: " + getBounces());
		TMWMain.debugLogger("Color: " + getColor());
		this.setAliveTick();
	}
	
	protected int getTicksAlive()
	{
		return this.entityData.get(TICKS_ALIVE);
	}
	
	protected void setAliveTick()
	{
		ticksAlive++;
		this.entityData.set(TICKS_ALIVE, ticksAlive);
		
	}
	
	protected void setBounces()
	{
		bounces++;
		this.entityData.set(BOUNCES_DONE, bounces);
	}
	
	protected int getBounces()
	{
		return this.entityData.get(BOUNCES_DONE);
	}
	
	protected void setColor(int color)
	{
		intColors = color;
		this.entityData.set(COLOR, intColors);
		
	}
	
	protected int getColor()
	{
		return this.entityData.get(COLOR);
	}	
}
