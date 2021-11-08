package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.Explosion.Mode;
import net.minecraftforge.fml.network.NetworkHooks;
import themastergeneral.thismeanswar.items.TMWItems;

public class RocketBaseEntity extends ProjectileItemEntity {
	
	protected float bulletDmg;
	protected float maxSpeed;
	   public RocketBaseEntity(EntityType<? extends RocketBaseEntity> p_i50159_1_, World p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	      this.maxSpeed = 0.0F;
	   }

	   public RocketBaseEntity(World worldIn, LivingEntity throwerIn, float explosionRadius, Item bullet, float maxSpd) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.maxSpeed = maxSpd;
	   }

	   public RocketBaseEntity(World worldIn, double x, double y, double z, float explosionRadius, float maxSpd) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.maxSpeed = maxSpd;
	   }

	@Override
	protected Item getDefaultItem() {
		return null;
	}
	
	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
	      super.onHitEntity(p_213868_1_);
	      if (!this.getCommandSenderWorld().isClientSide()) {
		      this.getCommandSenderWorld().explode(this.getEntity(), this.getX(), this.getY(), this.getZ(), bulletDmg, Mode.BREAK);
		      this.remove();
	      }
	   }

	   /**
	    * Called when this EntityFireball hits a block or entity.
	    */
	@Override
	   protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
	      super.onHitBlock(p_230299_1_);
	      if (!this.getCommandSenderWorld().isClientSide()) {
	         this.getCommandSenderWorld().explode(this.getEntity(), this.getX(), this.getY(), this.getZ(), bulletDmg, Mode.BREAK);
	         this.remove();
	      }

	   }
	   
   @Override
   public void tick() {
	   super.tick();
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, -0.3D, 0.0D);
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, +0.3D, 0.0D);
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, +0.3D);
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, -0.3D);
   }

}
