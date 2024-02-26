package themastergeneral.thismeanswar.items.interfaces;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class AbstractBulletItem extends AbstractModItem {

	protected AbstractModItem bulletCasing;
	protected AbstractModItem bulletTip;
	protected TagKey<Item> compatBullet;
	//No ammo stack limit...
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip) 
	{
		super(new Properties());
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = null;
		
	}
	
	//No ammo stack limit, but has tag for bullet compatibily.
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, TagKey<Item> compatBullet) 
	{
		super(new Properties());
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = compatBullet;
		
	}
	
	public AbstractBulletItem() 
	{
		super(new Properties());
		this.bulletCasing = null;
		this.bulletTip = null;
		this.compatBullet = null;
		
	}
	
	public AbstractBulletItem(TagKey<Item> compatBullet) 
	{
		super(new Properties());
		this.compatBullet = compatBullet;
		
	}
	
	//Ammo stack limit...
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, int maxSize) 
	{
		super(new Properties().stacksTo(maxSize));
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = null;
	}
	
	//Ammo stack limit with bullet share tag
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, int maxSize, TagKey<Item> compatBullet) 
	{
		super(new Properties().stacksTo(maxSize));
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = compatBullet;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	public Item returnCasingItem()
	{
		return bulletCasing;
	}
	
	public Item returnTipItem()
	{
		return bulletTip;
	}
	
	public TagKey<Item> getCompatBullet()
	{
		if (compatBullet != null)
			return compatBullet;
		else
			return null;
	}

}
