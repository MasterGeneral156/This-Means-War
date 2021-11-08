package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import themastergeneral.thismeanswar.items.BulletItem;

public class BulletBaseEntity extends ProjectileItemEntity {
	protected float bulletDmg;
	protected BulletItem bulletItm;
	   public BulletBaseEntity(EntityType<? extends BulletBaseEntity> p_i50159_1_, World p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	      this.setNoGravity(true);
	   }

	   public BulletBaseEntity(World worldIn, LivingEntity throwerIn, float explosionRadius, BulletItem bullet) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.bulletItm = bullet;
	      this.setNoGravity(true);
	   }

	   public BulletBaseEntity(World worldIn, double x, double y, double z, float explosionRadius, BulletItem bullet) {
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
	   protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
	      super.onHitEntity(p_213868_1_);
	      Entity entity = p_213868_1_.getEntity();
	      entity.hurt(DamageSource.thrown(this, null), bulletDmg);
	      this.remove();
	   }

	   /**
	    * Called when this EntityFireball hits a block or entity.
	    */
	   protected void onHitBlock(BlockRayTraceResult result) 
	   {
	         this.remove();
	   }
	   
	   @Nonnull
		@Override
		public IPacket<?> getAddEntityPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}
	}
