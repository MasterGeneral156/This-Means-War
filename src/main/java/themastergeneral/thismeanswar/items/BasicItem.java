package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.TMWMain;

public class BasicItem extends AbstractModItem {

	public BasicItem() 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP));
	}
	
	public BasicItem(int maxStackSize) 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP).stacksTo(maxStackSize));
	}

}
