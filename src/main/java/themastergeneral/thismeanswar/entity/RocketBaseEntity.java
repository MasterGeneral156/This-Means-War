package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import themastergeneral.thismeanswar.config.Constants;

public class RocketBaseEntity extends ThrowableItemProjectile {
	
	protected float bulletDmg;
	protected float maxSpeed;
	   public RocketBaseEntity(EntityType<? extends RocketBaseEntity> p_i50159_1_, Level p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	      this.maxSpeed = 0.0F;
	      this.setInvulnerable(true);
	   }

	   public RocketBaseEntity(Level worldIn, LivingEntity throwerIn, float explosionRadius, Item bullet, float maxSpd) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.maxSpeed = maxSpd;
	      this.setInvulnerable(true);
	   }

	   public RocketBaseEntity(Level worldIn, double x, double y, double z, float explosionRadius, float maxSpd) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.maxSpeed = maxSpd;
	      this.setInvulnerable(true);
	   }

	@Override
	protected Item getDefaultItem() {
		return null;
	}
	
	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.getId());
	}
	
	public void applyRandomSpread(float spreadAmount) {
        // Adjust the motion (velocity) based on random spread
        this.setDeltaMovement(this.getDeltaMovement().add(
                this.random.nextFloat() * spreadAmount,
                this.random.nextFloat() * spreadAmount,
                this.random.nextFloat() * spreadAmount
        ));
    }
	
	@Override
	protected void onHitEntity(EntityHitResult p_213868_1_) {
	      super.onHitEntity(p_213868_1_);
	      if (!this.getCommandSenderWorld().isClientSide()) 
	      {
	    	  Entity entity = p_213868_1_.getEntity();
	    	  entity.hurt(this.damageSources().explosion(this, this), bulletDmg * Constants.directHitExplosionMulti);
		      this.getCommandSenderWorld().explode(this, this.getX(), this.getY(), this.getZ(), bulletDmg, ExplosionInteraction.TNT);
		      this.remove(Entity.RemovalReason.KILLED);
	      }
	   }

	   /**
	    * Called when this EntityFireball hits a block or entity.
	    */
	@Override
	   protected void onHitBlock(BlockHitResult p_230299_1_) {
	      super.onHitBlock(p_230299_1_);
	      if (!this.getCommandSenderWorld().isClientSide()) {
	         this.getCommandSenderWorld().explode(this, this.getX(), this.getY(), this.getZ(), bulletDmg, ExplosionInteraction.TNT);
	         this.remove(Entity.RemovalReason.KILLED);
	      }

	   }
	   
   @Override
   public void tick() 
   {
	   super.tick();
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, -0.3D, 0.0D);
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, +0.3D, 0.0D);
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, +0.3D);
	   this.getCommandSenderWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, -0.3D);
   }

}
