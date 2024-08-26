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

	public static String ammoFillBar(int current, int max)
	{
        int barLength = Math.min(max, 30);  // Length of the progress bar
		int progress = (int) (((double) current / max) * barLength);

		StringBuilder bar = new StringBuilder();
		bar.append("§r[");
		for (int i = 0; i < barLength; i++) {
			if (i < progress) {
				bar.append("§2|");
			} else {
				bar.append("§4|");
			}
		}
		bar.append("§r]");

		return bar.toString();
	}

}
