package themastergeneral.thismeanswar.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;
import themastergeneral.thismeanswar.block.entity.BlockEntityCrusher;
import themastergeneral.thismeanswar.block.entity.BlockEntityMedicBox;

public class TMWBlockEntityRegistry {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<BlockEntityType<BlockEntityAmmoStorage>> ammo_box = TILES.register("ammo_box", () -> BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box).build(null));
	public static final RegistryObject<BlockEntityType<BlockEntityAmmoStorage>> ammo_box_medium = TILES.register("ammo_box_medium", () -> BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box_medium).build(null));
	public static final RegistryObject<BlockEntityType<BlockEntityAmmoStorage>> ammo_box_large = TILES.register("ammo_box_large", () -> BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box_large).build(null));
	
	public static final RegistryObject<BlockEntityType<BlockEntityMedicBox>> medic_box = TILES.register("medic_box", () -> BlockEntityType.Builder.of(BlockEntityMedicBox::new, TMWBlocks.medic_box).build(null));
	public static final RegistryObject<BlockEntityType<BlockEntityMedicBox>> medic_box_medium = TILES.register("medic_box_medium", () -> BlockEntityType.Builder.of(BlockEntityMedicBox::new, TMWBlocks.medic_box_medium).build(null));
	public static final RegistryObject<BlockEntityType<BlockEntityMedicBox>> medic_box_large = TILES.register("medic_box_large", () -> BlockEntityType.Builder.of(BlockEntityMedicBox::new, TMWBlocks.medic_box_large).build(null));
	public static final RegistryObject<BlockEntityType<BlockEntityCrusher>> crusher = TILES.register("crusher", () -> BlockEntityType.Builder.of(BlockEntityCrusher::new, TMWBlocks.crusher).build(null));
}
