package themastergeneral.thismeanswar.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import themastergeneral.thismeanswar.entity.bullet.BulletAPEntity;
import themastergeneral.thismeanswar.entity.bullet.BulletBaseEntity;
import themastergeneral.thismeanswar.entity.bullet.RocketBaseEntity;
import themastergeneral.thismeanswar.items.interfaces.AbstractBulletItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;

public class RocketGunItem extends NuGunItem
{

	public RocketGunItem(int shotTime, int reloadTime, NuMagazineItem magazine, float damage, float bulletSpeed, float bulletSpread) {
		super(shotTime, reloadTime, magazine, damage, bulletSpeed, bulletSpread);
	}

	public RocketGunItem(int shotTime, AbstractBulletItem bullet, float damage, int maxAmmo, float bulletSpeed, float bulletSpread) {
		super(shotTime, bullet, damage, maxAmmo, bulletSpeed, bulletSpread);
	}

	@Override
	public BulletBaseEntity getRoundEntity(ItemStack stack, Player player)
	{
		return new RocketBaseEntity(player.getCommandSenderWorld(), player, getBulletDamage(stack), bullet);
	}
}