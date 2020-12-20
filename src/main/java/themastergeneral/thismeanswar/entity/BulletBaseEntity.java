package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BulletBaseEntity extends ProjectileItemEntity 
{
	public float bulletDmg;
	
	public BulletBaseEntity(EntityType<? extends BulletBaseEntity> type, double x, double y, double z,
			World worldIn, float damage) {
		super(type, x, y, z, worldIn);
		bulletDmg = damage;
	}
	
	public BulletBaseEntity(double x, double y, double z, World worldIn, float damage) 
	{
	      super(EntityType.SNOWBALL, x, y, z, worldIn);
	      bulletDmg = damage;
	}

	public BulletBaseEntity(World worldIn, LivingEntity throwerIn, float damage) {
		super(EntityType.SNOWBALL, throwerIn, worldIn);
      bulletDmg = damage;
   }

	@Override
	protected Item getDefaultItem() 
	{
		return null;
	}
	
	@Override
	protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
	      super.onEntityHit(p_213868_1_);
	      Entity entity = p_213868_1_.getEntity();
	      entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), bulletDmg);
	   }
	
	@Override
	protected void onImpact(RayTraceResult result) 
	{
	      super.onImpact(result);
	      if (!this.world.isRemote) {
	         this.world.setEntityState(this, (byte)3);
	         this.remove();
	      }
	}

}
