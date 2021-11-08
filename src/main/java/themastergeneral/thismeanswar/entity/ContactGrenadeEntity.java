package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.Explosion.Mode;
import net.minecraftforge.fml.network.NetworkHooks;

public class ContactGrenadeEntity extends ProjectileItemEntity {
	protected float bulletDmg;
	   public ContactGrenadeEntity(EntityType<? extends ContactGrenadeEntity> p_i50159_1_, World p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	   }

	   public ContactGrenadeEntity(World worldIn, LivingEntity throwerIn, float explosionRadius) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	   }

	   public ContactGrenadeEntity(World worldIn, double x, double y, double z, float explosionRadius) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.bulletDmg = explosionRadius;
	   }

	   protected Item getDefaultItem() {
	      return null;
	   }

	   /**
	    * Called when the arrow hits an entity
	    */
	   @Override
	   protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
	      super.onHitEntity(p_213868_1_);
	      this.getCommandSenderWorld().explode(this.getEntity(), this.getX(), this.getY(), this.getZ(), bulletDmg, Mode.BREAK);
	      this.remove();
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
	   
	   @Nonnull
		@Override
		public IPacket<?> getAddEntityPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}
	}
