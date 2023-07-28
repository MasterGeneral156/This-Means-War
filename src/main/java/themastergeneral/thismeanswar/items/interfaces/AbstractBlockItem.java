package themastergeneral.thismeanswar.items.interfaces;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.TMWItems;

public class AbstractBlockItem extends BlockItem {

	public AbstractBlockItem(Block p_i48527_1_) 
	{
		super(p_i48527_1_, new Properties());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		if (stack.getItem() == TMWItems.ammo_box)
			tooltip.add(ModUtils.displayTranslation("Holds 512 rounds."));
		if (stack.getItem() == TMWItems.ammo_box_medium)
			tooltip.add(ModUtils.displayTranslation("Holds 1,024 rounds."));
		if (stack.getItem() == TMWItems.ammo_box_large)
			tooltip.add(ModUtils.displayTranslation("Holds 2,048 rounds."));
		if (stack.getItem() == TMWItems.medic_box)
		{
			tooltip.add(ModUtils.displayTranslation("thismeanswar.medic_box_desc"));
			tooltip.add(ModUtils.displayString("Maximum Health: 1024"));
		}
	}
}
