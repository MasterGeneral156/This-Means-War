package themastergeneral.thismeanswar.entity.bullet;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;

public class BulletMedicalEntity extends BulletBaseEntity {

	public BulletMedicalEntity(EntityType<? extends BulletBaseEntity> p_i50159_1_, Level p_i50159_2_) {
		super(p_i50159_1_, p_i50159_2_);
		this.damageSource = this.damageSources().magic();
	}

	public BulletMedicalEntity(Level worldIn, LivingEntity throwerIn, float explosionRadius, AbstractBulletItem bullet) {
		super(worldIn, throwerIn, explosionRadius, bullet);
		this.damageSource = this.damageSources().magic();
	}

	public BulletMedicalEntity(Level worldIn, double x, double y, double z, float explosionRadius, AbstractBulletItem bullet) {
		super(worldIn, x, y, z, explosionRadius, bullet);
		this.damageSource = this.damageSources().magic();
	}

	@Override
    protected void onHitEntity(EntityHitResult result)
    {
		Entity entity = result.getEntity();
		if (entity instanceof LivingEntity living)
			living.heal(bulletDmg);
   }
}
