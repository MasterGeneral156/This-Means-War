package themastergeneral.thismeanswar.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.TMWMain;

public class DesignatorItem extends BaseTMWItem {

	public DesignatorItem() 
	{
		super(new Properties().stacksTo(1).tab(TMWMain.ITEMGROUP));
	}
	
	//@TODO Spawn entity above where player is, which falls and causes boom.
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		return null;
	}
}
