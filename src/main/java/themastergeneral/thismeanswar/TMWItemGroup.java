package themastergeneral.thismeanswar;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.items.define.TMWPistols;

public class TMWItemGroup extends ItemGroup 
{

	public TMWItemGroup() 
	{
		super("thismeanswar");
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack((IItemProvider) TMWPistols.glock_26);	//maybe works
	}

}
