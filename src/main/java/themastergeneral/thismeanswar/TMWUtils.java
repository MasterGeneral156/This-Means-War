package themastergeneral.thismeanswar;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import themastergeneral.thismeanswar.items.NuBulletBoxItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;

public class TMWUtils {
	
	public static float makeFloatReadable(float value)
	{
		return Math.round(value * 100F) / 100F;
	}
	
	public static double makeDoubleReadable(double value)
	{
		return Math.round(value * 100D) / 100D;
	}
	
	public static boolean isMagButNotBox(ItemStack stack)
	{
		Item item = stack.getItem();
		return ((item instanceof NuMagazineItem) && (!(item instanceof NuBulletBoxItem)));
	}

}
