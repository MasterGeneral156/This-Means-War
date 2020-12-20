package themastergeneral.thismeanswar.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class TMWEntities {
	public static final EntityType<BulletBaseEntity> BULLET_ENTITY = EntityType.Builder.<BulletBaseEntity>create(
			BulletBaseEntity::new, EntityClassification.MISC)
			.size(1F, 1F)
			.setUpdateInterval(10)
			.setShouldReceiveVelocityUpdates(true)
			.trackingRange(1250)
			.build("");
}
