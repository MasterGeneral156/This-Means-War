package themastergeneral.thismeanswar.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;

public class TMWBlockEntityRegistry {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TMWMain.MODID);
	
	public static final RegistryObject<BlockEntityType<BlockEntityAmmoStorage>> ammo_box = TILES.register("ammo_box", () -> BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box).build(null));
}
