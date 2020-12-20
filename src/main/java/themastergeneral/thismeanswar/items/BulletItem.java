package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import themastergeneral.thismeanswar.TMWMain;

public class BulletItem extends CTDItem {

	//No ammo stack limit...
	public BulletItem() 
	{
		super(new Properties().group(TMWMain.ITEMGROUP));
	}
	
	//Ammo stack limit...
	public BulletItem(int maxSize) 
	{
		super(new Properties().maxStackSize(maxSize).group(TMWMain.ITEMGROUP));
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}

}
