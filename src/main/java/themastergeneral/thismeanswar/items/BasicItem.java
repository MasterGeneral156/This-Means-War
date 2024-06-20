package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;

public class BasicItem extends AbstractModItem {

	public BasicItem() 
	{
		super(new Properties());
	}
	
	public BasicItem(int maxStackSize) 
	{
		super(new Properties().stacksTo(maxStackSize));
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		Item item = stack.getItem();
		if (item == TMWItems.creative_charm)
			tooltip.add(ModUtils.displayTranslation("thismeanswar.creative_charm_directions"));
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		Item item = stack.getItem();
	      if (item == TMWItems.creative_charm)
	    	  return true;
	      else if (item == TMWItems.creative_mag_capacity_upgrade)
	    	  return true;
	      else if (item == TMWItems.bayonet_creative)
	    	  return true;
	      else
	    	  return false;
	}

}
