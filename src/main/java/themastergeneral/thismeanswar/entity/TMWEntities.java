package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class TMWEntities {
	public static final EntityType<BulletBaseEntity> BULLET_ENTITY = EntityType.Builder.<BulletBaseEntity>of(
			BulletBaseEntity::new, EntityClassification.MISC)
			.sized(0.1F, 0.1F)
			.setUpdateInterval(1)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.build("bullet_entity");
	
	public static final EntityType<ContactGrenadeEntity> CONTACT_GRENADE_ENTITY = EntityType.Builder.<ContactGrenadeEntity>of(
			ContactGrenadeEntity::new, EntityClassification.MISC)
			.sized(0.25F, 0.25F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.build("contact_grenade_entity");
	
	public static final EntityType<RocketBaseEntity> ROCKET_ENTITY = EntityType.Builder.<RocketBaseEntity>of(
			RocketBaseEntity::new, EntityClassification.MISC)
			.sized(0.25F, 0.25F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.build("rocket_entity");
}
