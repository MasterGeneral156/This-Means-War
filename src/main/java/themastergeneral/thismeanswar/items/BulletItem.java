package themastergeneral.thismeanswar.items;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;

public class BulletItem extends CTDItem {

	//No ammo stack limit...
	public BulletItem() 
	{
		super(new Properties());
	}
	
	//Ammo stack limit...
	public BulletItem(int maxSize) 
	{
		super(new Properties().maxStackSize(maxSize));
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}

}
