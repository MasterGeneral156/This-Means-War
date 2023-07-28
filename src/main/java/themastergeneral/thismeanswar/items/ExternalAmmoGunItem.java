package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;

public class ExternalAmmoGunItem extends AbstractGunItem {

	public ExternalAmmoGunItem(int shotTime, int reloadTime, AbstractMagazineItem magazine,
			float damage, float bulletSpeed, float bulletSpread) {
		super(shotTime, reloadTime, magazine, magazine.returnBulletItem(), damage, bulletSpeed, bulletSpread);
	}

}
