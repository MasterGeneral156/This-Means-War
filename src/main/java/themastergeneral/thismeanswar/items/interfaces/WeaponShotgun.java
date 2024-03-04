package themastergeneral.thismeanswar.items.interfaces;

public class WeaponShotgun extends AbstractGunItem {

	public WeaponShotgun(int shotTime, int reloadTime, AbstractMagazineItem magazine,
			float damage, float bulletSpeed, float bulletSpread) {
		super(shotTime, reloadTime, magazine, magazine.bulletRequired, damage, bulletSpeed, bulletSpread);
	}

	public WeaponShotgun(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed,
			float bulletSpread) {
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, bulletSpread);
	}
}
