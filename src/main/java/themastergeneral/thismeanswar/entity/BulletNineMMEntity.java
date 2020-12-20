package themastergeneral.thismeanswar.entity;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import themastergeneral.thismeanswar.items.TMWItems;

public class BulletNineMMEntity extends BulletBaseEntity 
{

	public BulletNineMMEntity(double x, double y, double z, World worldIn, float damage) {
		super(x, y, z, worldIn, damage);
	}
	
	@Override
	protected Item getDefaultItem() 
	{
		return TMWItems.nine_mm_round;
	}

}
