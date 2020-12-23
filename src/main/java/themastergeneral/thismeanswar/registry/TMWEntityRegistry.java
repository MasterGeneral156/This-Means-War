package themastergeneral.thismeanswar.registry;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.entity.TMWEntities;

public class TMWEntityRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITES = DeferredRegister.create(ForgeRegistries.ENTITIES, TMWMain.MODID);
	
	public static final RegistryObject<EntityType<?>> bullet_entity = TMWEntityRegistry.ENTITES.register("bullet_entity", () -> TMWEntities.BULLET_ENTITY);
	public static final RegistryObject<EntityType<?>> contact_grenade_entity = TMWEntityRegistry.ENTITES.register("contact_grenade_entity", () -> TMWEntities.CONTACT_GRENADE_ENTITY);
}
