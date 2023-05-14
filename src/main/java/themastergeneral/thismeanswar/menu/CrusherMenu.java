package themastergeneral.thismeanswar.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class CrusherMenu extends AbstractContainerMenu {

	protected CrusherMenu(MenuType<?> p_38851_, int p_38852_) {
		super(p_38851_, p_38852_);
	}

	@Override
	public boolean stillValid(Player p_38874_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
		// TODO Auto-generated method stub
		return null;
	}

}
