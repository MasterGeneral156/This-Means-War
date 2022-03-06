package themastergeneral.thismeanswar;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import themastergeneral.thismeanswar.items.define.TMWPistols;

public class TMWItemGroup extends CreativeModeTab
{

	public TMWItemGroup() 
	{
		super("thismeanswar");
	}

	@Override
	public ItemStack makeIcon() 
	{
		return new ItemStack((ItemLike) TMWPistols.glock_26);	//maybe works
	}

}
