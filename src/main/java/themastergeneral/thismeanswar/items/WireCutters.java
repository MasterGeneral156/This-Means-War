package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.block.BlockBarbedWire;

public class WireCutters extends DurabilityItem {

	public WireCutters(int durability) 
	{
		super(durability);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		
		Block block = state.getBlock();
		if (block instanceof BlockBarbedWire wire)
			return 20F;
		else
			return 1.0F;
    }
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(ModUtils.displayTranslation("item.thismeanswar.cutter.desc"));
	}
	
}
