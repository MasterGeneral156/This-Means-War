package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.item.CTDDurabilityItem;

import net.minecraft.world.item.ItemStack;
import themastergeneral.thismeanswar.TMWMain;

public class DurabilityItem extends CTDDurabilityItem {

	public DurabilityItem(int durability) {
		super(new Properties().stacksTo(1).tab(TMWMain.ITEMGROUP), durability);
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
