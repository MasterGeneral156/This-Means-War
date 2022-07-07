package themastergeneral.thismeanswar.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.TMWMain;

public class AbstractBulletItem extends AbstractModItem {

	protected AbstractModItem bulletCasing;
	protected AbstractModItem bulletTip;
	//No ammo stack limit...
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip) 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP));
		bulletCasing = casing;
		bulletTip = tip;
		
	}
	
	public AbstractBulletItem() 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP));
		
	}
	
	//Ammo stack limit...
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, int maxSize) 
	{
		super(new Properties().stacksTo(maxSize).tab(TMWMain.ITEMGROUP));
		bulletCasing = casing;
		bulletTip = tip;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	public Item returnCasingItem()
	{
		return bulletCasing;
	}
	
	public Item returnTipItem()
	{
		return bulletTip;
	}

}
