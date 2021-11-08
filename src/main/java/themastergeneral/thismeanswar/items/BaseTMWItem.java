package themastergeneral.thismeanswar.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseTMWItem extends Item {

	public BaseTMWItem(Properties properties) {
		super(properties);
	}
	
	public static int returnSlotIDForItem(PlayerEntity playerIn, ItemStack stack)
	{
		int slotID = -1;
		for(int i = 0; i < playerIn.inventory.getContainerSize(); ++i) 
		{
			ItemStack itemstack1 = playerIn.inventory.getItem(i);
			if (itemstack1.getItem() == stack.getItem())
			{
				slotID=i;
				break;
			}
		}
		return slotID;
	}

}
