package themastergeneral.thismeanswar.items;

import net.minecraft.world.item.ItemStack;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;

public class InternalAmmoGunItem extends AbstractGunItem {

	public InternalAmmoGunItem(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed,
			float bulletSpread) {
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, bulletSpread);
	}
}
