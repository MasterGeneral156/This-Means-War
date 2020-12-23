package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.ModConstant;

public class BulletBaseEntity extends ProjectileItemEntity 
{
	public float bulletDmg;
	public Item bulletItm;
	
	protected int tick = 0;
	
	public BulletBaseEntity(EntityType<? extends BulletBaseEntity> type, double x, double y, double z,
			World worldIn, float damage, Item bullet) {
		super(type, x, y, z, worldIn);
		bulletDmg = damage;
		bulletItm = bullet;
	}
	
	public BulletBaseEntity(double x, double y, double z, World worldIn, float damage, Item bullet) 
	{
	      super(TMWEntities.BULLET_ENTITY, x, y, z, worldIn);
	      bulletDmg = damage;
	}
	
	@OnlyIn(Dist.CLIENT)
	private IParticleData makeParticle() 
	{
	  return (IParticleData)new ItemParticleData(ParticleTypes.ITEM, new ItemStack(bulletItm));
	}

	public BulletBaseEntity(World worldIn, LivingEntity throwerIn, float damage, Item bullet) {
		super(TMWEntities.BULLET_ENTITY, throwerIn, worldIn);
      bulletDmg = damage;
   }
	
	public BulletBaseEntity(EntityType<BulletBaseEntity> type, World world) 
	{
		super(type, world);
	}

	@Override
	protected Item getDefaultItem() 
	{
		return bulletItm;
	}
	
	@Override
	protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
	      super.onEntityHit(p_213868_1_);
	      Entity entity = p_213868_1_.getEntity();
	      entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), bulletDmg);
	      this.remove();
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
	
	@Override
	public void tick() 
	{
		super.tick();
		tick++;
		if (tick >= ModConstant.BULLET_KILL_TICKS)
			this.remove();
	}

}
