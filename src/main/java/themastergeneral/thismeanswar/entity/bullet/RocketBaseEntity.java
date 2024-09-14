package themastergeneral.thismeanswar.entity.bullet;

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
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;

public class RocketBaseEntity extends BulletBaseEntity {
	
	protected float bulletDmg;

	public RocketBaseEntity(EntityType<? extends BulletBaseEntity> p_i50159_1_, Level p_i50159_2_) {
		super(p_i50159_1_, p_i50159_2_);
	}

	public RocketBaseEntity(Level worldIn, LivingEntity throwerIn, float explosionRadius, AbstractBulletItem bullet) {
		super(worldIn, throwerIn, explosionRadius, bullet);
	}

	public RocketBaseEntity(Level worldIn, double x, double y, double z, float explosionRadius, AbstractBulletItem bullet) {
		super(worldIn, x, y, z, explosionRadius, bullet);
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
}
