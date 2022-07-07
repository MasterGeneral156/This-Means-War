package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		if (stack.getItem() == TMWItems.mag_capacity_upgrade)
		{
			tooltip.add(new TranslatableComponent("Use while sneaking with magazine in off-hand."));
			tooltip.add(new TranslatableComponent("Max Upgrades: 3"));
		}
	}

}
