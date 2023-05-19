package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.config.Constants;

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
		if (stack.getItem() == TMWItems.mag_capacity_upgrade)
		{
			tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_mag_directions"));
			tooltip.add(ModUtils.displayString("Max Upgrades: " + Constants.maxMagUpgrades));
		}
		if (stack.getItem() == TMWItems.gun_rof_upgrade)
		{
			tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_rof_directions"));
			tooltip.add(ModUtils.displayString("Converts gun to automatic."));
		}
		if (stack.getItem() == TMWItems.gun_rof_downgrade)
		{
			tooltip.add(ModUtils.displayTranslation("thismeanswar.upgrade_rof_directions"));
			tooltip.add(ModUtils.displayString("Converts gun to 'fully semi-automatic'."));
		}
		if (stack.getItem() == TMWItems.creative_charm)
		{
			tooltip.add(ModUtils.displayTranslation("thismeanswar.creative_charm_directions"));
		}
	}

}
