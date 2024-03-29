package themastergeneral.thismeanswar.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import themastergeneral.thismeanswar.block.TMWBlocks;

public class TMWBlockEntities {
	public static final BlockEntityType<BlockEntityAmmoStorage> AMMO_STORAGE = BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box).build(null);
	public static final BlockEntityType<BlockEntityAmmoStorage> AMMO_STORAGE_MEDIUM = BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box_medium).build(null);
	public static final BlockEntityType<BlockEntityAmmoStorage> AMMO_STORAGE_LARGE = BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.ammo_box_large).build(null);
	
	public static final BlockEntityType<BlockEntityAmmoStorage> MEDIC_STORAGE = BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.medic_box).build(null);
	public static final BlockEntityType<BlockEntityAmmoStorage> MEDIC_STORAGE_MEDIUM = BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.medic_box_medium).build(null);
	public static final BlockEntityType<BlockEntityAmmoStorage> MEDIC_STORAGE_LARGE = BlockEntityType.Builder.of(BlockEntityAmmoStorage::new, TMWBlocks.medic_box_large).build(null);
	//public static final BlockEntityType<BlockEntityCrusher> CRUSHER = BlockEntityType.Builder.of(BlockEntityCrusher::new, TMWBlocks.crusher).build(null);
}
