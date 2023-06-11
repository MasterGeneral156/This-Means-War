package themastergeneral.thismeanswar.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class TMWEntities {
	public static final EntityType<BulletBaseEntity> BULLET_ENTITY = EntityType.Builder.<BulletBaseEntity>of(
			BulletBaseEntity::new, MobCategory.MISC)
			.sized(0.05F, 0.05F)
			.setUpdateInterval(2)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.noSummon()
			.fireImmune()
			.build("bullet_entity");
	
	public static final EntityType<BulletAPEntity> AP_BULLET_ENTITY = EntityType.Builder.<BulletAPEntity>of(
			BulletAPEntity::new, MobCategory.MISC)
			.sized(0.05F, 0.05F)
			.setUpdateInterval(2)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.noSummon()
			.fireImmune()
			.build("ap_bullet_entity");
	
	public static final EntityType<BulletFireEntity> FIRE_BULLET_ENTITY = EntityType.Builder.<BulletFireEntity>of(
			BulletFireEntity::new, MobCategory.MISC)
			.sized(0.05F, 0.05F)
			.setUpdateInterval(2)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.noSummon()
			.fireImmune()
			.build("fire_bullet_entity");
	
	public static final EntityType<ContactGrenadeEntity> CONTACT_GRENADE_ENTITY = EntityType.Builder.<ContactGrenadeEntity>of(
			ContactGrenadeEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F)
			.setUpdateInterval(5)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.fireImmune()
			.noSummon()
			.build("contact_grenade_entity");
	
	public static final EntityType<RocketBaseEntity> ROCKET_ENTITY = EntityType.Builder.<RocketBaseEntity>of(
			RocketBaseEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F)
			.setUpdateInterval(2)
			.setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(128)
			.clientTrackingRange(128)
			.noSummon()
			.fireImmune()
			.build("rocket_entity");
}
