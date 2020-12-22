package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion.Mode;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.ModConstant;

public class ContactExplosiveEntity extends ProjectileItemEntity 
{
	public float bulletDmg;
	public Item bulletItm;
	
	protected int tick = 0;
	
	public ContactExplosiveEntity(EntityType<? extends ContactExplosiveEntity> type, double x, double y, double z,
			World worldIn, float explosionRadius, Item throwable) {
		super(type, x, y, z, worldIn);
		bulletDmg = explosionRadius;
		bulletItm = throwable;
	}
	
	public ContactExplosiveEntity(double x, double y, double z, World worldIn, float damage, Item bullet) 
	{
	      super(TMWEntities.CONTACT_EXPLOSION_ENTITY, x, y, z, worldIn);
	      bulletDmg = damage;
	}

	public ContactExplosiveEntity(World worldIn, LivingEntity throwerIn, float damage, Item bullet) {
		super(TMWEntities.CONTACT_EXPLOSION_ENTITY, throwerIn, worldIn);
      bulletDmg = damage;
   }
	
	public ContactExplosiveEntity(EntityType<ContactExplosiveEntity> type, World world) 
	{
		super(type, world);
	}

	@Override
	protected Item getDefaultItem() 
	{
		return bulletItm;
	}
	
	@Override
	public ItemStack getItem() 
	{
		return new ItemStack(bulletItm);
	}
	
	@OnlyIn(Dist.CLIENT)
	private IParticleData makeParticle() 
	{
	  return (IParticleData)new ItemParticleData(ParticleTypes.ITEM, new ItemStack(bulletItm));
	}
	
	@Override
	protected void onImpact(RayTraceResult result) 
	{
	      super.onImpact(result);
	      if (!this.world.isRemote) {
	         this.world.setEntityState(this, (byte)3);
	         this.getEntityWorld().createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), bulletDmg, Mode.BREAK);
	         this.remove();
	      }
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		tick++;
		if (tick >= ModConstant.CONTACT_EXPLOSIVE_KILL_TICKS)
			this.remove();
	}

}
