package themastergeneral.thismeanswar.block;

import net.minecraft.world.item.DyeColor;

public interface ITeamBlock {

	public static DyeColor color = DyeColor.WHITE;
	
	public default DyeColor getTeamColor()
	{
		return color;
	}
}
