package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.item.CTDDurabilityItem;

import net.minecraft.world.item.ItemStack;

public class DurabilityItem extends CTDDurabilityItem {

	public DurabilityItem(int durability) {
		super(new Properties().stacksTo(1), durability);
	}
}
