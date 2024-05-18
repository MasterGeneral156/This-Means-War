package themastergeneral.thismeanswar.items;

import net.minecraft.world.item.ItemStack;

public class TMWCreativeItem extends DurabilityItem {

	public TMWCreativeItem() {
		super(Short.MAX_VALUE);
	}
	
	@Override
	public boolean isFoil(ItemStack stack)
	{
		return true;
	}

}
