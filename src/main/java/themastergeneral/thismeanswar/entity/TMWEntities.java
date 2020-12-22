package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class TMWEntities {
	public static final EntityType<BulletBaseEntity> BULLET_ENTITY = EntityType.Builder.<BulletBaseEntity>create(
			BulletBaseEntity::new, EntityClassification.MISC)
			.size(1F, 1F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.trackingRange(128)
			.build("");
	
	public static final EntityType<ContactExplosiveEntity> CONTACT_EXPLOSION_ENTITY = EntityType.Builder.<ContactExplosiveEntity>create(
			ContactExplosiveEntity::new, EntityClassification.MISC)
			.size(1F, 1F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.trackingRange(128)
			.build("");
}
