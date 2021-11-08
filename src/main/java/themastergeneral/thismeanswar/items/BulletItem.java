package themastergeneral.thismeanswar.items;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import themastergeneral.thismeanswar.TMWMain;

public class BulletItem extends BaseTMWItem {

	//No ammo stack limit...
	public BulletItem() 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP));
	}
	
	//Ammo stack limit...
	public BulletItem(int maxSize) 
	{
		super(new Properties().stacksTo(maxSize).tab(TMWMain.ITEMGROUP));
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}

}
