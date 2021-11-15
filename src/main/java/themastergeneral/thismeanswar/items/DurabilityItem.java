package themastergeneral.thismeanswar.items;

import net.minecraft.item.ItemStack;
import themastergeneral.thismeanswar.TMWMain;

public class DurabilityItem extends BaseTMWItem {

	public DurabilityItem(int durability) {
		super(new Properties().stacksTo(1).tab(TMWMain.ITEMGROUP).defaultDurability(durability));
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
    {
		ItemStack newStack = itemStack.copy();
		newStack.setDamageValue(newStack.getDamageValue() + 1);
		return newStack;
        
    }
	
	@Override
	public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }
}
