package themastergeneral.thismeanswar.items;

import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;

public class MultiAmmoCostGunItem extends AbstractGunItem 
{

	protected int roundsSpent;
	public MultiAmmoCostGunItem(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed,
			float bulletSpread, int roundsPerShot) 
	{
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, bulletSpread);
		this.roundsSpent = roundsPerShot;
	}
	
	public MultiAmmoCostGunItem(int shotTime, int reloadTime, AbstractMagazineItem magazine, AbstractBulletItem bullet, 
			float damage, float bulletSpeed, float bulletSpread, int roundsPerShot) 
	{
		super(shotTime, reloadTime, magazine, bullet, damage, bulletSpeed, bulletSpread);
		this.roundsSpent = roundsPerShot;
	}
	
	@Override
	public int roundsFired()
	{
		return this.roundsSpent;
	}

}
