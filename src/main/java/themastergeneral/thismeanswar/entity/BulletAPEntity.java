package themastergeneral.thismeanswar.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import themastergeneral.thismeanswar.items.AbstractBulletItem;
import themastergeneral.thismeanswar.items.TMWItems;

public class BulletAPEntity extends ThrowableItemProjectile {

	protected float bulletDmg;
	protected AbstractBulletItem bulletItm;
	
	protected int ticksAlive = 0;
	public BulletAPEntity(EntityType<? extends BulletAPEntity> p_i50159_1_, Level p_i50159_2_) {
	      super(p_i50159_1_, p_i50159_2_);
	      this.bulletDmg = 0.0F;
	      this.bulletItm = TMWItems.round_12g;
	      this.setNoGravity(true);
	      this.setInvulnerable(true);
	   }

	   public BulletAPEntity(Level worldIn, LivingEntity throwerIn, float explosionRadius, AbstractBulletItem bullet) {
	      super(EntityType.SNOWBALL, throwerIn, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.bulletItm = bullet;
	      this.setNoGravity(true);
	      this.setInvulnerable(true);
	      this.setItem(new ItemStack(bullet));
	   }

	   public BulletAPEntity(Level worldIn, double x, double y, double z, float explosionRadius, AbstractBulletItem bullet) {
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      this.bulletDmg = explosionRadius;
	      this.bulletItm = bullet;
	      this.setNoGravity(true);
	      this.setInvulnerable(true);
	      this.setItem(new ItemStack(bullet));
	   }
    
    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) 
    {
	      super.onHitEntity(p_213868_1_);
	      Entity entity = p_213868_1_.getEntity();
	      entity.hurt(this.damageSources().magic(), bulletDmg);
	      this.remove(Entity.RemovalReason.KILLED);
	   }

	@Override
	protected Item getDefaultItem() {
		return null;
	}
	   
	   
}
