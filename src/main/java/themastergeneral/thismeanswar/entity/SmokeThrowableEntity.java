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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import themastergeneral.thismeanswar.TMWMain;

public class SmokeThrowableEntity extends ThrowableItemProjectile implements Tickable {
	protected Vector3f color;
	protected int ticksAlive = 0;
	protected int bounces = 0;
	   public SmokeThrowableEntity(EntityType<? extends SmokeThrowableEntity> p_i50159_1_, Level p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.color = Vec3.fromRGB24(2551600).toVector3f();
	      this.setInvulnerable(true);
	      this.canBeCollidedWith();
	   }

	   public SmokeThrowableEntity(Level worldIn, LivingEntity throwerIn, int Colors) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.color = Vec3.fromRGB24(Colors).toVector3f();
	      this.setInvulnerable(true);
	      this.canBeCollidedWith();
	   }

	   public SmokeThrowableEntity(Level worldIn, double x, double y, double z, int Colors) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.color = Vec3.fromRGB24(Colors).toVector3f();
	      this.setInvulnerable(true);
	      this.canBeCollidedWith();
	   }

	   protected Item getDefaultItem() {
	      return null;
	   }
	   
	   @Override
	   protected void onHitBlock(BlockHitResult result) {
		   BlockPos pos = result.getBlockPos();
		   if (this.getBounces() + 1 <= 5)
		   {
			   this.moveTowardsClosestSpace(pos.getX(), pos.getY(), pos.getZ());
			   this.addBounce();
		   }
		   else
		   {
			   this.setPos(this.getX(), pos.getY()+1, this.getZ());
			   this.setDeltaMovement(Vec3.ZERO);
			   this.setNoGravity(true);
		   }
		   TMWMain.LOGGER.info(this.getBounces());
		   TMWMain.LOGGER.info("what fuck");
		   super.onHitBlock(result);
	   }
	   
	   @Nonnull
		@Override
		public Packet<ClientGamePacketListener> getAddEntityPacket() 
		{
			return NetworkHooks.getEntitySpawningPacket(this);
		}
	   
		
		@Override
		public void tick() 
		{
			TMWMain.LOGGER.info("what fuck");
			int failCon = 0;
			if (this.isInWater())
				failCon++;
			if (this.isOnFire())
				failCon++;
			int aliveTick = getTicksAlive();
			if ((aliveTick > 100) && (failCon == 0))
			{
				Minecraft.getInstance().level.addParticle(new DustParticleOptions(color, 1F), getX(), getEyeY(), getZ(), 2.5D, 100D, 1D);
				if (aliveTick >= (60 * 60 * 20) + 100)	//72100 ticks we die
					kill();
			}
			//kill earlier if its wet or on fire...
			if ((failCon > 0) && (aliveTick >= 700))
				kill();
			addAliveTick();

			TMWMain.LOGGER.info("ticks" + getTicksAlive());
			super.tick();
		}
		
		protected int getTicksAlive()
		{
			CompoundTag tag = this.getPersistentData();
			if (tag.contains("ticksAlive"))
			{
				return tag.getInt("ticksAlive");
			}
			else
			{
				tag.putInt("ticksAlive", 0);
				this.save(tag);
				return 0;
			}
			
		}
		
		protected void addAliveTick()
		{
			CompoundTag tag = this.getPersistentData();
			if (tag.contains("ticksAlive"))
				tag.putInt("ticksAlive", tag.getInt("ticksAlive") + 1);
			else
				tag.putInt("ticksAlive", 1);
			this.save(tag);
			//TMWMain.LOGGER.info("outtag" + tag);
			
		}
		
		protected void addBounce()
		{
			CompoundTag tag = this.getPersistentData();
			if (tag.contains("bounces"))
				tag.putInt("bounces", tag.getInt("bounces") + 1);
			else
				tag.putInt("bounces", 1);
			this.save(tag);
			
		}
		
		protected int getBounces()
		{
			CompoundTag tag = this.getPersistentData();
			if (tag.contains("bounces"))
			{
				return tag.getInt("bounces");
			}
			else
			{
				tag.putInt("bounces", 0);
				return 0;
			}
		}
		
		@Override
		public void readAdditionalSaveData(CompoundTag tag) {
			super.readAdditionalSaveData(tag);
			ticksAlive = tag.getInt("ticksAlive");
			bounces = tag.getInt("bounces");
		}
		
		@Override
		public void addAdditionalSaveData(CompoundTag tag) 
		{
			super.addAdditionalSaveData(tag);
			tag.putInt("ticksAlive", this.getTicksAlive());
			tag.putInt("bounces", this.getBounces());
		}
		
		@Override
		public void onAddedToWorld()
		{
			super.onAddedToWorld();
			TMWMain.LOGGER.info("pew, added");
		}
		
		
}
