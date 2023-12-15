package themastergeneral.thismeanswar.entity;

import javax.annotation.Nonnull;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ContactGrenadeEntity extends ThrowableItemProjectile {
	protected float bulletDmg;
	   public ContactGrenadeEntity(EntityType<? extends ContactGrenadeEntity> p_i50159_1_, Level p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	      this.setInvulnerable(true);
	   }

	   public ContactGrenadeEntity(Level worldIn, net.minecraft.world.entity.LivingEntity throwerIn, float explosionRadius) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.setInvulnerable(true);
	   }

	   public ContactGrenadeEntity(Level worldIn, double x, double y, double z, float explosionRadius) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.setInvulnerable(true);
	   }

	   protected Item getDefaultItem() {
	      return null;
	   }

	   /**
	    * Called when the arrow hits an entity
	    */
	   @Override
	   protected void onHitEntity(EntityHitResult p_213868_1_) {
	      super.onHitEntity(p_213868_1_);
	      this.getCommandSenderWorld().explode(this, this.getX(), this.getY(), this.getZ(), bulletDmg, ExplosionInteraction.TNT);
	      this.remove(Entity.RemovalReason.KILLED);
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
	   
	   @Nonnull
	   @Override
	   public Packet<ClientGamePacketListener> getAddEntityPacket() {
			Entity entity = this.getOwner();
			return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.getId());
		}
	}
