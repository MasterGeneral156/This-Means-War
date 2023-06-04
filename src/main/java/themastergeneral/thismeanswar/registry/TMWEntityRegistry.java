package themastergeneral.thismeanswar.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.entity.TMWEntities;

public class TMWEntityRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<EntityType<?>> bullet_entity = ENTITES.register("bullet_entity", () -> TMWEntities.BULLET_ENTITY);
	public static final RegistryObject<EntityType<?>> ap_bullet_entity = ENTITES.register("ap_bullet_entity", () -> TMWEntities.AP_BULLET_ENTITY);
	public static final RegistryObject<EntityType<?>> contact_grenade_entity = ENTITES.register("contact_grenade_entity", () -> TMWEntities.CONTACT_GRENADE_ENTITY);
	public static final RegistryObject<EntityType<?>> rocket_entity = ENTITES.register("rocket_entity", () -> TMWEntities.ROCKET_ENTITY);
}
