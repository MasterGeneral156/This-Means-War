package themastergeneral.thismeanswar.items.interfaces;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import themastergeneral.thismeanswar.TMWMain;

public class WeaponSniper extends AbstractGunItem {

	protected float fovModifier;
	
	public WeaponSniper(int shotTime, int reloadTime, AbstractMagazineItem magazine,
			float damage, float bulletSpeed, float bulletSpread, float zoom) {
		super(shotTime, reloadTime, magazine, magazine.bulletRequired, damage, bulletSpeed, bulletSpread);
		fovModifier = zoom;
	}

	public WeaponSniper(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed,
			float bulletSpread, float zoom) {
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, bulletSpread);
		fovModifier = zoom;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		Player player = (Player) entityIn;
		if ((stack.getItem() instanceof WeaponSniper) && (stack.getItem() == this.asItem()))
		{
			if (worldIn.isClientSide())
			{
				if (Screen.hasAltDown())
				{
					player.getPersistentData().putFloat("fovModifier", fovModifier);
					TMWMain.debugLogger("Zoom " + fovModifier);
				}
				else
					player.getPersistentData().remove("fovModifier");
			}
		}
	}
}
