package themastergeneral.thismeanswar.items.tiers;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import themastergeneral.thismeanswar.items.TMWItems;

public class WarArmor implements ArmorMaterial {

    private final String name;
    private final int[] slotProtections = new int[]{13, 15, 16, 11};

    public WarArmor(String name) {
        this.name = name;
    }
    
	@Override
	public int getDurabilityForSlot(EquipmentSlot slot) {
		return slotProtections[slot.getIndex()] * 124;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slot) {
		return slotProtections[slot.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return 12;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_NETHERITE;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(TMWItems.block_steel);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return 5.6F;
	}

	@Override
	public float getKnockbackResistance() {
		return 1.3F;
	}

}
