package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import themastergeneral.thismeanswar.items.AbstractBulletItem;

public class BulletBaseEntity extends ThrowableItemProjectile {
	protected float bulletDmg;
	protected AbstractBulletItem bulletItm;
	
	protected int ticksAlive = 0;
	   public BulletBaseEntity(EntityType<? extends BulletBaseEntity> p_i50159_1_, Level p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	      this.setNoGravity(true);
	   }

	   public BulletBaseEntity(Level worldIn, LivingEntity throwerIn, float explosionRadius, AbstractBulletItem bullet) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.bulletItm = bullet;
	      this.setNoGravity(true);
	   }

	   public BulletBaseEntity(Level worldIn, double x, double y, double z, float explosionRadius, AbstractBulletItem bullet) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.bulletItm = bullet;
	      this.setNoGravity(true);
	   }

	   protected Item getDefaultItem() {
	      return null;
	   }

	   /**
	    * Called when the arrow hits an entity
	    */
	   protected void onHitEntity(EntityHitResult p_213868_1_) {
	      super.onHitEntity(p_213868_1_);
	      Entity entity = p_213868_1_.getEntity();
	      entity.hurt(this.damageSources().thrown(this, this), bulletDmg);
	      this.remove(Entity.RemovalReason.KILLED);
	   }

	   /**
	    * Called when this EntityFireball hits a block or entity.
	    */
	   protected void onHitBlock(BlockHitResult result) 
	   {
	         this.remove(Entity.RemovalReason.KILLED);
	   }
	   
	   @Nonnull
	   @Override
	   public Packet<ClientGamePacketListener> getAddEntityPacket() 
	   {
			return NetworkHooks.getEntitySpawningPacket(this);
		}
	}
