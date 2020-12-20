package themastergeneral.thismeanswar;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import themastergeneral.thismeanswar.items.TMWItems;

public class TMWItemGroup extends ItemGroup 
{

	public TMWItemGroup() 
	{
		super("thismeanswar");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(TMWItems.glock_26);
	}

}
