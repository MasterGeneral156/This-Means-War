package themastergeneral.thismeanswar.items.tiers;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import themastergeneral.thismeanswar.items.TMWItems;

public class BasicProtArmor implements ArmorMaterial {

    private final String name;
    private final int[] slotProtections = new int[]{1, 2, 3, 1};

    public BasicProtArmor(String name) {
        this.name = name;
    }
    
    @Override
	public int getDurabilityForType(Type type) {
		return this.slotProtections[type.getSlot().getIndex()] * 63;
	}

	@Override
	public int getDefenseForType(Type type) {
		return this.slotProtections[type.getSlot().getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return 1;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_LEATHER;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(TMWItems.kevlar_raw);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return 0.0F;
	}

	@Override
	public float getKnockbackResistance() {
		return 0.0F;
	}

}

