package themastergeneral.thismeanswar.items;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;

public class NuBulletBoxItem extends NuMagazineItem {

	private AbstractBulletItem bulletRequired;
    private TagKey<Item> compatMags;
    private int maxAmmo;
    private int baseAmmoSize;
    
    public static int SLOT_AMMO = 0;
    public static int SLOT_CAP_UPGRADES = 1;
    
    public NuBulletBoxItem(AbstractBulletItem Ammo, int maxAmmoSize) 
	{
    	super(Ammo, maxAmmoSize);
        this.bulletRequired = Ammo;
        this.maxAmmo = maxAmmoSize;
        this.baseAmmoSize = maxAmmoSize;
	}
}
