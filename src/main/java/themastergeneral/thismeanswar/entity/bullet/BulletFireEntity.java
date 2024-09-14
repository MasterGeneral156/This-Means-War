package themastergeneral.thismeanswar.entity.bullet;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;

public class BulletFireEntity extends BulletBaseEntity {

	protected float bulletDmg;

	public BulletFireEntity(EntityType<? extends BulletBaseEntity> p_i50159_1_, Level p_i50159_2_) {
		super(p_i50159_1_, p_i50159_2_);
		this.damageSource = this.damageSources().onFire();
	}

	public BulletFireEntity(Level worldIn, LivingEntity throwerIn, float explosionRadius, AbstractBulletItem bullet) {
		super(worldIn, throwerIn, explosionRadius, bullet);
		this.damageSource = this.damageSources().onFire();
	}

	public BulletFireEntity(Level worldIn, double x, double y, double z, float explosionRadius, AbstractBulletItem bullet) {
		super(worldIn, x, y, z, explosionRadius, bullet);
		this.damageSource = this.damageSources().onFire();
	}

	@Override
    protected void onHitEntity(EntityHitResult p_213868_1_) 
    {
	      super.onHitEntity(p_213868_1_);
	      Entity entity = p_213868_1_.getEntity();
	      entity.setSecondsOnFire((int) this.bulletDmg);
   }
}
