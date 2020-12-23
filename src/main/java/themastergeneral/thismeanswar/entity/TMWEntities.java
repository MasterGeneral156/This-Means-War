package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class TMWEntities {
	public static final EntityType<BulletBaseEntity> BULLET_ENTITY = EntityType.Builder.<BulletBaseEntity>create(
			BulletBaseEntity::new, EntityClassification.MISC)
			.size(1F, 1F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.build("bullet_entity");
	
	public static final EntityType<ContactGrenadeEntity> CONTACT_GRENADE_ENTITY = EntityType.Builder.<ContactGrenadeEntity>create(
			ContactGrenadeEntity::new, EntityClassification.MISC)
			.size(1F, 1F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.build("contact_grenade_entity");
}
