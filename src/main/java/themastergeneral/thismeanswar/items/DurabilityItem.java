package themastergeneral.thismeanswar.items;

import net.minecraft.world.item.ItemStack;
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
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
	      if (stack.getItem() == TMWItems.hammer_creative)
	    	  return true;
	      else
	    	  return false;
	}
}
