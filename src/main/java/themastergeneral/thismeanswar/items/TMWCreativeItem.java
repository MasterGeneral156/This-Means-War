package themastergeneral.thismeanswar.items;

import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class TMWCreativeItem extends DurabilityItem {

	public TMWCreativeItem() {
		super(() -> (int) Short.MAX_VALUE, (int) Short.MAX_VALUE);
	}

	@Override
	public boolean isFoil(ItemStack stack)
	{
		return true;
	}

}
