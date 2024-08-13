package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import com.themastergeneral.ctdcore.item.CTDDurabilityItem;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DurabilityItem extends CTDDurabilityItem {

	protected int durability;
	
	public DurabilityItem(int durability) {
		super(new Properties().stacksTo(1), durability);
		this.durability = durability;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		if (stack.isDamageableItem())
			if (Screen.hasShiftDown())
				tooltip.add(ModUtils.displayString("Durability: " + this.remainingDamage(stack) + " / " + this.getMaxDamage(stack)));
			else
				tooltip.add(ModUtils.displayString("Durability: " + ModUtils.returnShortenedNumber(this.remainingDamage(stack)) + " / " + ModUtils.returnShortenedNumber(this.getMaxDamage(stack))));
	}

	protected int remainingDamage(ItemStack stack)
	{
		return stack.getMaxDamage() - stack.getDamageValue();
	}
}
